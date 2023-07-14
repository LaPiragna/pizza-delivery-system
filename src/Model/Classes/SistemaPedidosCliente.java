package Model.Classes;

import Model.Interfaces.IArchivos;

import java.io.*;

/**
 * En esta clase se pressenta la interactividad del cliente para realizar pedidos.*/
public class SistemaPedidosCliente {

    public static String mostrarMenu() {
        return """
                1. TU PEDIDO:
                2. Enviar la solicitud del pedido.
                                                 0. Salir.
                """;
    }

    public static String mostrarMenuModificarPedido() {
        return """
                1. Listar productos:
                2. Agrega:
                3. Quita:
                                                 0. Salir.
                """;
    }

    public static int menu(int eleccion) {
        switch (eleccion) {
            case 0: //Salida de la aplicacion. -> LISTO
                eleccion = 0;
                break;
            case 1: //Pedido (modificacion): -> pendiente
                eleccion = 1;
                break;
            case 2: //Enviar pedido. -> pendiente
                eleccion = 2;
                break;
        }
        return eleccion;
    }

    public static int menuModificacionDelPedido(int eleccion,
                           ColeccionPedidos coleccionPedidos) {
        switch (eleccion) {
            case 0: //Salir. -> LISTO
                eleccion = 0;
                break;
            case 1: //Listar productos. -> LISTO
                eleccion = 1;
                break;
            case 2: //Agregar producto. -> LISTO
                eleccion = 2;
                break;
            case 3: //Quitar producto. -> pendiente
                eleccion = 3;
                break;
        }
        return eleccion;
    }

    public static boolean agregarPedido(int id, int cantidad,ColeccionPedidos coleccionPedidos) {
        return coleccionPedidos.agregarPedido(id,cantidad);
    }

    public static Pedido quitarPedido(int indice, int cantidad, ColeccionPedidos coleccionPedidos) {
        return coleccionPedidos.quitarPedido(indice, cantidad);
    }

    public static String obtenerEstadoDelPedido(ColeccionPedidos coleccionPedidos) {
        return coleccionPedidos.obtenerEstadoDelPedido();
    }

    public static boolean enviarPedido(ColeccionPedidos coleccionPedidos) {
        boolean ret = false;
        if (coleccionPedidos.getPedidos().size() == 0) return ret; //excepcion.
        File file = new File(IArchivos.archivo_pedidos);
        if (!file.exists()) {
            try {file.createNewFile();}
            catch (IOException e) {System.out.println("Error al crear el archivo. " + e.getMessage() + '.');}
        }
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(coleccionPedidos);
            ret = true;
        }
        catch (FileNotFoundException e) {System.out.println("Error al crear el stream de flujo. " + e.getMessage() + '.');}
        catch (IOException e) {System.out.println("Error al crear el flujo de datos." + e.getMessage() + '.');}
        finally {try {fileOutputStream.close();objectOutputStream.close();}catch (IOException e) {System.out.println("Error al cerrar el archivo. " + e.getMessage() + '.');}}
        return ret;
    }

}

