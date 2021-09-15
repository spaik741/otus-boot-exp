package otus.dz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import otus.dz.treatment.QuestionsTestService;

import java.util.List;

@SpringBootApplication
public class OtusBootExpApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(OtusBootExpApplication.class, args);
        QuestionsTestService testService = context.getBean(QuestionsTestService.class);
        testService.runTest();
    }

}
