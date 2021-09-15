package otus.dz.treatment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.constraint.UniqueHashCode;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;
import otus.dz.entity.Quest;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Component
public class Survey {

    private final String lang;
    private final String pathFile;

    public Survey(@Value("${quiz.locale}") String lang, @Value("${quiz.pathFile}") String pathFile) {
        this.lang = lang;
        this.pathFile = pathFile;
    }

    public List<Quest> getQuestions() throws Exception {
        String nameFile = String.format(pathFile, lang);
        List<Quest> questList = new ArrayList<>();
        InputStreamReader reader = null;
        ICsvBeanReader csvBeanReader = null;
        try {
            reader = new InputStreamReader(getClass().getClassLoader().getResourceAsStream(nameFile), StandardCharsets.UTF_8);
            csvBeanReader = new CsvBeanReader(reader, CsvPreference.STANDARD_PREFERENCE);
            String[] mapping = new String[]{"id", "quest", "varAnswers", "correctAnswer"};
            CellProcessor[] procs = getProcessors();
            Quest quest;
            while ((quest = csvBeanReader.read(Quest.class, mapping, procs)) != null) {
                questList.add(quest);
            }
        } finally {
            if (csvBeanReader != null) {
                csvBeanReader.close();
            }
            if (reader != null) {
                reader.close();
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
