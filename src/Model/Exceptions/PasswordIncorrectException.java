package Model.Exceptions;

public class PasswordIncorrectException extends Exception{
    private String message = "La clave es incorrecta.";

    public PasswordIncorrectException() {
    }

    public PasswordIncorrectException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
