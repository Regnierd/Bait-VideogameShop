package es.iespuertodelacruz.bait.modelo.movimientosModelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import es.iespuertodelacruz.bait.api.movimientos.Pedido;
import es.iespuertodelacruz.bait.api.personas.Usuario;
import es.iespuertodelacruz.bait.api.productos.Producto;
import es.iespuertodelacruz.bait.exceptions.PersistenciaException;
import es.iespuertodelacruz.bait.modelo.mysql.BbddSqlite;
import es.iespuertodelacruz.bait.modelo.mysql.UtilidadesSQL;
import es.iespuertodelacruz.bait.modelo.personasModelo.UsuarioModelo;
import es.iespuertodelacruz.bait.modelo.productosModelo.ProductoModelo;

public class PedidoModelo {
    private static final String ID_PEDIDO = "idPedido";
    public static final String TABLE_NAME = "PEDIDOS";
    private static UtilidadesSQL utilidadesSQL = new UtilidadesSQL(TABLE_NAME, "idPedido,unidades,total,fechaPedido,idCliente,idProducto");
    BbddSqlite persistencia;
    UsuarioModelo usuarioModelo;
    ProductoModelo productoModelo;

    /**
     * Constructor basico de la clase
     * @throws PersistenciaException
     */
    public PedidoModelo() throws PersistenciaException{     
        usuarioModelo = new UsuarioModelo();
        productoModelo = new ProductoModelo();
        persistencia = new BbddSqlite(TABLE_NAME,null, null);
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

        connection = persistencia.getConnection();
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
            persistencia.closeConnection(connection, preparedStatement, null);
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
            connection = persistencia.getConnection();
            preparedStatement = connection.prepareStatement(utilidadesSQL.setDelete(ID_PEDIDO));
            preparedStatement.setString(1, idPedido);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenciaException("Ha ocurrido un error al eliminar el pedido", e);
        }finally{
            persistencia.closeConnection(connection, preparedStatement, null);
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
            connection = persistencia.getConnection();
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
            persistencia.closeConnection(connection, preparedStatement, null);
        }
        
    }

    /**
     * Funcion que busca un pedido por su identficador
     * @param identificador del pedido que se va a buscar
     * @return el pedido encontrado
     * @throws PersistenciaException error a controlar
     */
    public Pedido buscaPorIdentificador(String identificador) throws PersistenciaException {
        ArrayList<Pedido> lista;
        Pedido pedido = null;
        String sql = utilidadesSQL.setSelectOne(ID_PEDIDO);
        lista = buscarPorElemento(sql, identificador); 

        if (!lista.isEmpty()) {
            pedido = lista.get(0);
        }
        
        return pedido;
    }

    /**
     * Funcion que realiza un consulta y devuelve una lista de pedidos
     * @param sql consulta que se va a realizar
     * @param valor del campo patron
     * @return una lista de pedidos 
     * @throws PersistenciaException error a controlar
     */
    private ArrayList<Pedido> buscarPorElemento(String sql, String valor) throws PersistenciaException{
        ResultSet resultSet;
        ArrayList<Pedido> lista = new ArrayList<>();

        resultSet = persistencia.buscarElemento(sql, valor);
        try {
            while (resultSet.next()){
                String idPedido = resultSet.getString(ID_PEDIDO);
                int unidades = resultSet.getInt("unidades");
                float total = resultSet.getFloat("total");
                String fechaPedido = resultSet.getString("fechaPedido");
                String idCliente = resultSet.getString("idCliente");
                String idProducto = resultSet.getString("idProducto");
                
                Usuario cliente = usuarioModelo.buscaPorDni(idCliente);
                Producto producto = productoModelo.buscarPorId(idProducto);
                Pedido pedido = new Pedido(idPedido, unidades, total, fechaPedido, cliente, producto);
                lista.add(pedido);
            }
        } catch (SQLException e) {
            throw new PersistenciaException(e.getMessage());
        }

        return lista;

    }


    /**
     * Funcion que obtiene un listado de los usuarios y los devuelve
     * @return la lista de pedidos
     * @throws PersistenciaException error a controlar
    */
    public ArrayList<Pedido> obtenerListado() throws PersistenciaException {
        ArrayList<Pedido> lista;
        String sql = utilidadesSQL.getSELECTALL();

        lista = buscarPorElemento(sql, "");

        return lista;
    }

}
