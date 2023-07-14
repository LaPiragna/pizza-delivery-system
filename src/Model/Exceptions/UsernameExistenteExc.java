package Model.Exceptions;

public class UsernameExistenteExc extends Exception{
    private String message = "el username ya existe. Creá otro :3";
    private final String username;

    public UsernameExistenteExc(String username) {
        this.username = username;
        this.message = username + " ya esta en usx.";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
