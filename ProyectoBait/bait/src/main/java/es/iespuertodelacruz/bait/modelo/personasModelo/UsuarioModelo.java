package es.iespuertodelacruz.bait.modelo.personasModelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import es.iespuertodelacruz.bait.api.personas.Usuario;
import es.iespuertodelacruz.bait.exceptions.PersistenciaException;
import es.iespuertodelacruz.bait.modelo.mysql.BbddSqlite;
import es.iespuertodelacruz.bait.modelo.mysql.UtilidadesSQL;
public class UsuarioModelo {
    BbddSqlite persistencia;
    private static UtilidadesSQL utilidadesSQL = new UtilidadesSQL("Usuario", "dni, nombre, apellidos, email, direccion"
    + ", telefono, pais, codigoPostal, provincia, nombreUsuario, password, rol, saldo");

    /**
     * Constructor basico de la clase
     * @throws PersistenciaException
     */
    public UsuarioModelo() throws PersistenciaException {
        persistencia = new BbddSqlite(null, null);
    }

    /**
     * Metodo que inserta un usuario en la base de datos
     * @param usuario que se va a insertar en la base de datos
     * @throws PersistenciaException error a controlar
     */
    public void insertar(Usuario usuario) throws PersistenciaException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = persistencia.getConnection();
            preparedStatement = connection.prepareStatement(utilidadesSQL.getINSERT());
            preparedStatement.setString(1, usuario.getDni());
            preparedStatement.setString(2, usuario.getNombre());
            preparedStatement.setString(3, usuario.getApellidos());
            preparedStatement.setString(4, usuario.getEmail());
            preparedStatement.setString(5, usuario.getDireccion());
            preparedStatement.setString(6, usuario.getTelefono());
            preparedStatement.setString(7, usuario.getPais());
            preparedStatement.setString(8, usuario.getCodigoPostal());
            preparedStatement.setString(9, usuario.getProvincia());
            preparedStatement.setString(10, usuario.getNombreUsuario());
            preparedStatement.setString(11, usuario.getPassword());
            preparedStatement.setString(12, usuario.getRol());
            preparedStatement.setFloat(13, usuario.getSaldo());
            
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new PersistenciaException("Ha ocurrido un error al insertar un usuario", e);
        }finally{
            persistencia.closeConnection(connection, preparedStatement, null);
        }
    }

    /**
     * Metodo que elimina un usuario en la base datos
     * @param dni del usuario que se va a borrar
     * @throws PersistenciaException error a controlar
     */
    public void eliminar(String dni) throws PersistenciaException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = persistencia.getConnection();
            preparedStatement = connection.prepareStatement(utilidadesSQL.setDelete("dni"));
            preparedStatement.setString(1, dni);

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new PersistenciaException("Ha ocurrido un error al eliminar un usuario", e);
        }finally{
            persistencia.closeConnection(connection, preparedStatement, null);
        }
    }

    /**
     * Funcion que busca un usuario en la base de datos y lo devuelve
     * @param dni del usuario que se va a buscar
     * @return
     * @throws PersistenciaException error a controlar
     */
    public Usuario buscar(String parametro, String valor) throws PersistenciaException{
        Connection connection = null;    
        ResultSet resultSet = null;
        Usuario usuario;    
        PreparedStatement preparedStatement = null;

        try {
            connection = persistencia.getConnection();
            preparedStatement = connection.prepareStatement(utilidadesSQL.setSelectOne(parametro));
            preparedStatement.setString(1, valor);
            resultSet = preparedStatement.executeQuery();

            String dni = resultSet.getString("dni");
            String nombre = resultSet.getString("nombre");
            String apellidos = resultSet.getString("apellidos");
            String email = resultSet.getString("email");
            String direccion = resultSet.getString("direccion");
            String telefono = resultSet.getString("telefono");
            String pais = resultSet.getString("pais");
            String codigoPostal = resultSet.getString("codigoPostal");
            String provincia = resultSet.getString("provincia");
            String nombreUsuario = resultSet.getString("nombreUsuario");
            String password = resultSet.getString("password");
            String rol = resultSet.getString("rol");
            Float saldo = resultSet.getFloat("saldo");
            usuario = new Usuario(dni, nombre, apellidos, email, direccion, telefono, pais, codigoPostal, provincia, nombreUsuario, password, rol, saldo);
            
        } catch (SQLException e) {
            throw new PersistenciaException("Ha ocurrido un error al buscar al usuario", e);
        } finally {
            persistencia.closeConnection(connection, preparedStatement, resultSet);
        }

        return usuario;
    }

        /**
     * Funcion que busca un usuario por su dni
     * @param dni de la persona que se va abuscar
     * @return el usuario encontrado
     * @throws PersistenciaException error a controlar
     */
    public Usuario buscaPorDni(String dni) throws PersistenciaException {
        Usuario usuario;

        usuario = buscar("dni", dni);

        return usuario;
    }

    /**
     * Funcion que buscar un usuario por su nombre de usuario
     * @param nombreUsuario del usuario que se va a buscar
     * @return el usuario encontrado
     * @throws PersistenciaException error a controlar
     */
    public Usuario buscaPorNombreUsuario(String nombreUsuario) throws PersistenciaException {
        Usuario usuario;

        usuario = buscar("nombreUsuario", nombreUsuario);

        return usuario;
    }

        /**
     * Funcion que obtiene un listado de los usuarios y los devuelve
     * @return la lista de usaurio
     * @throws PersistenciaException error a controlar
     */
    public ArrayList<Usuario> obtenerListado() throws PersistenciaException {
        Connection connection = null;
        ArrayList<Usuario> usuarios = new ArrayList<>();
        ResultSet resultSet = null;
        Statement statement = null;

        try {
            connection = persistencia.getConnection();
            statement = connection.createStatement();
            statement.setMaxRows(30);

            resultSet = statement.executeQuery(utilidadesSQL.getSELECTALL());
            while (resultSet.next()) {
                String dni = resultSet.getString("dni");
                String nombre = resultSet.getString("nombre");
                String apellidos = resultSet.getString("apellidos");
                String email = resultSet.getString("email");
                String direccion = resultSet.getString("direccion");
                String telefono = resultSet.getString("telefono");
                String pais = resultSet.getString("pais");
                String codigoPostal = resultSet.getString("codigoPostal");
                String provincia = resultSet.getString("provincia");
                String nombreUsuario = resultSet.getString("nombreUsuario");
                String password = resultSet.getString("password");
                String rol = resultSet.getString("rol");
                Float saldo = resultSet.getFloat("saldo");
                Usuario usuario = new Usuario(dni, nombre, apellidos, email, direccion, telefono, pais, codigoPostal,
                        provincia, nombreUsuario, password, rol, saldo);
                usuarios.add(usuario);
            }
        } catch (Exception e) {
            throw new PersistenciaException("Ha ocurrido un error al obtener toda la lista de usuarios", e);
        }finally{
            persistencia.closeConnection(connection, statement, resultSet);
        }

        return usuarios;
    }

    /**
     * Funcion que busca un usuario por su nombreUsuario en la base de datos
     * @param nombreUsuario del usuario a buscar
     * @return el usuario encontrado
     * @throws PersistenciaException error a controlar
     */
    public Usuario login(String nombreUsuario) throws PersistenciaException {
        Usuario usuario = null;
        usuario = buscaPorNombreUsuario(nombreUsuario);

        return usuario;
    }
    
    /**
     * Metodo que modifica un usuario en la base datos
     * @param usuario con los nuevos cambios
     * @throws PersistenciaException error a controlar
     */
    public void modificar(Usuario usuario) throws PersistenciaException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = persistencia.getConnection();
            preparedStatement = connection.prepareStatement(utilidadesSQL.setUpdate());
            preparedStatement.setString(1, usuario.getDni());
            preparedStatement.setString(2, usuario.getNombre());
            preparedStatement.setString(3, usuario.getApellidos());
            preparedStatement.setString(4, usuario.getEmail());
            preparedStatement.setString(5, usuario.getDireccion());
            preparedStatement.setString(6, usuario.getTelefono());
            preparedStatement.setString(7, usuario.getPais());
            preparedStatement.setString(8, usuario.getCodigoPostal());
            preparedStatement.setString(9, usuario.getProvincia());
            preparedStatement.setString(10, usuario.getNombreUsuario());
            preparedStatement.setString(11, usuario.getPassword());
            preparedStatement.setString(12, usuario.getRol());
            preparedStatement.setFloat(13, usuario.getSaldo());
            preparedStatement.setString(14, usuario.getDni());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new PersistenciaException("Ha ocurrido un error al modificar un usuario", e);
        }finally{
            persistencia.closeConnection(connection, preparedStatement, null);
        }
    }

}
