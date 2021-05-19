package es.iespuertodelacruz.bait.modelo.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import es.iespuertodelacruz.bait.api.personas.Usuario;
import es.iespuertodelacruz.bait.exceptions.PersistenciaException;


public class SQLUsuario extends Bbdd {
    private static UtilidadesSQL utilidadesSQL = new UtilidadesSQL("Cliente", "dni, nombre, apellidos, email, direccion"
            + ", telefono, pais, codigoPostal, provincia, nombreUsuario, password, rol, saldo");

    /**
     * Constructor basico de la clase
     */
    public SQLUsuario(String driver, String url, String usuario, String password) {
        super(driver, url, usuario, password);
    }

    /**
     * Metodo que inserta un Usuario en la base de datos
     * 
     * @param usuario que va a insertar en la base de datos
     * @throws PersistenciaException error a controlar
     */
    public void insertar(Usuario usuario) throws PersistenciaException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
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
            closeConnection(connection, preparedStatement, null);
        }
    
    }

    /**
     * Metodo encargado de eliminar un usuario en la base de datos
     * 
     * @param dni del usuario
     * @throws PersistenciaException error a controlar
     */
    public void eliminar(String dni) throws PersistenciaException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(utilidadesSQL.setDelete("dni"));
            preparedStatement.setString(1, dni);

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new PersistenciaException("Ha ocurrido un error al eliminar un usuario", e);
        }finally{
            closeConnection(connection, preparedStatement, null);
        }

    }

    /**
     * Metodo que modifica un campo en conreto de la base datos
     * @param usuario usuario con los nuevos cambios
     * @throws PersistenciaException error a controlar
     */
    public void modificar(Usuario usuario) throws PersistenciaException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
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
            closeConnection(connection, preparedStatement, null);
        }
        
    }

    /**
     * Funcion que busca un usuario en la base de datos y lo devuelve
     * @param dni del usuario que se va a buscar
     * @return 
     * @throws PersistenciaException error a controlar
     */
    public Usuario buscar(String dni) throws PersistenciaException{
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Usuario usuario;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(utilidadesSQL.setSelectOne(dni));

            String nombre = resultSet.getString("nombre ");
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
        } catch (Exception e) {
            throw new PersistenciaException("Ha ocurrido un error al buscar un usuario", e);
        }finally{
            closeConnection(connection, statement, resultSet);
        }  

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
            connection = getConnection();
            statement = connection.createStatement();
            statement.setMaxRows(30);

            resultSet = statement.executeQuery(utilidadesSQL.getSELECTALL());
            while (resultSet.next()) {
                String dni = resultSet.getString("dni");
                String nombre = resultSet.getString("nombre ");
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
            closeConnection(connection, statement, resultSet);
        }

        return usuarios;
    }
}