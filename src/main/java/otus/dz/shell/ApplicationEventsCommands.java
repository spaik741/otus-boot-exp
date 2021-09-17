package otus.dz.shell;

import org.apache.commons.lang3.StringUtils;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import otus.dz.service.LocalizationService;
import otus.dz.treatment.QuestionsTestService;

import static otus.dz.message.Messages.*;

@ShellComponent
public class ApplicationEventsCommands {

    private String userName;
    private QuestionsTestService questionsTestService;
    private LocalizationService ls;

    public ApplicationEventsCommands(QuestionsTestService questionsTestService, LocalizationService ls) {
        this.questionsTestService = questionsTestService;
        this.ls = ls;
    }

    @ShellMethod(value = "Test runner", key = {"t", "test", "runTest"})
    @ShellMethodAvailability(value = "isCommandAvailable")
    public void runTest() {
        questionsTestService.runTest();
    }

    @ShellMethod(value = "Login command", key = {"li", "login"})
    public String login(String userName) {
        this.userName = userName;
        return String.format(ls.getMessage(HELLO_MSG), userName);
    }

    @ShellMethod(value = "Logout command", key = {"lo", "logout"})
    @ShellMethodAvailability(value = "isCommandAvailable")
    public String logout() {
        this.userName = "";
        return ls.getMessage(LOGOUT_MSG);
    }

    private Availability isCommandAvailable() {
        return StringUtils.isBlank(userName) ? Availability.unavailable(ls.getMessage(REGISTER_MSG)) : Availability.available();
    }
}
