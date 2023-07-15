package Model.Classes;
import Model.Exceptions.EmptyOrderException;

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
            eleccion = scanner.nextInt();eleccion = SistemaPedidosCliente.menu(eleccion);
            switch (eleccion) {
                case 0 -> {break;}
                case 1 -> { //Ver pedido.
                    do {
                        System.out.println(SistemaPedidosCliente.obtenerEstadoDelPedido(coleccionPedidos));
                        System.out.println(SistemaPedidosCliente.mostrarMenuModificarPedido());
                        scanner.nextLine();eleccion = scanner.nextInt();
                        eleccion = SistemaPedidosCliente.menuModificacionDelPedido(eleccion, coleccionPedidos);
                        switch (eleccion) {
                            case 0 -> {eleccion=1;break;}
                            case 1 -> {System.out.println(ListaPizzas.listarPizzas());eleccion=2;break;}
                            case 2 -> {//agregar producto.
                                int id, cantidad, confirmar;
                                System.out.println("Pizza id (ingresa 0 para cancelar): ");id = scanner.nextInt();
                                if (id != 0) {
                                    System.out.println("Ingresar cantidad: ");
                                    cantidad = scanner.nextInt();
                                    do {
                                        System.out.println("""
                                        1. Confirmar.
                                        0. Cancelar.""");
                                        confirmar = scanner.nextInt();
                                        if (confirmar == 1) SistemaPedidosCliente.agregarPedido(id, cantidad, coleccionPedidos); //retorno boolean.
                                    } while (confirmar != 1 &&
                                            confirmar != 0);
                                }break;
                            }
                            case 3 -> { //quitar producto.
                                int indice, cantidad;System.out.println("Ingresa el numero del articulo: ");indice = scanner.nextInt();
                                System.out.println("Ingresa la cantidad que quieres remover: ");cantidad = scanner.nextInt();
                                try {Pedido aux = SistemaPedidosCliente.quitarPedido(indice, cantidad, coleccionPedidos);}
                                catch (IndexOutOfBoundsException e) {System.out.println(e.getMessage());}
                                break;
                            }
                        }
                    } while (eleccion != 1);break;
                }
                case 2 -> {//enviar el pedido.//usar excepcion.
                    boolean flag = false;
                    try {flag = SistemaPedidosCliente.enviarPedido(coleccionPedidos);}
                    catch (EmptyOrderException e) {System.out.println(e.getMessage()+"a");}
                    if (flag) {System.out.println("El pedido se ha enviado correctamente.");eleccion = 0;}
                    break;
                }
            }
        } while (eleccion != 0);
    }
}
