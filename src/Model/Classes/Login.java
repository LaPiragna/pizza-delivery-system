package Model.Classes;
import Model.Exceptions.*;

import java.io.*;
import java.util.*;

/**
 * Clase para registracion/inicio sesion.
 * */
public class Login {
    private static final File archivoCuentas = new File("cuentas.bin");
    /**
     * Registra una nueva cuenta de usuario con el nombre de usuario y la contraseña proporcionados.
     *
     * @param username El nombre de usuario para la nueva cuenta.
     * @param clave La contraseña para la nueva cuenta.
     * @return La cuenta registrada.
     * @throws RegistrxCanceladxExc Si ocurre un error durante el registro.
     * @throws UsernameExistenteExc Si el nombre de usuario ya existe.
     */
    public static Cuenta registro(String username, String clave) throws RegistrxCanceladxExc, UsernameExistenteExc {

        Cuenta cuenta = new Cuenta();

        HashMap<String, Cuenta> hashMap=new HashMap<>();

        ObjectOutputStream objectOutputStream = null;
        ObjectInputStream objectInputStream = null;

        if (archivoCuentas.exists()) {

            try {

                objectInputStream = new ObjectInputStream(new FileInputStream(archivoCuentas));

                while (true) {

                    try {

                        Cuenta aux = (Cuenta) objectInputStream.readObject();
                        hashMap.put(aux.getNombreUsuario(), aux);

                    } catch (ClassNotFoundException e) {
                        throw new RegistrxCanceladxExc();

                    }

                }

            } catch (EOFException eofException) {
                eofException.getMessage();
            } catch (IOException e) {
                throw new RegistrxCanceladxExc();

            } finally {

                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    throw new RegistrxCanceladxExc();
                }

            }

            if (username.equals("0")) { //cancela el registro

                throw new RegistrxCanceladxExc();

            } else {

                if (!(hashMap.containsKey(username))) { //verificar que nx exista el username

                    cuenta = new Cuenta(username, clave, UUID.randomUUID());

                    hashMap.put(cuenta.getNombreUsuario(), cuenta);

                } else {

                    throw new UsernameExistenteExc(username);

                }
            }

            //REESCRITURA DEL ARCHIVO CON LA NUEVA CUENTA.
            try {

                objectOutputStream = new ObjectOutputStream(new FileOutputStream(archivoCuentas));

                for (Map.Entry<String, Cuenta> entry : hashMap.entrySet()) { //sobreexritura de archivx
                    objectOutputStream.writeObject(entry.getValue());
                }

            } catch (IOException e) {
                throw new RegistrxCanceladxExc();
            } finally {
                try {
                    if (objectOutputStream != null) {
                        objectOutputStream.close();
                    }
                } catch (IOException e) {
                    throw new RegistrxCanceladxExc();
                }

            }

        }
        //SI EL ARCHIVO NO EXISTE.
        else {

            if (username.equals("0")) {
                throw new RegistrxCanceladxExc();
            }

            cuenta = new Cuenta(username, clave, UUID.randomUUID());

            hashMap.put(cuenta.getNombreUsuario(), cuenta);

            try {

                objectOutputStream = new ObjectOutputStream(new FileOutputStream(archivoCuentas));
                objectOutputStream.writeObject(cuenta);

            } catch (IOException e) {
                throw new RegistrxCanceladxExc();
            }finally {
                try {
                    if (objectOutputStream != null) {
                        objectOutputStream.close();
                    }
                } catch (IOException e) {
                    throw new RegistrxCanceladxExc();
                }
            }
        }
        return cuenta;
    }

    /**
     * Inicia sesión con una cuenta de usuario existente.
     * <p>
     * Esta función solicita al usuario que ingrese un nombre de usuario y una clave para iniciar sesión.
     * Si el archivo de cuentas existe, la función lee las cuentas existentes del archivo y verifica si el nombre de usuario y la clave son correctos.
     * Si el nombre de usuario y la clave son correctos, la función devuelve la cuenta correspondiente.
     * Si el nombre de usuario o la clave son incorrectos, la función vuelve a solicitar al usuario que ingrese los datos.
     *
     * @return La cuenta de usuario con la que se inició sesión.
     * @throws IOException Si ocurre un error al leer el archivo de cuentas.
     * @throws InicixSesixnCanceladxExc Si el usuarix decide salir o si se superan los intentos de
     * inicio de sesion o i no existe el archivo.
     * @throws ClaveIncorrectaExc si se superan los intentos de inicio de sesion.
     */
    public static Cuenta iniciarSesion(String usuario, String clave) throws InicixSesixnCanceladxExc, ClaveIncorrectaExc, UsernameInexistenteExc {

        //Este objeto lee la informacion del archivo de las cuentas.
        ObjectInputStream objectInputStream = null;

        //En esta coleccion se van a almacenar la informacion del archivo de cuentas.
        HashMap<String, Cuenta> hashMap = new HashMap<>();

        //instancia auxiliar.
        Cuenta aux = new Cuenta();

        //NO EXISTE EL ARCHIVO.
        if (!archivoCuentas.exists()) {

            try {

                throw new InicixSesixnCanceladxExc("No existen cuentas registradas aun.");

            } catch (InicixSesixnCanceladxExc inicixSesixnCanceladxExc) {

                System.out.println(inicixSesixnCanceladxExc.getMessage());

            }
            //EXISTE EL ARCHIVO.
        } else {

            try {

                //se inicializa el stream lector.
                objectInputStream = new ObjectInputStream(new FileInputStream(archivoCuentas));

                try {

                    while (true) {

                        /*Se almacena toda la informacion del archivo en el HashMap*/
                        Cuenta aux1 = (Cuenta) objectInputStream.readObject();
                        hashMap.put(aux1.getNombreUsuario(), aux1);

                    }

                } catch (EOFException eofException) {

                    eofException.getMessage();

                } catch (ClassNotFoundException e) {

                    System.out.println(e.getMessage());

                }

                //SE CANCELA EL INICIO DE SESION.
                if (usuario.equals("0")) throw new InicixSesixnCanceladxExc();

                //USUARIO COINCIDE.
                if (hashMap.containsKey(usuario)) {

                    aux = hashMap.get(usuario);

                }
                //USUARIO NO EXISTE.
                else {
                    throw new UsernameInexistenteExc(usuario);
                }

                if (aux.verificarClave(clave))
                    /*
                     * Retorna satisfactoriamente la instancia
                     * del objeto de tipo Modelo.Cuenta.
                     * */
                    return aux;

                //Ingreso incorrecto de clave. Se lanza excepcion.
                throw new ClaveIncorrectaExc();

            }
            catch (IOException e) { //de inicializar el ObjectInputStream.
                System.out.println(e.getMessage());
            }
            finally {
                try {
                    objectInputStream.close(); //cierra stream
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        throw new InicixSesixnCanceladxExc("inicio de sesion no satisfactorio.");
    }


    /**
     * Este metodo muestra las opciones del menu
     * 1. Registro.
     * 2. Inicio de sesion.
     * 3. Salida del programa.
     * */
    public static void printMenu() {
        System.out.println("0) Salir:");
        System.out.println("1) Registrarse:");
        System.out.println("2) Iniciar sesion:");
    }

    /**
     * Verifica si la clave es correcta para una cuenta dada.
     *
     * @param clave la clave a verificar
     * @param cuenta la cuenta para la cual se verifica la clave
     * @return verdadero si la clave es correcta, falso en caso contrario
     */
    public static boolean validarEliminacion(String clave, Cuenta cuenta) {
        return cuenta.verificarClave(clave);
    }


    /**
     * Elimina una cuenta del archivo de cuentas.
     *
     * @param cuenta la cuenta a eliminar
     * @param clave la clave de la cuenta
     * @throws ClaveIncorrectaExc si la clave es incorrecta
     */
    public static void borrarCuenta(Cuenta cuenta, String clave) throws ClaveIncorrectaExc {
        boolean aux = validarEliminacion(clave, cuenta);
        if (aux) {
            ArrayList<Cuenta> arrayList = new ArrayList<>();
            ObjectInputStream objectInputStream = null;
            ObjectOutputStream objectOutputStream = null;

            //LECTURA
            try {
                objectInputStream = new ObjectInputStream(new FileInputStream(archivoCuentas));
                while (objectInputStream.available() > 0) {
                    try {
                        Cuenta cuenta1 = (Cuenta) objectInputStream.readObject();
                        arrayList.add(cuenta1);
                    } catch (ClassNotFoundException e) {
                        System.err.println("Error: " + e.getMessage());
                    }
                }
            } catch (IOException e) {
                System.err.println("Error: " + e.getMessage());
            } finally {
                try {
                    if (objectInputStream != null) {
                        objectInputStream.close();
                    }
                } catch (IOException e) {
                    System.err.println("Error: " + e.getMessage());
                }
            }

            // Eliminar la cuenta del ArrayList
            arrayList.remove(cuenta);

            //REESCRITURA
            try {
                objectOutputStream = new ObjectOutputStream(new FileOutputStream(archivoCuentas));
                for (Cuenta c : arrayList) {
                    try {
                        objectOutputStream.writeObject(c);
                    } catch (IOException e) {
                        System.err.println("Error: " + e.getMessage());
                    }
                }
            } catch (IOException e) {
                System.err.println("Error: " + e.getMessage());
            } finally {
                try {
                    if (objectOutputStream != null) {
                        objectOutputStream.close();
                    }
                } catch (IOException e) {
                    System.err.println("Error: " + e.getMessage());
                }
            }
        } else {
            throw new ClaveIncorrectaExc("La clave es incorrecta.");
        }
    }


}
