package es.iespuertodelacruz.bait.modelo.productosModelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import es.iespuertodelacruz.bait.api.productos.Categoria;
import es.iespuertodelacruz.bait.exceptions.PersistenciaException;
import es.iespuertodelacruz.bait.modelo.mysql.BbddSqlite;
import es.iespuertodelacruz.bait.modelo.mysql.UtilidadesSQL;

public class CategoriaModelo {
    BbddSqlite persistencia;
    public static final String TABLE_NAME = "CATEGORIAS";
    public static final String IDENTIFICADOR = "idCategoria";

    private static UtilidadesSQL utilidadesSQL = new UtilidadesSQL(TABLE_NAME, IDENTIFICADOR + ", nombre");

    /**
     * Constructor basico de la clase
     * @throws PersistenciaException error a controlar
     */
    public CategoriaModelo() throws PersistenciaException {
        persistencia = new BbddSqlite(TABLE_NAME,null, null);
    }

    /**
     * Metodo que inserta una categoria en la base de datos
     * @param categoria que se va a insertar en la base de datos
     * @throws PersistenciaException error a controlar
     */
    public void insertar(Categoria categoria) throws PersistenciaException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = persistencia.getConnection();
            preparedStatement = connection.prepareStatement(utilidadesSQL.getINSERT());
            preparedStatement.setString(1, categoria.getIdCategoria());
            preparedStatement.setString(2, categoria.getNombre());
            
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new PersistenciaException("Ha ocurrido un error al insertar la categoria", e);
        }finally{
            persistencia.closeConnection(connection, preparedStatement, null);
        }
    }

    /**
     * Metodo que elimina una categoria en la base datos
     * @param idCategoria de la categoria
     * @throws PersistenciaException error a controlar
     */
    public void eliminar(String idCategoria) throws PersistenciaException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = persistencia.getConnection();
            preparedStatement = connection.prepareStatement(utilidadesSQL.setDelete(IDENTIFICADOR));
            preparedStatement.setString(1, idCategoria);

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new PersistenciaException("Ha ocurrido un error al eliminar una categoria", e);
        }finally{
            persistencia.closeConnection(connection, preparedStatement, null);
        }
    }

    /**
     * Funcion que realiza un consulta y devuelve una lista de categorias
     * @param sql consulta que se va a realizar
     * @param valor del campo a filtrar
     * @return una lista de categoria
     * @throws PersistenciaException error a controlar
     */
    private ArrayList<Categoria> buscarPorElemento(String sql, String valor) throws PersistenciaException{
        ResultSet resultSet;
        ArrayList<Categoria> lista = new ArrayList<>();

        resultSet = persistencia.buscarElemento(sql, valor);

        try {
            while (resultSet.next()){
                String idCategoria = resultSet.getString(IDENTIFICADOR);
                String nombre = resultSet.getString("nombre");

                Categoria categoria = new Categoria(idCategoria, nombre);
                lista.add(categoria);
            }
        } catch (SQLException e) {
            throw new PersistenciaException(e.getMessage());
        }

        return lista;

    }

    /**
     * Funcion que busca una categoria por su idCategoria
     * @param idCategoria de la categoria que se va buscar
     * @return la categoria encontrada
     * @throws PersistenciaException error a controlar
     */
    public Categoria buscarPorId(String idCategoria) throws PersistenciaException {
        ArrayList<Categoria> lista;
        Categoria categoria = null;
        String sql = utilidadesSQL.setSelectOne(IDENTIFICADOR);
        lista = buscarPorElemento(sql, idCategoria); 

        if (!lista.isEmpty()) {
            categoria = lista.get(0);
        }

        return categoria;
    }

    /**
     * Funcion que busca una categoria por su nombre
     * @param nombre de la categoria que se va abuscar
     * @return la categoria encontrada
     * @throws PersistenciaException error a controlar
     */
    public Categoria buscarPorNombre(String nombre) throws PersistenciaException {
        ArrayList<Categoria> lista;
        Categoria categoria = null;
        String sql = utilidadesSQL.setSelectOne("nombre");
        lista = buscarPorElemento(sql, nombre); 

        if(!lista.isEmpty()){
            categoria = lista.get(0);
        }

        return categoria;
    }

    /**
     * Funcion que obtiene un listado de las categorias y los devuelve
     * @return la lista de categorias
     * @throws PersistenciaException error a controlar
    */
    public ArrayList<Categoria> obtenerListado() throws PersistenciaException {
        ArrayList<Categoria> lista;
        String sql = utilidadesSQL.getSELECTALL();

        lista = buscarPorElemento(sql, "");

        return lista;
    }
    
    /**
     * Metodo que modifica una categoria en la base datos
     * @param categoria con los nuevos cambios
     * @throws PersistenciaException error a controlar
     */
    public void modificar(Categoria categoria) throws PersistenciaException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = persistencia.getConnection();
            preparedStatement = connection.prepareStatement(utilidadesSQL.setUpdate());
            preparedStatement.setString(1, categoria.getIdCategoria());
            preparedStatement.setString(2, categoria.getNombre());
            preparedStatement.setString(3, categoria.getIdCategoria());

            preparedStatement.execute();
        } catch (Exception e) {
            throw new PersistenciaException("Ha ocurrido un error al modificar la categoria", e);
        }finally{
            persistencia.closeConnection(connection, preparedStatement, null);
        }
    }

}
