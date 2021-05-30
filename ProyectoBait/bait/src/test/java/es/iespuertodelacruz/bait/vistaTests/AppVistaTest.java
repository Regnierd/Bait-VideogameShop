package es.iespuertodelacruz.bait.vistaTests;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayInputStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.iespuertodelacruz.bait.vista.AppVista;

public class AppVistaTest {
    AppVista appVista;
    String[] args = null;
    private ByteArrayInputStream testIn;
    
    @BeforeEach
    public void setUp() {
        if (appVista == null) {
            appVista = new AppVista();
        }
    }

    @Test
    public void menuUsuarioTest() {
       assertTrue(true);
    }
}
