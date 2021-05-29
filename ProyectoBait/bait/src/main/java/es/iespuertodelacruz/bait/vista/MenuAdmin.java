package es.iespuertodelacruz.bait.vista;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import es.iespuertodelacruz.bait.api.personas.Usuario;
import es.iespuertodelacruz.bait.api.productos.Categoria;
import es.iespuertodelacruz.bait.api.productos.Marca;
import es.iespuertodelacruz.bait.api.productos.Producto;
import es.iespuertodelacruz.bait.controlador.movimientosController.PedidoController;
import es.iespuertodelacruz.bait.controlador.personasController.UsuarioController;
import es.iespuertodelacruz.bait.controlador.productosController.CategoriaController;
import es.iespuertodelacruz.bait.controlador.productosController.MarcaController;
import es.iespuertodelacruz.bait.controlador.productosController.ProductoController;
import es.iespuertodelacruz.bait.exceptions.ApiException;
import es.iespuertodelacruz.bait.exceptions.PersistenciaException;

public class MenuAdmin {
    private static final String ERROR_OPCION_ELEGIDA = "Tiene que elegir una de las opciones del menu: 0 al ";
    private static final String ERROR_TIPO_DATO = "El tipo de dato introducido es incorrecto.";
    Scanner sn;
    UsuarioController usuarioController;
    ProductoController productoController;
    CategoriaController categoriaController;
    MarcaController marcaController;
    PedidoController pedidoController;
    Usuario usuario;

    /**
     * Constructor basico de la clase
     * @throws PersistenciaException
     */
    public MenuAdmin() throws PersistenciaException {
        sn = new Scanner(System.in);
        usuarioController = new UsuarioController();
        productoController = new ProductoController();
        categoriaController = new CategoriaController();
        marcaController = new MarcaController();
        pedidoController = new PedidoController();
    }

    /**
     * Metodo que valida nombre y contrasenia del admin
     */
    public void menuPrincial() {
        String nombreAcceso;
        String password;
        System.out.println("Login Admin");
        System.out.println("Introduce nombre acceso.");
        nombreAcceso = sn.next();
        System.out.println("Introduce la password");
        password = sn.next();
        sn.nextLine();
        try {
            usuario = usuarioController.login(nombreAcceso, password, "Admin");
            menuOpciones(usuario);
        } catch (ApiException | PersistenciaException ex) {
            System.err.println("El nombre de acceso o la password son incorrectos.");
        }
    }

    /**
     * Metodo que muestra el menu de opciones que puede realizar el administrador
     */
    private void menuOpciones(Usuario admin) {
        boolean salir = false;
        int opcion;

        try {
            while (!salir) {
                System.out.println("Menu opciones administrador");
                System.out.println("1. Modificar Usuario");
                System.out.println("2. Modificar productos");
                System.out.println("3. Modificar categorias");
                System.out.println("4. Modificar marcas");
                System.out.println("5. Modificar pedidos");
                System.out.println("0. Salir");
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
                    case 0:
                        salir = true;
                        break;
                    default:
                        System.err.println(ERROR_OPCION_ELEGIDA + "6");
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
        String dni;
        while (!salir) {
            opcion = obtenerOpcion("cliente");
            switch (opcion) {
                case 1:
                    try {
                        Usuario usuario = registrarUsuario();
                        usuarioController.insertar(usuario);
                        System.out.println("Usuario insertado correctamente");
                    } catch (ApiException | PersistenciaException e) {
                        System.out.println("Error al insertar al cliente en la base de datos.");
                    }
                    break;
                case 2:
                    dni = obtenerDato("dni del usuario a eliminar.");
                    try {
                        usuarioController.eliminar(dni);
                        System.out.println("Usuario eliminado correctamente.");
                    } catch (PersistenciaException | ApiException e) {
                        System.out.println("Error al eliminar el usuario.");
                    }
                    break;
                case 3:
                    Usuario nuevUsuario;
                    nuevUsuario = registrarUsuario();
                    try {
                        usuarioController.modificar(nuevUsuario);
                        System.out.println("Usuario modificado correctamente.");
                    } catch (PersistenciaException | ApiException e) {
                        System.out.println("Error al modificar el usuario.");
                    }
                    break;
                case 4:
                    dni = obtenerDato("dni del usuario a buscar.");
                    try {
                        Usuario usuario = usuarioController.buscar(dni);
                        System.out.println(usuario.toString());
                    } catch (PersistenciaException | ApiException e) {
                        System.out.println("Error al buscar el usuario.");
                    }
                    break;
                case 5:
                    ArrayList<Usuario> usuarios;
                    try {
                        usuarios = usuarioController.obtenerListado();
                        System.out.println(usuarios.toString());
                    } catch (PersistenciaException | ApiException e) {
                        System.out.println("Ha ocurrido un error al obtener la lista");
                    }
                case 0:
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
            System.out.println("Menu de opciones " + tabla);
            System.out.println("1. Insertar " + tabla);
            System.out.println("2. Eliminar " + tabla);
            System.out.println("3. Modificar " + tabla);
            System.out.println("4. Buscar " + tabla);
            System.out.println("5. Lista " + tabla);
            System.out.println("0. Salir");
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
        String idProducto;
        while (!salir) {
            opcion = obtenerOpcion("producto.");
            switch (opcion) {
                case 1:
                    try {
                        Producto producto = registrarProducto();
                        productoController.insertar(producto);
                        System.out.println("Producto insertado correctamente");
                    } catch (ApiException | PersistenciaException e) {
                        System.out.println("Error al insertar al producto en la base de datos.");
                    }
                    break;
                case 2:
                    idProducto = obtenerDato("idProducto del producto.");
                    try {
                        productoController.eliminar(idProducto);
                        System.out.println("Producto eliminado correctamente.");
                    } catch (PersistenciaException | ApiException e) {
                        System.out.println("Error al eliminar el producto.");
                    }
                    break;
                case 3:
                    Producto nuevoProducto;
                    nuevoProducto = registrarProducto();
                    try {
                        productoController.modificar(nuevoProducto);
                        System.out.println("Producto modificado correctamente.");
                    } catch (PersistenciaException | ApiException e) {
                        System.out.println("Error al modificar el producto.");
                    }
                    break;
                case 4:
                    idProducto = obtenerDato("idProdcuto.");
                    try {
                        Producto producto = productoController.buscar(idProducto);
                        System.out.println(producto.toString());
                    } catch (PersistenciaException e) {
                        System.out.println("Error al buscar el producto.");
                    }
                    break;
                case 5:
                    menuBuscarProductos();
                case 0:
                    salir = true;
                    break;
                default:
                    System.err.println(ERROR_OPCION_ELEGIDA + "5");
            }
        }
    }

    private Producto registrarProducto() {
        return null;
    }

    /**
     * Menu para realizar el CRUD categorias
     */
    private void menuCategorias() {
        int opcion;
        boolean salir = false;
        String idCategoria;
        while (!salir) {
            opcion = obtenerOpcion("Categoria");
            switch (opcion) {
                case 1:
                    try {
                        Categoria categoria = registrarCategoria();
                        categoriaController.insertar(categoria);
                        System.out.println("Categoria insertado correctamente");
                    } catch (ApiException | PersistenciaException e) {
                        System.out.println("Error al insertar la categoria en la base de datos.");
                    }
                    break;
                case 2:
                    idCategoria = obtenerDato("idCategoria.");
                    try {
                        categoriaController.eliminar(idCategoria);
                        System.out.println("Categoria eliminado correctamente.");
                    } catch (PersistenciaException | ApiException e) {
                        System.out.println("Error al eliminar la categoria.");
                    }
                    break;
                case 3:
                    Categoria nuevaCategoria;
                    nuevaCategoria = registrarCategoria();
                    try {
                        categoriaController.modificar(nuevaCategoria);
                        System.out.println("Categoria modificado correctamente.");
                    } catch (PersistenciaException | ApiException e) {
                        System.out.println("Error al modificar la categoria.");
                    }
                    break;
                case 4:
                    idCategoria = obtenerDato("idCategoria.");
                    try {
                        Categoria categoria = categoriaController.buscar(idCategoria);
                        System.out.println(categoria.toString());
                    } catch (PersistenciaException e) {
                        System.out.println("Error al buscar la categoria.");
                    }
                    break;
                case 5:
                    ArrayList<Categoria> categorias;
                    try {
                        categorias = categoriaController.obtenerListado();
                        System.out.println(categorias.toString());
                    } catch (PersistenciaException | ApiException e) {
                        System.out.println("Ha ocurrido un error al obtener la lista");
                    }
                    break;
                case 0:
                    salir = true;
                    break;
                default:
                    System.err.println(ERROR_OPCION_ELEGIDA + "5");
            }
        }
    }

    private Categoria registrarCategoria() {
        return null;
    }

    /**
     * Menu para realizar el CRUD de marcas
     */
    private void menuMarcas() {
        int opcion;
        boolean salir = false;
        String idMarca;
        while (!salir) {
            opcion = obtenerOpcion("marca");
            switch (opcion) {
                case 1:
                    try {
                        Marca marca = registrarMarca();
                        marcaController.insertar(marca);
                        System.out.println("Marca insertado correctamente");
                    } catch (ApiException | PersistenciaException e) {
                        System.out.println("Error al insertar la marca en la base de datos.");
                    }
                    break;
                case 2:
                    idMarca = obtenerDato("idMarca.");
                    try {
                        marcaController.eliminar(idMarca);
                        System.out.println("Marca eliminado correctamente.");
                    } catch (PersistenciaException | ApiException e) {
                        System.out.println("Error al eliminar la marca.");
                    }
                    break;
                case 3:
                    Marca nuevaMarca;
                    nuevaMarca = registrarMarca();
                    try {
                        marcaController.modificar(nuevaMarca);
                        System.out.println("Marca modificado correctamente.");
                    } catch (PersistenciaException | ApiException e) {
                        System.out.println("Error al modificar la marca.");
                    }
                    break;
                case 4:
                    idMarca = obtenerDato("idMarca.");
                    try {
                        Marca marca = marcaController.buscar(idMarca);
                        System.out.println(marca.toString());
                    } catch (PersistenciaException e) {
                        System.out.println("Error al buscar la marca.");
                    }
                    break;
                case 5:
                    ArrayList<Categoria> categorias;
                    try {
                        categorias = categoriaController.obtenerListado();
                        System.out.println(categorias.toString());
                    } catch (PersistenciaException | ApiException e) {
                        System.out.println("Ha ocurrido un error al obtener la lista");
                    }
                    break;
                case 0:
                    salir = true;
                    break;
                default:
                    System.err.println(ERROR_OPCION_ELEGIDA + "5");
            }
        }
    }

    private Marca registrarMarca() {
        return null;
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
                case 0:
                    salir = true;
                    break;
                default:
                    System.err.println(ERROR_OPCION_ELEGIDA + "4");
            }
        }
    }

    /**
     * Menu que registrar un nuevo usuario
     * 
     * @return el cleinte creado
     */
    public Usuario registrarUsuario() {
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

        cliente = new Usuario(dni, nombre, apellidos, email, direccion, telefono, pais, codigoPostal, provincia,
                nombreUsuario, password, "Cliente", 0f);
        return cliente;
    }

    /**
     * Funcion que obtiene un dato y los devuelve
     * 
     * @param mensaje del dato que se va a obtener
     * @return el dato obtenido
     */
    public String obtenerDato(String mensaje) {
        String dato;

        System.out.println("Introduce " + mensaje);
        dato = sn.nextLine();

        return dato;
    }

    /**
     * Menu para buscar productos por nombre, categoria y marca
     */
    public void menuBuscarProductos() {
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
                            System.out.println(productos.toString());
                        } catch (ApiException | PersistenciaException e) {
                            System.err.println("Error al busca los productos por nombre.");
                        }
                        break;
                    case 2:
                        System.out.println("Introdusca el idCategoria");
                        String idCategoria = sn.nextLine();
                        try {
                            productos = productoController.buscarPorCategoria(idCategoria);
                            System.out.println(productos.toString());
                        } catch (ApiException | PersistenciaException e) {
                            System.err.println("Error al busca los productos por categoria.");
                        }
                        break;
                    case 3:
                        System.out.println("Introdusca el id de la marca.");
                        String idMarca = sn.nextLine();
                        try {
                            productos = productoController.buscarPorMarca(idMarca);
                            System.out.println(productos.toString());
                        } catch (ApiException | PersistenciaException e) {
                            System.err.println("Error al busca los productos por marca.");
                        }
                        break;
                    case 0:
                        salir = true;
                        break;
                    default:
                        System.err.println(ERROR_OPCION_ELEGIDA + "3");
                }
            }
        } catch (InputMismatchException ex) {
            System.out.println(ERROR_TIPO_DATO);
        }
    }

}