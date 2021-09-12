package otus.dz.treatment;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
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
import java.util.Locale;


@Slf4j
@Data
@Component
public class Survey {

    private final MessageSource messageSource;
    private final String lang;

    public Survey(@Value("${locale}") String lang, MessageSource messageSource) {
        this.messageSource = messageSource;
        this.lang = lang;
    }

    public List<Quest> getQuestions() throws Exception {
        String pathToFile = messageSource.getMessage("pathFile", new String[]{}, Locale.forLanguageTag(lang));
        List<Quest> questList = new ArrayList<>();
        InputStreamReader reader = new InputStreamReader(getClass().getClassLoader().getResourceAsStream(pathToFile), StandardCharsets.UTF_8);
        ICsvBeanReader csvBeanReader = new CsvBeanReader(reader, CsvPreference.STANDARD_PREFERENCE);
        String[] mapping = new String[]{"id", "quest", "varAnswers", "correctAnswer"};
        CellProcessor[] procs = getProcessors();
        Quest quest;
        while ((quest = csvBeanReader.read(Quest.class, mapping, procs)) != null) {
            questList.add(quest);
        }
        csvBeanReader.close();
        reader.close();
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
