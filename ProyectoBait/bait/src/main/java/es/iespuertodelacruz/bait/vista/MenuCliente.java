package es.iespuertodelacruz.bait.vista;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import es.iespuertodelacruz.bait.api.movimientos.Compra;
import es.iespuertodelacruz.bait.api.movimientos.Pedido;
import es.iespuertodelacruz.bait.api.personas.Usuario;
import es.iespuertodelacruz.bait.api.productos.Producto;
import es.iespuertodelacruz.bait.controlador.movimientosController.CompraController;
import es.iespuertodelacruz.bait.controlador.movimientosController.PedidoController;
import es.iespuertodelacruz.bait.controlador.personasController.UsuarioController;
import es.iespuertodelacruz.bait.controlador.productosController.ProductoController;
import es.iespuertodelacruz.bait.exceptions.ApiException;
import es.iespuertodelacruz.bait.exceptions.PersistenciaException;

public class MenuCliente {
    private static final String TIPO_DATO_INCORRECTO = "El tipo de dato introducido es incorrecto.";
    private static final String ELEGIR_OPCION_DEL_MENU = "Tiene que elegir una de las opciones del menu: 0 al ";

    Scanner sn;
    UsuarioController usuarioController;
    PedidoController pedidoController;
    CompraController compraController;
    ProductoController productoController;
    /**
     * Constructor basico del menu del cliente
     * @throws PersistenciaException
     */
    public MenuCliente() throws PersistenciaException {
        sn = new Scanner(System.in);
        usuarioController = new UsuarioController();
        pedidoController = new PedidoController();
        compraController = new CompraController();
        productoController = new ProductoController();
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
                        } catch (ApiException | PersistenciaException e) {
                            System.out.println("Error al realizar el registro");
                        }
                        break;
                    case 2:
                        try {
                            cliente = validarCliente();
                            System.out.println("Sesion iniciada correctamente.");
                            menuOpciones(cliente);
                        } catch (ApiException e) {
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
     * @throws ApiException error a controlar
     */
    private Usuario validarCliente() throws ApiException {
        String nombreUsuario;
        String password;
        Usuario cliente = null;
        System.out.println("Login Cliente");
        System.out.println("Introduce el nombre de usuario:");
        nombreUsuario = sn.nextLine();
        System.out.println("Intrduce la password:");
        password = sn.nextLine();

        try {
            cliente = usuarioController.login(nombreUsuario, password, "Cliente");
        } catch (PersistenciaException | ApiException e) {
            System.out.println(e.getMessage());
        }

        return cliente;
    }

    /**
     * Menu para buscar productos por nombre, categoria y marca
     */
    public void menuProductos() {//REFACTORIZAR
        boolean salir = false;
        int opcion;
        ArrayList<Producto> productos;
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
                        System.out.println("Introdusca un nombre");
                        String nombre = sn.nextLine();
                        try {
                            productos = productoController.buscarPorNombre(nombre);
                            recorrerProductos(productos);
                        } catch (ApiException | PersistenciaException e) {
                            System.err.println("Error al busca los productos por nombre.");
                        }
                        break;
                    case 2:
                        System.out.println("Introdusca el idCategoria");
                        String idCategoria = sn.nextLine();
                        try {
                            productos = productoController.buscarPorCategoria(idCategoria);
                            recorrerProductos(productos);
                        } catch (ApiException | PersistenciaException e) {
                            System.err.println("Error al busca los productos por categoria.");
                        }
                        break;
                    case 3:
                        System.out.println("Introdusca el id de la marca.");
                        String idMarca = sn.nextLine();
                        try {
                            productos = productoController.buscarPorMarca(idMarca);
                            recorrerProductos(productos);
                        } catch (ApiException | PersistenciaException e) {
                            System.err.println("Error al busca los productos por marca.");
                        }
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
     * Funcion que recorre los productos y los muestra
     * @param productos lista de productos
     */
    private void recorrerProductos(ArrayList<Producto> productos) {
        for (Producto producto : productos) {
            System.out.println(producto.toString());
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
        ArrayList<Pedido> pedidos;
        ArrayList<Compra> compras;
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
                        System.out.println("Introduzca la cantidad que quiere añadir.");
                        float saldo = sn.nextFloat();
                        try {
                            usuarioController.añadirSaldo(cliente, saldo);
                            System.out.println("Saldo añadido carrectamente.");
                        } catch (PersistenciaException e) {
                            System.err.println("Error al añadir el saldo");
                        } catch (ApiException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 2:
                        realizarPedido();
                        break;
                    case 3:
                        menuProductos();
                        break;
                    case 4:        
                        try {
                            pedidos = pedidoController.obtenerListado(cliente.getDni());
                            System.out.println(pedidos.toString());
                        } catch (PersistenciaException e1) {
                            System.out.println("Error al obtener la lista de todos los pedidos");
                        }
                        break;
                    case 5:
                        try {
                            compras = compraController.obtenerListado();
                            System.out.println(compras.toString());
                        } catch (PersistenciaException e1) {
                            System.out.println("Error al obtener la lista de todas las compras");
                        }
                        break;
                    case 6:
                        Usuario nuevoCliente = registrar();
                        try {
                            usuarioController.modificar(nuevoCliente);
                            System.out.println("Se ha modificado correctemente.");
                        } catch (ApiException | PersistenciaException e) {
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
        System.out.println("Introdusca datos para realizar el pedido.");
        String idProducto = obtenerDato("Introduce el id del producto.");
        int unidades = Integer.parseInt(obtenerDato("unidades que quiere comprar."));
        pedidoController.realizarPedido(idProducto, unidades);
        System.out.println("Pedido realizado correctamente.");
    }

    /**
     * Menu que registrar un nuevo cliente
     * 
     * @return el cleinte creado
     */
    public Usuario registrar() {//REFACTORIZAR
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

        cliente = new Usuario(dni, nombre, apellidos, email, direccion, telefono, pais, codigoPostal, provincia, nombreUsuario, password, "Cliente", 0f);
        return cliente;
    }

    /**
     * Funcion que obtiene un dato y los devuelve
     * @param mensaje del dato que se va a obtener
     * @return el dato obtenido
     */
    public String obtenerDato(String mensaje) {//REFACTORIZAR
        String dato;

        System.out.println("Introduce " + mensaje);
        dato = sn.nextLine();

        return dato;
    }

}
