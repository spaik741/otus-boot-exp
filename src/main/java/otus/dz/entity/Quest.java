package otus.dz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Quest {

    private String id;
    private String quest;
    private String varAnswers;
    private String answer;
    private String correctAnswer;
}
