package otus.dz.service;

import otus.dz.entity.Quest;

import java.util.List;
import java.util.Optional;

public interface QuestService {

    List<Quest> getAllQuests();

    Optional<Quest> getQuest(String id);
}
