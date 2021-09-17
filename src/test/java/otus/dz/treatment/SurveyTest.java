package otus.dz.treatment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import otus.dz.entity.Quest;
import otus.dz.properties.AppProperties;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest("spring.shell.interactive.enabled=false")
class SurveyTest {

    @Autowired
    private Survey survey;


    @Test
    public void cleanTest() {
        survey = new Survey(new AppProperties("", ""));
        assertThrows(Exception.class, () -> {
            survey.getQuestions();
        });
    }

    @Test
    public void getQuestionsTest() {
        try {
            List<Quest> quests = survey.getQuestions();
            assertEquals(5, quests.size());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}