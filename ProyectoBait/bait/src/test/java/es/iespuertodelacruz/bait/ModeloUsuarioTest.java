package es.iespuertodelacruz.bait;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.iespuertodelacruz.bait.api.personas.Usuario;
import es.iespuertodelacruz.bait.controlador.personasController.UsuarioController;
import es.iespuertodelacruz.bait.exceptions.PersistenciaException;
import es.iespuertodelacruz.bait.modelo.mysql.SQL.SQLUsuario;
import es.iespuertodelacruz.bait.modelo.personasModelo.UsuarioModelo;

public class ModeloUsuarioTest {
    SQLUsuario sqlUsuario;
    Usuario usuario;
    UsuarioModelo usuarioModelo;
    UsuarioController usuarioController;

    @BeforeEach
    public void setUp() {
        if (sqlUsuario == null) {
            sqlUsuario = new SQLUsuario("org.sqlite.JDBC", "jdbc:sqlite:bait.db", null, null);
        }
        usuario = new Usuario("dni", "nombre", "apellidos", "email", "direccion", "telefono", "pais", "codigoPostal", "provincia", "nombreUsuario", "password", "Admin", 0f);
        
        try {
            sqlUsuario.init();
            sqlUsuario.insertar(usuario);
        } catch (PersistenciaException e) {
            fail("Ha ocurrido un error al lanzar el init");
        }
    }

    @AfterEach
    public void after() {
        try {
            sqlUsuario.eliminar(usuario.getDni());
        } catch (PersistenciaException e) {
            fail("Error al eliminar el usuario");
        }
    }

    @Test
    public void buscar() {
        Usuario usuarioBuscado;
        try {
            usuarioBuscado = sqlUsuario.buscaPorDni(usuario.getDni());
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
            sqlUsuario.modificar(usuario);
            usuarioModificado = sqlUsuario.buscaPorDni(usuario.getDni());
            assertEquals(usuario, usuarioModificado, "No se modifico correctamente.");
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void login(){
        usuarioController = new UsuarioController();
        try {
            usuarioController.login("jonay", "1234", "Admin");
        } catch (Exception e) {
            fail();
        }
    }
}
