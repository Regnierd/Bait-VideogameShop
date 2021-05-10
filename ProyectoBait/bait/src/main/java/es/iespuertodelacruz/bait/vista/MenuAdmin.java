package es.iespuertodelacruz.bait.vista;

import java.util.InputMismatchException;
import java.util.Scanner;

import es.iespuertodelacruz.bait.api.personas.Empleado;
import es.iespuertodelacruz.bait.controlador.personasController.EmpleadoController;

public class MenuAdmin {
    Scanner sn;
    EmpleadoController empleadoController;

    /**
     * Constructor basico de la clase
     */
    public MenuAdmin() {
        empleadoController = new EmpleadoController();
        sn = new Scanner(System.in);
    }

    /**
     * Menu basico para el login de un empleado
     */
    public void menuPrincial() {
        Empleado empleado;
        String nombre;
        String password;

        System.out.println("Introduce nombre usuario.");
        nombre = sn.nextLine();
        System.out.println("Introduce la contraseña.");
        password = sn.nextLine();

        //empleado = empleadoController.validarEmpleado();
        System.out.println("Empleado valido;");

        menuOpciones();

    }

    private void menuOpciones() {
        boolean salir = false;
        int opcion;

        try {
            while (!salir) {
                System.out.println("Menu de administracion.");
                System.out.println("1. CRUD Clientes");
                System.out.println("2. CRUD Productos.");
                System.out.println("3. CRUD Categoria");
                System.out.println("4. CRUD Marca");
                System.out.println("5. CRUD Pedido");
                System.out.println("6. CRUD Empleado");
                System.out.println("7. Salir.");
                opcion = sn.nextInt();
                sn.nextLine();
                switch (opcion) {
                    case 1:
                        menuCliente();
                        break;
                    case 2:
                        menuProducto();
                        break;
                    case 3:
                        menuCategoria();
                        break;
                    case 4:
                        menuMarca();
                        break;
                    case 5:
                        menuEmpleado();
                        break;
                    case 6:
                        menuEmpleado();
                        break;
                    case 7:
                        salir = true;
                        break;
                    default:
                        System.err.println("Tiene que elegir una de las opciones del menu: 1 al 7");
                }
            }
        } catch (InputMismatchException ex) {
            System.out.println("El tipo de dato introducido es incorrecto.");
        }
    }

    private void menuCliente() {
    }

    private void menuProducto() {
    }

    private void menuCategoria() {
    }

    private void menuMarca() {
    }

    private void menuEmpleado() {
    }

}
