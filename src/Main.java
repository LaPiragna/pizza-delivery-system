import Model.Classes.AplicacionCliente;
import Model.Classes.Cuenta;
import Model.Classes.Login;
import Model.Classes.MenuPrincipal;
import Model.Exceptions.*;

import java.util.Scanner;
/*** Clase principal. Cxntenedor del programa.**/
public class Main {
    public static Scanner scanner = new Scanner(System.in);
    /*** Metodo main.* */
    public static void main(String[] arg) {
        Cuenta cuenta = new Cuenta();// variable auxiliar.
        int menuIngreso = 1;//VARIABLES DE CONTROL DE FLUJO.
        while (menuIngreso != 0) {
            menuIngreso = 0; //reset
            while (menuIngreso < 1 || menuIngreso > 3) {   // VALIDACION.
                Login.printMenu(); // muestra del menu
                menuIngreso = scanner.nextInt();
            }
            String usuario = "", clave = "";
            if (menuIngreso != 0) {/* - - - INGRESO DE DATOS */
                System.out.println("Ingresar nombre de usuario: ");scanner.nextLine();usuario = scanner.nextLine();
                System.out.println("Ingresar clave: ");clave = scanner.nextLine();
            }
            switch (menuIngreso) {/* SWITCH DEL MENU LOGIN */
                /**
                 * Aclaraciones acerca de la variable menuIngreso.
                 * 3  -> se sale del juego.
                 * 1  -> se ingresa luego al menu principal para continuar.
                 * */
                case 1: //REGISTRO
                    try {
                        cuenta = Login.registro(usuario, clave); //retorna la cuenta registrada.
                        menuIngreso=1;//accede al menu principal
                    }
                    catch (UsernameExistenteExc e) {
                        System.out.println(e.getMessage());
                        menuIngreso = 3; //no accede al menu principal
                    }
                    catch (RegistryCancelledException e) {
                        System.out.println(e.getMessage()); //usuario cancelo el registro.
                        menuIngreso = 3; //no accede al menu principal
                    }
                    break;
                case 2: //INICIO DE SESION.
                    try {
                        cuenta = Login.iniciarSesion(usuario, clave); //retorna la cuenta que ha iniciado sesion.
                        menuIngreso =1; //accede al menu principal
                    } catch (UsernameInexistenteExc e) {
                        System.out.println(e.getMessage());
                        menuIngreso = 3; //no accede al menu principal
                    }catch (LoginCancelledException e) { //usuario cancela el proceso de inicio de sesion.
                        System.out.println(e.getMessage());
                        menuIngreso = 3; //no accede al menu principal
                    } catch (PasswordIncorrectException e) { //usuario ingresa clave incorrectamente.
                        System.out.println(e.getMessage());
                        menuIngreso = 3; //no accede al menu principal
                    }
                    break;
                case 3: //SALIR DEL PROGRAMA.
                    menuIngreso = 0;
                    break;
            }
            /* SWITCH DEL MENU LOGIN */
            /*  - -  - - - - - - - - - - - LOGIN */

            if (menuIngreso != 0) {

                while (menuIngreso != 0) {

                    /*  - - - - - -  - - - - - -  MENU PRINCIPAL  - - - - - -   - - - - - - */
                    menuIngreso = 0;

                    //VALIDACION
                    while (menuIngreso < 1 || menuIngreso > 4) {
                        MenuPrincipal.printMenu(); //opciones del menu principal. IMPLEMENTAR
                        menuIngreso = scanner.nextInt();
                    }
                    switch (menuIngreso) {/* SWITCH DEL MENU PRINCIPAL */
                        case 0: //SALIR
                            break;
                        case 1:// ENTRAR EN LA APLICACION
                            AplicacionCliente aplicacionCliente = new AplicacionCliente(); aplicacionCliente.main();
                            break;
                        case 2:
                            //ELIMINAR CUENTA.
                            System.out.println("Ingresar la clave de la cuenta para eliminarla.");
                            System.out.println("            (0 para salir)");scanner.nextLine();
                            String auxiliar = scanner.nextLine(); //clave a ingresar.
                            if (auxiliar.equals("0")) System.out.println("eliminacixn cancelada.");// usuarix decide nx eliminar la cuenta.
                            else { //usuarix decidix eliminar la cuenta.
                                try {Login.borrarCuenta(cuenta, auxiliar);menuIngreso = 0;}//ESC. del menu principal.
                                catch (PasswordIncorrectException e) {System.out.println(e.getMessage());}
                            }
                            break;
                        case 4:cuenta = null;menuIngreso = 4;break;//ESC. del menu principal. cerrar sesion.
                    }/* SWITCH DEL MENU PRINCIPAL */
                    /*  - - - - - -  - - - - - -  MENU PRINCIPAL  - - - - - -   - - - - - - */
                }
            }
        }
    }
}
