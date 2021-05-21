package es.iespuertodelacruz.bait;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.iespuertodelacruz.bait.api.personas.Usuario;
import es.iespuertodelacruz.bait.controlador.personasController.UsuarioController;
import es.iespuertodelacruz.bait.exceptions.ApiException;
import es.iespuertodelacruz.bait.exceptions.PersistenciaException;

public class UsuarioControllerTest {
    UsuarioController usuarioController;
    Usuario usuario;

    @BeforeEach
    public void setUp() throws PersistenciaException {
        if (usuarioController == null) {
            usuarioController = new UsuarioController();
        }

        usuario = new Usuario("dni", "nombre", "apellidos", "email", "direccion", "telefono", "pais", "codigoPostal",
                "provincia", "nombreUsuario", "password", "Admin", 0f);
        try {
            usuarioController.validar(usuario);
        } catch (ApiException e1) {
            fail("El validar no esta funcionando correctamente.");
        }
        try {
            usuarioController.insertar(usuario);
        } catch (PersistenciaException | ApiException e) {
            fail("Ha ocurrido un error al insertar el usuario");
        }
    }

    @AfterEach
    public void after() {
        try {
            usuarioController.eliminar(usuario.getDni());
        } catch (PersistenciaException | ApiException e) {
            fail("Error al eliminar el usuario");
        }
    }

    @Test
    public void buscarTest() {
        Usuario usuarioBuscado;
        try {
            usuarioBuscado = usuarioController.buscar(usuario.getDni());
            assertEquals(usuario, usuarioBuscado, "Deberian ser iguales");
        } catch (PersistenciaException e) {
            fail("El usuario buscado no es el correcto.");
        } 
    }

    @Test
    public void loginTest() {
        try {
            usuarioController.login("jonay", "1234", "Admin");
        } catch (Exception e) {
            fail("Error al realizar el login");
        }
    }

    @Test
    public void añadirSaldoTest() {
        Usuario usuarioDb;
        float saldoAañadir = 20;
        try {
            usuarioController.añadirSaldo(usuario, saldoAañadir);
            usuarioDb = usuarioController.buscar(usuario.getDni());
            assertEquals(saldoAañadir, usuario.getSaldo(), "El saldo no se a añadido correctamente.");
        } catch (PersistenciaException | ApiException e) {
            fail("No se a añadido el saldo correctamente.");
        } 
    }

    @Test
    public void modificarTest() {
        Usuario usuarioModificado;
        usuario.setNombre("Pepe");
        try {
            usuarioController.modificar(usuario);
            usuarioModificado = usuarioController.buscar(usuario.getDni());
            assertEquals(usuario, usuarioModificado, "No se modifico correctamente.");
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void obtenerListadoTest() {
        ArrayList<Usuario> usuarios = null;
        try {
            usuarios = usuarioController.obtenerListado();
            assertTrue(usuarios.contains(usuario));
        } catch (PersistenciaException | ApiException e) {
            fail("Error al obtener el listado");
        }
        
    }

}
