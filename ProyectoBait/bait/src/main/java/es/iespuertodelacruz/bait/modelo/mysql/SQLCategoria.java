package es.iespuertodelacruz.bait.modelo.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import es.iespuertodelacruz.bait.api.productos.Categoria;
import es.iespuertodelacruz.bait.api.productos.Producto;
import es.iespuertodelacruz.bait.exceptions.BbddException;

public class SQLCategoria extends Bbdd{
    private static UtilidadesSQL utilidadesSQL = new UtilidadesSQL("Categoria","idCategoria,nombre");

    /**
     * Contructor basico de la clase
     */        
    public SQLCategoria(String driver, String url, String usuario, String password) {
        super(driver, url, usuario, password);
    }

    /**
     * Metodo que inserta una Categoria en la base de datos 
     * @param categoria que va a insertar en la base de datos
     * @throws SQLException error controlado
     * @throws BbddException error controlado
     */
    public void inserta(Categoria categoria) throws SQLException, BbddException{
        Connection connection;
        PreparedStatement preparedStatement;

        connection = getConnection();
        preparedStatement = connection.prepareStatement(utilidadesSQL.getINSERT());
        preparedStatement.setString(1, categoria.getIdCategoria());
        preparedStatement.setString(2, categoria.getNombre());

        preparedStatement.executeUpdate();

        closeConnection(connection, preparedStatement, null);
    }

    /**
     * Metodo encargado de eliminar una categoria en la base de datos
     * @param idCategoria identificador de la categoria
     * @throws SQLException error a controlar
     * @throws BbddException error a controlar
     */
    public void eliminar(String idCategoria) throws SQLException, BbddException{
        Connection connection;
        PreparedStatement preparedStatement;

        connection = getConnection();
        preparedStatement = connection.prepareStatement(utilidadesSQL.setDelete("idCategoria"));
        preparedStatement.setString(1, idCategoria);

        preparedStatement.executeUpdate();

        closeConnection(connection, preparedStatement, null);
    }

    /**
     * Metodo que modifica un campo en concreto de la base de datos
     * @param categoria con los nuevos cambios que se van a actualizar
     * @throws BbddException error a cotrolar
     * @throws SQLException error a controlar
     */
    public void modificar(Categoria categoria) throws BbddException, SQLException{
        Connection connection;
        PreparedStatement preparedStatement;
        
        connection = getConnection();
        preparedStatement = connection.prepareStatement(utilidadesSQL.setUpdate());
        preparedStatement.setString(1, categoria.getIdCategoria());
        preparedStatement.setString(2, categoria.getNombre());

        preparedStatement.executeUpdate();
        closeConnection(connection, preparedStatement, null);
    }


    /**
     * Funcion que busca una categoria en la base de datos y la devuelve
     * @param idCategoria identificador de la categoria
     * @return la categoria buscada
     * @throws BbddException error controlado
     * @throws SQLException error controlado
     */
    public Categoria buscar(String idCategoria) throws BbddException, SQLException {
        Connection connection;
        Statement statement;
        ResultSet resultSet;

        connection = getConnection();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(utilidadesSQL.setSelectOne(idCategoria));

        String nombre = resultSet.getString("idCategoria");
        String apellidos = resultSet.getString("nombre");

        Categoria categoria = new Categoria(idCategoria, nombre);

        closeConnection(connection, statement, resultSet);

        return categoria;
    }

    /**
     * Funcion que obtiene un listado de las categorias y las devuelve
     * @return la lista con todas la categorias
     * @throws SQLException error controlado
     * @throws BbddException error controlado
     */
    public ArrayList<Categoria> obtenerListado() throws SQLException, BbddException {
        Connection connection;
        ArrayList<Categoria> categorias = new ArrayList<>();
        ResultSet resultSet;
        Statement statement;

        connection = getConnection();
        statement = connection.createStatement();
        statement.setMaxRows(30);

        resultSet = statement.executeQuery(utilidadesSQL.getSELECTALL());
        while (resultSet.next()) {
            String idCategoria = resultSet.getString("dni");
            String nombre = resultSet.getString("nombre");

            Categoria categoria = new Categoria(idCategoria, nombre);
            categorias.add(categoria);;
        }

        closeConnection(connection, statement, resultSet);
        
        return categorias;
    }

}
