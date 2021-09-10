package otus.dz.treatment;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;


@Slf4j
@Component
public class Survey {

    private final MessageSource messageSource;
    private final String lang;

    public Survey(@Value("${locale}") String lang, MessageSource messageSource) {
        this.messageSource = messageSource;
        this.lang = lang;
    }

    public List<Quest> getQuestions() {
        String pathToFile;
        if (StringUtils.contains(lang, "ru")) {
            pathToFile = messageSource.getMessage("pathFile", new String[]{}, Locale.forLanguageTag("ru-RU"));
        } else {
            pathToFile = messageSource.getMessage("pathFile", new String[]{}, Locale.forLanguageTag(""));
        }
        List<Quest> questList = new ArrayList<>();
        try {
            FileReader file = new FileReader(Objects.requireNonNull(getClass().getClassLoader().getResource(pathToFile)).getFile());
            ICsvBeanReader csvBeanReader = new CsvBeanReader(file, CsvPreference.STANDARD_PREFERENCE);
            String[] mapping = new String[]{"id", "quest", "varAnswers", "correctAnswer"};
            CellProcessor[] procs = getProcessors();
            Quest quest;
            while ((quest = csvBeanReader.read(Quest.class, mapping, procs)) != null) {
                questList.add(quest);
            }

        } catch (Exception e) {
            log.info("Ошибка парсинга файла {}", pathToFile);
            e.printStackTrace();
        }
        if (CollectionUtils.isEmpty(questList)) {
            log.info("Список вопросов пуст.");
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
