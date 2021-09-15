package ru.otus.dz.service;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import otus.dz.dao.QuestDAO;
import otus.dz.entity.Quest;
import otus.dz.service.QuestServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class QuestServiceImplTest {

    private QuestDAO questDAO;
    private QuestServiceImpl questService;

    @BeforeEach
    public void init() {
        questDAO = Mockito.mock(QuestDAO.class);
        questService = new QuestServiceImpl(questDAO);
    }

    @Test
    public void cleanTest() {
        Quest quest1 = new Quest("1", "?", "yes", "","");
        List<Quest> questList = Arrays.asList(quest1);
        Mockito.when(questDAO.getAllQuests()).thenReturn(questList);
        assertTrue(CollectionUtils.isNotEmpty(questService.getAllQuests()));
    }

}