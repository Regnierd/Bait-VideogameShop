package es.iespuertodelacruz.bait.apiTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.iespuertodelacruz.bait.api.personas.Usuario;

public class UsuarioTest {
    private static final String DNI = "12345678A";
    Usuario usuario = new Usuario();

    @BeforeEach
    public void setUp() {
        usuario = crearUsuario(DNI,"Nombre","apellidos"
        ,"email@gmail.com","direccion","telefono", "pais", "codigoPostal", "provincia","nombreUsuario", "password", "Cliente", 0F);
    }

    @Test
    public void crearTest() {
        assertEquals(DNI,usuario.getDni(),"El usuario no se creao correctamente");
    }

    @Test
    public void mostrarTest() {
        String informacion = usuario.toString();
        assertTrue(informacion.contains(DNI),"No esta mostrado el usuario correcto");
    }

    /**
     * Metodo que crear un usuario y lo devuelve
     * @param dni del usuario
     * @param nombre del usuario
     * @param apellidos del usuario
     * @param email del usuario
     * @param direccion del usuario
     * @param telefono del usuario
     * @param pais del usuario
     * @param codigoPostal del usuario
     * @param provincia del usuario
     * @param nombreUsuario del usuario
     * @param password del usuario
     * @param rol del usuario
     * @param saldo del usuario
     * @return el usuario creado
     */
    private Usuario crearUsuario(String dni, String nombre, String apellidos, String email, String direccion, String telefono, String pais, String codigoPostal, String provincia, String nombreUsuario, String password, String rol, float saldo) {
        usuario.setDni(dni);
        usuario.setNombre(nombre);
        usuario.setApellidos(apellidos);
        usuario.setEmail(email);
        usuario.setDireccion(direccion);
        usuario.setTelefono(telefono);
        usuario.setPais(pais);
        usuario.setCodigoPostal(codigoPostal);
        usuario.setProvincia(provincia);
        usuario.setNombreUsuario(nombreUsuario);
        usuario.setPassword(password);
        usuario.setRol(rol);
        usuario.setSaldo(saldo);

        return new Usuario(dni, nombre, apellidos, email, direccion, telefono, pais, codigoPostal, provincia, nombreUsuario, password, rol, saldo);
    }
}


