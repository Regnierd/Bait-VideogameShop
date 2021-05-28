package es.iespuertodelacruz.bait;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.iespuertodelacruz.bait.api.productos.Marca;
import es.iespuertodelacruz.bait.exceptions.PersistenciaException;
import es.iespuertodelacruz.bait.modelo.productosModelo.MarcaModelo;

public class MarcaModeloTest {
    private static final String ID_MARCA = "MAR_LG";
    private static final String NOMBRE_MARCA = "Lg";

    MarcaModelo marcaModelo;
    Marca marca;

    @BeforeEach
    public void setUp() throws PersistenciaException {
        if (marcaModelo == null) {
            try {
                marcaModelo = new MarcaModelo();
            } catch (PersistenciaException e) {
                fail(e.getMessage());
            }       
        }
        marca = new Marca(ID_MARCA, NOMBRE_MARCA);
        
        try {
            marcaModelo.insertar(marca);
        } catch (PersistenciaException e) {
            fail("Ha ocurrido un error al insertar la marca");
        }
    }

    @AfterEach
    public void after() {
        try {
            marcaModelo.eliminar(ID_MARCA);
        } catch (PersistenciaException e) {
            fail("Error al eliminar la marca");
        }
    }

    @Test
    public void buscarPorIdTest() {
        Marca marcaBuscada;
        try {
            marcaBuscada = marcaModelo.buscarPorId(ID_MARCA);
            assertEquals(marca, marcaBuscada, "Deberian ser iguales");
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void buscarPorNombreTest() {
        Marca marcaBuscada;
        try {
            marcaBuscada = marcaModelo.buscarPorNombre(NOMBRE_MARCA);
            assertEquals(marca, marcaBuscada, "Deberian ser iguales");
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void obtenerListadoTest() {
        ArrayList<Marca> lista;
        try {
            lista = marcaModelo.obtenerListado();
            assertTrue(lista.contains(marca), "La lista no contiene la marca correcta");
        } catch (PersistenciaException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void modificarTest() {
        Marca marcaBuscada;
        marca.setNombre("OtroNombre");
        try {
            marcaModelo.modificar(marca);
            marcaBuscada = marcaModelo.buscarPorId(ID_MARCA);
            assertEquals(marca, marcaBuscada, "Las marcas deberian ser iguales");
        } catch (PersistenciaException e) {
            fail(e.getMessage());
        }
    }


}
