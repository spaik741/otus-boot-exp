package ru.otus.dz.treatment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.context.MessageSource;
import otus.dz.entity.Quest;
import otus.dz.treatment.Survey;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

class SurveyTest {

    private Survey survey;
    private MessageSource messageSource;

    @BeforeEach
    public void init() {
        messageSource = Mockito.mock(MessageSource.class);
        survey = new Survey("", messageSource);
    }

    @Test
    public void cleanTest() {
        Mockito.when(messageSource.getMessage(any(), any())).thenReturn("");
        assertThrows(Exception.class, () -> {
            survey.getQuestions();
        });
    }

}