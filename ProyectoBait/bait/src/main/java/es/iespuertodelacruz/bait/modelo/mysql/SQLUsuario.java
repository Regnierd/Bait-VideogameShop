package es.iespuertodelacruz.bait.modelo.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import es.iespuertodelacruz.bait.api.personas.Usuario;
import es.iespuertodelacruz.bait.exceptions.BbddException;

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
     * @throws BbddException error a controlar
     * @throws SQLException  error a controlar
     */
    public void insertar(Usuario usuario) throws BbddException, SQLException {
        Connection connection;
        PreparedStatement preparedStatement;

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

        closeConnection(connection, preparedStatement, null);
    }

    /**
     * Metodo encargado de eliminar un usuario en la base de datos
     * 
     * @param dni del usuario
     * @throws SQLException  error a controlar
     * @throws BbddException error a controlar
     */
    public void eliminar(String dni) throws SQLException, BbddException {
        Connection connection;
        PreparedStatement preparedStatement;

        connection = getConnection();
        preparedStatement = connection.prepareStatement(utilidadesSQL.setDelete("dni"));
        preparedStatement.setString(1, dni);

        preparedStatement.executeUpdate();

        closeConnection(connection, preparedStatement, null);
    }

    public void modificar(Usuario usuario) throws BbddException, SQLException {
        Connection connection;
        PreparedStatement preparedStatement;

        connection = getConnection();
        preparedStatement = connection.prepareStatement(utilidadesSQL.setUpdate());
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
        closeConnection(connection, preparedStatement, null);
    }

    /**
     * Funcion que busca un usuario en la base de datos y lo deveulve
     * @param dni del usuario que se va a buscar
     * @return 
     * @throws BbddException
     * @throws SQLException
     */
    public Usuario buscar(String dni) throws BbddException, SQLException {
        Connection connection;
        Statement statement;
        ResultSet resultSet;

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
        Usuario usuario = new Usuario(dni, nombre, apellidos, email, direccion, telefono, pais, codigoPostal, provincia, nombreUsuario, password, rol, saldo);

        closeConnection(connection, statement, resultSet);

        return usuario;
    }

    /**
     * Funcion que obtiene un listado de los usuario y los deveuvle
     * @return la lista de usaurio
     * @throws SQLException error a controlar
     * @throws BbddException error a controlar
     */
    public ArrayList<Usuario> obtenerListado() throws SQLException, BbddException {
        Connection connection;
        ArrayList<Usuario> usuarios = new ArrayList<>();
        ResultSet resultSet;
        Statement statement;

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

        closeConnection(connection, statement, resultSet);
        
        return usuarios;
    }
}
