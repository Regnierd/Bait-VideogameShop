package es.iespuertodelacruz.bait.apiTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.iespuertodelacruz.bait.api.productos.Categoria;

public class CategoriaTest {
    private static final String IDCATEGORIA = "idCategoria";
    Categoria categoria = new Categoria();
    Categoria categoria2 = new Categoria();
    @BeforeEach
    public void setUp() {
        categoria = crear(IDCATEGORIA, "nombre");
        categoria2 = new Categoria("OtroNombre");
    }

    @Test
    public void crearTest() {
        assertEquals(IDCATEGORIA,categoria.getIdCategoria(),"La categoria no se creado correctamente");
    }

    @Test
    public void mostrarTest() {
        String informacion = categoria.toString();
        assertTrue(informacion.contains(IDCATEGORIA),"No esta mostrado la categoria correcto");
    }

    /**
     * Funcion que crear y retorn una categoria
     * @param idCategoria de la categoria
     * @param nombre de la categoria
     * @return la categoria
     */
    private Categoria crear(String idCategoria, String nombre) {
        categoria.setIdCategoria(idCategoria);
        categoria.setNombre(nombre);

        return new Categoria(idCategoria, nombre);
    }
}

