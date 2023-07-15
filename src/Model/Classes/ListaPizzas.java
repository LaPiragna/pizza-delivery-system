package Model.Classes;
import Model.Interfaces.IArchivos;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * La clase ListaPizzas representa una lista de pizzas.
 * La lista se carga desde un archivo JSON al iniciar la clase.
 */
public class ListaPizzas {

    private static final ArrayList<Pizza> pizzas = cargarLista();

    /**
     * Carga la lista de pizzas desde un archivo JSON.
     * @return una lista de objetos Pizza.
     */
    private static ArrayList<Pizza> cargarLista(){
        ArrayList<Pizza> aux = new ArrayList<>();
        File file = new File(IArchivos.PIZZAS_JSON);
        ObjectMapper objectMapper = new ObjectMapper();
        Pizza[] pizzas1;
        try {
            pizzas1 = objectMapper.readValue(file, Pizza[].class);
            aux.addAll(Arrays.asList(pizzas1));
        } catch (IOException e) {
            System.out.println("Error al leer el JSON." + e.getMessage() + '.');
        }
        return aux;
    }

    /**
     * Devuelve una representación en cadena de la lista de pizzas.
     * @return una cadena que representa la lista de pizzas.
     */
    public static String listarPizzas() {
        StringBuilder stringBuilder = new StringBuilder("Lista:\n");
        for (int i = 0; i < pizzas.size(); i++) {
            stringBuilder.append(pizzas.get(i).toString())
                    .append("\n");
        }
        return stringBuilder.toString();
    }

    /**
     * Obtiene una pizza por su id.
     * @param id el id de la pizza a obtener.
     * @return el objeto Pizza con el id especificado.
     */
    public static Pizza obtenerPizza(int id) {
        return pizzas.get(id-1);
    }
}
