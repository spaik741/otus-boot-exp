package otus.dz.treatment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.constraint.UniqueHashCode;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;
import otus.dz.entity.Quest;
import otus.dz.properties.AppProperties;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Component
public class Survey {

    private final AppProperties properties;

    public Survey(AppProperties properties) {
        this.properties = properties;
    }

    public List<Quest> getQuestions() throws Exception {
        String nameFile = properties.getNameFile();
        List<Quest> questList = new ArrayList<>();
        try (InputStreamReader reader = new InputStreamReader(getClass().getClassLoader().getResourceAsStream(nameFile), StandardCharsets.UTF_8);
             ICsvBeanReader csvBeanReader = new CsvBeanReader(reader, CsvPreference.STANDARD_PREFERENCE)){
            String[] mapping = new String[]{"id", "quest", "varAnswers", "correctAnswer"};
            CellProcessor[] procs = getProcessors();
            Quest quest;
            while ((quest = csvBeanReader.read(Quest.class, mapping, procs)) != null) {
                questList.add(quest);
            }
        }
        return questList;
    }

    private CellProcessor[] getProcessors() {
        return new CellProcessor[]{
                new UniqueHashCode(),
                new NotNull(),
                new Optional(),
                new Optional()
        };
    }
}
