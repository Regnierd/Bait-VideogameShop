package es.iespuertodelacruz.bait.controladorTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.iespuertodelacruz.bait.api.productos.Categoria;
import es.iespuertodelacruz.bait.controlador.productosController.CategoriaController;
import es.iespuertodelacruz.bait.exceptions.ApiException;
import es.iespuertodelacruz.bait.exceptions.PersistenciaException;

public class CategoriaControllerTest {
    private static final String IDCATEGORIA = "cat_CATEGORIA";
    private static final String IDCATEGORIA_INEXISTENTE = "categoriaInexistente";

    CategoriaController categoriaController;
    Categoria categoria;
   

    @BeforeEach
    public void setUp() {
        try {
            if (categoriaController == null) {         
                categoriaController = new CategoriaController();              
            }
        } catch (PersistenciaException e) {
            fail(e.getMessage());
        }
        
        categoria = new Categoria(IDCATEGORIA, "nombre");
    
        try {
            categoriaController.insertar(categoria);
        } catch (PersistenciaException | ApiException e) {
            fail(e.getMessage());
        }

    }

    @AfterEach
    public void after() {
        try {           
            categoriaController.eliminar(IDCATEGORIA);
        } catch (PersistenciaException | ApiException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void insertarErrorTest(){
        try {
            categoriaController.insertar(categoria);
            fail("No deberia llegar aqui");
        } catch (PersistenciaException | ApiException e) {
            assertTrue(e.getMessage().contains("ya existe"));
        }
    }

    @Test
    public void eliminarErrorTest() {
        try {
            categoriaController.eliminar(IDCATEGORIA_INEXISTENTE);
            fail("No deberia llegar aqui");
        } catch (PersistenciaException | ApiException e) {
            assertTrue(e.getMessage().contains("no existe"));
        }
    }

    @Test
    public void validarTest() {
        Categoria categoriaVacio = new Categoria();
       
        try {
            categoriaController.validar(categoriaVacio);
            fail("No deberia llegar aqui");
        } catch (ApiException e) {
            assertTrue(e.getMessage().contains("nulo o vacio"));
        }

        Categoria categoriaNulo = null;
        try {
            categoriaController.validar(categoriaNulo);
            fail("No deberia llegar aqui");
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("La categoria no puede ser nulo"));
        }

        
    }

    @Test
    public void modificarTest() {
        Categoria categoriaEncontrado;
        categoria.setNombre("periferico");
        try {
            categoriaController.modificar(categoria);
            categoriaEncontrado = categoriaController.buscar(IDCATEGORIA);
            assertEquals(categoria, categoriaEncontrado, "Las categorias deberian ser iguales");
        } catch (PersistenciaException | ApiException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void modificarErrorTest() {
        Categoria categoriaInexistente = new Categoria(IDCATEGORIA_INEXISTENTE, "categoriaInexistente");
        try {
            categoriaController.modificar(categoriaInexistente);
            fail("No deberia llegar aqui");
        } catch (PersistenciaException | ApiException e) {
            assertTrue(e.getMessage().contains("no existe"));
        }
    }

    @Test
    public void obtenerListadoTest() {
        ArrayList<Categoria> lista;
        try {
            lista = categoriaController.obtenerListado();
            assertTrue(lista.contains(categoria), "La lista no contiene la categoria correcta");
        } catch (PersistenciaException | ApiException e) {
            fail(e.getMessage());
        }
    }


}
