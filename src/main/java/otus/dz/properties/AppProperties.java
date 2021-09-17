package otus.dz.properties;

import lombok.Getter;

@Getter
public class AppProperties {

    private String lang;
    private String nameFile;

    public AppProperties(String lang, String nameFile) {
        this.lang = lang;
        this.nameFile = nameFile;
    }
}
