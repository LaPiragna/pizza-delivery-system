package Model.Exceptions;

public class RegistryCancelledException extends Exception{
    private String message;

    public RegistryCancelledException() {
        this.message = "registrx de cuenta canceladx.";
    }

    public RegistryCancelledException(String message) {
        this.message = message + "registrx de cuenta canceladx.";
    }

    public String getMessage() {
        return message;
    }
}
