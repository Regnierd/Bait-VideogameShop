package es.iespuertodelacruz.bait.modelo.mysql.SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import es.iespuertodelacruz.bait.api.movimientos.Envio;
import es.iespuertodelacruz.bait.api.movimientos.Pedido;
import es.iespuertodelacruz.bait.exceptions.PersistenciaException;
import es.iespuertodelacruz.bait.modelo.mysql.Bbdd;

public class SQLEnvio extends Bbdd{
    private static UtilidadesSQL utilidadesSQL = new UtilidadesSQL("Envio", "idEnvio, idPedido, fechaEnvio, estado");
    SQLPedido sqlPedido;

    /**
     * Constructor basico de la clase 
     */
    public SQLEnvio(String driver, String url, String usuario, String password) {
        super(driver, url, usuario, password);    
        sqlPedido = new SQLPedido(driver, url, usuario, password);
    }

    
    /**
     * Metodo que inserta un envio en la base de datos
     * 
     * @param envio que va a insertar en la base de datos
     * @throws PersistenciaException error a controlar
     */
    public void insertar(Envio envio) throws PersistenciaException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(utilidadesSQL.getINSERT());
            preparedStatement.setString(1, envio.getIdEnvio());
            preparedStatement.setString(2, envio.getPedido().getIdPedido());
            preparedStatement.setString(3, envio.getFechaEnvio());
            preparedStatement.setString(4, envio.getEstado());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new PersistenciaException("Ha ocurrido un error al insertar el envio", e);
        }finally{
            closeConnection(connection, preparedStatement, null);
        }

    }

    /**
     * Metodo encargado de eliminar un envio en la base de datos
     * 
     * @param idEnvio identificador del envio a borrar
     * @throws PersistenciaException error a controlar
     */
    public void eliminar(String idEnvio) throws PersistenciaException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(utilidadesSQL.setDelete("idEnvio"));
            preparedStatement.setString(1, idEnvio);

            preparedStatement.executeUpdate();

        } catch (Exception e) {
            throw new PersistenciaException("Ha ocurrido un error al eliminar el envio", e);
        }finally{
            closeConnection(connection, preparedStatement, null);
        }

    }

    /**
     * Metodo que modifica un campo en concreto de la base datos
     * @param envio con los nuevos cambios
     * @throws PersistenciaException error a controlar
     */
    public void modificar(Envio envio) throws PersistenciaException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(utilidadesSQL.setUpdate());
            preparedStatement.setString(1, envio.getIdEnvio());
            preparedStatement.setString(2, envio.getPedido().getIdPedido());
            preparedStatement.setString(3, envio.getFechaEnvio());
            preparedStatement.setString(4, envio.getEstado());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new PersistenciaException("Ha ocurrido un error al modificar el ENVIO", e);
        }finally{
            closeConnection(connection, preparedStatement, null);
        }
        
        
    }

    /**
     * Funcion que busca un envio en la base de datos y lo devuelve
     * @param idEnvio que se va a buscar
     * @return el envio 
     * @throws PersistenciaException error a controlar
     */
    public Envio buscar(String idEnvio) throws PersistenciaException {
        Connection connection = null;
        ResultSet resultSet = null;
        Envio envio;
        PreparedStatement preparedStatement = null;
      
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(utilidadesSQL.setSelectOne("idEnvio"));
            preparedStatement.setString(1, idEnvio);
            resultSet = preparedStatement.executeQuery();

            String idPedido = resultSet.getString("idPedido");
            String fechaEnvio = resultSet.getString("fechaEnvio");
            String estado = resultSet.getString("estado");

            Pedido pedido = sqlPedido.buscar(idPedido);
            envio = new Envio(idEnvio, pedido, fechaEnvio, estado);
        } catch (SQLException e) {
            throw new PersistenciaException("Ha ocurrido un error al buscar el envio", e);
        } finally {
            closeConnection(connection, preparedStatement, resultSet);
        }

        return envio;
    }

    /**
     * Funcion que obtiene un listado de los envios y los devuelve
     * @return la lista de los envios
     * @throws PersistenciaException error a controlar
     */
    public ArrayList<Envio> obtenerListado() throws PersistenciaException {
        Connection connection = null;
        ArrayList<Envio> envios = new ArrayList<>();
        ResultSet resultSet = null;
        Statement statement = null;
        
        try {
            connection = getConnection();
            statement = connection.createStatement();
            statement.setMaxRows(30);

            resultSet = statement.executeQuery(utilidadesSQL.getSELECTALL());
            while (resultSet.next()) {
                String idEnvio = resultSet.getString("idEnvio");
                String idPedido = resultSet.getString("idPedido");
                String fechaEnvio = resultSet.getString("fechaEnvio");
                String estado = resultSet.getString("estado");

                Pedido pedido = sqlPedido.buscar(idPedido);
                
                Envio envio = new Envio(idEnvio, pedido, fechaEnvio, estado);
                envios.add(envio);
            }
        } catch (Exception e) {
            throw new PersistenciaException("Ha ocurrido un error al buscar un envio", e);
        }finally{
            closeConnection(connection, statement, resultSet);
        }
             
        return envios;
    }

    /**
     * Funcion que obtiene un listado de los envios de un pedido
     * @return la lista de los envios
     * @throws PersistenciaException error a controlar
     */
    public ArrayList<Envio> obtenerListadoPorPedido(String idPedido) throws PersistenciaException {
        Connection connection = null;
        ArrayList<Envio> envios = new ArrayList<>();
        ResultSet resultSet = null;
        Statement statement = null;
        
        try {
            connection = getConnection();
            statement = connection.createStatement();
            statement.setMaxRows(30);

            resultSet = statement.executeQuery(utilidadesSQL.setSelectOne("idPedido"));
            while (resultSet.next()) {
                String idEnvio = resultSet.getString("idEnvio");
                String fechaEnvio = resultSet.getString("fechaEnvio");
                String estado = resultSet.getString("estado");

                Pedido pedido = sqlPedido.buscar(idPedido);
                
                Envio envio = new Envio(idEnvio, pedido, fechaEnvio, estado);
                envios.add(envio);
            }
        } catch (Exception e) {
            throw new PersistenciaException("Ha ocurrido un error al buscar un envio", e);
        }finally{
            closeConnection(connection, statement, resultSet);
        }
             
        return envios;
    }
}
