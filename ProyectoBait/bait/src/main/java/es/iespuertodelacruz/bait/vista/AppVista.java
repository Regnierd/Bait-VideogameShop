package es.iespuertodelacruz.bait.vista;

import java.util.Scanner;

public class App{
    static MenuAdministrador menuAdministrador;
    static MenuPersona menuPersona;
    public static void main( String[] args ){
        if(menuAdministrador == null){
            menuAdministrador = new MenuAdministrador();
        }
        if(menuPersona == null){
            menuPersona = new MenuPersona();
        }

        boolean salir = false;
        int opcion;
        Scanner sn = new Scanner(System.in);
        
        try{
            while(!salir){
                System.out.println("1. Administrador");
                System.out.println("2. Persona normal");
                System.out.println("3. Salir");
                System.out.println("¿Quien eres?");
                opcion = sn.nextInt();
                switch (opcion) {
                    case 1:
                        menuAdministrador.menu();
                        break;
                    case 2:
                        menuPersona.menu();
                        break;
                    case 3:
                        salir = true;
                        break;
                    default:
                        System.err.println("Tiene que elegir una de las opciones del menu: 1-3");
                }
            }
        }catch(){

        }
    }
}
