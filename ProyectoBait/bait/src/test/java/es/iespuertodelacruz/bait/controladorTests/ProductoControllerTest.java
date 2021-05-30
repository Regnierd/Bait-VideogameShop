package es.iespuertodelacruz.bait.controladorTests;

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
import es.iespuertodelacruz.bait.controlador.productosController.CategoriaController;
import es.iespuertodelacruz.bait.controlador.productosController.MarcaController;
import es.iespuertodelacruz.bait.controlador.productosController.ProductoController;
import es.iespuertodelacruz.bait.exceptions.ApiException;
import es.iespuertodelacruz.bait.exceptions.PersistenciaException;

public class ProductoControllerTest {
    private static final String IDPRODUCTO = "proc_PRODUCTO";
    private static final String IDCATEGORIA = "cat_CATEGORIA";
    private static final String IDMARCA = "mar_MARCA";
    private static final String IDMARCA_INEXISTENTE = "marcaInexistente";
    private static final String IDCATEGORIA_INEXISTENTE = "categoriaInexistente";
    private static final String NOMBRE_INEXISTENTE = "nombreInexistente";
    private static final String IDPRODUCTO_INEXISTENTE = "productoInexistente";
    private static final String NOMBRE = "auricular";
    private static final int UNIDADES = 15;
    ProductoController productoController;
    CategoriaController categoriaController;
    MarcaController marcaController;
    Producto producto;
    Categoria categoria;
    Marca marca;

    @BeforeEach
    public void setUp() {
        try {
            if (productoController == null) {         
                productoController = new ProductoController();              
            }
            if(categoriaController == null){               
                categoriaController = new CategoriaController();               
            }
            if(marcaController == null){             
                marcaController = new MarcaController();            
            }
        } catch (PersistenciaException e) {
            fail(e.getMessage());
        }
        
        categoria = new Categoria(IDCATEGORIA, "nombre");
        marca = new Marca(IDMARCA, "nombre");
        producto = new Producto(IDPRODUCTO, NOMBRE, categoria, 10, "auriculares marsgaming", UNIDADES, marca);

        try {
            categoriaController.insertar(categoria);
            marcaController.insertar(marca);
            productoController.insertar(producto);
        } catch (PersistenciaException | ApiException e) {
            fail(e.getMessage());
        }

    }

    @AfterEach
    public void after() {
        try {
            productoController.eliminar(IDPRODUCTO);
            categoriaController.eliminar(IDCATEGORIA);
            marcaController.eliminar(IDMARCA);
        } catch (PersistenciaException | ApiException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void insertarErrorTest(){
        try {
            productoController.insertar(producto);
        } catch (PersistenciaException | ApiException e) {
            assertTrue(e.getMessage().contains("ya existe"));
        }
    }

    @Test
    public void eliminarErrorTest() {
        try {
            productoController.eliminar(IDPRODUCTO_INEXISTENTE);
        } catch (PersistenciaException | ApiException e) {
            assertTrue(e.getMessage().contains("no existe"));
        }
    }

    @Test
    public void validarTest() {
        Producto productoVacio = new Producto();
        productoVacio.setStock(-1);
        try {
            productoController.validar(productoVacio);
            fail("No deberia llegar aqui");
        } catch (ApiException e) {
            assertTrue(e.getMessage().contains("nulo o vacio"));
        }

        Producto productoNulo = null;
        try {
            productoController.validar(productoNulo);
            fail("No deberia llegar aqui");
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("El producto no puede ser nulo"));
        }

        
    }

    @Test
    public void buscarTest() {
        Producto productoBuscado;
        try {
            productoBuscado = productoController.buscar(IDPRODUCTO);
            assertEquals(producto, productoBuscado, "Las usuario deberian ser iguales");
        } catch (PersistenciaException | ApiException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void buscarPorCategoriaTest() {
        ArrayList<Producto> productosCategorias;
        try {
            productosCategorias = productoController.buscarPorCategoria(IDCATEGORIA);
            assertTrue(productosCategorias.contains(producto), "La lista no contiene el producto buscado por categoria");
        } catch (PersistenciaException | ApiException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void buscarPorCategoriaErrorTest() {
        try {
            productoController.buscarPorCategoria(IDCATEGORIA_INEXISTENTE); 
            fail("No deberia llegar aqui");       
        } catch (PersistenciaException | ApiException e) {
            assertTrue(e.getMessage().contains("No existe"));
        }
    }

    @Test
    public void buscarPorMarcaTest() {
        ArrayList<Producto> productosMarcas;
        try {
            productosMarcas = productoController.buscarPorMarca(IDMARCA);
            assertTrue(productosMarcas.contains(producto), "La lista no contiene el producto buscado por marca");
        } catch (PersistenciaException | ApiException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void buscarPorMarcaErrorTest() {
        try {
            productoController.buscarPorMarca(IDMARCA_INEXISTENTE);    
            fail("No deberia llegar aqui");      
        } catch (PersistenciaException | ApiException e) {
            assertTrue(e.getMessage().contains("No existe"));
        }
    }

    @Test
    public void buscarPorNombreTest() {
        ArrayList<Producto> productosNombre;
        try {
            productosNombre = productoController.buscarPorNombre(NOMBRE);
            assertTrue(productosNombre.contains(producto), "La lista no contiene el producto buscado por nombre");
        } catch (PersistenciaException | ApiException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void buscarPorNombreErrorTest() {
        try {
            productoController.buscarPorNombre(NOMBRE_INEXISTENTE);
            fail("No deberia llegar aqui");
        } catch (PersistenciaException | ApiException e) {
            assertTrue(e.getMessage().contains("No existe"));
        }
    }


    @Test
    public void modificarTest() {
        Producto productoEncontrado;
        producto.setNombre("teclado");
        try {
            productoController.modificar(producto);
            productoEncontrado = productoController.buscar(IDPRODUCTO);
            assertEquals(producto, productoEncontrado, "Los productos deberian ser iguales");
        } catch (PersistenciaException | ApiException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void modificarErrorTest() {
        Producto productoInexistente = new Producto("idNuevo", "auricular", categoria, 10, "auriculares marsgaming", 15, marca);
        try {
            productoController.modificar(productoInexistente);
            fail("No deberia llegar aqui");
        } catch (PersistenciaException | ApiException e) {
            assertTrue(e.getMessage().contains("no existe"));
        }
    }

    @Test
    public void obtenerListadoTest() {
        ArrayList<Producto> lista;
        try {
            lista = productoController.obtenerListado();
            assertTrue(lista.contains(producto), "La lista no contiene el producto correcto");
        } catch (PersistenciaException | ApiException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void reducirStockTest() {
        Producto productoEncontrado;
        int nuevoStock;
        nuevoStock = UNIDADES - 10;
        try {
            productoController.reducirStock(IDPRODUCTO, 10);
            productoEncontrado = productoController.buscar(IDPRODUCTO);
            assertEquals(nuevoStock,productoEncontrado.getStock(), "Los stock deberian ser iguales");
        } catch (PersistenciaException | ApiException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void aumentarStockTest() {
        Producto productoEncontrado;
        int nuevoStock;
        nuevoStock = UNIDADES + 10;
        try {
            productoController.aumentarStock(IDPRODUCTO, 10);
            productoEncontrado = productoController.buscar(IDPRODUCTO);
            assertEquals(nuevoStock, productoEncontrado.getStock(), "Los stock deberian ser iguales");
        } catch (PersistenciaException | ApiException e) {
            fail(e.getMessage());
        }
    }


}
