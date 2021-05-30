package es.iespuertodelacruz.bait.controladorTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

public class PedidoControllerTest {

    private static final String IDPEDIDO = "ped_idPedido";
    private static final String IDPEDIDO_INEXISTENTE = "ped_idPedidoInexistente";
    private static final String DNI = "43387932L";
    private static final String IDPRODUCTO = "pro_idProducto";
    private static final String NOMBRE_USUARIO = "usuario";
    private static final String IDCATEGORIA = "idCategoria";
    private static final String IDMARCA = "idMarca";

    PedidoController pedidoController;
    UsuarioController usuarioController;
    CategoriaController categoriaController;
    MarcaController marcaController;
    ProductoController productoController;
    EnvioController envioController;

    Pedido pedido;
    Usuario usuario;
    Producto producto;
    Categoria categoria;
    Marca marca;

    @BeforeEach
    public void setUp() {
        try {
            if (pedidoController == null) {
                pedidoController = new PedidoController();
            }
            if (usuarioController == null) {
                usuarioController = new UsuarioController();
            }
            if (categoriaController == null) {
                categoriaController = new CategoriaController();
            }
            if (marcaController == null) {
                marcaController = new MarcaController();
            }
            if (productoController == null) {
                productoController = new ProductoController();
            }
            if (envioController == null) {
                envioController = new EnvioController();
            }
        } catch (PersistenciaException e) {
            fail(e.getMessage());
        }

        usuario = new Usuario(DNI, "nombre", "apellidos", "email", "direccion", "telefono", "pais", "codigoPostal",
                "provincia", NOMBRE_USUARIO, "password", "Admin", 0f);
        categoria = new Categoria(IDCATEGORIA, "nombre");
        marca = new Marca(IDMARCA, "nombre");
        producto = new Producto(IDPRODUCTO, "nombre", categoria, 10f, "descripcion", 15, marca);
        pedido = new Pedido(IDPEDIDO, 2, 20, "20-5-2021", usuario, producto);

        try {
            usuarioController.insertar(usuario);
            categoriaController.insertar(categoria);
            marcaController.insertar(marca);
            productoController.insertar(producto);
            pedidoController.insertar(pedido);
        } catch (PersistenciaException | ApiException e) {
            fail(e.getMessage());
        }

    }

    @AfterEach
    public void after() {
        try {
            usuarioController.eliminar(DNI);
            categoriaController.eliminar(IDCATEGORIA);
            marcaController.eliminar(IDMARCA);
            productoController.eliminar(IDPRODUCTO);
            pedidoController.eliminar(IDPEDIDO);
        } catch (PersistenciaException | ApiException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void validarPedido() {
        Pedido pedidoVacio = new Pedido();
        try {
            pedidoController.validar(pedidoVacio);

        } catch (ApiException e) {
            assertTrue(e.getMessage().contains("nulo o vacio"));
        }

        Pedido pedidoNulo = null;
        try {
            pedidoController.validar(pedidoNulo);
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("es nulo"));
        }
    }

    @Test
    public void insertarErrorTest(){
        try {
            pedidoController.insertar(pedido);
        } catch (PersistenciaException | ApiException e) {
            assertTrue(e.getMessage().contains("ya existe"));
        }
    }

    @Test
    public void eliminarErrorTest() {
        try {
            pedidoController.eliminar(IDPEDIDO_INEXISTENTE);
        } catch (PersistenciaException | ApiException e) {
            assertTrue(e.getMessage().contains("no existe"));
        }
    }

    @Test
    public void buscarTest() {
        Pedido pedidoBuscado;
        try {
            pedidoBuscado = pedidoController.buscar(IDPEDIDO);
            assertEquals(pedido, pedidoBuscado, "Los pedidos deberian ser iguales");
        } catch (PersistenciaException | ApiException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void buscarErrorTest() {
        Pedido pedidoBuscado;
        try {
            pedidoBuscado = pedidoController.buscar(IDPEDIDO_INEXISTENTE);
        } catch (PersistenciaException | ApiException e) {
            assertTrue(e.getMessage().contains("no existe"));
        }
    }

    @Test
    public void modificarTest() {
        Pedido pedidoEncontrado;
        pedido.setUnidades(50);
        try {
            pedidoController.modificar(pedido);
            pedidoEncontrado = pedidoController.buscar(IDPEDIDO);
            assertEquals(pedido, pedidoEncontrado, "Los pedidos deberian ser iguales");
        } catch (PersistenciaException | ApiException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void modificarErrorTest() {
        Pedido pedidoInexistente = new Pedido(IDPEDIDO_INEXISTENTE, 8, 20, "2021-05-26", usuario, producto);
        try {
            pedidoController.modificar(pedidoInexistente);
        } catch (PersistenciaException | ApiException e) {
            assertTrue(e.getMessage().contains("no existe"));
        }
    }

    @Test
    public void obtenerListadoTest() {
        ArrayList<Pedido> lista;
        try {
            lista = pedidoController.obtenerListado();
            assertTrue(lista.contains(pedido), "La lista no contiene el pedido correcto");
        } catch (PersistenciaException | ApiException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void realizarPedidoTest() {
        String idPedido = producto.getIdProducto()+"-"+usuario.getDni();
        String idEnvio = "env_"+idPedido;
        Pedido pedidoEncontrado;
        int unidades = 10;
        try {
            pedidoController.realizarPedido(usuario, producto.getIdProducto(), unidades);
            pedidoEncontrado = pedidoController.buscar(idPedido);
            assertEquals(usuario, pedidoEncontrado.getUsuario(), "El usuario que realizado el pedido no es correcto");
            assertEquals(producto.getIdProducto(),pedidoEncontrado.getProducto().getIdProducto(), "Los productos deberian ser iguales");
            assertEquals(unidades, pedidoEncontrado.getUnidades(), "Las unidades deberian ser iguales");
        } catch (PersistenciaException | ApiException e) {
            fail(e.getMessage());
        } finally {
            try {
                pedidoController.eliminar(idPedido);
                envioController.eliminar(idEnvio);
            } catch (PersistenciaException | ApiException e) {
                fail(e.getMessage());
            }
        }
    }

}
