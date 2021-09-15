package otus.dz.dao;

import org.springframework.stereotype.Component;
import otus.dz.entity.Quest;
import otus.dz.treatment.Survey;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class QuestDAOImpl implements QuestDAO {

    private final Survey survey;

    public QuestDAOImpl(Survey survey) {
        this.survey = survey;
    }

    @Override
    public List<Quest> getAllQuests() {
        try {
            return survey.getQuestions();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Optional<Quest> getQuest(String id) {
        return getAllQuests().stream().filter(s -> s.getId().equals(id)).findFirst();
    }
}
