package ru.otus.dz.treatment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.MessageSource;
import otus.dz.entity.Quest;
import otus.dz.treatment.Survey;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class SurveyTest {

    private Survey survey;
    private MessageSource messageSource;

    @BeforeEach
    public void init() {
        survey = new Survey("", messageSource);
    }

    @Test
    public void cleanTest() {
        List<Quest> questList = survey.getQuestions();
        assertNotNull(questList);
    }

}