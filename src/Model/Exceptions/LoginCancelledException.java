package Model.Exceptions;

public class LoginCancelledException extends Exception{
    private String message;

    public LoginCancelledException() {
        this.message = "se ha canceladx el inicix de sesixn.";
    }

    public LoginCancelledException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
