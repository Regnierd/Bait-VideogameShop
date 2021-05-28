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
   public void test() {
       assertTrue(true);
   }
}
