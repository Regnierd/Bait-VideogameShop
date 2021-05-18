package es.iespuertodelacruz.bait;

import static org.junit.jupiter.api.Assertions.fail;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.iespuertodelacruz.bait.api.personas.Usuario;
import es.iespuertodelacruz.bait.exceptions.BbddException;
import es.iespuertodelacruz.bait.modelo.mysql.SQLUsuario;

public class AppTest {
    SQLUsuario sqlCliente;
    Usuario usuario;

    @BeforeEach
    public void setUp() {
        if (sqlCliente == null) {
            sqlCliente = new SQLUsuario("org.sqlite.JDBC", "jdbc:sqlite:bait.db", null, null);
        }
        usuario = new Usuario("dni", "nombre", "apellidos", "email", "direccion", "telefono","pais","codigoPostal","provincia","nombreUsuario", "password", "rol", 0f);
    }

    @Test
    public void insertarTest() {
        try {
            sqlCliente.insertar(usuario);
        } catch (BbddException | SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void eliminarTest() {
        String dni = "dni";
        try {
            sqlCliente.eliminar(dni);
        } catch (SQLException | BbddException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void modificar() {
        usuario.setNombre("Pepe");
        try {
            sqlCliente.modificar(usuario);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
