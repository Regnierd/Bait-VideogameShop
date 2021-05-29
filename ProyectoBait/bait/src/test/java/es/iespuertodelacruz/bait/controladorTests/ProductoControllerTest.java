package es.iespuertodelacruz.bait.controladorTests;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

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
    private static final String IDENTIFICADOR = "proc_PRODUCTO";
    ProductoController productoController;
    CategoriaController categoriaController;
    MarcaController marcaController;
    Producto producto;
    Categoria categoria;
    Marca marca;

    @BeforeEach
    public void setUp() {
        if (productoController == null) {
            try {
                productoController = new ProductoController();
            } catch (PersistenciaException e) {
                fail(e.getMessage());
            }
        }
        if(categoriaController == null){
            try {
                categoriaController = new CategoriaController();
            } catch (PersistenciaException e) {
                fail(e.getMessage());
            }
        }
        if(marcaController == null){
            try {
                marcaController = new MarcaController();
            } catch (PersistenciaException e) {
                fail(e.getMessage());
            }
        }
        categoria = new Categoria("id_Categoria", "nombre");
        marca = new Marca("id_Marca", "nombre");
        producto = new Producto(IDENTIFICADOR, "auricular", categoria, 10, "auriculares marsgaming", 15, marca);

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
            productoController.eliminar(IDENTIFICADOR);
        } catch (PersistenciaException | ApiException e) {
            fail(e.getMessage());
        }
    }

}
