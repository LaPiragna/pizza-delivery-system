package Model.Exceptions;

public class RegistrxCanceladxExc extends Exception{
    private String message;

    public RegistrxCanceladxExc() {
        this.message = "registrx de cuenta canceladx.";
    }

    public String getMessage() {
        return message;
    }
}
