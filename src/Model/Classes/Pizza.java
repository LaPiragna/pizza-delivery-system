package Model.Classes;
import Model.Interfaces.IJson;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.Serializable;

/**
 * Esta clase representa una Pizza con sus propiedades y métodos.
 * Implementa la interfaz IJson para proporcionar métodos para convertir a y desde JSON.
 */
public class Pizza implements IJson, Serializable {
    private int id;
    private String name;
    private boolean veg;
    private String description;
    private String img;
    private int price;

    /**
     * Constructor predeterminado para la clase Pizza.
     */
    public Pizza() {
    }

    /**
     * Obtiene el id de la pizza.
     *
     * @return el id de la pizza.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el id de la pizza.
     *
     * @param id el nuevo id de la pizza.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre de la pizza.
     *
     * @return el nombre de la pizza.
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre de la pizza.
     *
     * @param name el nuevo nombre de la pizza.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Verifica si la pizza es apta para veganos.
     *
     * @return verdadero si la pizza es apta para veganos, falso en caso contrario.
     */
    public boolean isVeg() {
        return veg;
    }

    /**
     * Establece si la pizza es apta para veganos o no.
     *
     * @param veg verdadero si la pizza es apta para veganos, falso en caso contrario.
     */
    public void setVeg(boolean veg) {
        this.veg = veg;
    }

    /**
     * Obtiene la descripción de la pizza.
     *
     * @return la descripción de la pizza.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Establece la descripción de la pizza.
     *
     * @param description la nueva descripción de la pizza.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Obtiene la URL de imagen de la pizza.
     *
     * @return la URL de imagen de la pizza.
     */
    public String getImg() {
        return img;
    }

    /**
     * Establece la URL de imagen de la pizza.
     *
     * @param img la nueva URL de imagen de la pizza.
     */
    public void setImg(String img) {
        this.img = img;
    }
    /**
     * Obtiene el precio de la pizza.
     *
     * @return el precio de la pizza.
     */
    public int getPrice() {
        return price;
    }
    /**
     * Devuelve una cadena que indica si esta pizza es apta para veganos o no.
     *
     * @return "Sí." si esta pizza es apta para veganos, "No." en caso contrario.
     */
    private String isForVegan() {
        if (this.veg) return "Sí.";
        else return "No.";
    }

    /**
     * Devuelve una representación en cadena de este objeto Pizza.
     *
     * @return una representación en cadena de este objeto Pizza.
     */
    @Override
    public String toString() {
        return  "<"+id+"> " + name + ".\n" +
                "Para veganos -> " + isForVegan() + "\n" +
                "Descripción: " + description + "\n" +
                "URL de imagen: " + img + ".\n" +
                "$"+price+" pesos por pizza.\n";
    }

    /**
     * Convierte este objeto Pizza en un JSONObject.
     *
     * @param value un objeto para convertir a JSON.
     * @return un JSONObject que representa este objeto Pizza.
     */
    @Override
    public JSONObject toJSON(Object value) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", name);
            jsonObject.put("veg", veg);
            jsonObject.put("description", description);
            jsonObject.put("img", img);
        } catch (JSONException e) {System.out.println("Error al generar el JSON. " + e.getMessage() + '.');}
        return jsonObject;
    }

    /**
     * Inicializa este objeto Pizza a partir de un JSONObject.
     *
     * @param jsonObject un JSONObject que representa un objeto Pizza.
     */
    @Override
    public void fromJSON(JSONObject jsonObject) {
        try {
            this.setName(jsonObject.getString("name"));
            this.setVeg(jsonObject.getBoolean("veg"));
            this.setDescription(jsonObject.getString("description"));
            this.setImg(jsonObject.getString("img"));
        } catch (JSONException e) {
            System.out.println("Error al leer el JSONObject. " + e.getMessage() + '.');
        }
    }
}
