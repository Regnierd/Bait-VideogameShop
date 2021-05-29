package es.iespuertodelacruz.bait.modelo.movimientosModelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import es.iespuertodelacruz.bait.api.movimientos.Compra;
import es.iespuertodelacruz.bait.api.movimientos.Pedido;
import es.iespuertodelacruz.bait.exceptions.PersistenciaException;
import es.iespuertodelacruz.bait.modelo.mysql.BbddSqlite;
import es.iespuertodelacruz.bait.modelo.mysql.UtilidadesSQL;

public class CompraModelo {
    private static final String ID_COMPRA = "idCompra";
    public static final String TABLE_NAME = "COMPRAS";
    UtilidadesSQL utilidadesSQL = new UtilidadesSQL(TABLE_NAME, ID_COMPRA + ", totalCompra, idPedido");
    BbddSqlite persistencia;
    PedidoModelo pedidoModelo;

    /**
     * Construcor basico de la clase
     * 
     * @throws PersistenciaException
     */
    public CompraModelo() throws PersistenciaException {
        persistencia = new BbddSqlite(TABLE_NAME, null, null);
        pedidoModelo = new PedidoModelo();
    }

    /**
     * Metodo que inserta una compra en la base de datos
     * 
     * @param compra que va a insertar
     * @throws PersistenciaException error a controlar
     */
    public void insertar(Compra compra) throws PersistenciaException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = persistencia.getConnection();
            preparedStatement = connection.prepareStatement(utilidadesSQL.getINSERT());
            preparedStatement.setString(1, compra.getIdCompra());
            preparedStatement.setFloat(2, compra.getTotalCompra());
            preparedStatement.setString(3, compra.getPedido().getIdPedido());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new PersistenciaException("Ha ocurrido un error al insertar una compra", e);
        } finally {
            persistencia.closeConnection(connection, preparedStatement, null);
        }

    }

    /**
     * Metodo encargado de eliminar una compra en la base de datos
     * 
     * @param idCompra de la compra
     * @throws PersistenciaException error a controlar
     */
    public void eliminar(String idCompra) throws PersistenciaException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = persistencia.getConnection();
            preparedStatement = connection.prepareStatement(utilidadesSQL.setDelete(ID_COMPRA));
            preparedStatement.setString(1, idCompra);

            preparedStatement.executeUpdate();

        } catch (Exception e) {
            throw new PersistenciaException("Ha ocurrido un error al eliminar una compra", e);
        } finally {
            persistencia.closeConnection(connection, preparedStatement, null);
        }

    }

    /**
     * Metodo que modifica un campo en concreto de la base datos
     * 
     * @param compra con los nuevos cambios
     * @throws PersistenciaException error a controlar
     */
    public void modificar(Compra compra) throws PersistenciaException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = persistencia.getConnection();
            preparedStatement = connection.prepareStatement(utilidadesSQL.setUpdate());
            preparedStatement.setString(1, compra.getIdCompra());
            preparedStatement.setFloat(2, compra.getTotalCompra());
            preparedStatement.setString(3, compra.getPedido().getIdPedido());
            preparedStatement.setString(4, compra.getIdCompra());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new PersistenciaException("Ha ocurrido un error al modificar una compra", e);
        } finally {
            persistencia.closeConnection(connection, preparedStatement, null);
        }

    }

    /**
     * Funcion que realiza un consulta y devuelve una lista de compra
     * 
     * @param sql   consulta que se va a realizar
     * @param valor del campo patron
     * @return una lista de compras
     * @throws PersistenciaException error a controlar
     */
    private ArrayList<Compra> buscarPorElemento(String sql, String valor) throws PersistenciaException {
        ResultSet resultSet;
        ArrayList<Compra> lista = new ArrayList<>();

        resultSet = persistencia.buscarElemento(sql, valor);

        try {
            while (resultSet.next()) {
                String idCompra = resultSet.getString(ID_COMPRA);
                float totalCompra = resultSet.getFloat("totalCompra");
                String idPedido = resultSet.getString("idPedido");

                Pedido pedido = pedidoModelo.buscaPorIdentificador(idPedido);

                Compra compra = new Compra(idCompra, totalCompra, pedido);
                lista.add(compra);
            }
        } catch (SQLException e) {
            throw new PersistenciaException(e.getMessage());
        }

        return lista;

    }

    /**
     * Funcion que obtiene un listado de las compras y los devuelve
     * 
     * @return la lista de compras
     * @throws PersistenciaException error a controlar
     */
    public ArrayList<Compra> obtenerListado() throws PersistenciaException {
        ArrayList<Compra> lista;
        String sql = utilidadesSQL.getSELECTALL();

        lista = buscarPorElemento(sql, "");

        return lista;
    }

    /**
     * Funcion que busca una compra por su identificador
     * 
     * @param identificador de la compra que se va abuscar
     * @return la compra encontrada
     * @throws PersistenciaException error a controlar
     */
    public Compra buscaPorIdentificador(String identificador) throws PersistenciaException {
        ArrayList<Compra> lista;
        Compra compra = null;
        String sql = utilidadesSQL.setSelectOne(ID_COMPRA);
        lista = buscarPorElemento(sql, identificador);

        if (!lista.isEmpty()) {
            compra = lista.get(0);
        }

        return compra;
    }
}
