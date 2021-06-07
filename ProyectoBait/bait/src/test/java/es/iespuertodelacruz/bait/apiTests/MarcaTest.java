package es.iespuertodelacruz.bait.apiTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.iespuertodelacruz.bait.api.productos.Marca;

public class MarcaTest {
    private static final String IDMARCAR = "idMarca";
    Marca marca = new Marca();
    Marca marca2 = new Marca();
    @BeforeEach
    public void setUp() {
        marca = crear(IDMARCAR, "nombre");
        marca2 = new Marca("OtroNombre");
    }

    @Test
    public void crearTest() {
        assertEquals(IDMARCAR,marca.getIdMarca(),"La marca no se creado correctamente");
    }

    @Test
    public void mostrarTest() {
        String informacion = marca.toString();
        assertTrue(informacion.contains(IDMARCAR),"No esta mostrado la marca correcta");
    }

    /**
     * Funcion que crea y devuelve un marca
     * @param idMarca de la marca
     * @param nombre de la marca
     * @return la marca creada
     */
    private Marca crear(String idMarca, String nombre) {
        marca.setIdMarca(idMarca);
        marca.setNombre(nombre);

        return new Marca(idMarca,nombre);
    }
}

