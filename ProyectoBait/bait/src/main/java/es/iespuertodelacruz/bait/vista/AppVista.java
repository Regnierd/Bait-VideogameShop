package es.iespuertodelacruz.bait.vista;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AppVista{
    static MenuAdmin menuAdmin;
    static MenuCliente menuCliente;

    public static void main( String[] args ){
        if(menuAdmin == null){
            menuAdmin = new MenuAdmin();
        }
        if(menuCliente == null){
            menuCliente = new MenuCliente();
        }

        menuPrincipal();
    }

    /**
     * Menu basico para preguntar si es un cliente o un administrador
     */
    private static void menuPrincipal() {   
        boolean salir = false;
        int opcion;
        Scanner sn = new Scanner(System.in);
        try{
            while(!salir){
                System.out.println("1. Empleado.");
                System.out.println("2. Cliente.");
                System.out.println("3. Salir");
                System.out.println("¿Quien eres?");
                opcion = sn.nextInt();
                sn.nextLine();
                switch (opcion) {
                    case 1:
                        menuAdmin.menuPrincial();
                        break;
                    case 2:
                        menuCliente.menuPrincial();
                        break;
                    case 3:
                        salir = true;
                        break;
                    default:
                        System.err.println("Tiene que elegir una de las opciones del menu: 1 al 3");
                }
            }
        }catch(InputMismatchException ex){
            System.out.println("El tipo de dato introducido es incorrecto.");
        }
    }   
}
