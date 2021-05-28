package es.iespuertodelacruz.bait.modelo.movimientosModelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import es.iespuertodelacruz.bait.api.movimientos.Envio;
import es.iespuertodelacruz.bait.api.movimientos.Pedido;
import es.iespuertodelacruz.bait.exceptions.PersistenciaException;
import es.iespuertodelacruz.bait.modelo.mysql.BbddSqlite;
import es.iespuertodelacruz.bait.modelo.mysql.UtilidadesSQL;

public class EnvioModelo {
    private static final String ID_ENVIO = "idEnvio";
    public static final String TABLE_NAME = "ENVIOS";
    private static UtilidadesSQL utilidadesSQL = new UtilidadesSQL(TABLE_NAME, "idEnvio, idPedido, fechaEnvio, estado");
    BbddSqlite persistencia;
    PedidoModelo pedidoModelo;
    /**
     * Constructor basico de la clase
     * @throws PersistenciaException
     */
    public EnvioModelo() throws PersistenciaException{
        persistencia = new BbddSqlite(TABLE_NAME,null, null);
        pedidoModelo = new PedidoModelo();
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
            connection = persistencia.getConnection();
            preparedStatement = connection.prepareStatement(utilidadesSQL.getINSERT());
            preparedStatement.setString(1, envio.getIdEnvio());
            preparedStatement.setString(2, envio.getPedido().getIdPedido());
            preparedStatement.setString(3, envio.getFechaEnvio());
            preparedStatement.setString(4, envio.getEstado());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new PersistenciaException("Ha ocurrido un error al insertar el envio", e);
        }finally{
            persistencia.closeConnection(connection, preparedStatement, null);
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
            connection = persistencia.getConnection();
            preparedStatement = connection.prepareStatement(utilidadesSQL.setDelete(ID_ENVIO));
            preparedStatement.setString(1, idEnvio);

            preparedStatement.executeUpdate();

        } catch (Exception e) {
            throw new PersistenciaException("Ha ocurrido un error al eliminar el envio", e);
        }finally{
            persistencia.closeConnection(connection, preparedStatement, null);
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
            connection = persistencia.getConnection();
            preparedStatement = connection.prepareStatement(utilidadesSQL.setUpdate());
            preparedStatement.setString(1, envio.getIdEnvio());
            preparedStatement.setString(2, envio.getPedido().getIdPedido());
            preparedStatement.setString(3, envio.getFechaEnvio());
            preparedStatement.setString(4, envio.getEstado());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new PersistenciaException("Ha ocurrido un error al modificar el ENVIO", e);
        }finally{
            persistencia.closeConnection(connection, preparedStatement, null);
        }
        
        
    }

    /**
     * Funcion que realiza un consulta y devuelve una lista de envio
     * 
     * @param sql   consulta que se va a realizar
     * @param valor del campo patron
     * @return una lista de envios
     * @throws PersistenciaException error a controlar
     */
    private ArrayList<Envio> buscarPorElemento(String sql, String valor) throws PersistenciaException {
        ResultSet resultSet;
        ArrayList<Envio> lista = new ArrayList<>();

        resultSet = persistencia.buscarElemento(sql, valor);

        try {
            while (resultSet.next()) {
                String idEnvio = resultSet.getString("idEnvio");
                String idPedido = resultSet.getString("idPedido");
                String fechaEnvio = resultSet.getString("fechaEnvio");
                String estado = resultSet.getString("estado");
               

                Pedido pedido = pedidoModelo.buscaPorIdentificador(idPedido);

                Envio envio = new Envio(idEnvio, pedido, fechaEnvio, estado);
                lista.add(envio);
            }
        } catch (SQLException e) {
            throw new PersistenciaException(e.getMessage());
        }

        return lista;

    }

    /**
     * Funcion que obtiene un listado de los envios y los devuelve
     * 
     * @return la lista de envios
     * @throws PersistenciaException error a controlar
     */
    public ArrayList<Envio> obtenerListado() throws PersistenciaException {
        ArrayList<Envio> lista;
        String sql = utilidadesSQL.getSELECTALL();

        lista = buscarPorElemento(sql, "");

        return lista;
    }

    /**
     * Funcion que busca un envio por su identificador
     * 
     * @param identificador del envio que se va abuscar
     * @return el envio encontrado
     * @throws PersistenciaException error a controlar
     */
    public Envio buscaPorIdentificador(String identificador) throws PersistenciaException {
        ArrayList<Envio> lista;
        Envio envio;
        String sql = utilidadesSQL.setSelectOne("idEnvio");
        lista = buscarPorElemento(sql, identificador);

        envio = lista.get(0);

        return envio;
    }

    

   


}
