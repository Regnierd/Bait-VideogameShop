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

        // empleado = empleadoController.validarEmpleado();
        System.out.println("Empleado valido;");

        menuOpciones();

    }

    private void menuOpciones() {
        boolean salir = false;
        int opcion;

        try {
            while (!salir) {
                System.out.println("Menu de administracion.");
                System.out.println("1. Operar con Clientes");
                System.out.println("2. Operar con Productos.");
                System.out.println("3. Operar con Categoria");
                System.out.println("4. Operar con Marca");
                System.out.println("5. Operar con Pedido");
                System.out.println("6. Operar con Empleado");
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
        boolean salir = false;
        int opcion;
        Scanner sn = new Scanner(System.in);
        try {
            while (!salir) {
                System.out.println("1. Eliminar Cliente.");
                System.out.println("2. Buscar Clientes.");
                System.out.println("3. Salir");
                opcion = sn.nextInt();
                sn.nextLine();
                switch (opcion) {
                    case 1:
                        // 
                        break;
                    case 2:
                        //
                        break;
                    case 3:
                        salir = true;
                        break;
                    default:
                        System.err.println("Tiene que elegir una de las opciones del menu: 1 al 3");
                }
            }
        } catch (InputMismatchException ex) {
            System.out.println("El tipo de dato introducido es incorrecto.");
        }
    }

    private void menuProducto() {
        boolean salir = false;
        int opcion;
        Scanner sn = new Scanner(System.in);
        try {
            while (!salir) {
                System.out.println("1. Insertar Producto");
                System.out.println("2. Eliminar Producto");
                System.out.println("3. Modificar Producto");
                System.out.println("3. Buscar Producto");
                System.out.println("4. Salir.");
                opcion = sn.nextInt();
                sn.nextLine();
                switch (opcion) {
                    case 1:
                        //
                        break;
                    case 2:
                        //
                        break;
                    case 3:
                        //
                        break;
                    case 4:
                        salir = true;
                        break;
                    default:
                        System.err.println("Tiene que elegir una de las opciones del menu: 1 al 4");
                }
            }
        } catch (InputMismatchException ex) {
            System.out.println("El tipo de dato introducido es incorrecto.");
        }
    }

    private void menuCategoria() {
        boolean salir = false;
        int opcion;
        Scanner sn = new Scanner(System.in);
        try {
            while (!salir) {
                System.out.println("1. Insertar Categoria");
                System.out.println("2. Eliminar Categoria");
                System.out.println("3. Modificar Categoria");
                System.out.println("3. Buscar Categoria");
                System.out.println("4. Salir.");
                opcion = sn.nextInt();
                sn.nextLine();
                switch (opcion) {
                    case 1:
                        //
                        break;
                    case 2:
                        //
                        break;
                    case 3:
                        //
                        break;
                    case 4:
                        salir = true;
                        break;
                    default:
                        System.err.println("Tiene que elegir una de las opciones del menu: 1 al 4");
                }
            }
        } catch (InputMismatchException ex) {
            System.out.println("El tipo de dato introducido es incorrecto.");
        }
    }

    private void menuMarca() {
        boolean salir = false;
        int opcion;
        Scanner sn = new Scanner(System.in);
        try {
            while (!salir) {
                System.out.println("1. Insertar Marca");
                System.out.println("2. Eliminar Marca");
                System.out.println("3. Modificar Marca");
                System.out.println("3. Buscar Marca");
                System.out.println("4. Salir.");
                opcion = sn.nextInt();
                sn.nextLine();
                switch (opcion) {
                    case 1:
                        //
                        break;
                    case 2:
                        //
                        break;
                    case 3:
                        //
                        break;
                    case 4:
                        salir = true;
                        break;
                    default:
                        System.err.println("Tiene que elegir una de las opciones del menu: 1 al 4");
                }
            }
        } catch (InputMismatchException ex) {
            System.out.println("El tipo de dato introducido es incorrecto.");
        }
    }

    private void menuEmpleado() {
        boolean salir = false;
        int opcion;
        Scanner sn = new Scanner(System.in);
        try {
            while (!salir) {
                System.out.println("1. Insertar Empleados");
                System.out.println("2. Eliminar Empleados");
                System.out.println("3. Modificar Empleados");
                System.out.println("3. Buscar Empleados");
                System.out.println("4. Salir.");
                opcion = sn.nextInt();
                sn.nextLine();
                switch (opcion) {
                    case 1:
                        //
                        break;
                    case 2:
                        //
                        break;
                    case 3:
                        //
                        break;
                    case 4:
                        salir = true;
                        break;
                    default:
                        System.err.println("Tiene que elegir una de las opciones del menu: 1 al 4");
                }
            }
        } catch (InputMismatchException ex) {
            System.out.println("El tipo de dato introducido es incorrecto.");
        }
    }

}
