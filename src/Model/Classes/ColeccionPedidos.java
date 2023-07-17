package Model.Classes;
import Model.Interfaces.IColeccionPedidos;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * La clase ColeccionPedidos representa una colección de pedidos.
 * Implementa la interfaz IColeccionPedidos.
 */
public class ColeccionPedidos implements IColeccionPedidos, Serializable {
    private ArrayList<Pedido> pedido;

    /**
     * Constructor para la clase ColeccionPedidos.
     */
    public ColeccionPedidos() {
        pedido = new ArrayList<>();
    }

    /**
     * Obtiene la lista de pedidos.
     * @return una lista de objetos Pedido.
     */
    public ArrayList<Pedido> getPedidos() {
        return pedido;
    }

    /**
     * Agrega un nuevo pedido a la colección.
     * Si ya existe un pedido con el mismo id, se incrementa su cantidad.
     * @param id el id de la pizza a agregar.
     * @param cant la cantidad de pizzas a agregar.
     * @return true si el pedido fue agregado correctamente, false en caso contrario.
     */
    @Override
    public boolean agregarPedido(int id, int cant) {
        int i = 0;

        while (i < pedido.size() &&
                pedido.get(i).getId() != id)
            i++;

        if (i == pedido.size()) {
            return pedido.add(new Pedido(id, cant));
        }
        else {
            pedido.get(i).setCant(pedido.get(i).getCant()+cant);
            return true;
        }

    }

    /**
     * Quita un pedido de la colección.
     * Si la cantidad a quitar es mayor o igual a la cantidad del pedido, se elimina el pedido completo.
     * Si no, se reduce la cantidad del pedido en la cantidad especificada.
     * @param indidce el índice del pedido a quitar.
     * @param cantidad la cantidad de pizzas a quitar del pedido.
     * @return el objeto Pedido quitado o modificado.
     */
    @Override
    public Pedido quitarPedido(int indidce, int cantidad) {
        indidce--;
        Pedido aux = pedido.get(indidce);
        if (aux.getCant() <= cantidad) {
            return this.pedido.remove(indidce);
        } else {
            aux.setCant(aux.getCant() - cantidad);
            return aux;
        }
    }

    /**
     * Devuelve una representación en cadena del estado del pedido.
     * @return una cadena que representa el estado del pedido.
     */
    public String obtenerEstadoDelPedido() {
        StringBuilder stringBuilder = new StringBuilder("Tu pedido:\n");
        if (pedido.size() == 0) {
            return "Aun no has agregado al pedido!";
        } else {
            int i = 0;
            for (Pedido p:pedido) {
                stringBuilder.append(i).append(")\n")
                        .append(p.toString())
                        .append("\n");
                i++;
            }
        }
        return stringBuilder.toString();
    }
}
