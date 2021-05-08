package es.iespuertodelacruz.bait.vista;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AppVista{
    static MenuEmpleado menuEmpleado;
    static MenuCliente menuCliente;
    public static void main( String[] args ){
        if(menuEmpleado == null){
            menuEmpleado = new MenuEmpleado();
        }
        if(menuCliente == null){
            menuCliente = new MenuCliente();
        }

        menuPrincipal();
    }

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
                        menuEmpleado.menu();
                        break;
                    case 2:
                        menuCliente.menuLogin();
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
