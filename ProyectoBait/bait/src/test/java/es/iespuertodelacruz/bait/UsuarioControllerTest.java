package es.iespuertodelacruz.bait;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

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
        
        usuario = new Usuario("dni", "nombre", "apellidos", "email", "direccion", "telefono", "pais", "codigoPostal", "provincia", "nombreUsuario", "password", "Admin", 0f);
        
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
    public void buscar() {
        Usuario usuarioBuscado;
        try {
            usuarioBuscado = usuarioController.buscar(usuario.getDni());
            assertEquals(usuario, usuarioBuscado, "Deberian ser iguales");
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void modificar() {
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
    public void login(){
        try {
            usuarioController.login("jonay", "1234", "Admin");
        } catch (Exception e) {
            fail("Error al realizar el login");
        }
    }
}
