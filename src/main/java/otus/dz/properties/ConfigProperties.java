package otus.dz.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigProperties {
    @Bean
    public AppProperties appProperties(@Value("${quiz.locale}") String lang, @Value("${quiz.pathFile}") String pathFile) {
        return new AppProperties(lang, String.format(pathFile, lang));
    }
}
