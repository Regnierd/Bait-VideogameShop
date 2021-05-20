package es.iespuertodelacruz.bait.modelo.personasModelo;

import java.util.ArrayList;

import es.iespuertodelacruz.bait.api.personas.Usuario;
import es.iespuertodelacruz.bait.exceptions.PersistenciaException;
import es.iespuertodelacruz.bait.modelo.mysql.SQL.SQLUsuario;

public class UsuarioModelo {
    SQLUsuario persistencia;

    /**
     * Constructor basico de la clase
     */
    public UsuarioModelo() {
        persistencia = new SQLUsuario("org.sqlite.JDBC", "jdbc:sqlite:bait.db", null, null);
    }

    /**
     * Metodo que inserta un usuario en la base de datos
     * @param usuario que se va a insertar en la base de datos
     * @throws PersistenciaException error a controlar
     */
    public void insertar(Usuario usuario) throws PersistenciaException {
        persistencia.insertar(usuario);
    }

    /**
     * Metodo que elimina un usuario en la base datos
     * @param dni del usuario que se va a borrar
     * @throws PersistenciaException error a controlar
     */
    public void eliminar(String dni) throws PersistenciaException {
        persistencia.eliminar(dni);
    }

    /**
     * Funcion que buscca un usuario por su dni en la base datos
     * @param dni del usaurio que se va a buscar
     * @return el usuario encontrado
     * @throws PersistenciaException error a controlar
     */
    public Usuario buscar(String dni) throws PersistenciaException {
        Usuario usuario = null;
        usuario = persistencia.buscaPorDni(dni);

        return usuario;
    }

    /**
     * Funcion que busca un usuario por su nombreUsuario en la base de datos
     * @param nombreUsuario del usuario a buscar
     * @return el usuario encontrado
     * @throws PersistenciaException error a controlar
     */
    public Usuario login(String nombreUsuario) throws PersistenciaException {
        Usuario usuario = null;
        usuario = persistencia.buscaPorNombreUsuario(nombreUsuario);

        return usuario;
    }
    
    /**
     * Metodo que modifica un usuario en la base datos
     * @param usuario con los nuevos cambios
     * @throws PersistenciaException error a controlar
     */
    public void modificar(Usuario usuario) throws PersistenciaException {
        persistencia.modificar(usuario);
    }

    public ArrayList<Usuario> obtenerListado() throws PersistenciaException {
        ArrayList<Usuario> usuarios;
        usuarios = persistencia.obtenerListado();

        return usuarios;
    }

}
