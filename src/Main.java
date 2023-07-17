import Model.Classes.AplicacionCliente;
import Model.Classes.Cuenta;
import Model.Classes.Login;
import Model.Classes.MenuPrincipal;
import Model.Exceptions.*;
import Model.Interfaces.IErrors;

import java.util.InputMismatchException;
import java.util.Scanner;
/*** Clase principal. Cxntenedor del programa.**/
public class Main {
    public static Scanner scanner = new Scanner(System.in);
    /*** Metodo main.* */
    public static void main(String[] arg) {
        Cuenta cuenta = new Cuenta();// variable auxiliar.
        int menuIngreso = -1;//VARIABLE DE CONTROL DE FLUJO.
        while (menuIngreso != 0) {
            menuIngreso = -1;
            while (menuIngreso < 0 || menuIngreso > 2) {   // VALIDACION.
                Login.printMenu(); // muestra del menu
                try {menuIngreso = scanner.nextInt();if(menuIngreso < 0 || menuIngreso > 2) System.out.println(IErrors.INPUTERROR_MSG);}
                catch (InputMismatchException e){System.out.println(IErrors.INPUTERROR_MSG);menuIngreso=-1;scanner.nextLine();}
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
                    catch (UsernameExistenteExc e) { //el username ya existe
                        System.out.println(e.getMessage());
                        menuIngreso = 4; //no accede al menu principal
                    }
                    catch (RegistryCancelledException e) {//usuario cancelo el registro.
                        System.out.println(e.getMessage());
                        menuIngreso = 4; //no accede al menu principal
                    }
                    break;
                case 2: //INICIO DE SESION.
                    try {
                        cuenta = Login.iniciarSesion(usuario, clave); //retorna la cuenta que ha iniciado sesion.
                        menuIngreso =1; //accede al menu principal
                    } catch (UsernameInexistenteExc e) {//el username no se pudo encontrar.
                        System.out.println(e.getMessage());
                        menuIngreso = 4; //no accede al menu principal
                    }catch (LoginCancelledException e) { //usuario cancela el proceso de inicio de sesion.
                        System.out.println(e.getMessage());
                        menuIngreso = 4; //no accede al menu principal
                    } catch (PasswordIncorrectException e) { //usuario ingresa clave incorrectamente.
                        System.out.println(e.getMessage());
                        menuIngreso = 4; //no accede al menu principal
                    }
                    break;
                case 3: //SALIR DEL PROGRAMA.
                    menuIngreso = 0;
                    break;
            }
            /* SWITCH DEL MENU LOGIN */
            /*  - -  - - - - - - - - - - - LOGIN */
            if (menuIngreso != 0 && menuIngreso != 4) {
                while (menuIngreso != 0) {
                    /*  - - - - - -  - - - - - -  MENU PRINCIPAL  - - - - - -   - - - - - - */
                    menuIngreso = -1;
                    //VALIDACION
                    while (menuIngreso < 0 || menuIngreso > 2) {
                        System.out.println(MenuPrincipal.printMenu()); //opciones del menu principal. IMPLEMENTAR
                        try {menuIngreso = scanner.nextInt();}
                        catch (InputMismatchException e){System.out.println(IErrors.INPUTERROR_MSG);
                            scanner.nextLine();menuIngreso = -1;}
                    }
                    switch (menuIngreso) {/* SWITCH DEL MENU PRINCIPAL */
                        case 0: //SALIR
                            cuenta = null;menuIngreso = 0;break;//ESC. del menu principal. cerrar sesion.
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
                    }/* SWITCH DEL MENU PRINCIPAL */
                    /*  - - - - - -  - - - - - -  MENU PRINCIPAL  - - - - - -   - - - - - - */
                }
                menuIngreso = -1; // re-assign value to not get out of the whole program.
            }
        }
    }
}
