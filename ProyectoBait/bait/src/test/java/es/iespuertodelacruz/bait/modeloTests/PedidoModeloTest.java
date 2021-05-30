package es.iespuertodelacruz.bait.modeloTests;

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
import es.iespuertodelacruz.bait.exceptions.PersistenciaException;

import es.iespuertodelacruz.bait.modelo.movimientosModelo.PedidoModelo;
import es.iespuertodelacruz.bait.modelo.personasModelo.UsuarioModelo;
import es.iespuertodelacruz.bait.modelo.productosModelo.CategoriaModelo;
import es.iespuertodelacruz.bait.modelo.productosModelo.MarcaModelo;
import es.iespuertodelacruz.bait.modelo.productosModelo.ProductoModelo;

public class PedidoModeloTest {

    private static final String IDPEDIDO = "ped_idPedido";
    private static final String DNI = "43387932L";
    private static final String IDPRODUCTO = "pro_idProducto";
    private static final String NOMBRE_USUARIO = "usuario";
    private static final String IDCATEGORIA = "idCategoria";
    private static final String IDMARCA = "idMarca";

    PedidoModelo pedidoModelo;
    UsuarioModelo usuarioModelo;
    CategoriaModelo categoriaModelo;
    MarcaModelo marcaModelo;
    ProductoModelo productoModelo;
    Pedido pedido;
    Usuario usuario;
    Producto producto;
    Categoria categoria;
    Marca marca;

    @BeforeEach
    public void setUp() {
        try {
            if (pedidoModelo == null) {
                pedidoModelo = new PedidoModelo();
            }
            if (usuarioModelo == null) {
                usuarioModelo = new UsuarioModelo();
            }
            if (categoriaModelo == null) {
                categoriaModelo = new CategoriaModelo();
            }
            if (marcaModelo == null) {
                marcaModelo = new MarcaModelo();
            }
            if (productoModelo == null) {
                productoModelo = new ProductoModelo();
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
            usuarioModelo.insertar(usuario);
            categoriaModelo.insertar(categoria);
            marcaModelo.insertar(marca);
            productoModelo.insertar(producto);
            pedidoModelo.insertar(pedido);
        } catch (PersistenciaException e) {
            fail("Error al insertar el pedido");
        }

    }

    @AfterEach
    public void after() {
        try {
            usuarioModelo.eliminar(DNI);
            categoriaModelo.eliminar(IDCATEGORIA);
            marcaModelo.eliminar(IDMARCA);
            productoModelo.eliminar(IDPRODUCTO);
            pedidoModelo.eliminar(IDPEDIDO);

        } catch (PersistenciaException e) {
            fail("Error al eliminar el pedido");
        }
    }

    @Test 
    public void insertarErrorTest() {
        String mensaje = "Ha ocurrido un error al insertar";
        try {
            pedidoModelo.insertar(pedido);
            fail("No deberia llegar aqui");
        } catch (PersistenciaException e) {
            assertTrue(e.getMessage().contains(mensaje));
        }
    }

    @Test
    public void buscarPorIdTest() {
        Pedido pedidoBuscado;
        try {
            pedidoBuscado = pedidoModelo.buscaPorIdentificador(IDPEDIDO);
            assertEquals(pedido, pedidoBuscado, "Deberian ser iguales");
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void obtenerListadoTest() {
        ArrayList<Pedido> lista;
        try {
            lista = pedidoModelo.obtenerListado();
            assertTrue(lista.contains(pedido), "La lista no contiene el usuario correcto");
        } catch (PersistenciaException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void obtenerListadoPorDniTest() {
        ArrayList<Pedido> lista;
        try {
            lista = pedidoModelo.obtenerListado(DNI);
            assertTrue(lista.contains(pedido), "La lista no contiene el usuario correcto");
        } catch (PersistenciaException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void modificarTest() {
        Pedido pedidoBuscado;
        pedido.setUnidades(3);
        try {
            pedidoModelo.modificar(pedido);
            pedidoBuscado = pedidoModelo.buscaPorIdentificador(IDPEDIDO);
            assertEquals(pedido, pedidoBuscado, "Los pedidos deberian ser iguales");
        } catch (PersistenciaException e) {
            fail(e.getMessage());
        }
    }

}
