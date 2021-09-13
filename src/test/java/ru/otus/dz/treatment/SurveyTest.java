package ru.otus.dz.treatment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import otus.dz.treatment.Survey;

import static org.junit.jupiter.api.Assertions.assertThrows;

class SurveyTest {

    private Survey survey;

    @BeforeEach
    public void init() {
        survey = new Survey("", "");
    }

    @Test
    public void cleanTest() {
        assertThrows(Exception.class, () -> {
            survey.getQuestions();
        });
    }

}