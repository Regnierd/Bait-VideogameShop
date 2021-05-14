package es.iespuertodelacruz.bait.vista;

import java.util.InputMismatchException;
import java.util.Scanner;

import es.iespuertodelacruz.bait.api.personas.Usuario;
import es.iespuertodelacruz.bait.controlador.movimientosController.PedidoController;
import es.iespuertodelacruz.bait.controlador.personasController.UsuarioController;
import es.iespuertodelacruz.bait.exceptions.UsuarioException;

public class MenuCliente {
    private static final String TIPO_DATO_INCORRECTO = "El tipo de dato introducido es incorrecto.";
    private static final String ELEGIR_OPCION_DEL_MENU = "Tiene que elegir una de las opciones del menu: 0 al ";

    Scanner sn;
    UsuarioController usuarioController;
    PedidoController pedidoController;

    /**
     * Constructor basico del menu del cliente
     */
    public MenuCliente() {
        sn = new Scanner(System.in);
        usuarioController = new UsuarioController();
        pedidoController = new PedidoController();
    }

    /**
     * Menu basico para el cliente
     */
    public void menuPrincial() {
        boolean salir = false;
        int opcion;
        Usuario cliente;
        try {
            while (!salir) {
                System.out.println("Menu principal del cliente");
                System.out.println("1. Registrarse");
                System.out.println("2. Iniciar sesion");
                System.out.println("3. Accerder sin cuenta");
                System.out.println("0. Salir");
                System.out.println("Selecciona opcion:");
                opcion = sn.nextInt();
                sn.nextLine();
                switch (opcion) {
                    case 1:
                        cliente = registrar();
                        try {
                            usuarioController.insertar(cliente);
                            System.out.println("Se ha registrado correctamente.");
                            menuOpciones(cliente);
                        } catch (UsuarioException e) {
                            System.out.println("Error al realizar el registro");
                        }
                        break;
                    case 2:
                        try {
                            cliente = validarCliente();
                            System.out.println("Sesion iniciada correctamente.");
                            menuOpciones(cliente);
                        } catch (UsuarioException e) {
                            System.out.println("El usuario o la password no son correctos.");
                        }
                        break;
                    case 3:
                        menuProductos();
                        break;
                    case 0:
                        salir = true;
                        break;
                    default:
                        System.err.println(ELEGIR_OPCION_DEL_MENU + "3");
                }
            }
        } catch (InputMismatchException ex) {
            System.out.println(TIPO_DATO_INCORRECTO);
        }
    }

    /**
     * Funcion que busca un cliente a partir de un nombre de usuario y password y
     * lo devuevle si existe
     * 
     * @return el cliente si existe
     * @throws UsuarioException error a controlar
     */
    private Usuario validarCliente() throws UsuarioException {
        String nombreUsuario;
        String password;
        Usuario cliente;
        System.out.println("Login Cliente");
        System.out.println("Introduce el nombre de usuario:");
        nombreUsuario = sn.nextLine();
        System.out.println("Intrduce la password:");
        password = sn.nextLine();

        cliente = usuarioController.buscarUsuario(nombreUsuario, password, "Cliente");

        return cliente;
    }

    /**
     * Menu para buscar productos por nombre, categoria y marca
     */
    private void menuProductos() {
        boolean salir = false;
        int opcion;
        try {
            while (!salir) {
                System.out.println("Buscar producto");
                System.out.println("1. Buscar producto por nombre");
                System.out.println("2. Buscar producto por categoria");
                System.out.println("3. Buscar producto por marca");
                System.out.println("0. Salir");
                System.out.println("Selecciona opcion:");
                opcion = sn.nextInt();
                sn.nextLine();

                switch (opcion) {
                    case 1:
                        // codigo para mostrar los productos por nombre
                        break;
                    case 2:
                        // codigo para mostrar los productos por catagoria
                        break;
                    case 3:
                        // codigo para mostrar los productos por marca
                        break;
                    case 0:
                        salir = true;
                        break;
                    default:
                        System.err.println(ELEGIR_OPCION_DEL_MENU + "3");
                }
            }
        } catch (InputMismatchException ex) {
            System.out.println(TIPO_DATO_INCORRECTO);
        }
    }

    /**
     * Menu de opciones que puede realizar el cliente
     * 
     * @param cliente con la sesion actual
     */
    private void menuOpciones(Usuario cliente) {
        boolean salir = false;
        int opcion;
        try {
            while (!salir) {
                System.out.println("Menu de opciones cliente");
                System.out.println("1. Añadir saldo");
                System.out.println("2. Realizar pedido");
                System.out.println("3. Buscar productos");
                System.out.println("4. Ver pedidos");
                System.out.println("5. Ver compras");
                System.out.println("6. Editar mis datos");
                System.out.println("0. Salir");
                opcion = sn.nextInt();
                sn.nextLine();
                switch (opcion) {
                    case 1:
                        System.out.println("Introdusca la cantidad que quiere añadir.");
                        float saldo = sn.nextFloat();
                        try {
                            usuarioController.añadirSaldo(saldo);
                        } catch (UsuarioException e) {
                            System.err.println("Error al añadir el saldo");
                        }
                        break;
                    case 2:
                        realizarPedido();
                        break;
                    case 3:
                        menuProductos();
                        break;
                    case 4:
                        // codigo para ver los pedidos
                        break;
                    case 5:
                        // codigo para ver las compras
                        break;
                    case 6:
                        Usuario nuevoCliente = registrar();
                        try {
                            usuarioController.modificar(nuevoCliente);
                        } catch (UsuarioException e) {
                            System.err.println("No se han podido registrar los cambios");
                        }
                        break;
                    case 0:
                        salir = true;
                        break;
                    default:
                        System.err.println(ELEGIR_OPCION_DEL_MENU + "6");
                }
            }
        } catch (InputMismatchException ex) {
            System.out.println(TIPO_DATO_INCORRECTO);
        }
    }

    /**
     * Metodo que obtienes los datos y realiza un pedido
     */
    private void realizarPedido() {
        System.out.println("Introdusca datos para relalizar el pedido.");
        String idProducto = obtenerDato("Introduce el id del producto.");
        int unidades = Integer.parseInt(obtenerDato("unidades que quiere comprar."));
        pedidoController.realizarPedido(idProducto, unidades);
    }

    /**
     * Menu que registrar un nuevo cliente
     * 
     * @return el cleinte creado
     */
    private Usuario registrar() {
        Usuario cliente = null;
        System.out.println("Datos para el registro");
        String dni = obtenerDato("el dni.");
        String nombre = obtenerDato("el nombre");
        String apellidos = obtenerDato("los apellidos.");
        String email = obtenerDato("el email");
        String direccion = obtenerDato("la direccion.");
        String telefono = obtenerDato("el telefono");
        String pais = obtenerDato("el pais.");
        String codigoPostal = obtenerDato("el codigo postal");
        String provincia = obtenerDato("la provincia.");
        String nombreUsuario = obtenerDato("el nombre usuario");
        String password = obtenerDato("la password");

        cliente = new Usuario(dni, nombre, apellidos, email, direccion, telefono, pais, codigoPostal, provincia, nombreUsuario, password, 0f, "CLiente");
        return cliente;
    }

    /**
     * Funcion que obtiene un dato y los devuelve
     * @param mensaje del dato que se va a obtener
     * @return el dato obtenido
     */
    private String obtenerDato(String mensaje) {
        String dato;

        System.out.println("Introduce " + mensaje);
        dato = sn.nextLine();

        return dato;
    }
}
