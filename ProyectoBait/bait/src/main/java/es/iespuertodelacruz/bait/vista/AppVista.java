package es.iespuertodelacruz.bait.vista;

import java.util.InputMismatchException;
import java.util.Scanner;

import es.iespuertodelacruz.bait.exceptions.PersistenciaException;
import es.iespuertodelacruz.bait.modelo.mysql.Bbdd;

public class AppVista{
    static MenuAdmin menuAdmin;
    static MenuUsuario menuUsuario;
    static Bbdd bbdd;

    public static void main( String[] args ) throws PersistenciaException{
        if(menuAdmin == null){
            menuAdmin = new MenuAdmin();
        }
        if(menuUsuario == null){
            menuUsuario = new MenuUsuario();
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
                System.out.println("Menu principal");
                System.out.println("1. Empleado");
                System.out.println("2. Cliente");
                System.out.println("0. Salir");
                System.out.println("¿Quien eres?");
                opcion = sn.nextInt();
                sn.nextLine();
                switch (opcion) {
                    case 1:
                        menuAdmin.menuPrincial();
                        break;
                    case 2:
                        menuUsuario.menuPrincial();
                        break;
                    case 0:
                        salir = true;
                        break;
                    default:
                        System.err.println("Tiene que elegir una de las opciones del menu: 0 al 2");
                }
            }
        }catch(InputMismatchException ex){
            System.out.println("El tipo de dato introducido es incorrecto.");
        }
    }   
}
