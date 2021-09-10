package otus.dz.service;

import org.springframework.stereotype.Service;
import otus.dz.dao.QuestDAO;
import otus.dz.entity.Quest;

import java.util.List;
import java.util.Optional;

@Service
public class QuestServiceImpl implements QuestService {

    private final QuestDAO questDAO;

    public QuestServiceImpl(QuestDAO questDAO) {
        this.questDAO = questDAO;
    }

    @Override
    public List<Quest> getAllQuests() {
        return questDAO.getAllQuests();
    }

    @Override
    public Optional<Quest> getQuest(String id) {
        return questDAO.getQuest(id);
    }
}
