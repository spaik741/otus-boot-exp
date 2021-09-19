package otus.dz.service;

import org.springframework.context.MessageSource;
import otus.dz.message.Messages;

public interface LocalizationService {

    String getMessage(Messages nameMessage);

}
