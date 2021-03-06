package otus.dz.treatment;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import otus.dz.entity.Quest;
import otus.dz.io.IOService;
import otus.dz.service.LocalizationService;
import otus.dz.service.QuestService;

import java.util.List;

import static otus.dz.message.Messages.RESULT_TEST;
import static otus.dz.message.Messages.START_MSG;

@Slf4j
@Service
public class QuestionsTestService {

    private final IOService ioService;
    private final QuestService questService;
    private final LocalizationService ls;

    public QuestionsTestService(IOService ioService, QuestService questService, LocalizationService ls) {
        this.ioService = ioService;
        this.questService = questService;
        this.ls = ls;
    }

    public void runTest() {
        List<Quest> quests = questService.getAllQuests();
        int countResult = runSurvey(quests);
        runResult(quests, countResult);
    }

    private int runSurvey(List<Quest> quests) {
        int countResult = CollectionUtils.size(quests);
        ioService.printString(ls.getMessage(START_MSG));
        for (Quest quest : quests) {
            ioService.printString(quest.getQuest());
            if (StringUtils.isNotBlank(quest.getVarAnswers())) {
                ioService.printString(quest.getVarAnswers());
            }
            String answer = ioService.readString();
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
                ioService.printString(quest.getId() + ": +");
            } else {
                ioService.printString(quest.getId() + ": -");
            }
        }
        ioService.printString(ls.getMessage(RESULT_TEST)
                + " " + countResult + "/" + countQuest);
    }
}
