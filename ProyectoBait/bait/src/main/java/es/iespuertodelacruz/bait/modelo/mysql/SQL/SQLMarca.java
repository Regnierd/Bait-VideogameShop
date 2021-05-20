package es.iespuertodelacruz.bait.modelo.mysql.SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import es.iespuertodelacruz.bait.api.productos.Marca;
import es.iespuertodelacruz.bait.exceptions.PersistenciaException;
import es.iespuertodelacruz.bait.modelo.mysql.Bbdd;

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
     * @throws PersistenciaException error a controlar
     */
    public void insertar(Marca marca) throws PersistenciaException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(utilidadesSQL.getINSERT());
            preparedStatement.setString(1, marca.getIdMarca());
            preparedStatement.setString(2, marca.getNombre());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new PersistenciaException("Ha ocurrido un error al insertar una marca", e);
        }finally{
            closeConnection(connection, preparedStatement, null);
        }

    }

    /**
     * Metodo encargado de eliminar una marca en la base de datos
     * 
     * @param idMarca de la marca
     * @throws PersistenciaException error a controlar
     */
    public void eliminar(String idMarca) throws PersistenciaException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(utilidadesSQL.setDelete("idMarca"));
            preparedStatement.setString(1, idMarca);

            preparedStatement.executeUpdate();

        } catch (Exception e) {
            throw new PersistenciaException("Ha ocurrido un error al eliminar una marca", e);
        }finally{
            closeConnection(connection, preparedStatement, null);
        }

    }

    /**
     * Metodo que modifica un campo en concreto de la base datos
     * @param marca con los nuevos cambios
     * @throws PersistenciaException error a controlar
     */
    public void modificar(Marca marca) throws PersistenciaException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(utilidadesSQL.setUpdate());
            preparedStatement.setString(1, marca.getIdMarca());
            preparedStatement.setString(2, marca.getNombre());
            preparedStatement.setString(3, marca.getIdMarca());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new PersistenciaException("Ha ocurrido un error al modificar una marca", e);
        }finally{
            closeConnection(connection, preparedStatement, null);
        }
        
        
    }

    /**
     * Funcion que busca una marca en la base de datos y lo devuelve
     * @param idMarca que se va a buscar
     * @return Marca
     * @throws PersistenciaException error a controlar
     */
    public Marca buscar(String idMarca) throws PersistenciaException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Marca marca;

        try {
            connection = getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(utilidadesSQL.setSelectOne(idMarca));

            String nombre = resultSet.getString("nombre ");
            marca = new Marca(idMarca, nombre);
        } catch (Exception e) {
            throw new PersistenciaException("Ha ocurrido un error al buscar una marca", e);
        }finally{
            closeConnection(connection, statement, resultSet);
        }

        return marca;
    }

    /**
     * Funcion que obtiene un listado de las marcas y los devuelve
     * @return la lista de marcas
     * @throws PersistenciaException error a controlar
     */
    public ArrayList<Marca> obtenerListado() throws PersistenciaException {
        Connection connection = null;
        ArrayList<Marca> marcas = new ArrayList<>();
        ResultSet resultSet = null;
        Statement statement = null;
        try {
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
        } catch (Exception e) {
            throw new PersistenciaException("Ha ocurrido un error al buscar una marca", e);
        }finally{
            closeConnection(connection, statement, resultSet);
        }
             
        return marcas;
    }
    
}
