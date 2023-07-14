package Modelo;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.UUID;

/**Esta clase representa a las cuentas que generan los usuarios.
 * También está contenida aquí la información acerca del combate dentro del juego (formación del equipo).
 * @author Gonzalo Varela / piragna.
 * */
public class Cuenta implements Serializable {
    private String nombreUsuario;
    private  String clave;
    private final UUID uuid;

    public Cuenta() {
        nombreUsuario = "invitado";
        uuid = UUID.randomUUID();
        clave = "";
    }

    public Cuenta(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
        uuid = UUID.randomUUID();
        clave = "";
    }

    public Cuenta(String nombreUsuario, String clave, UUID uuid) {
        this.nombreUsuario = nombreUsuario;
        this.clave = clave;
        this.uuid = uuid;
    }

    /** Guarda los datos de la cuenta en el archivo con ruta especificada por parámetro.
     * @return
     * true si se guardó correctamente.
     * false si no se guardó correctamente.
     * @author Gonzalo Varela / piragna.
     * */
    public boolean persistirCuenta(ObjectOutputStream objectOutputStream) {
        boolean retorno = false;
        try {
            objectOutputStream.writeObject(this);
            retorno = true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return retorno;
    }
    /* GETTERS */
    public String getNombreUsuario() {
        return nombreUsuario;
    }
    public UUID getUuid() {
        return uuid;
    }
    /*METODOS*/
    public boolean equals(Cuenta input) {
        return this.getUuid() == input.getUuid();
    }

    @Override
    public String toString() {
        return this.nombreUsuario + " - " + this.clave +
                "\n(" + this.uuid + ")";
    }

    /** Compara un string ingresado por parámetro con la clave de la cuenta.
     * @return
     * true si coinciden los datos.
     * false si no coinciden los datos.
     * @author Gonzalo Varela / piragna. */
    public boolean verificarClave(String input) {
        return this.clave.equals(input);
    }
}
