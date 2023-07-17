package Model.Classes;
import Model.Exceptions.EmptyOrderException;
import Model.Interfaces.IErrors;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * La clase AplicacionCliente representa la interfaz de usuario para realizar pedidos.
 * Permite al usuario ver el estado del pedido, modificarlo y enviarlo.
 */
public class AplicacionCliente {
    private static final Scanner scanner = new Scanner(System.in);
    private final ColeccionPedidos coleccionPedidos = new ColeccionPedidos();

    /**
     * Constructor para la clase AplicacionCliente.
     */
    public AplicacionCliente() {
    }

    /**
     * Método principal de la aplicación.
     * Muestra el menú al usuario y permite realizar acciones sobre el pedido.
     */
    public void main() {
        int eleccion;
        do {
            System.out.println(SistemaPedidosCliente.mostrarMenu());
            try {
                eleccion = scanner.nextInt();
                switch (eleccion) {
                    case 0 -> {break;}
                    case 1 -> { //MenuModificacionPedido.
                        do {
                            System.out.println(SistemaPedidosCliente.obtenerEstadoDelPedido(coleccionPedidos));
                            System.out.println(SistemaPedidosCliente.mostrarMenuModificarPedido());
                            scanner.nextLine(); //limpieza de buffer.
                            try {eleccion = scanner.nextInt();}
                            catch (InputMismatchException e){System.out.println(IErrors.INPUTERROR_MSG);eleccion = 0;}
                            switch (eleccion) {
                                case 0 -> {break;} //Salir
                                case 1 -> {System.out.println(ListaPizzas.listarPizzas());break;}
                                case 2 -> {//agregar producto.
                                    int id, cantidad, confirmar = -1;
                                    do {
                                        try {
                                            System.out.println("Pizza id: ");
                                            System.out.println("(ingresa 0 para cancelar)");
                                            id = scanner.nextInt();
                                            //(id menor a 0 o id mayor a 30) Error.
                                            if (id <0 || id > 30) throw new IndexOutOfBoundsException();
                                            if (id != 0) {
                                                System.out.println("Ingresar cantidad: ");
                                                cantidad = scanner.nextInt();
                                                System.out.println("""
                                                        1. Confirmar.
                                                        0. Cancelar.""");
                                                confirmar = scanner.nextInt();
                                                if (confirmar == 1) SistemaPedidosCliente.agregarPedido(id, cantidad, coleccionPedidos); //retorno boolean.
                                                else if (confirmar != 0) {System.out.println(IErrors.INPUTERROR_MSG);}
                                            }
                                            else confirmar = 0;
                                        } catch (InputMismatchException | IndexOutOfBoundsException e) {scanner.nextLine();System.out.println(IErrors.INPUTERROR_MSG);}
                                    } while (confirmar != 1
                                            && confirmar != 0);
                                    break;
                                }
                                case 3 -> { //quitar producto. //2 errores (indice / cantidad).
                                    int indice = -1, cantidad = -1, confirmar;
                                    do {
                                        try {
                                            System.out.println("Ingresar orden (0 para cancelar):");
                                            indice = scanner.nextInt();
                                            if (indice < 0 || indice > coleccionPedidos.getPedidos().size()) throw new IndexOutOfBoundsException();
                                            else if (indice == 0){}
                                            else {
                                                System.out.println("Ingresar la cantidad: ");cantidad = scanner.nextInt();
                                                System.out.println("""
                                                        1. Confirmar.
                                                        0. Cancelar.""");
                                                confirmar = scanner.nextInt();
                                                if (confirmar == 1) {Pedido aux = SistemaPedidosCliente.quitarPedido(indice, cantidad, coleccionPedidos);indice = 0;}
                                                else {indice = 0;System.out.println("Operacion cancelada.");}
                                            }
                                        }
                                        catch (InputMismatchException | IndexOutOfBoundsException e){scanner.nextLine();System.out.println(IErrors.INPUTERROR_MSG);indice = -1;}
                                    } while (indice != 0);
                                    break;
                                }
                            }
                        } while (eleccion != 0);eleccion = 1;break;
                    }
                    case 2 -> {//enviar el pedido.//usar excepcion.
                        boolean flag = false;
                        try {flag = SistemaPedidosCliente.enviarPedido(coleccionPedidos);}
                        catch (EmptyOrderException e) {System.out.println(e.getMessage());}
                        if (flag) {System.out.println("El pedido se ha enviado correctamente.");}
                        break;
                    }
                }
            }
            catch (InputMismatchException e){System.out.println(IErrors.INPUTERROR_MSG);
                scanner.nextLine();eleccion=-1;}
        } while (eleccion != 0);
    }
}
