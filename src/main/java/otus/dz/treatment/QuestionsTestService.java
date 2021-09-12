package otus.dz.treatment;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import otus.dz.entity.Quest;
import otus.dz.io.ConsoleService;
import otus.dz.service.QuestService;

import java.util.List;
import java.util.Locale;

@Slf4j
@Service
public class QuestionsTestService {

    private final ConsoleService consoleService;
    private final QuestService questService;
    private final Survey survey;

    public QuestionsTestService(ConsoleService consoleService, QuestService questService, Survey survey) {
        this.consoleService = consoleService;
        this.questService = questService;
        this.survey = survey;
    }

    public void runTest() {
        List<Quest> quests = questService.getAllQuests();
        int countResult = runSurvey(quests);
        runResult(quests, countResult);
    }

    private int runSurvey(List<Quest> quests) {
        int countResult = CollectionUtils.size(quests);
        consoleService.printString(survey.getMessageSource().getMessage("helloMessage", new String[]{}, Locale.forLanguageTag(survey.getLang())));
        for (Quest quest : quests) {
            consoleService.printString(quest.getQuest());
            if (StringUtils.isNotBlank(quest.getVarAnswers())) {
                consoleService.printString(quest.getVarAnswers());
            }
            String answer = consoleService.readString(System.in);
            if (StringUtils.isBlank(answer) || (StringUtils.isNotBlank(quest.getCorrectAnswer()) && !StringUtils.equals(answer, quest.getCorrectAnswer()))) {
                countResult = countResult - 1;
            } else {
                quest.setAnswer(answer);
            }
        }
        return countResult;
    }

    private void runResult(List<Quest> quests, int countResult) {
        int countQuest = CollectionUtils.size(quests);
        for (Quest quest : quests) {
            if (StringUtils.isNotBlank(quest.getAnswer())) {
                consoleService.printString(quest.getId() + ": +");
            } else {
                consoleService.printString(quest.getId() + ": -");
            }
        }
        consoleService.printString(survey.getMessageSource().getMessage("resultTest", new String[]{}, Locale.forLanguageTag(survey.getLang()))
                + " " + countResult + "/" + countQuest);
    }
}
