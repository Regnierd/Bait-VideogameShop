package es.iespuertodelacruz.bait.modelo.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import es.iespuertodelacruz.bait.api.productos.Marca;
import es.iespuertodelacruz.bait.exceptions.BbddException;

public class SQLMarca extends Bbdd{
    private static UtilidadesSQL utilidadesSQL = new UtilidadesSQL("Marca", "idMarca, nombre");

    /**
     * Constructor basico de la clase 
     */
    public SQLMarca(String driver, String url, String usuario, String password) {
        super(driver, url, usuario, password);       
    }

    
    /**
     * Metodo que inserta una marca en la base de datos
     * 
     * @param marca que va a insertar en la base de datos
     * @throws BbddException error a controlar
     * @throws SQLException  error a controlar
     */
    public void insertar(Marca marca) throws BbddException, SQLException {
        Connection connection;
        PreparedStatement preparedStatement;

        connection = getConnection();
        preparedStatement = connection.prepareStatement(utilidadesSQL.getINSERT());
        /**
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
        preparedStatement.setFloat(13, usuario.getSaldo());*/
        

        preparedStatement.executeUpdate();

        closeConnection(connection, preparedStatement, null);
    }

    /**
     * Metodo encargado de eliminar una marca en la base de datos
     * 
     * @param idMarca de la marca
     * @throws SQLException  error a controlar
     * @throws BbddException error a controlar
     */
    public void eliminar(String idMarca) throws SQLException, BbddException {
        Connection connection;
        PreparedStatement preparedStatement;

        connection = getConnection();
        preparedStatement = connection.prepareStatement(utilidadesSQL.setDelete("idMarca"));
        preparedStatement.setString(1, idMarca);

        preparedStatement.executeUpdate();

        closeConnection(connection, preparedStatement, null);
    }

    /**
     * Metodo que modifica un campo en concreto de la base datos
     * @param marca con los nuevos cambios
     * @throws BbddException error a controlar
     * @throws SQLException error a controlar
     */
    public void modificar(Marca marca) throws BbddException, SQLException {
        Connection connection;
        PreparedStatement preparedStatement;

        connection = getConnection();
        preparedStatement = connection.prepareStatement(utilidadesSQL.setUpdate());
        /**
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
        preparedStatement.setString(14, usuario.getDni());*/

        preparedStatement.executeUpdate();
        closeConnection(connection, preparedStatement, null);
    }

    /**
     * Funcion que busca una marca en la base de datos y lo devuelve
     * @param idMarca que se va a buscar
     * @return Marca
     * @throws BbddException
     * @throws SQLException
     */
    public Marca buscar(String idMarca) throws BbddException, SQLException {
        Connection connection;
        Statement statement;
        ResultSet resultSet;

        connection = getConnection();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(utilidadesSQL.setSelectOne(idMarca));

        String nombre = resultSet.getString("nombre ");
        Marca marca = new Marca(idMarca, nombre);

        closeConnection(connection, statement, resultSet);

        return marca;
    }

    /**
     * Funcion que obtiene un listado de las marcas y los devuelve
     * @return la lista de marcas
     * @throws SQLException error a controlar
     * @throws BbddException error a controlar
     */
    public ArrayList<Marca> obtenerListado() throws SQLException, BbddException {
        Connection connection;
        ArrayList<Marca> marcas = new ArrayList<>();
        ResultSet resultSet;
        Statement statement;

        connection = getConnection();
        statement = connection.createStatement();
        statement.setMaxRows(30);

        resultSet = statement.executeQuery(utilidadesSQL.getSELECTALL());
        while (resultSet.next()) {
            String idMarca = resultSet.getString("idMarca");
            String nombre = resultSet.getString("nombre ");
            
            Marca marca = new Marca(idMarca, nombre);
            marcas.add(marca);
        }

        closeConnection(connection, statement, resultSet);
        
        return marcas;
    }
    
}
