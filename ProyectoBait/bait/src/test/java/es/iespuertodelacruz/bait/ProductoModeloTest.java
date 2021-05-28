package es.iespuertodelacruz.bait;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.iespuertodelacruz.bait.api.productos.Categoria;
import es.iespuertodelacruz.bait.api.productos.Marca;
import es.iespuertodelacruz.bait.api.productos.Producto;
import es.iespuertodelacruz.bait.exceptions.PersistenciaException;
import es.iespuertodelacruz.bait.modelo.productosModelo.CategoriaModelo;
import es.iespuertodelacruz.bait.modelo.productosModelo.MarcaModelo;
import es.iespuertodelacruz.bait.modelo.productosModelo.ProductoModelo;

public class ProductoModeloTest {
    private static final String ID_PRODUCTO = "pro_test";
    private static final String NOMBRE_PRODUCTO = "test";
    private static final String ID_CATEGORIA = "cat_test";
    private static final String ID_MARCA = "mar_test";


    ProductoModelo productoModelo;
    CategoriaModelo categoriaModelo;
    MarcaModelo marcaModelo;
    Producto producto;

    @BeforeEach
    public void setUp() throws PersistenciaException {
        try {
            if (productoModelo == null) {
                productoModelo = new ProductoModelo();
            }
            if (categoriaModelo == null) {
                categoriaModelo = new CategoriaModelo();
            }
            if (marcaModelo == null) {
                marcaModelo = new MarcaModelo();
            }
        } catch (PersistenciaException e) {
            fail(e.getMessage());
        }

        Categoria categoria = new Categoria(ID_CATEGORIA, NOMBRE_PRODUCTO);
        Marca marca = new Marca(ID_MARCA, NOMBRE_PRODUCTO);
        producto = new Producto(ID_PRODUCTO, NOMBRE_PRODUCTO, categoria, 10f, "Producto test", 5, marca);

        try {
            categoriaModelo.insertar(categoria);
            marcaModelo.insertar(marca);
            productoModelo.insertar(producto);
        } catch (PersistenciaException e) {
            fail("Ha ocurrido un error al insertar la categoria , la marca o el producto");
        }
    }

    @AfterEach
    public void after() {
        try {
            productoModelo.eliminar(ID_PRODUCTO);
            categoriaModelo.eliminar(ID_CATEGORIA);
            marcaModelo.eliminar(ID_MARCA);
        } catch (PersistenciaException e) {
            fail("Error al eliminar la categoria, marca o producto.");
        }
    }

    @Test
    public void buscarPorIdTest() {
        Producto productoBuscado;
        try {
            productoBuscado = productoModelo.buscarPorId(ID_PRODUCTO);
            assertEquals(producto, productoBuscado, "Deberian ser iguales");
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void buscarPorNombreTest() {
        ArrayList<Producto> lista;
        try {
            lista = productoModelo.buscarPorNombre(NOMBRE_PRODUCTO);
            assertTrue(lista.contains(producto), "La lista no contiene el producto correcto");
        } catch (Exception e) {
            fail(e.getMessage());
        }
     }

    @Test
    public void buscarPorMarcaTest() {
        ArrayList<Producto> lista;
        try {
            lista = productoModelo.buscarPorMarca(ID_MARCA);
            assertTrue(lista.contains(producto), "La lista no contiene el producto correcto");
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    
    @Test
    public void buscarPorCategoriaTest() {
        ArrayList<Producto> lista;
        try {
            lista = productoModelo.buscarPorCategoria(ID_CATEGORIA);
            assertTrue(lista.contains(producto), "La lista no contiene el producto correcto");
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void obtenerListadoTest() {
        ArrayList<Producto> lista;
        try {
            lista = productoModelo.obtenerListado();
            assertTrue(lista.contains(producto), "La lista no contiene el producto correcto");
        } catch (PersistenciaException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void modificarTest() {
        Producto productoBuscado;
        producto.setNombre("OtroNombre");
        try {
            productoModelo.modificar(producto);
            productoBuscado = productoModelo.buscarPorId(ID_PRODUCTO);
            assertEquals(producto, productoBuscado, "Los productos deberian ser iguales");
        } catch (PersistenciaException e) {
            fail(e.getMessage());
        }
    }
}
