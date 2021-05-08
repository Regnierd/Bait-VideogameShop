package es.iespuertodelacruz.bait;

import static org.junit.jupiter.api.Assertions.fail;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.iespuertodelacruz.bait.api.personas.Cliente;
import es.iespuertodelacruz.bait.exceptions.BbddException;
import es.iespuertodelacruz.bait.modelo.mysql.SQLCliente;

public class AppTest {
    SQLCliente sqlCliente;
    Cliente cliente;

    @BeforeEach
    public void setUp() {
        if (sqlCliente == null) {
            sqlCliente = new SQLCliente("org.sqlite.JDBC", "jdbc:sqlite:bait.db", null, null);
        }
    }

    @Test
    public void insertarTest() {
        cliente = new Cliente("78648922P", "Jonay", "Hernandez Izquierdo", "Ubr La Arbeja nº8", 0f);
        try {
            sqlCliente.insertar(cliente);
        } catch (BbddException | SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void eliminarTest() {
        String dni = "78648922P";
        try {
            sqlCliente.eliminar(dni);
        } catch (SQLException | BbddException e) {
            fail(e.getMessage());
        }
    }
}
