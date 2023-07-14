package Model.Exceptions;

public class UsernameInexistenteExc extends Exception {
    private String message;
    private String username;

    public UsernameInexistenteExc(String username) {
        this.username = username;
        this.message = "nx se ha encxntradx al usuarix '" + username + "'.";
    }

    public String getMessage() {
        return message;
    }
}
