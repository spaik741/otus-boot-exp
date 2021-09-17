package otus.dz.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import otus.dz.message.Messages;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest("spring.shell.interactive.enabled=false")
class LocalizationServiceImplTest {

    @Autowired
    private LocalizationService localizationService;

    @Test
    public void getMessageTest() {
        String hello = localizationService.getMessage(Messages.HELLO_MSG);
        assertEquals("Hello in test, Mishka!", String.format(hello, "Mishka"));
    }

}