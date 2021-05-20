package es.iespuertodelacruz.bait.modelo.mysql.SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import es.iespuertodelacruz.bait.api.movimientos.Compra;
import es.iespuertodelacruz.bait.api.movimientos.Pedido;
import es.iespuertodelacruz.bait.exceptions.PersistenciaException;
import es.iespuertodelacruz.bait.modelo.mysql.Bbdd;

public class SQLCompra extends Bbdd {
    UtilidadesSQL utilidadesSQL = new UtilidadesSQL("Compra", "idCompra, totalCompra, idPedido");
    SQLPedido sqlPedido;

    /**
     * Constructor basico de la clase
     */
    public SQLCompra(String driver, String url, String usuario, String password) {
        super(driver, url, usuario, password);
        sqlPedido = new SQLPedido(driver, url, usuario, password);
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
            connection = getConnection();
            preparedStatement = connection.prepareStatement(utilidadesSQL.getINSERT());
            preparedStatement.setString(1, compra.getIdCompra());
            preparedStatement.setFloat(2, compra.getTotalCompra());
            preparedStatement.setString(3, compra.getPedido().getIdPedido());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new PersistenciaException("Ha ocurrido un error al insertar una compra", e);
        } finally {
            closeConnection(connection, preparedStatement, null);
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
            connection = getConnection();
            preparedStatement = connection.prepareStatement(utilidadesSQL.setDelete("idCompra"));
            preparedStatement.setString(1, idCompra);

            preparedStatement.executeUpdate();

        } catch (Exception e) {
            throw new PersistenciaException("Ha ocurrido un error al eliminar una compra", e);
        } finally {
            closeConnection(connection, preparedStatement, null);
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
            connection = getConnection();
            preparedStatement = connection.prepareStatement(utilidadesSQL.setUpdate());
            preparedStatement.setString(1, compra.getIdCompra());
            preparedStatement.setFloat(2, compra.getTotalCompra());
            preparedStatement.setString(3, compra.getPedido().getIdPedido());
            preparedStatement.setString(4, compra.getIdCompra());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new PersistenciaException("Ha ocurrido un error al modificar una compra", e);
        } finally {
            closeConnection(connection, preparedStatement, null);
        }

    }

    /**
     * Funcion que busca una compra en la base de datos y lo devuelve
     * 
     * @param idCompra que se va a buscar
     * @return Compra
     * @throws PersistenciaException error a controlar
     */
    public Compra buscar(String idCompra) throws PersistenciaException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Compra compra;

        try {
            connection = getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(utilidadesSQL.setSelectOne(idCompra));

            float totalCompra = resultSet.getFloat("totalCompra ");
            String idPedido = resultSet.getString("idPedido");

            Pedido pedido = sqlPedido.buscar(idPedido);

            compra = new Compra(idCompra, totalCompra, pedido);
        } catch (Exception e) {
            throw new PersistenciaException("Ha ocurrido un error al buscar una marca", e);
        } finally {
            closeConnection(connection, statement, resultSet);
        }

        return compra;
    }

    /**
     * Funcion que obtiene un listado de las marcas y los devuelve
     * 
     * @return la lista de marcas
     * @throws PersistenciaException error a controlar
     */
    public ArrayList<Compra> obtenerListado() throws PersistenciaException {
        Connection connection = null;
        ArrayList<Compra> compras = new ArrayList<>();
        ResultSet resultSet = null;
        Statement statement = null;
        Compra compra;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            statement.setMaxRows(30);

            resultSet = statement.executeQuery(utilidadesSQL.getSELECTALL());
            while (resultSet.next()) {
                String idCompra = resultSet.getString("idCompra");
                float totalCompra = resultSet.getFloat("totalCompra ");
                String idPedido = resultSet.getString("idPedido");

                Pedido pedido = sqlPedido.buscar(idPedido);

                compra = new Compra(idCompra, totalCompra, pedido);
                compras.add(compra);
            }
        } catch (Exception e) {
            throw new PersistenciaException("Ha ocurrido un error al buscar una marca", e);
        } finally {
            closeConnection(connection, statement, resultSet);
        }

        return compras;
    }

}
