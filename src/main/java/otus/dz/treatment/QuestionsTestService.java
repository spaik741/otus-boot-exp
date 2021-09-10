package otus.dz.treatment;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import otus.dz.entity.Quest;
import otus.dz.service.QuestService;

import java.util.List;
import java.util.Scanner;

@Slf4j
@Service
public class QuestionsTestService {

    private final QuestService questService;

    public QuestionsTestService(QuestService questService) {
        this.questService = questService;
    }

    public void runTest() {
        List<Quest> quests = questService.getAllQuests();
        int countQuest = CollectionUtils.size(quests);
        int countResult = countQuest;
        for (Quest quest : quests) {
            ConsoleService.printString(quest.getQuest());
            if (StringUtils.isNotBlank(quest.getVarAnswers())){
                ConsoleService.printString(quest.getVarAnswers());
            }
            String answer = ConsoleService.readString(System.in);
            if (StringUtils.isBlank(answer) || (StringUtils.isNotBlank(quest.getCorrectAnswer()) && !StringUtils.equals(answer, quest.getCorrectAnswer()))) {
                countResult = countResult - 1;
            } else {
                quest.setAnswer(answer);
            }
        }
        for (Quest quest : quests) {
            if (StringUtils.isNotBlank(quest.getAnswer())) {
                ConsoleService.printString(quest.getId() + ": +");
            } else {
                ConsoleService.printString(quest.getId() + ": -");
            }
        }
        ConsoleService.printString("Result test: " + countResult +"/" + countQuest);
    }

}
