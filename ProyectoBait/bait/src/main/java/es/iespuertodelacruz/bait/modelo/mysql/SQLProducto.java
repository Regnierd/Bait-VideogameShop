package es.iespuertodelacruz.bait.modelo.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import es.iespuertodelacruz.bait.api.productos.Categoria;
import es.iespuertodelacruz.bait.api.productos.Marca;
import es.iespuertodelacruz.bait.api.productos.Producto;
import es.iespuertodelacruz.bait.exceptions.PersistenciaException;

public class SQLProducto extends Bbdd {
    private static UtilidadesSQL utilidadesSQL = new UtilidadesSQL("Producto","idProducto, nombre, precio, descripcion "
                             + ", stock, idCategoria, idMarca");

    /**
     * Constructor basico de la clase
    * @param driver de la conexion
    * @param url de la base de datos
    * @param usuario de la base de datos
    * @param password de la base de datos
    */
    public SQLProducto(String driver, String url, String usuario, String password) {
        super(driver, url, usuario, password);

    }

    /**
     * Metodo que inserta un producto en la base de datos
     * 
     * @param producto que va a insertar en la base de datos
     * @throws BbddException error a controlar
     * @throws SQLException  error a controlar
     */
    public void insertar(Producto producto) throws PersistenciaExceptionn {
        Connection connection;
        PreparedStatement preparedStatement;
        connection = getConnection();
        preparedStatement = connection.prepareStatement(utilidadesSQL.getINSERT());
        preparedStatement.setString(1, producto.getIdProducto());
        preparedStatement.setString(2, producto.getNombre());
        preparedStatement.setFloat(3, producto.getPrecio());
        preparedStatement.setString(4, producto.getDescripcion());
        preparedStatement.setInt(5, producto.getStock());
        preparedStatement.setString(6, producto.getCategoria().getIdCategoria());
        preparedStatement.setString(7, producto.getMarca().getIdMarca());

        preparedStatement.executeUpdate();

        closeConnection(connection, preparedStatement, null);
    }

    /**
     * Metodo encargado de eliminar un producto en la base de datos
     * 
     * @param idProducto del producto
     * @throws SQLException  error a controlar
     * @throws BbddException error a controlar
     */
    public void eliminar(String idProducto) throws PersistenciaException {
        Connection connection;
        PreparedStatement preparedStatement;

        connection = getConnection();
        preparedStatement = connection.prepareStatement(utilidadesSQL.setDelete("idProducto"));
        preparedStatement.setString(1, idProducto);

        preparedStatement.executeUpdate();

        closeConnection(connection, preparedStatement, null);
    }

    /**
     * Metodo encargado de modificar un producto en la base de datos
     * @param producto a modificar
     * @throws BbddException error a controlar
     * @throws SQLException error a controlar
     */
    public void modificar(Producto producto) throws PersistenciaException {
        Connection connection;
        PreparedStatement preparedStatement;

        connection = getConnection();
        preparedStatement = connection.prepareStatement(utilidadesSQL.setUpdate());
        preparedStatement.setString(1, producto.getIdProducto());
        preparedStatement.setString(2, producto.getNombre());
        preparedStatement.setFloat(3, producto.getPrecio());
        preparedStatement.setString(4, producto.getDescripcion());
        preparedStatement.setInt(5, producto.getStock());
        preparedStatement.setString(6, producto.getCategoria().getIdCategoria());
        preparedStatement.setString(7, producto.getMarca().getIdMarca());
        preparedStatement.setString(8, producto.getIdProducto());

        preparedStatement.executeUpdate();
        closeConnection(connection, preparedStatement, null);
    }

    /**
     * Funcion que busca un producto en la base de datos y lo devuelve
     * 
     * @param idProducto del producto que se va a buscar
     * @return producto 
     * @throws BbddException error a controlar
     * @throws SQLException error a controlar
     */
    public Producto buscar(String idProducto) throws PersistenciaException {
        Connection connection;
        Statement statement;
        ResultSet resultSet;
        Producto producto;

        connection = getConnection();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(utilidadesSQL.setSelectOne(idProducto));

        String nombre = resultSet.getString("nombre ");
        float precio = resultSet.getFloat("precio ");
        String descripcion = resultSet.getString("descripcion ");
        int stock = resultSet.getInt("stock ");
        String idCategoria = resultSet.getString("idCategoria ");
        String idMarca = resultSet.getString("idMarca");

        producto = new Producto(idProducto, nombre, precio, descripcion, stock, idCategoria, idMarca); //Producto() esta en rojo

        closeConnection(connection, statement, resultSet);

        return producto;
    }

    /**
     * Funcion que obtiene un listado de los producto y los devuelve
     * 
     * @return la lista de productos
     * @throws SQLException  error a controlar
     * @throws BbddException error a controlar
     */
    public ArrayList<Producto> obtenerListado() throws PersistenciaException {
        Connection connection;
        ArrayList<Producto> productos = new ArrayList<>();
        ResultSet resultSet;
        Statement statement;

        connection = getConnection();
        statement = connection.createStatement();
        statement.setMaxRows(30);

        resultSet = statement.executeQuery(utilidadesSQL.getSELECTALL());

        while (resultSet.next()) {
            String idProducto = resultSet.getString("idProducto");
            String nombre = resultSet.getString("nombre ");
            float precio = resultSet.getFloat("precio ");
            String descripcion = resultSet.getString("descripcion ");
            int stock = resultSet.getInt("stock ");
            String idCategoria = resultSet.getString("idCategoria ");
            String idMarca = resultSet.getString("idMarca");

            Producto producto = new Producto(idProducto, nombre, precio, descripcion, stock, idCategoria, idMarca); //Producto() esta en rojo

            productos.add(producto);
        }

        closeConnection(connection, statement, resultSet);

        return productos;
    }
}