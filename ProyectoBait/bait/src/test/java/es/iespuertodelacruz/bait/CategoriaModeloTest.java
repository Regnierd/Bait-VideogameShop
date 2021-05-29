package es.iespuertodelacruz.bait;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.iespuertodelacruz.bait.api.productos.Categoria;
import es.iespuertodelacruz.bait.exceptions.PersistenciaException;
import es.iespuertodelacruz.bait.modelo.productosModelo.CategoriaModelo;

public class CategoriaModeloTest {
    private static final String ID_CATEGORIA = "CAT_COMIC";
    private static final String NOMBRE_CATEGORIA = "Comic";

    CategoriaModelo categoriaModelo;
    Categoria categoria;

    @BeforeEach
    public void setUp() throws PersistenciaException {
        if (categoriaModelo == null) {
            try {
                categoriaModelo = new CategoriaModelo();
            } catch (PersistenciaException e) {
                fail(e.getMessage());
            }       
        }
        categoria = new Categoria(ID_CATEGORIA, NOMBRE_CATEGORIA);
        
        try {
            categoriaModelo.insertar(categoria);
        } catch (PersistenciaException e) {
            fail("Ha ocurrido un error al insertar la categoria");
        }
    }

    @AfterEach
    public void after() {
        try {
            categoriaModelo.eliminar(ID_CATEGORIA);
        } catch (PersistenciaException e) {
            fail("Error al eliminar la categoria");
        }
    }

    @Test
    public void buscarPorIdTest() {
        Categoria categoriaBuscada;
        try {
            categoriaBuscada = categoriaModelo.buscarPorId(ID_CATEGORIA);
            assertEquals(categoria, categoriaBuscada, "Deberian ser iguales");
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void buscarPorNombreTest() {
        Categoria categoriaBuscada;
        try {
            categoriaBuscada = categoriaModelo.buscarPorNombre(NOMBRE_CATEGORIA);
            assertEquals(categoria, categoriaBuscada, "Deberian ser iguales");
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void obtenerListadoTest() {
        ArrayList<Categoria> lista;
        try {
            lista = categoriaModelo.obtenerListado();
            assertTrue(lista.contains(categoria), "La lista no contiene la categoria correcta");
        } catch (PersistenciaException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void modificarTest() {
        Categoria categoriaBuscada;
        categoria.setNombre("OtroNombre");
        try {
            categoriaModelo.modificar(categoria);
            categoriaBuscada = categoriaModelo.buscarPorId(ID_CATEGORIA);
            assertEquals(categoria, categoriaBuscada, "Las categorias deberian ser iguales");
        } catch (PersistenciaException e) {
            fail(e.getMessage());
        }
    }


}
