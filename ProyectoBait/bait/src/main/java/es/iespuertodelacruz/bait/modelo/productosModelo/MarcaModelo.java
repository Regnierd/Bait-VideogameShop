package es.iespuertodelacruz.bait.modelo.productosModelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import es.iespuertodelacruz.bait.api.productos.Marca;
import es.iespuertodelacruz.bait.exceptions.PersistenciaException;
import es.iespuertodelacruz.bait.modelo.mysql.BbddSqlite;
import es.iespuertodelacruz.bait.modelo.mysql.UtilidadesSQL;

public class MarcaModelo {
    public static final String TABLE_NAME = "MARCAS";
    private static final String IDENTIFICADOR = "idMarca";
    private static UtilidadesSQL utilidadesSQL = new UtilidadesSQL(TABLE_NAME, IDENTIFICADOR + ", nombre");
    BbddSqlite persistencia;

    /**
     * Constructor basico de la clase
     * 
     * @throws PersistenciaException
     */
    public MarcaModelo() throws PersistenciaException {
        persistencia = new BbddSqlite(TABLE_NAME, null, null);
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
            connection = persistencia.getConnection();
            preparedStatement = connection.prepareStatement(utilidadesSQL.getINSERT());
            preparedStatement.setString(1, marca.getIdMarca());
            preparedStatement.setString(2, marca.getNombre());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new PersistenciaException("Ha ocurrido un error al insertar una marca", e);
        } finally {
            persistencia.closeConnection(connection, preparedStatement, null);
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
            connection = persistencia.getConnection();
            preparedStatement = connection.prepareStatement(utilidadesSQL.setDelete(IDENTIFICADOR));
            preparedStatement.setString(1, idMarca);

            preparedStatement.executeUpdate();

        } catch (Exception e) {
            throw new PersistenciaException("Ha ocurrido un error al eliminar una marca", e);
        } finally {
            persistencia.closeConnection(connection, preparedStatement, null);
        }

    }

    /**
     * Funcion que realiza un consulta y devuelve una lista de categorias
     * 
     * @param sql   consulta que se va a realizar
     * @param valor del campo a filtrar
     * @return una lista de categoria
     * @throws PersistenciaException error a controlar
     */
    private ArrayList<Marca> buscarPorElemento(String sql, String valor) throws PersistenciaException {
        ResultSet resultSet;
        ArrayList<Marca> lista = new ArrayList<>();

        resultSet = persistencia.buscarElemento(sql, valor);

        try {
            while (resultSet.next()) {
                String IdMarca = resultSet.getString(IDENTIFICADOR);
                String nombre = resultSet.getString("nombre");

                Marca marca = new Marca(IdMarca, nombre);
                lista.add(marca);
            }
        } catch (SQLException e) {
            throw new PersistenciaException(e.getMessage());
        }

        return lista;

    }

    /**
     * Funcion que busca una marca por su identificador
     * 
     * @param idMarca de la categoria que se va buscar
     * @return la marca encontrada
     * @throws PersistenciaException error a controlar
     */
    public Marca buscarPorId(String idMarca) throws PersistenciaException {
        ArrayList<Marca> lista;
        Marca marca = null;
        String sql = utilidadesSQL.setSelectOne(IDENTIFICADOR);
        lista = buscarPorElemento(sql, idMarca);

        if (!lista.isEmpty()) {
            marca = lista.get(0);
        }

        return marca;
    }

    /**
     * Funcion que busca una marca por su nombre
     * 
     * @param nombre de la marca que se va buscar
     * @return la marca encontrada
     * @throws PersistenciaException error a controlar
     */
    public Marca buscarPorNombre(String nombre) throws PersistenciaException {
        ArrayList<Marca> lista;
        Marca marca = null;
        String sql = utilidadesSQL.setSelectOne("nombre");
        lista = buscarPorElemento(sql, nombre);

        if (!lista.isEmpty()) {
            marca = lista.get(0);
        }

        return marca;
    }

    /**
     * Funcion que obtiene un listado de las marcas y los devuelve
     * 
     * @return la lista de las marcas
     * @throws PersistenciaException error a controlar
     */
    public ArrayList<Marca> obtenerListado() throws PersistenciaException {
        ArrayList<Marca> lista;
        String sql = utilidadesSQL.getSELECTALL();

        lista = buscarPorElemento(sql, "");

        return lista;
    }

    /**
     * Metodo que modifica un campo en concreto de la base datos
     * 
     * @param marca con los nuevos cambios
     * @throws PersistenciaException error a controlar
     */
    public void modificar(Marca marca) throws PersistenciaException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = persistencia.getConnection();
            preparedStatement = connection.prepareStatement(utilidadesSQL.setUpdate());
            preparedStatement.setString(1, marca.getIdMarca());
            preparedStatement.setString(2, marca.getNombre());
            preparedStatement.setString(3, marca.getIdMarca());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new PersistenciaException("Ha ocurrido un error al modificar una marca", e);
        } finally {
            persistencia.closeConnection(connection, preparedStatement, null);
        }

    }

}
