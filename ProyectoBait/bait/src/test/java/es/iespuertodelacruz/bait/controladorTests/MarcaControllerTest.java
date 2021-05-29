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

public class MarcaControllerTest {
    private static final String IDMARCA = "mar_Marca";
    private static final String IDMARCA_INEXISTENTE = "marcaaInexistente";

    MarcaController marcaController;
    Marca marca;
   

    @BeforeEach
    public void setUp() {
        try {
            if (marcaController == null) {         
                marcaController = new MarcaController();              
            }
        } catch (PersistenciaException e) {
            fail(e.getMessage());
        }
        
        marca = new Marca(IDMARCA, "nombre");
    
        try {
            marcaController.insertar(marca);
        } catch (PersistenciaException | ApiException e) {
            fail(e.getMessage());
        }

    }

    @AfterEach
    public void after() {
        try {           
            marcaController.eliminar(IDMARCA);

        } catch (PersistenciaException | ApiException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void test(){
        try {
            marcaController.insertar(marca);
        } catch (PersistenciaException | ApiException e) {
            assertTrue(e.getMessage().contains("ya existe"));
        }
    }

    @Test
    public void eliminarErrorTest() {
        try {
            marcaController.eliminar(IDMARCA_INEXISTENTE);
        } catch (PersistenciaException | ApiException e) {
            assertTrue(e.getMessage().contains("no existe"));
        }
    }

    @Test
    public void validarTest() {
        Marca marcaVacia = new Marca();
       
        try {
            marcaController.validar(marcaVacia);
        
        } catch (ApiException e) {
            assertTrue(e.getMessage().contains("nulo o vacio"));
        }

        Marca marcaNulo = null;
        try {
            marcaController.validar(marcaNulo);
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("La marca no puede ser nulo"));
        }

        
    }

    @Test
    public void modificarTest() {
        Marca marcaEncontrado;
        marca.setNombre("Microsoft");
        try {
            marcaController.modificar(marca);
            marcaEncontrado = marcaController.buscar(IDMARCA);
            assertEquals(marca, marcaEncontrado, "Las marcas deberian ser iguales");
        } catch (PersistenciaException | ApiException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void modificarErrorTest() {
        Marca marcaInexistente = new Marca(IDMARCA_INEXISTENTE, "marcaInexistente");
        try {
            marcaController.modificar(marcaInexistente);
        } catch (PersistenciaException | ApiException e) {
            assertTrue(e.getMessage().contains("no existe"));
        }
    }

    @Test
    public void obtenerListadoTest() {
        ArrayList<Marca> lista;
        try {
            lista = marcaController.obtenerListado();
            assertTrue(lista.contains(marca), "La lista no contiene la categoria correcta");
        } catch (PersistenciaException | ApiException e) {
            fail(e.getMessage());
        }
    }


}
