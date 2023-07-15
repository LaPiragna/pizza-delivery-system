package Model.Exceptions;

public class UsernameInexistenteExc extends Exception {
    private String message;
    private String username;

    public UsernameInexistenteExc(String username) {
        this.username = username;
        this.message = "EL usuario '" + username + "' no existe.";
    }

    public String getMessage() {
        return message;
    }
}
