package Model.Interfaces;
import Model.Classes.Pedido;

public interface IColeccionPedidos {
    boolean agregarPedido(int id, int cant);

    Pedido quitarPedido(int indidce, int cantidad);
}
