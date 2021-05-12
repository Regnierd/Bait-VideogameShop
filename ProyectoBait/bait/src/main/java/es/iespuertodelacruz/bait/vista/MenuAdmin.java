package es.iespuertodelacruz.bait.vista;

import java.util.InputMismatchException;
import java.util.Scanner;

import es.iespuertodelacruz.bait.controlador.personasController.UsuarioController;
import es.iespuertodelacruz.bait.exceptions.UsuarioException;

public class MenuAdmin {
    private static final String ERROR_OPCION_ELEGIDA = "Tiene que elegir una de las opciones del menu: 1 al ";
    private static final String ERROR_TIPO_DATO = "El tipo de dato introducido es incorrecto.";
    Scanner sn;
    UsuarioController usuarioController;

    /**
     * Constructir basico de la clase
     */
    public MenuAdmin() {
        sn = new Scanner(System.in);
        usuarioController = new UsuarioController();
    }

    /**
     * Metodo que valida nombre y contrasenia del admin
     */
    public void menuPrincial() {
        String nombreAcceso;
        String password;

        System.out.println("Introduce nombre acceso.");
        nombreAcceso = sn.nextLine();
        System.out.println("Introduce la password");
        password = sn.nextLine();

        try {
            usuarioController.buscarUsuario(nombreAcceso, password, "Admin");
            menuOpciones();
        } catch (UsuarioException ex) {
            System.err.println("El nombre de acceso o la password son incorrectos.");
        }
    }

    private void menuOpciones() {
        boolean salir = false;
        int opcion;

        try {
            while (!salir) {
                System.out.println("1. Modificar clientes.");
                System.out.println("2. Modificar productos.");
                System.out.println("3. Modificar categorias.");
                System.out.println("4. Modificar marcas");
                System.out.println("5. Modificar pedidos");
                System.out.println("6. Modificar Administradores");
                System.out.println("7. Salir");
                opcion = sn.nextInt();
                sn.nextLine();
                switch (opcion) {
                    case 1:
                        menuClientes();
                        break;
                    case 2:
                        menuProductos();
                        break;
                    case 3:
                        menuCategorias();
                        break;
                    case 4:
                        menuMarcas();
                        break;
                    case 5:
                        menuPedidos();
                        break;
                    case 6:
                        menuAdministradores();
                        break;
                    case 7:
                        salir = true;
                        break;
                    default:
                        System.err.println(ERROR_OPCION_ELEGIDA + "7");
                        break;
                }
            }
        } catch (InputMismatchException ex) {
            System.out.println(ERROR_TIPO_DATO);
        }
    }

    /**
     * Menu para relizar el CRUD de clientes
     */
    private void menuClientes() {
        int opcion;
        boolean salir = false;
        while (!salir) {
            opcion = obtenerOpcion("cliente");
            switch (opcion) {
                case 1:
                    // Codigo para insertar un cliente
                    break;
                case 2:
                    // codigo para eliminar un cliente
                    break;
                case 3:
                    // codigo para modificar un cliente
                    break;
                case 4:
                    // codigo para buscar un cliente
                case 5:
                    salir = true;
                    break;
                default:
                    System.err.println(ERROR_OPCION_ELEGIDA + "5");
            }
        }
    }

    /**
     * Funcion que obtiene una opcion para realizar un CRUD en la tabla que se pasa
     * el nombre en los parametros
     * 
     * @param tabla nombre de la tabla en que se va a realizar el CRUD
     * @return la opcion del CRUD a realizar
     */
    private int obtenerOpcion(String tabla) {
        int opcion = -1;
        Scanner sn = new Scanner(System.in);
        try {
            System.out.println("1. Insertar " + tabla);
            System.out.println("2. Eliminar " + tabla);
            System.out.println("3. Modificar " + tabla);
            System.out.println("4. Buscar " + tabla);
            System.out.println("5. Salir.");
            opcion = sn.nextInt();
            sn.nextLine();
        } catch (InputMismatchException ex) {
            System.out.println(ERROR_TIPO_DATO);
        }

        return opcion;
    }

    /**
     * Menu para realizar el CRUD de productos
     */
    private void menuProductos() {
        int opcion;
        boolean salir = false;
        while (!salir) {
            opcion = obtenerOpcion("productos");
            switch (opcion) {
                case 1:
                    // Codigo para insertar un producto
                    break;
                case 2:
                    // codigo para eliminar un producto
                    break;
                case 3:
                    // codigo para modificar un producto
                    break;
                case 4:
                    // codigo para buscar un producto
                case 5:
                    salir = true;
                    break;
                default:
                    System.err.println(ERROR_OPCION_ELEGIDA + "5");
            }
        }
    }

    /**
     * Menu para realizar el CRUD categorias
     */
    private void menuCategorias() {
        int opcion;
        boolean salir = false;
        while (!salir) {
            opcion = obtenerOpcion("categoria");
            switch (opcion) {
                case 1:
                    // Codigo para insertar un categoria
                    break;
                case 2:
                    // codigo para eliminar un categoria
                    break;
                case 3:
                    // codigo para modificar un categoria
                    break;
                case 4:
                    // codigo para buscar un categoria
                case 5:
                    salir = true;
                    break;
                default:
                    System.err.println(ERROR_OPCION_ELEGIDA + "5");
            }
        }
    }

    /**
     * Menu para realizar el CRUD de marcas
     */
    private void menuMarcas() {
        int opcion;
        boolean salir = false;
        while (!salir) {
            opcion = obtenerOpcion("marca");
            switch (opcion) {
                case 1:
                    // Codigo para insertar un marca
                    break;
                case 2:
                    // codigo para eliminar un marca
                    break;
                case 3:
                    // codigo para modificar un marca
                    break;
                case 4:
                    // codigo para buscar un marca
                case 5:
                    salir = true;
                    break;
                default:
                    System.err.println(ERROR_OPCION_ELEGIDA + "5");
            }
        }
    }

    /**
     * Menu para realizar el CRUD pedidos
     */
    private void menuPedidos() {
        int opcion;
        boolean salir = false;
        while (!salir) {
            opcion = obtenerOpcion("pedido");
            switch (opcion) {
                case 1:
                    // Codigo para insertar un pedido
                    break;
                case 2:
                    // codigo para eliminar un pedido
                    break;
                case 3:
                    // codigo para modificar un pedido
                    break;
                case 4:
                    // codigo para buscar un pedido
                case 5:
                    salir = true;
                    break;
                default:
                    System.err.println(ERROR_OPCION_ELEGIDA + "5");
            }
        }
    }

    /**
     * Menu para realizar el CRUD adminsitradores
     */
    private void menuAdministradores() {
        int opcion;
        boolean salir = false;
        while (!salir) {
            opcion = obtenerOpcion("administradores");
            switch (opcion) {
                case 1:
                    // Codigo para insertar un administradores
                    break;
                case 2:
                    // codigo para eliminar un administradores
                    break;
                case 3:
                    // codigo para modificar un administradores
                    break;
                case 4:
                    // codigo para buscar un administradores
                case 5:
                    salir = true;
                    break;
                default:
                    System.err.println(ERROR_OPCION_ELEGIDA + "5");
            }
        }
    }

}