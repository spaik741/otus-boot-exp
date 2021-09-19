package otus.dz.message;

public enum Messages {

    HELLO_MSG("helloMessage"),
    START_MSG("startMessage"),
    RESULT_TEST("resultTest"),
    LOGOUT_MSG("logoutMessage"),
    REGISTER_MSG("registerMessage");

    private final String message;

    Messages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
