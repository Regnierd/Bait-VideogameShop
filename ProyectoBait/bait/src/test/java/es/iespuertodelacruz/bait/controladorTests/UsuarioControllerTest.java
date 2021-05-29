package es.iespuertodelacruz.bait.controladorTests;

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
    private static final String DNI = "12345678A";
    private static final String DNI_INEXISTENTE = "87654321B";
    private static final String PASSWORD_INEXISTENTE = "1234";

    private static final String NOMBRE_USUARIO = "test";
    private static final String PASSWORD = "password";
    private static final String ROL = "Admin";


    UsuarioController usuarioController;
    Usuario usuario;

    @BeforeEach
    public void setUp() {
        if (usuarioController == null) {
            try {
                usuarioController = new UsuarioController();
            } catch (PersistenciaException e) {
                fail(e.getMessage());
            }
        }

        usuario = new Usuario(DNI, "nombre", "apellidos", "email", "direccion", "telefono", "pais", "codigoPostal",
                "provincia", NOMBRE_USUARIO, PASSWORD, ROL, 0f);

        try {
            usuarioController.insertar(usuario);
        } catch (PersistenciaException | ApiException e) {
            fail(e.getMessage());
        }

    }

    @AfterEach
    public void after() {
        try {
            usuarioController.eliminar(DNI);
        } catch (PersistenciaException | ApiException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void insertarErrorTest() {
        try {
            usuarioController.insertar(usuario);
        } catch (PersistenciaException | ApiException e) {
            assertTrue(e.getMessage().contains("ya existe"));
        }
    }

    @Test
    public void eliminarErrorTest() {
        try {
            usuarioController.eliminar(DNI_INEXISTENTE);
        } catch (PersistenciaException | ApiException e) {
            assertTrue(e.getMessage().contains("no existe"));
        }
    }

    @Test
    public void validarTest() {
        Usuario usuarioVacio = new Usuario();
        try {
            usuarioController.validar(usuarioVacio);
        } catch (ApiException e) {
            assertTrue(e.getMessage().contains("nulo o vacio"));
        }

        Usuario usuarioNulo = null;
        try {
            usuarioController.validar(usuarioNulo);
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("El usuario no puede ser nulo"));
        }
    }

    @Test
    public void buscar() {
        Usuario usuarioBuscado;
        try {
            usuarioBuscado = usuarioController.buscar(DNI);
            assertEquals(usuario, usuarioBuscado, "Las usuario deberian ser iguales");
        } catch (PersistenciaException | ApiException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void buscarErrorTest() {
        Usuario usuarioBuscado;
        try {
            usuarioBuscado = usuarioController.buscar(DNI_INEXISTENTE);
        } catch (PersistenciaException | ApiException e) {
            assertTrue(e.getMessage().contains("no existe"));
        }
    }

    @Test
    public void loginTest() {
        Usuario usuarioEncontrado;
        try {
            usuarioEncontrado = usuarioController.login(NOMBRE_USUARIO, PASSWORD, ROL);
            assertEquals(usuario, usuarioEncontrado, "Los usuario deberian ser iguales");
        } catch (PersistenciaException | ApiException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void loginErrorTest() {
        try {
            usuarioController.login(NOMBRE_USUARIO, PASSWORD_INEXISTENTE, ROL);
        } catch (PersistenciaException | ApiException e) {
            assertTrue(e.getMessage().contains("Las credenciales introducidas son incorrectas"));
        }
    }

    @Test
    public void añadirSaldoTest() {
        Usuario usuarioEncontrado;
        try {
            usuarioController.añadirSaldo(usuario, 20);
            usuarioEncontrado = usuarioController.buscar(DNI);
            assertTrue(usuarioEncontrado.getSaldo() == 20, "El saldo no se añadido correctamente");
        } catch (PersistenciaException | ApiException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void añadirSaldoErrorTest() {
        try {
            usuarioController.añadirSaldo(usuario, -10);
        } catch (PersistenciaException | ApiException e) {
            assertTrue(e.getMessage().contains("menor o igual que 0"));
        }
    }

    @Test
    public void modificarTest() {
        Usuario usuarioEncontrado;
        usuario.setNombre("nuevoNombre");
        try {
            usuarioController.modificar(usuario);
            usuarioEncontrado = usuarioController.buscar(DNI);
            assertEquals(usuario, usuarioEncontrado, "Los usuario deberian ser iguales");
        } catch (PersistenciaException | ApiException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void modificarErrorTest() {
        Usuario usuarioInexistente = new Usuario("dni", "nombre", "apellidos", "email", "direccion", "telefono", "pais", "codigoPostal", "provincia", "nombreUsuario", "password", "rol", 0f);
        try {
            usuarioController.modificar(usuarioInexistente);
        } catch (PersistenciaException | ApiException e) {
            assertTrue(e.getMessage().contains("no existe"));
        }
    }

    @Test
    public void obtenerListadoTest() {
        ArrayList<Usuario> lista;
        try {
            lista = usuarioController.obtenerListado();
            assertTrue(lista.contains(usuario), "La lista no contiene el usuario correcto");
        } catch (PersistenciaException | ApiException e) {
            fail(e.getMessage());
        }
    }

    
}
