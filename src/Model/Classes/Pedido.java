package Model.Classes;
import java.io.Serializable;

/**
 * La clase Pedido representa un pedido de pizza.
 * Cada pedido tiene un id de pizza y una cantidad de pizzas.
 */
public class Pedido implements Serializable {
    private int id; //id de la pizza. (0 - 29)
    private int cant; //cantidad de pizzas.

    /**
     * Constructor para la clase Pedido.
     * @param id el id de la pizza.
     * @param cant la cantidad de pizzas.
     */
    public Pedido(int id, int cant) {
        this.id = id;
        this.cant = cant;
    }

    /**
     * Obtiene el id de la pizza.
     * @return el id de la pizza.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el id de la pizza.
     * @param id el nuevo id de la pizza.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene la cantidad de pizzas.
     * @return la cantidad de pizzas.
     */
    public int getCant() {
        return cant;
    }

    /**
     * Establece la cantidad de pizzas.
     * @param cant la nueva cantidad de pizzas.
     */
    public void setCant(int cant) {
        this.cant = cant;
    }

    /**
     * Devuelve una representación en cadena del pedido.
     * @return una cadena que representa el pedido.
     */
    @Override
    public String toString() {
        Pizza pizza = ListaPizzas.obtenerPizza(id);
        return pizza.getName() + " - " +
                cant + " unidades.\n" +
                "Veggie. \n" +
                pizza.getDescription() + "\n" +
                "TOTAL: $" + calcularPrecio() + " pesos.";
    }

    /**
     * Calcula el precio total del pedido.
     * @return el precio total del pedido en pesos.
     */
    private int calcularPrecio() {
        return ListaPizzas.obtenerPizza(id).getPrice() * cant;
    }
}
