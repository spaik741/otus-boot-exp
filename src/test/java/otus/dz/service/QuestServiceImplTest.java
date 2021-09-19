package otus.dz.service;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import otus.dz.dao.QuestDAO;
import otus.dz.dao.QuestDAOImpl;
import otus.dz.entity.Quest;
import otus.dz.service.QuestServiceImpl;
import otus.dz.treatment.Survey;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest("spring.shell.interactive.enabled=false")
class QuestServiceImplTest {

    @Configuration
    static class TestConfig {
    }


    private QuestServiceImpl questService;
    @MockBean
    private QuestDAO questDAO;
    @MockBean
    private Survey survey;

    @BeforeEach
    public void init() {
        questDAO = new QuestDAOImpl(survey);
        questService = new QuestServiceImpl(questDAO);
    }

    @Test
    public void testNotEmpty() {
        List<Quest> questList = getListQuests();
        Mockito.when(questDAO.getAllQuests()).thenReturn(questList);
        assertTrue(CollectionUtils.isNotEmpty(questService.getAllQuests()));
    }

    @Test
    public void testIdSearch() throws Exception {
        List<Quest> questList = getListQuests();
        Mockito.when(survey.getQuestions()).thenReturn(questList);
        Quest questResult = questService.getQuest("2").get();
        assertEquals("!", questResult.getQuest());
    }

    private List<Quest> getListQuests() {
        Quest quest1 = new Quest("1", "?", "yes", "", "");
        Quest quest2 = new Quest("2", "!", "no", "", "");
        return Arrays.asList(quest1, quest2);
    }
}