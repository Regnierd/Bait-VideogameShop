package es.iespuertodelacruz.bait.vista;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import es.iespuertodelacruz.bait.api.movimientos.Envio;
import es.iespuertodelacruz.bait.api.movimientos.Pedido;
import es.iespuertodelacruz.bait.api.personas.Usuario;
import es.iespuertodelacruz.bait.api.productos.Categoria;
import es.iespuertodelacruz.bait.api.productos.Marca;
import es.iespuertodelacruz.bait.api.productos.Producto;
import es.iespuertodelacruz.bait.controlador.movimientosController.EnvioController;
import es.iespuertodelacruz.bait.controlador.movimientosController.PedidoController;
import es.iespuertodelacruz.bait.controlador.personasController.UsuarioController;
import es.iespuertodelacruz.bait.controlador.productosController.CategoriaController;
import es.iespuertodelacruz.bait.controlador.productosController.MarcaController;
import es.iespuertodelacruz.bait.controlador.productosController.ProductoController;
import es.iespuertodelacruz.bait.exceptions.ApiException;
import es.iespuertodelacruz.bait.exceptions.PersistenciaException;

public class MenuAdmin extends MenuUsuario{
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
    EnvioController envioController;
    Usuario usuario;

    /**
     * Constructor basico de la clase
     * @throws PersistenciaException error a controlar
     */
    public MenuAdmin() throws PersistenciaException {
        sn = new Scanner(System.in);
        usuarioController = new UsuarioController();
        productoController = new ProductoController();
        categoriaController = new CategoriaController();
        marcaController = new MarcaController();
        pedidoController = new PedidoController();
        envioController = new EnvioController();
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
        try {
            usuario = usuarioController.login(nombreAcceso, password, "Admin");
            menuOpciones(usuario);
        } catch (ApiException | PersistenciaException e) {
            System.err.println("**"+e.getMessage()+"**");
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
                System.out.println("6. Modificar envios");
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
                    case 6:
                        menuEnvios();
                        break;
                    case 0:
                        salir = true;
                        break;
                    default:
                        System.err.println(ERROR_OPCION_ELEGIDA + "6**");
                        break;
                }
            }
        } catch (InputMismatchException | NumberFormatException ex) {
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
                        System.out.println("**"+e.getMessage()+"**");
                    }
                    break;
                case 2:
                    dni = obtenerDato("dni del usuario a eliminar.");
                    try {
                        usuarioController.eliminar(dni);
                        System.out.println("**Usuario eliminado correctamente.**");
                    } catch (PersistenciaException | ApiException e) {
                        System.out.println("**"+e.getMessage()+"**");
                    }
                    break;
                case 3:
                    Usuario nuevUsuario;
                    nuevUsuario = registrarUsuario();
                    try {
                        usuarioController.modificar(nuevUsuario);
                        System.out.println("**Usuario modificado correctamente.**");
                    } catch (PersistenciaException | ApiException e) {
                        System.out.println("**"+e.getMessage()+"**");
                    }
                    break;
                case 4:
                    dni = obtenerDato("dni del usuario a buscar.");
                    try {
                        usuario = usuarioController.buscar(dni);
                        System.out.println(usuario.toString());
                    } catch (PersistenciaException | ApiException e) {
                        System.out.println("**"+e.getMessage()+"**");
                    }
                    break;
                case 5:
                    ArrayList<Usuario> usuarios;
                    try {
                        usuarios = usuarioController.obtenerListado();
                        System.out.println(listarUsuarios(usuarios));
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
     * Funcion que lista la listas de usuario
     * @param usuarios lista que va a recorrer
     * @return string con la lista 
     */
    private String listarUsuarios(ArrayList<Usuario> usuarios) {
        String lista = "";
        int contador = 1;
        for (Usuario usuario : usuarios) {
            lista += "*"+contador+"* | "+ usuario.toString() + "\n";
            contador++;
        }
        return lista;
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
        } catch (InputMismatchException ex) {
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
                        System.out.println("**"+e.getMessage()+"**");
                    }
                    break;
                case 2:
                    idProducto = obtenerDato("idProducto del producto.");
                    try {
                        productoController.eliminar(idProducto);
                        System.out.println("**Producto eliminado correctamente.**");
                    } catch (PersistenciaException | ApiException e) {
                        System.out.println("**"+e.getMessage()+"**");
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
                        System.out.println("**"+e.getMessage()+"**");
                    }
                    break;
                case 4:
                    idProducto = obtenerDato("idProducto.");
                    try {
                        Producto producto = productoController.buscar(idProducto);
                        System.out.println(producto.toString());
                    } catch (PersistenciaException | ApiException e) {
                        System.out.println("**"+e.getMessage()+"**");
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
                        System.err.println("**"+e.getMessage()+"**");
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
            System.err.println("**"+e.getMessage()+"**");
        }

        float precio = Float.parseFloat(obtenerDato("el precio por unidad de producto."));   

        String descripcion = obtenerDato("la descripcion del producto.");
        int  stock = Integer.parseInt(obtenerDato("el stock inicial del producto"));
        
        String idMarca = obtenerDato("el idMarca");
        try {
            marca = marcaController.buscar(idMarca);
        } catch (PersistenciaException | ApiException e) {
            System.err.println("**"+e.getMessage()+"**");
        }

        producto = new Producto(nombre, categoria, precio, descripcion, stock, marca);
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
                        Categoria categoria = new Categoria(nombre);
                        categoriaController.insertar(categoria);
                        System.out.println("**Categoria insertado correctamente**");
                    } catch (ApiException | PersistenciaException e) {
                        System.out.println("**"+e.getMessage()+"**");
                    }
                    break;
                case 2:
                    idCategoria = obtenerDato("idCategoria.");
                    try {
                        categoriaController.eliminar(idCategoria);
                        System.out.println("**Categoria eliminado correctamente.**");
                    } catch (PersistenciaException | ApiException e) {
                        System.out.println("**"+e.getMessage()+"**");
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
                        System.out.println("**"+e.getMessage()+"**");
                    }
                    break;
                case 4:
                    idCategoria = obtenerDato("idCategoria.");
                    try {
                        Categoria categoria = categoriaController.buscar(idCategoria);
                        System.out.println(categoria.toString());
                    } catch (PersistenciaException | ApiException e) {
                        System.out.println("**"+e.getMessage()+"**");
                    }
                    break;
                case 5:
                    ArrayList<Categoria> categorias;
                    try {
                        categorias = categoriaController.obtenerListado();
                        System.out.println(listarCategorias(categorias));
                    } catch (PersistenciaException | ApiException e) {
                        System.out.println("**"+e.getMessage()+"**");
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
     * Funcion que recorre un list de categorias
     * @param categorias que se van a recorrer
     * @return string con la informacion de la lista
     */
    private String listarCategorias(ArrayList<Categoria> categorias) {
        String lista = "";
        int contador = 1;
        for (Categoria categoria : categorias) {
            lista += "*"+contador+"* | "+ categoria.toString() + "\n";
            contador++;
        }
        return lista;
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
                        Marca marca = new Marca(nombre);
                        marcaController.insertar(marca);
                        System.out.println("**Marca insertado correctamente**");
                    } catch (ApiException | PersistenciaException e) {
                        System.out.println("**"+e.getMessage()+"**");
                    }
                    break;
                case 2:
                    idMarca = obtenerDato("idMarca.");
                    try {
                        marcaController.eliminar(idMarca);
                        System.out.println("**Marca eliminado correctamente.**");
                    } catch (PersistenciaException | ApiException e) {
                        System.out.println("**"+e.getMessage()+"**");
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
                        System.out.println("**"+e.getMessage()+"**");
                    }
                    break;
                case 4:
                    idMarca = obtenerDato("idMarca.");
                    try {
                        Marca marca = marcaController.buscar(idMarca);
                        System.out.println(marca.toString());
                    } catch (PersistenciaException | ApiException e) {
                        System.out.println("**"+e.getMessage()+"**");
                    }
                    break;
                case 5:
                    ArrayList<Marca> marcas;
                    try {
                        marcas = marcaController.obtenerListado();
                        System.out.println(listarMarcas(marcas));
                    } catch (PersistenciaException | ApiException e) {
                        System.out.println("**"+e.getMessage()+"**");
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
     * Funcion que recorre un list de marcas
     * @param marcas que se van a recorrer
     * @return string con la informacion de la lista
     */
    private String listarMarcas(ArrayList<Marca> marcas) {
        String lista = "";
        int contador = 1;
        for (Marca marca : marcas) {
            lista += "*"+contador+"* | "+ marca.toString() + "\n";
            contador++;
        }
        return lista;
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
                        System.out.println("**"+e.getMessage()+"**");
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
                        System.out.println("**"+e.getMessage()+"**");
                    }
                    break;
                case 3:
                    idPedido = obtenerDato("idPedido.");
                    try {
                        Pedido pedido = pedidoController.buscar(idPedido);
                        System.out.println(pedido.toString());
                    } catch (PersistenciaException | ApiException e) {
                        System.out.println("**"+e.getMessage()+"**");
                    }
                    break;
                case 4:
                    ArrayList<Pedido> pedidos;
                    try {
                        pedidos = pedidoController.obtenerListado();
                        System.out.println(listarPedidos(pedidos));
                    } catch (PersistenciaException | ApiException e) {
                        System.out.println("**"+e.getMessage()+"**");
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
     * Funcion que recorre un lista de pedidos
     * @param pedidos que se van a recorrer
     * @return string con la informacion de la lista
     */
    private String listarPedidos(ArrayList<Pedido> pedidos) {
        String lista = "";
        int contador = 1;
        for (Pedido pedido : pedidos) {
            lista += "*"+contador+"* | "+pedido.toString() + "\n";
            contador++;
        }
        return lista;
    }

    /**
     * Menu para realizar el CRUD de envios
     */
    private void menuEnvios() {
        Envio envio;
        int opcion;
        boolean salir = false;
        String idEnvio;
        while (!salir) {
            opcion = obtenerOpcionMovimientos("envios");
            switch (opcion) {
                case 1:
                    idEnvio = obtenerDato("el idEnvio");
                    try {
                        envioController.eliminar(idEnvio);
                        System.out.println("**Envio eliminado correctamente**");
                    } catch (PersistenciaException | ApiException e) {
                        System.err.println("**"+e.getMessage()+"**");
                    }
                    break;
                case 2:
                    idEnvio = obtenerDato("idEnvio");
                    envio = registrarEnvio();
                    envio.setIdEnvio(idEnvio);
                    try {
                        envioController.modificar(envio);
                        System.out.println("**Se ha modificado correctamente");
                    } catch (ApiException | PersistenciaException e) {
                        System.err.println("**"+e.getMessage()+"**");
                    }
                    break;
                case 3:
                    idEnvio = obtenerDato("idEnvio.");
                    try {
                        envio = envioController.buscar(idEnvio);
                        System.out.println(envio.toString());
                    } catch (PersistenciaException | ApiException e) {
                        System.out.println("**"+e.getMessage()+"**");
                    }
                    break;
                case 4:
                    ArrayList<Envio> envios;
                    try {
                        envios = envioController.obtenerListado();
                        System.out.println(listarEnvios(envios));
                    } catch (PersistenciaException | ApiException e) {
                        System.out.println("**"+e.getMessage()+"**");
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
     * Funcion que recorre un lista de envios
     * @param envios que se van a recorrer
     * @return string con la informacion de la lista
     */
    private String listarEnvios(ArrayList<Envio> envios) {
        String lista = "";
        int contador = 1;
        for (Envio envio : envios) {
            lista += "*"+contador+"* | "+envio.toString() + "\n";
            contador++;
        }
        return lista;
    }

    /**
     * Menu que registrar un nuevo envio
     * 
     * @return el envio creado
     */
    public Envio registrarEnvio() {
        Envio envio;
        System.out.println("Datos para el registro");
        Pedido pedido = registrarPedido();
        String fechaEnvio = obtenerDato("la fecha del envio[aa-mm-dd]");
        String estado = obtenerDato("estado del envio.");

        envio = new Envio(pedido, fechaEnvio, estado);

        return envio;

    }

    /**
     * Menu que registrar un nuevo usuario
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
            System.err.println("**"+e.getMessage()+"**");
        }
        
        String idProducto = obtenerDato("el idProducto");
        try {
            producto = productoController.buscar(idProducto);
        } catch (PersistenciaException | ApiException e) {
            System.err.println("**"+e.getMessage()+"**");
        }

        pedido = new Pedido(unidades, total, fechaPedido, usuario, producto);
        return pedido;
    }


}