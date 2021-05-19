package es.iespuertodelacruz.bait.modelo.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import es.iespuertodelacruz.bait.api.productos.Categoria;
import es.iespuertodelacruz.bait.exceptions.PersistenciaException;

public class SQLCategoria extends Bbdd {
    private static UtilidadesSQL utilidadesSQL = new UtilidadesSQL("Categoria", "idCategoria,nombre");

    /**
     * Contructor basico de la clase
     */
    public SQLCategoria(String driver, String url, String usuario, String password) {
        super(driver, url, usuario, password);
    }

    /**
     * Metodo que inserta una Categoria en la base de datos
     * 
     * @param categoria que va a insertar en la base de datos
     * @throws SQLException  error controlado
     * @throws BbddException error controlado
     */
    public void inserta(Categoria categoria) throws PersistenciaException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        connection = getConnection();
        try {
            preparedStatement = connection.prepareStatement(utilidadesSQL.getINSERT());
            preparedStatement.setString(1, categoria.getIdCategoria());
            preparedStatement.setString(2, categoria.getNombre());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenciaException("Ha ocurrido un error al insertar la categoria.", e);
        }finally{
            closeConnection(connection, preparedStatement, null);
        }

        
    }

    /**
     * Metodo encargado de eliminar una categoria en la base de datos
     * 
     * @param idCategoria           identificador de la categoria
     * @param PersistenciaException error en caso de no poder eliminar
     */
    public void eliminar(String idCategoria) throws PersistenciaException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(utilidadesSQL.setDelete("idCategoria"));
            preparedStatement.setString(1, idCategoria);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenciaException("Ha ocurrido un error al eliminar la categoria", e);
        }finally{
            closeConnection(connection, preparedStatement, null);
        }

        
    }

    /**
     * Metodo que modifica un campo en concreto de la base de datos
     * 
     * @param categoria modificada
     * @throws PersistenciaException error controlado
     */
    public void modificar(Categoria categoria) throws PersistenciaException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(utilidadesSQL.setUpdate());
            preparedStatement.setString(1, categoria.getIdCategoria());
            preparedStatement.setString(2, categoria.getNombre());
            preparedStatement.setString(3, categoria.getIdCategoria());
            
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenciaException("Ha courrido un error al modificar la categoria", e);
        }finally{
            closeConnection(connection, preparedStatement, null);
        }
        
    }

    /**
     * Funcion que busca una categoria en la base de datos y la devuelve
     * 
     * @param idCategoria identificador de la categoria
     * @return la categoria buscada
     * @throws persistenciaException error controlado
     */
    public Categoria buscar(String idCategoria) throws PersistenciaException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Categoria categoria = null;

        try {
            connection = getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(utilidadesSQL.setSelectOne(idCategoria));
            String nombre = resultSet.getString("idCategoria");
            String apellidos = resultSet.getString("nombre");
            categoria = new Categoria(idCategoria, nombre);
        } catch (SQLException e) {
            throw new PersistenciaException("Ha ocurrido un error al buscar la categoria", e);
        } finally {
            closeConnection(connection, statement, resultSet);
        }

        

        return categoria;
    }

    /**
     * Funcion que obtiene un listado de las categorias y las devuelve
     * 
     * @return la lista con todas la categorias
     * @throws SQLException  error controlado
     * @throws BbddException error controlado
     */
    public ArrayList<Categoria> obtenerListado() throws PersistenciaException{
        Connection connection = null;
        ArrayList<Categoria> categorias = new ArrayList<>();
        ResultSet resultSet = null;
        Statement statement = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            statement.setMaxRows(30);

            resultSet = statement.executeQuery(utilidadesSQL.getSELECTALL());
            while (resultSet.next()) {
                String idCategoria = resultSet.getString("dni");
                String nombre = resultSet.getString("nombre");

                Categoria categoria = new Categoria(idCategoria, nombre);
                categorias.add(categoria);
            }               
        } catch (SQLException e) {
            throw new PersistenciaException("Ha ocurrido un error al obtener el listado la categoria", e);
        }finally{
            closeConnection(connection, statement, resultSet);
        }
        return categorias;
    }

}