package es.iespuertodelacruz.bait.vista;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import es.iespuertodelacruz.bait.api.movimientos.Pedido;
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
    private static final String ERROR_AL_OBTENER_LISTADO = "**Ha ocurrido un error al obtener la lista**";
    private static final String OPCION_SALIR = "0. Salir";
    private static final String ERROR_OPCION_ELEGIDA = "**Tiene que elegir una de las opciones del menu: 0 al ";
    private static final String ERROR_TIPO_DATO = "**El tipo de dato introducido es incorrecto.**";
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
        nombreAcceso = sn.nextLine();
        System.out.println("Introduce la password");
        password = sn.nextLine();
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
                System.out.println(OPCION_SALIR);
                opcion = sn.nextInt();
                sn.nextLine();
                switch (opcion) {
                    case 1:
                        menuUsuario();
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
                        System.err.println(ERROR_OPCION_ELEGIDA + "6**");
                        break;
                }
            }
        } catch (InputMismatchException ex) {
            System.out.println(ERROR_TIPO_DATO);
        }
    }

    /**
     * Menu para relizar el CRUD de usuario
     */
    private void menuUsuario() {
        int opcion;
        boolean salir = false;
        String dni;
        Usuario usuario;
        while (!salir) {
            opcion = obtenerOpcion("usuario");
            switch (opcion) {
                case 1:
                    try {
                        usuario = registrarUsuario();
                        usuarioController.insertar(usuario);
                        System.out.println("**Usuario insertado correctamente**");
                    } catch (ApiException | PersistenciaException e) {
                        System.out.println("**Error al insertar al cliente en la base de datos.**");
                    }
                    break;
                case 2:
                    dni = obtenerDato("dni del usuario a eliminar.");
                    try {
                        usuarioController.eliminar(dni);
                        System.out.println("**Usuario eliminado correctamente.**");
                    } catch (PersistenciaException | ApiException e) {
                        System.out.println("**Error al eliminar el usuario.**");
                    }
                    break;
                case 3:
                    Usuario nuevUsuario;
                    nuevUsuario = registrarUsuario();
                    try {
                        usuarioController.modificar(nuevUsuario);
                        System.out.println("**Usuario modificado correctamente.**");
                    } catch (PersistenciaException | ApiException e) {
                        System.out.println("**Error al modificar el usuario.**");
                    }
                    break;
                case 4:
                    dni = obtenerDato("dni del usuario a buscar.");
                    try {
                        usuario = usuarioController.buscar(dni);
                        System.out.println(usuario.toString());
                    } catch (PersistenciaException | ApiException e) {
                        System.out.println("**Error al buscar el usuario.**");
                    }
                    break;
                case 5:
                    ArrayList<Usuario> usuarios;
                    String informacionLista = "";
                    try {
                        usuarios = usuarioController.obtenerListado();
                        for (Usuario usuarioLista : usuarios) { //DECIDIR SI HACER ESTO O UN METODO
                            informacionLista += usuarioLista.toString(); 
                        }
                        System.out.println(informacionLista);
                    } catch (PersistenciaException | ApiException e) {
                        System.out.println(ERROR_AL_OBTENER_LISTADO);
                    }
                    break;
                case 0:
                    salir = true;
                    break;
                default:
                    System.err.println(ERROR_OPCION_ELEGIDA + "5**");
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
            System.out.println(OPCION_SALIR);
            opcion = sn.nextInt();
            sn.nextLine();
        } catch (InputMismatchException | NumberFormatException ex) {
            System.out.println(ERROR_TIPO_DATO);
        }

        return opcion;
    }

    /**
     * Funcion que obtiene una opcion para realizar un CRUD en la tabla que se pasa
     * el nombre en los parametros sin la opcion de insertar
     * 
     * @param tabla nombre de la tabla en que se va a realizar el CRUD
     * @return la opcion del CRUD a realizar
     */
    private int obtenerOpcionMovimientos(String tabla) {
        int opcion = -1;
        Scanner sn = new Scanner(System.in);
        try {
            System.out.println("Menu de opciones " + tabla);
            System.out.println("1. Eliminar " + tabla);
            System.out.println("2. Modificar " + tabla);
            System.out.println("3. Buscar " + tabla);
            System.out.println("4. Lista " + tabla);
            System.out.println(OPCION_SALIR);
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
            System.out.println("Menu de opciones Producto");
            System.out.println("1. Insertar Producto");
            System.out.println("2. Eliminar Producto");
            System.out.println("3. Modificar Producto");
            System.out.println("4. Buscar Producto");
            System.out.println("5. Lista Producto");
            System.out.println("6. Añadir stock ");
            System.out.println(OPCION_SALIR);
            opcion = sn.nextInt();
            sn.nextLine();
            switch (opcion) {
                case 1:
                    try {
                        Producto producto = registrarProducto();
                        productoController.insertar(producto);
                        System.out.println("**Producto insertado correctamente**");
                    } catch (ApiException | PersistenciaException e) {
                        System.out.println("**Error al insertar al producto en la base de datos.**");
                    }
                    break;
                case 2:
                    idProducto = obtenerDato("idProducto del producto.");
                    try {
                        productoController.eliminar(idProducto);
                        System.out.println("**Producto eliminado correctamente.**");
                    } catch (PersistenciaException | ApiException e) {
                        System.out.println("**Error al eliminar el producto.**");
                    }
                    break;
                case 3:
                    Producto productoAmodificar;
                    idProducto = obtenerDato("idProducto.");
                    productoAmodificar = registrarProducto();
                    productoAmodificar.setIdProducto(idProducto);
                    try {
                        productoController.modificar(productoAmodificar);
                        System.out.println("**Producto modificado correctamente.**");
                    } catch (PersistenciaException | ApiException e) {
                        System.out.println("**Error al modificar el producto.**");
                    }
                    break;
                case 4:
                    idProducto = obtenerDato("idProducto.");
                    try {
                        Producto producto = productoController.buscar(idProducto);
                        System.out.println(producto.toString());
                    } catch (PersistenciaException | ApiException e) {
                        System.out.println("**Error al buscar el producto.**");
                    }
                    break;
                case 5:
                    menuBuscarProductos();
                    break;
                case 6:
                    int cantidad;
                    idProducto = obtenerDato("idProducto.");
                    cantidad = Integer.parseInt(obtenerDato("introduce la cantidad de unidades a añadir."));
                    try {
                        productoController.aumentarStock(idProducto, cantidad);
                        System.out.println("**Stock aumentado correctamente**");
                    } catch (ApiException | PersistenciaException e) {
                        System.err.println("**Ha ocurrido un error al aumentar el stock del producto**");
                    }
                    break;
                case 0:
                    salir = true;
                    break;
                default:
                    System.err.println(ERROR_OPCION_ELEGIDA + "6");
            }
        }
    }

    /**
     * Funcion que pide los datos para crear un nuevo
     * producto y al crearlo lo devuelve
     * @return el producto creado
     */
    private Producto registrarProducto() {
        Producto producto = null;
        Categoria categoria = null;
        Marca marca = null;

        System.out.println("Datos para el registro");
        String nombre = obtenerDato("el nombre del producto.");

        String idCategoria = obtenerDato("el idCategoria");
        try {
            categoria = categoriaController.buscar(idCategoria);
        } catch (PersistenciaException | ApiException e) {
            System.err.println("**La categoria que has introducido no existe**");
        }

        float precio = Float.parseFloat(obtenerDato("el precio por unidad de producto."));   

        String descripcion = obtenerDato("la descripcion del producto.");
        int  stock = Integer.parseInt(obtenerDato("el stock inicial del producto"));
        
        String idMarca = obtenerDato("el idMarca");
        try {
            marca = marcaController.buscar(idMarca);
        } catch (PersistenciaException | ApiException e) {
            System.err.println("**La marca que has introducido no existe**");
        }

        producto = new Producto("null", nombre, categoria, precio, descripcion, stock, marca);
        return producto;
    }

    /**
     * Menu para realizar el CRUD categorias
     */
    private void menuCategorias() {
        int opcion;
        boolean salir = false;
        String idCategoria;
        String nombre;
        while (!salir) {
            opcion = obtenerOpcion("Categoria");
            switch (opcion) {
                case 1:
                    try {               
                        nombre = obtenerDato("el nombre");
                        Categoria categoria = new Categoria("null", nombre);
                        categoriaController.insertar(categoria);
                        System.out.println("**Categoria insertado correctamente**");
                    } catch (ApiException | PersistenciaException e) {
                        System.out.println("**Error al insertar la categoria en la base de datos.**");
                    }
                    break;
                case 2:
                    idCategoria = obtenerDato("idCategoria.");
                    try {
                        categoriaController.eliminar(idCategoria);
                        System.out.println("**Categoria eliminado correctamente.**");
                    } catch (PersistenciaException | ApiException e) {
                        System.out.println("**Error al eliminar la categoria.**");
                    }
                    break;
                case 3:
                    Categoria nuevaCategoria = null;
                    idCategoria = obtenerDato("idCategoria");
                    nombre = obtenerDato("el nombre");
                    nuevaCategoria = new Categoria(idCategoria, nombre);
                    try {    
                        categoriaController.modificar(nuevaCategoria);
                        System.out.println("**Categoria modificado correctamente.**");
                    } catch (PersistenciaException | ApiException e) {
                        System.out.println("**Error al modificar la categoria.**");
                    }
                    break;
                case 4:
                    idCategoria = obtenerDato("idCategoria.");
                    try {
                        Categoria categoria = categoriaController.buscar(idCategoria);
                        System.out.println(categoria.toString());
                    } catch (PersistenciaException | ApiException e) {
                        System.out.println("**Error al buscar la categoria.**");
                    }
                    break;
                case 5:
                    ArrayList<Categoria> categorias;
                    try {
                        categorias = categoriaController.obtenerListado();
                        System.out.println(categorias.toString());
                    } catch (PersistenciaException | ApiException e) {
                        System.out.println("**Ha ocurrido un error al obtener la lista**");
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


    /**
     * Menu para realizar el CRUD de marcas
     */
    private void menuMarcas() {
        int opcion;
        boolean salir = false;
        String idMarca;
        String nombre;
        while (!salir) {
            opcion = obtenerOpcion("marca");
            switch (opcion) {
                case 1:
                    try {
                        nombre = obtenerDato("el nombre");
                        Marca marca = new Marca("null", nombre);
                        marcaController.insertar(marca);
                        System.out.println("**Marca insertado correctamente**");
                    } catch (ApiException | PersistenciaException e) {
                        System.out.println("**Error al insertar la marca en la base de datos.**");
                    }
                    break;
                case 2:
                    idMarca = obtenerDato("idMarca.");
                    try {
                        marcaController.eliminar(idMarca);
                        System.out.println("**Marca eliminado correctamente.**");
                    } catch (PersistenciaException | ApiException e) {
                        System.out.println("**Error al eliminar la marca.**");
                    }
                    break;
                case 3:
                    Marca nuevaMarca = null;
                    idMarca = obtenerDato("idMarca");
                    nombre = obtenerDato("el nombre");
                    nuevaMarca = new Marca(idMarca, nombre);
                    try {
                        marcaController.modificar(nuevaMarca);
                        System.out.println("**Marca modificado correctamente.**");
                    } catch (PersistenciaException | ApiException e) {
                        System.out.println("**Error al modificar la marca.**");
                    }
                    break;
                case 4:
                    idMarca = obtenerDato("idMarca.");
                    try {
                        Marca marca = marcaController.buscar(idMarca);
                        System.out.println(marca.toString());
                    } catch (PersistenciaException | ApiException e) {
                        System.out.println("**Error al buscar la marca.**");
                    }
                    break;
                case 5:
                    ArrayList<Marca> marcas;
                    try {
                        marcas = marcaController.obtenerListado();
                        System.out.println(marcas.toString());
                    } catch (PersistenciaException | ApiException e) {
                        System.out.println("**Ha ocurrido un error al obtener la lista**");
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


    /**
     * Menu para realizar el CRUD pedidos
     */
    private void menuPedidos() {
        int opcion;
        boolean salir = false;
        String idPedido;
        while (!salir) {
            opcion = obtenerOpcionMovimientos("pedidos");
            switch (opcion) {
                case 1:
                    idPedido = obtenerDato("idPedido.");
                    try {
                        pedidoController.eliminar(idPedido);
                        System.out.println("**Pedido eliminado correctamente.**");
                    } catch (PersistenciaException | ApiException e) {
                        System.out.println("**Error al eliminar el pedido.**");
                    }         
                    break;
                case 2:
                    Pedido nuevoPedido = null;
                    idPedido = obtenerDato("idPedido");
                    nuevoPedido = registrarPedido();
                    try {
                        pedidoController.modificar(nuevoPedido);
                        System.out.println("**Marca modificado correctamente.**");
                    } catch (PersistenciaException | ApiException e) {
                        System.out.println("**Error al modificar el pedido.**");
                    }
                    break;
                case 3:
                    idPedido = obtenerDato("idPedido.");
                    try {
                        Pedido pedido = pedidoController.buscar(idPedido);
                        System.out.println(pedido.toString());
                    } catch (PersistenciaException | ApiException e) {
                        System.out.println("**Error al buscar el pedido.**");
                    }
                    break;
                case 4:
                    ArrayList<Pedido> pedidos;
                    try {
                        pedidos = pedidoController.obtenerListado();
                        System.out.println(pedidos.toString());
                    } catch (PersistenciaException | ApiException e) {
                        System.out.println("**Ha ocurrido un error al obtener la lista**");
                    }
                    break;
                case 0:
                    salir = true;
                    break;
                default:
                    System.err.println(ERROR_OPCION_ELEGIDA + "4");
            }
        }
    }

    /**
     * Menu que registrar un nuevo pedido
     * 
     * @return el pedido creado
     */
    public Pedido registrarPedido() {
        Pedido pedido = null;
        Usuario usuario = null;
        Producto producto = null;

        System.out.println("Datos para el registro");
        int unidades = Integer.parseInt(obtenerDato("las unidades"));
        float total = Float.parseFloat(obtenerDato("el total del pedido"));
        String fechaPedido = obtenerDato("la fecha del pedido");
        String dni = obtenerDato("el dni del usuario");
        try {
            usuario = usuarioController.buscar(dni);
        } catch (PersistenciaException | ApiException e) {
            System.err.println("**El usuario que has introducido no existe**");
        }
        
        String idProducto = obtenerDato("el idProducto");
        try {
            producto = productoController.buscar(idProducto);
        } catch (PersistenciaException | ApiException e) {
            System.err.println("**El producto que has introducido no existe**");
        }

        pedido = new Pedido("null", unidades, total, fechaPedido, usuario, producto);//CAMBIAR EL NULL
        return pedido;
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
        String rol = obtenerDato("el rol");

        cliente = new Usuario(dni, nombre, apellidos, email, direccion, telefono, pais, codigoPostal, provincia,
                nombreUsuario, password, rol, 0f);
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
                            System.out.println(productos.toString());//CAMBIAR A UN METODO 
                        } catch (ApiException | PersistenciaException e) {
                            System.err.println("**Error al busca los productos por nombre.**");
                        }
                        break;
                    case 2:
                        identificadorBusqueda = obtenerDato("el idCategoria");
                        try {
                            productos = productoController.buscarPorCategoria(identificadorBusqueda);
                            System.out.println(productos.toString());//CAMBIAR A UN METODO
                        } catch (ApiException | PersistenciaException e) {
                            System.err.println("**Error al busca los productos por categoria.**");
                        }
                        break;
                    case 3:
                        identificadorBusqueda = obtenerDato("el idMarca");
                        try {
                            productos = productoController.buscarPorMarca(identificadorBusqueda);
                            System.out.println(productos.toString());//CAMBIAR A UN METODO
                        } catch (ApiException | PersistenciaException e) {
                            System.err.println("**Error al busca los productos por marca.**");
                        }
                        break;
                    case 4:
                        try {
                            productos = productoController.obtenerListado();
                            System.out.println(productos.toString());//CAMBIAR A UN METOD 
                        } catch (PersistenciaException | ApiException e) {
                            System.out.println(ERROR_AL_OBTENER_LISTADO);
                        }
                        break;
                    case 0:
                        salir = true;
                        break;
                    default:
                        System.err.println(ERROR_OPCION_ELEGIDA + "4");
                }
            }
        } catch (InputMismatchException ex) {
            System.out.println(ERROR_TIPO_DATO);
        }
    }

}