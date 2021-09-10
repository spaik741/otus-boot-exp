package otus.dz.dao;

import otus.dz.entity.Quest;

import java.util.List;
import java.util.Optional;

public interface QuestDAO {

    List<Quest> getAllQuests();

    Optional<Quest> getQuest(String id);
}
