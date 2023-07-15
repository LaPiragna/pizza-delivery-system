package Model.Exceptions;

public class EmptyOrderException extends Exception{
    private final String message;
    public EmptyOrderException() {
        this.message = "No se ha podido enviar el pedido porque estaba vacío." +
                " Intentar luego de solicitar un producto.";
    }
}
