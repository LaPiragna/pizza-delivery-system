package Model.Classes;
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
            System.out.println(SistemaPedidos.mostrarMenu());
            eleccion = scanner.nextInt();eleccion = SistemaPedidos.menu(eleccion);
            switch (eleccion) {
                case 0 -> {break;}
                case 1 -> { //Ver pedido.
                    do {
                        System.out.println(SistemaPedidos.obtenerEstadoDelPedido(coleccionPedidos));
                        System.out.println(SistemaPedidos.mostrarMenuModificarPedido());
                        scanner.nextLine();eleccion = scanner.nextInt();
                        eleccion = SistemaPedidos.menuModificacionDelPedido(eleccion, coleccionPedidos);
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
                                        if (confirmar == 1) SistemaPedidos.agregarPedido(id, cantidad, coleccionPedidos); //retorno boolean.
                                    } while (confirmar != 1 &&
                                            confirmar != 0);
                                }break;
                            }
                            case 3 -> {//quitar producto.
                                int indice, cantidad;System.out.println("Ingresa el numero del articulo: ");indice = scanner.nextInt();
                                System.out.println("Ingresa la cantidad que quieres remover: ");cantidad = scanner.nextInt();
                                Pedido aux = SistemaPedidos.quitarPedido(indice, cantidad,coleccionPedidos);
                                break;
                            }
                        }
                    } while (eleccion != 1);break;
                }
                case 2 -> {//enviar el pedido.
                    boolean flag = SistemaPedidos.enviarPedido(coleccionPedidos);
                    if (flag) {System.out.println("El pedido se ha enviado correctamente.");eleccion = 0;}
                    else {System.out.println("Hubo un error en el envio.");}
                    //usar excepcion.
                    break;
                }
            }
        } while (eleccion != 0);
    }
}
