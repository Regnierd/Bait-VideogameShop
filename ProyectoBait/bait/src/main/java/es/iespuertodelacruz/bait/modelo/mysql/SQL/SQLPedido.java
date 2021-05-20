package es.iespuertodelacruz.bait.modelo.mysql.SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import es.iespuertodelacruz.bait.api.movimientos.Pedido;
import es.iespuertodelacruz.bait.api.personas.Usuario;
import es.iespuertodelacruz.bait.api.productos.Producto;
import es.iespuertodelacruz.bait.exceptions.PersistenciaException;
import es.iespuertodelacruz.bait.modelo.mysql.Bbdd;

public class SQLPedido extends Bbdd {
    private static UtilidadesSQL utilidadesSQL = new UtilidadesSQL("Pedido", "idPedido,unidades,total,fechaPedido,idCliente,idProducto");
    SQLUsuario sqlUsuario;
    SQLProducto sqlProducto;

    /**
     * Contructor basico de la clase
     */
    public SQLPedido(String driver, String url, String usuario, String password) {
        super(driver, url, usuario, password);
        sqlProducto = new SQLProducto(driver, url, usuario, password);
        sqlUsuario = new SQLUsuario(driver, url, usuario, password);
    }

    /**
     * Metodo que inserta un pedido en la base de datos
     * 
     * @param pedido que va a insertar en la base de datos
     * @throws PersistenciaException error a controlar
     */
    public void insertar(Pedido pedido) throws PersistenciaException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        connection = getConnection();
        try {
            preparedStatement = connection.prepareStatement(utilidadesSQL.getINSERT());
            preparedStatement.setString(1, pedido.getIdPedido());
            preparedStatement.setInt(2, pedido.getUnidades());
            preparedStatement.setFloat(3, pedido.getTotal());
            preparedStatement.setString(4, pedido.getFechaPedido());
            preparedStatement.setString(5, pedido.getCliente().getDni());
            preparedStatement.setString(6, pedido.getProducto().getIdProducto());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenciaException("Ha ocurrido un error al insertar el pedido.", e);
        }finally{
            closeConnection(connection, preparedStatement, null);
        }

        
    }

    /**
     * Metodo encargado de eliminar un pedido en la base de datos
     * 
     * @param idPedido identificador del pedido
     * @param PersistenciaException error en caso de no poder eliminar
     */
    public void eliminar(String idPedido) throws PersistenciaException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(utilidadesSQL.setDelete("idPedido"));
            preparedStatement.setString(1, idPedido);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenciaException("Ha ocurrido un error al eliminar el pedido", e);
        }finally{
            closeConnection(connection, preparedStatement, null);
        }

        
    }

    /**
     * Metodo que modifica un campo en concreto de la base de datos
     * 
     * @param pedido modificado
     * @throws PersistenciaException error controlado
     */
    public void modificar(Pedido pedido) throws PersistenciaException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(utilidadesSQL.setUpdate());
            preparedStatement.setString(1, pedido.getIdPedido());
            preparedStatement.setInt(2, pedido.getUnidades());
            preparedStatement.setFloat(3, pedido.getTotal());
            preparedStatement.setString(4, pedido.getFechaPedido());
            preparedStatement.setString(5, pedido.getCliente().getDni());
            preparedStatement.setString(6, pedido.getProducto().getIdProducto());
            preparedStatement.setString(7, pedido.getIdPedido());
            
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenciaException("Ha courrido un error al modificar la categoria", e);
        }finally{
            closeConnection(connection, preparedStatement, null);
        }
        
    }

    /**
     * Funcion que busca un pedido en la base de datos y la devuelve
     * 
     * @param idPedido identificador del pedido
     * @return el pedido buscado
     * @throws persistenciaException error controlado
     */
    public Pedido buscar(String idPedido) throws PersistenciaException {
        Connection connection = null;
        ResultSet resultSet = null;
        Pedido pedido = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(utilidadesSQL.setSelectOne("idPedido"));
            preparedStatement.setString(1, idPedido);
            resultSet = preparedStatement.executeQuery();

            int unidades = resultSet.getInt("unidades");
            float total = resultSet.getFloat("total");
            String fechaPedido = resultSet.getString("fechaPedido");
            String dni = resultSet.getString("dni");
            String idProducto = resultSet.getString("idProducto");

            Usuario usuario = sqlUsuario.buscaPorDni(dni);
            Producto producto = sqlProducto.buscar(idProducto);

            pedido = new Pedido(idPedido, unidades, total, fechaPedido, usuario, producto);
            
        } catch (SQLException e) {
            throw new PersistenciaException("Ha ocurrido un error al buscar el pedido", e);
        } finally {
            closeConnection(connection, preparedStatement, resultSet);
        }

        return pedido;
    }

    /**
     * Funcion que obtiene un listado de los pedidos de un usuario en concreto
     * @param dni de la persona que se van a obtener su lista de pedidos
     * @return la lista de pedidos del usuario
     * @throws PersistenciaException error controlado
     */
    public ArrayList<Pedido> obtenerListado(String dni) throws PersistenciaException{
        Connection connection = null;
        ArrayList<Pedido> pedidos = new ArrayList<>();
        ResultSet resultSet = null;
        Statement statement = null;
        Pedido pedido;

        try {
            connection = getConnection();
            statement = connection.createStatement();
            statement.setMaxRows(30);

            resultSet = statement.executeQuery(utilidadesSQL.setSelectOne(dni));
            while (resultSet.next()) {
                String idPedido = resultSet.getString("idPedido");
                int unidades = resultSet.getInt("unidades");
                float total = resultSet.getFloat("total");
                String fechaPedido = resultSet.getString("fechaPedido");
                String idProducto = resultSet.getString("idProducto");
    
                Usuario usuario = sqlUsuario.buscaPorDni(dni);
                Producto producto = sqlProducto.buscar(idProducto);
    
                pedido = new Pedido(idPedido, unidades, total, fechaPedido, usuario, producto);
                pedidos.add(pedido);
            }               
        } catch (SQLException e) {
            throw new PersistenciaException("Ha ocurrido un error al obtener el listado de pedidos", e);
        }finally{
            closeConnection(connection, statement, resultSet);
        }
        return pedidos;
    }

}