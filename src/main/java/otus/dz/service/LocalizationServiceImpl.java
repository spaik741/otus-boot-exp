package otus.dz.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import otus.dz.message.Messages;
import otus.dz.properties.AppProperties;

import java.util.Locale;

@Service
public class LocalizationServiceImpl implements LocalizationService{

    private final MessageSource message;
    private final AppProperties properties;

    public LocalizationServiceImpl(MessageSource message, AppProperties properties) {
        this.message = message;
        this.properties = properties;
    }

    @Override
    public String getMessage(Messages nameMessage) {
        return message.getMessage(nameMessage.getMessage(), new String[]{}, Locale.forLanguageTag(properties.getLang()));
    }
}
