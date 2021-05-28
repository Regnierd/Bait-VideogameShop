package es.iespuertodelacruz.bait;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.iespuertodelacruz.bait.api.personas.Usuario;
import es.iespuertodelacruz.bait.exceptions.PersistenciaException;
import es.iespuertodelacruz.bait.modelo.personasModelo.UsuarioModelo;

public class UsuarioModeloTest {
    private static final String DNI = "12345678A";
    private static final String NOMBRE_USUARIO = "usuario";

    UsuarioModelo usuarioModelo;
    Usuario usuario;

    @BeforeEach
    public void setUp(){
        if (usuarioModelo == null) {
            try {
                usuarioModelo = new UsuarioModelo();
            } catch (PersistenciaException e) {
                fail(e.getMessage());
            }       
        }
        usuario = new Usuario(DNI, "nombre", "apellidos", "email", "direccion", "telefono", "pais", "codigoPostal", "provincia", NOMBRE_USUARIO, "password", "Admin", 0f);
        
        try {
            usuarioModelo.insertar(usuario);
        } catch (PersistenciaException e) {
            fail("Ha ocurrido un error al insertar el usuario");
        }
    }

    @AfterEach
    public void after() {
        try {
            usuarioModelo.eliminar(DNI);
        } catch (PersistenciaException e) {
            fail("Error al eliminar el usuario");
        }
    }

    @Test
    public void buscarPorDniTest() {
        Usuario usuarioBuscado;
        try {
            usuarioBuscado = usuarioModelo.buscaPorDni(DNI);
            assertEquals(usuario, usuarioBuscado, "Deberian ser iguales");
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void buscarPorNombreUsuarioTest() {
        Usuario usuarBuscado;
        try {
            usuarBuscado = usuarioModelo.buscaPorNombreUsuario(NOMBRE_USUARIO);
            assertEquals(usuario, usuarBuscado, "Los usuario deberian ser iguales");
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void obtenerListadoTest() {
        ArrayList<Usuario> lista;
        try {
            lista = usuarioModelo.obtenerListado();
            assertTrue(lista.contains(usuario), "La lista no contiene el usuario correcto");
        } catch (PersistenciaException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void modificarTest() {
        Usuario usuarioBuscado;
        usuario.setNombre("Pepe");
        try {
            usuarioModelo.modificar(usuario);
            usuarioBuscado = usuarioModelo.buscaPorDni(DNI);
            assertEquals(usuario, usuarioBuscado, "Los usuario deberian ser iguales");
        } catch (PersistenciaException e) {
            fail(e.getMessage());
        }
    }

}
