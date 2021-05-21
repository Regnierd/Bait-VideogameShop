package es.iespuertodelacruz.bait;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.iespuertodelacruz.bait.api.personas.Usuario;
import es.iespuertodelacruz.bait.exceptions.PersistenciaException;
import es.iespuertodelacruz.bait.modelo.personasModelo.UsuarioModelo;

public class ModeloUsuarioTest {
    UsuarioModelo usuarioModelo;
    Usuario usuario;

    @BeforeEach
    public void setUp() throws PersistenciaException {
        if (usuarioModelo == null) {
            usuarioModelo = new UsuarioModelo();
        }
        usuario = new Usuario("dni", "nombre", "apellidos", "email", "direccion", "telefono", "pais", "codigoPostal", "provincia", "nombreUsuario", "password", "Admin", 0f);
        
        try {
            usuarioModelo.insertar(usuario);
        } catch (PersistenciaException e) {
            fail("Ha ocurrido un error al lanzar el init");
        }
    }

    @AfterEach
    public void after() {
        try {
            usuarioModelo.eliminar(usuario.getDni());
        } catch (PersistenciaException e) {
            fail("Error al eliminar el usuario");
        }
    }

    @Test
    public void buscar() {
        Usuario usuarioBuscado;
        try {
            usuarioBuscado = usuarioModelo.buscaPorDni(usuario.getDni());
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
            usuarioModelo.modificar(usuario);
            usuarioModificado = usuarioModelo.buscaPorDni(usuario.getDni());
            assertEquals(usuario, usuarioModificado, "No se modifico correctamente.");
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

}
