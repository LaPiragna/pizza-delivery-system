package Model.Exceptions;

public class ClaveIncorrectaExc extends Exception{
    private String message = "La clave es incorrecta.";

    public ClaveIncorrectaExc() {
    }

    public ClaveIncorrectaExc(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
