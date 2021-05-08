package es.iespuertodelacruz.bait.vista;

import java.util.*;

import es.iespuertodelacruz.bait.api.personas.Cliente;
import es.iespuertodelacruz.bait.api.productos.Producto;
import es.iespuertodelacruz.bait.controlador.personasController.ClienteController;
import es.iespuertodelacruz.bait.controlador.productosController.ProductoController;

/**
 * Menu que permite a un cliente registrarse o iniciar sesion
 */
public class MenuCliente {
    Scanner sn = new Scanner(System.in);
    ClienteController clienteController = new ClienteController();
    ProductoController productoController = new ProductoController();

    public void menuLogin() {
        boolean salir = false;
        int opcion;
        try {
            while (!salir) {
                System.out.println("1. Registrarse.");
                System.out.println("2. Iniciar sesion.");
                System.out.println("3. Salir");
                System.out.println("Selecciona opcion:");
                opcion = sn.nextInt();
                sn.nextLine();
                switch (opcion) {
                    case 1:
                        try {
                            menuRegistro();
                            System.out.println("Se ha registrado correctamente.");
                        } catch (Exception e) {
                            System.out.println("Error al realizar el registro");
                        }

                        break;
                    case 2:
                        try {
                            Cliente cliente = menuInicio();
                            System.out.println("Sesion iniciada correctamente.");
                            menuOpciones(cliente);
                        } catch (Exception e) {
                            System.out.println("El usuario o la contraseñas no son correctos.");
                        }
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

    /**
     * Menu de opciones que tiene el cliente tras iniciar sesion
     * @param cliente cliente que ha iniciado sesion
     */
    private void menuOpciones(Cliente cliente) {
        boolean salir = false;
        int opcion;
        try {
            while (!salir) {
                System.out.println("1. Comprar Producto.");
                System.out.println("2. Buscar producto por tipo.");
                System.out.println("3. Buscar producto por marca.");
                System.out.println("4. Buscar producto por nombre.");
                System.out.println("5. Ver compras");
                System.out.println("6. Ver facturas");
                System.out.println("7. Salir.");
                opcion = sn.nextInt();
                sn.nextLine();
                switch (opcion) {
                    case 1:
                        comprarProducto(cliente);
                        break;
                    case 2:
                        buscarPorCategoria();
                        break;
                    case 3:
                        buscarPorMarca();
                        break;
                    case 4:
                        buscarPorNombre();
                        break;
                    case 5:
                        verCompras(cliente);
                        break;
                    case 6:
                        verFacturas(cliente);
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

    /**
     * Funcion que devuelve un cliente si exite a partir del nombreUsuario y contraseña
     * pasados por el usuario
     * @return el cliente si existe
     */
    private Cliente menuInicio() {
        String nombreUsuario;
        String password;
        Cliente cliente;

        System.out.println("Introduce el nombre de usuario");
        nombreUsuario = sn.nextLine();
        System.out.println("Introduce la contraseña");
        password = sn.nextLine();

        cliente = clienteController.buscarCliente(nombreUsuario, password);

        return cliente;
    }

    /**
     * Metodo que registra un nuevo cliente solicitando los datos al usuario
     */
    private void menuRegistro() {
        String dni, nombre, apellidos, direccion, telefono, codigoPostal, provincia, nombreUsuario, password;
        Cliente cliente;

        System.out.println("Datos para Registrarse");

        System.out.println("Introduce tu dni:");
        dni = sn.nextLine();
        System.out.println("Introduce tu nombre:");
        nombre = sn.nextLine();
        System.out.println("Introduce tu apellidos:");
        apellidos = sn.nextLine();
        System.out.println("Introduce tu direccion:");
        direccion = sn.nextLine();
        System.out.println("Introduce tu telefono: ");
        telefono = sn.nextLine();
        System.out.println("Introduce tu codigo postal: ");
        codigoPostal = sn.nextLine();
        System.out.println("Introduce tu provincia:");
        provincia = sn.nextLine();
        System.out.println("Introduce tu nombre de usuario:");
        nombreUsuario = sn.nextLine();
        System.out.println("Introduce tu contraseña:");
        password = sn.nextLine();
        System.out.println("Introduce tu saldo:");
        password = sn.nextLine();

        cliente = new Cliente(dni, nombre, apellidos, direccion, telefono, codigoPostal, provincia, nombreUsuario, password, 0f);
        clienteController.insertar(cliente);
    }

    /**
     * Metodo realiza un compra a partir del identificar del producto pasado por teclado
     * por el usuario
     * @param cliente con sesion actual
     */
    private void comprarProducto(Cliente cliente) {
        String idProducto;
        System.out.println("Introduce el identificador del producto.");
        idProducto = sn.nextLine();

        clienteController.comprar(idProducto, cliente);
    }

    /**
     * Metodo que busca y devuelve una lista de productos filtrados
     * por una categoria que se pasa por teclado
     */
    private void buscarPorCategoria() {
        String categoria;
        ArrayList<Producto> productos;

        System.out.println("Introduce la catagoria del producto");
        categoria = sn.nextLine();

        productos = productoController.buscarPorCategoria(categoria);

        for (Producto producto : productos) {
            System.out.println(producto.toString());
        }
    }

    /**
     * Metodo que busca y devuelve una lista de productos filtrados 
     * por la marca que se pasa por teclado
     */
    private void buscarPorMarca() {
        String marca;
        ArrayList<Producto> productos;

        System.out.println("Introduce la marca del producto");
        marca = sn.nextLine();

        productos = productoController.buscarPorMarca(marca);

        for (Producto producto : productos) {
            System.out.println(producto.toString());
        }
    }

    /**
     * Metodo que busca y devuelve una lista de productos filtrados por 
     * una nombre pasado por parametros
     */
    private void buscarPorNombre() {
        String nombre;
        ArrayList<Producto> productos;

        System.out.println("Introduce la catagoria del producto");
        nombre = sn.nextLine();

        productos = productoController.buscarPorNombre(nombre);

        for (Producto producto : productos) {
            System.out.println(producto.toString());
        }
    }

    /**
     * Metodo que devuelve todas las facturas del cliente
     * @param cliente con sesion acutal
     */
    private void verFacturas(Cliente cliente) {

    }

    /**
     * Metodo que devuelve todas las compras del cliente
     * @param cliente con sesion actual
     */
    private void verCompras(Cliente cliente) {
    }

}
