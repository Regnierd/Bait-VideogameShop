package es.iespuertodelacruz.bait.vista;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import es.iespuertodelacruz.bait.api.movimientos.Envio;
import es.iespuertodelacruz.bait.api.movimientos.Pedido;
import es.iespuertodelacruz.bait.api.personas.Usuario;
import es.iespuertodelacruz.bait.api.productos.Producto;
import es.iespuertodelacruz.bait.controlador.movimientosController.EnvioController;
import es.iespuertodelacruz.bait.controlador.movimientosController.PedidoController;
import es.iespuertodelacruz.bait.controlador.personasController.UsuarioController;
import es.iespuertodelacruz.bait.controlador.productosController.ProductoController;
import es.iespuertodelacruz.bait.exceptions.ApiException;
import es.iespuertodelacruz.bait.exceptions.PersistenciaException;

public class MenuUsuario {
    private static final String ERROR_AL_OBTENER_LISTADO = "**Ha ocurrido un error al obtener la lista**";
    private static final String ERROR_TIPO_DATO = "**El tipo de dato introducido es incorrecto.**";
    private static final String TIPO_DATO_INCORRECTO = "El tipo de dato introducido es incorrecto.";
    private static final String ELEGIR_OPCION_DEL_MENU = "Tiene que elegir una de las opciones del menu: 0 al ";
    private static final String OPCION_SALIR = "0. Salir";

    private Scanner sn;
    private UsuarioController usuarioController;
    private PedidoController pedidoController;
    private ProductoController productoController;
    private EnvioController envioController;
    /**
     * Constructor basico del menu del usuario
     * @throws PersistenciaException error a controlar
     */
    public MenuUsuario() throws PersistenciaException {
        sn = new Scanner(System.in);
        usuarioController = new UsuarioController();
        pedidoController = new PedidoController();
        productoController = new ProductoController();
        envioController = new EnvioController();
    }

    /**
     * Menu basico para el usuario
     */
    public void menuPrincial() {
        boolean salir = false;
        int opcion;
        Usuario usuario;
        try {
            while (!salir) {
                System.out.println("Menu principal del usuario");
                System.out.println("1. Registrarse");
                System.out.println("2. Iniciar sesion");
                System.out.println("3. Accerder sin cuenta");
                System.out.println(OPCION_SALIR);
                System.out.println("Selecciona opcion:");
                opcion = sn.nextInt();
                sn.nextLine();
                switch (opcion) {
                    case 1:
                        usuario = registrarUsuario();
                        try {
                            usuarioController.insertar(usuario);
                            System.out.println("**Se ha registrado correctamente.**");
                            menuOpciones(usuario);
                        } catch (ApiException | PersistenciaException e) {
                            System.out.println("**"+e.getMessage()+"**");
                        }
                        break;
                    case 2:
                        try {
                            usuario = validarUsuario();
                            System.out.println("**Sesion iniciada correctamente.**");
                            menuOpciones(usuario);
                        } catch (ApiException | PersistenciaException e) {
                            System.out.println("**"+e.getMessage()+"**");
                        }
                        break;
                    case 3:
                        menuBuscarProductos();
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
     * Funcion que busca un usuario a partir de un nombre de usuario y password y
     * lo devuevle si existe
     * 
     * @return el usuario si existe
     * @throws ApiException error a controlar
     * @throws PersistenciaException
     */
    private Usuario validarUsuario() throws ApiException, PersistenciaException {
        String nombreUsuario;
        String password;
        Usuario usuario = null;
        System.out.println("Login Usuario");
        System.out.println("Introduce el nombre de usuario:");
        nombreUsuario = sn.nextLine();
        System.out.println("Intrduce la password:");
        password = sn.nextLine();

        usuario = usuarioController.login(nombreUsuario, password, "Cliente");

        return usuario;
    }

    /**
     * Menu para buscar productos por nombre, categoria y marca
     */
    public void menuBuscarProductos() {
        boolean salir = false;
        int opcion;
        ArrayList<Producto> productos;
        String identificadorBusqueda;
        try {
            while (!salir) {
                System.out.println("Buscar producto");
                System.out.println("1. Buscar producto por nombre");
                System.out.println("2. Buscar producto por categoria");
                System.out.println("3. Buscar producto por marca");
                System.out.println("4. Listar todos");
                System.out.println(OPCION_SALIR);
                System.out.println("Selecciona opcion:");
                opcion = sn.nextInt();
                sn.nextLine();

                switch (opcion) {
                    case 1:
                        identificadorBusqueda = obtenerDato("el nombre.");
                        try {
                            productos = productoController.buscarPorNombre(identificadorBusqueda);
                            System.out.println(listarProductos(productos)); 
                        } catch (ApiException | PersistenciaException e) {
                            System.err.println("**"+e.getMessage()+"**");
                        }
                        break;
                    case 2:
                        identificadorBusqueda = obtenerDato("el idCategoria");
                        try {
                            productos = productoController.buscarPorCategoria(identificadorBusqueda);
                            System.out.println(listarProductos(productos));
                        } catch (ApiException | PersistenciaException e) {
                            System.err.println("**"+e.getMessage()+"**");
                        }
                        break;
                    case 3:
                        identificadorBusqueda = obtenerDato("el idMarca");
                        try {
                            productos = productoController.buscarPorMarca(identificadorBusqueda);
                            System.out.println(listarProductos(productos));
                        } catch (ApiException | PersistenciaException e) {
                            System.err.println("**"+e.getMessage()+"**");
                        }
                        break;
                    case 4:
                        try {
                            productos = productoController.obtenerListado();
                            System.out.println(listarProductos(productos)); 
                        } catch (PersistenciaException | ApiException e) {
                            System.out.println(ERROR_AL_OBTENER_LISTADO);
                        }
                        break;
                    case 0:
                        salir = true;
                        break;
                    default:
                        System.err.println(ERROR_AL_OBTENER_LISTADO + "4");
                }
            }
        } catch (InputMismatchException ex) {
            System.out.println(ERROR_TIPO_DATO);
        }
    }
    /**
     * Funcion para listas lista de productos
     * @param productos lista que se va a recorrer
     * @return string con la lista recorrida
     */
    private String listarProductos(ArrayList<Producto> productos) {
        String lista = "";
        int contador = 1;
        for (Producto producto : productos) {
            lista += "*"+contador+"* | "+producto.toString() + "\n";
            contador++;
        }
        return lista;
    }

    /**
     * Menu de opciones que puede realizar el usuario
     * 
     * @param usuario con la sesion actual
     */
    private void menuOpciones(Usuario usuario) {
        boolean salir = false;
        int opcion;
        ArrayList<Pedido> pedidos;
        ArrayList<Envio> envios;
        try {
            while (!salir) {
                System.out.println("Menu de opciones usuario");
                System.out.println("1. Añadir saldo");
                System.out.println("2. Realizar pedido");
                System.out.println("3. Buscar productos");
                System.out.println("4. Ver pedidos");
                System.out.println("5. Ver envios");
                System.out.println("6. Ver mis datos");
                System.out.println("7. Editar mis datos");
                System.out.println("0. Salir");
                opcion = sn.nextInt();
                sn.nextLine();
                switch (opcion) {
                    case 1:
                        System.out.println("Introduzca la cantidad que quiere añadir.");
                        float saldo = sn.nextFloat();
                        try {
                            usuarioController.añadirSaldo(usuario, saldo);
                            System.out.println("**Saldo añadido carrectamente.**");
                        } catch (PersistenciaException e) {
                            System.err.println("**"+e.getMessage()+"**");
                        } catch (ApiException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 2:
                        realizarPedido(usuario);
                        break;
                    case 3:
                        menuBuscarProductos();
                        break;
                    case 4:        
                        try {
                            pedidos = pedidoController.obtenerListado(usuario.getDni());
                            System.out.println(pedidos.toString());
                        } catch (PersistenciaException | ApiException e) {
                            System.out.println("**"+e.getMessage()+"**");
                        }
                        break;
                    case 5:
                        try {
                            envios = envioController.obtenerListado(usuario.getDni());
                            System.out.println(envios.toString());
                        } catch (PersistenciaException | ApiException e) {
                            System.out.println("**"+e.getMessage()+"**");
                        }
                        break;
                    case 6:
                        try {
                            usuario = usuarioController.buscar(usuario.getDni());
                            System.out.println(usuario.toString());
                        } catch (PersistenciaException | ApiException e) {
                            System.out.println("**"+e.getMessage()+"**");
                        } 
                        break;
                    case 7:
                        Usuario nuevoUsuario = registrarUsuario();
                        try {
                            usuarioController.modificar(nuevoUsuario);
                            System.out.println("**Se ha modificado correctemente.**");
                        } catch (ApiException | PersistenciaException e) {
                            System.err.println("**"+e.getMessage()+"**");
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
    private void realizarPedido(Usuario usuario) {
        System.out.println("Introduzca datos para realizar el pedido.");
        String idProducto = obtenerDato("el id del producto.");
        int unidades = Integer.parseInt(obtenerDato("unidades que quiere comprar."));
        try {
            pedidoController.realizarPedido(usuario ,idProducto, unidades);
            System.out.println("**Pedido realizado correctamente**");
        } catch (PersistenciaException | ApiException e) {
            System.err.println("**"+e.getMessage()+"**");
        }
    }

    /**
     * Menu que registrar un nuevo usuario
     * 
     * @return el cliente creado
     */
    public Usuario registrarUsuario() {
        Usuario usuario = null;
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

        usuario = new Usuario(dni, nombre, apellidos, email, direccion, telefono, pais, codigoPostal, provincia, nombreUsuario, password, "Cliente", 0f);
        return usuario;
    }

    /**
     * Funcion que obtiene un dato y los devuelve
     * @param mensaje del dato que se va a obtener
     * @return el dato obtenido
     */
    public String obtenerDato(String mensaje) {
        String dato;

        System.out.println("Introduce " + mensaje);
        dato = sn.nextLine();

        return dato;
    }

}
