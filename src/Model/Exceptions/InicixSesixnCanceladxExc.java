package Model.Exceptions;

public class InicixSesixnCanceladxExc extends Exception{
    private String message;

    public InicixSesixnCanceladxExc() {
        this.message = "se ha canceladx el inicix de sesixn.";
    }

    public InicixSesixnCanceladxExc(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
