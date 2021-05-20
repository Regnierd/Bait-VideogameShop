package es.iespuertodelacruz.bait.modelo.mysql;

import java.sql.*;
import java.util.ArrayList;

import es.iespuertodelacruz.bait.exceptions.PersistenciaException;
import es.iespuertodelacruz.bait.modelo.Fichero;

public class Bbdd {
    private String driver;
    private String url;
    private String usuario;
    private String password;
    private static final String TABLE = "TABLE";
    private static final String USUARIO = "USUARIO";
    private static final String PRODUCTO = "PRODUCTO";
    private static final String CATEGORIA = "CATEGORIA";
    private static final String MARCA = "MARCA";
    private static final String COMPRA = "COMPRA";
    private static final String PEDIDO = "PEDIDO";
    private static final String ENVIO = "ENVIO";

    /**
     * Constructor basico de la clase Bbdd
     * 
     * @param driver   que usuamos para trabajar con Bbdd
     * @param url      de la base de datos o fichero db
     * @param usuario  con acceso a la base de datos
     * @param password del usuario de la base de datos
     */
    public Bbdd(String driver, String url, String usuario, String password) {
        this.driver = driver;
        this.url = url;
        this.usuario = usuario;
        this.password = password;
    }

    /**
     * Metodo que crear la base de datos con sus correspondientes tablas 
     * si no existen
     * @throws PersistenciaException error a controlar
     */
    public void init() throws PersistenciaException {
        DatabaseMetaData databaseMetaData;
        Connection connection = null;
        ResultSet resultSet = null;
        ArrayList<String> listaTablas = new ArrayList<>();

        try {
            connection = getConnection();
            databaseMetaData = connection.getMetaData();
            resultSet = databaseMetaData.getTables(null, null, null, new String[] {TABLE});
            while (resultSet.next()) {
                listaTablas.add(resultSet.getString("TABLE_NAME"));
            }

            crearTabla(USUARIO, listaTablas);
            crearTabla(PRODUCTO, listaTablas);
            crearTabla(CATEGORIA, listaTablas);
            crearTabla(MARCA, listaTablas);
            //crearTabla(COMPRA, listaTablas);
            //crearTabla(PEDIDO, listaTablas);
            //crearTabla(ENVIO, listaTablas);

        } catch (Exception e) {
            throw new PersistenciaException("Se ha producido un error en la inicializacion de la BBDD", e);
        } finally {
            closeConnection(connection, null, resultSet);
        }
    }

    /**
     * Metodo que lee el fichero con la informacion para crear la tabla
     * y la lanza en la base datos
     * @param tabla que se va a crear
     * @param listaTablas lista de tablas que hay actualmente en la base de datos
     * @throws PersistenciaException error a controlar
     */
    private void crearTabla(String tabla, ArrayList<String> listaTablas) throws PersistenciaException{
        Fichero fichero = new Fichero();
        String tablaFichero = "resorce/tables/"+ tabla +".sql";
        String insertFichero = "resorce/insert/"+ tabla +"_INSERT.sql";
        String informacion;

        if (!listaTablas.contains(tabla)) {
            informacion = fichero.leer(tablaFichero);
            actualizar(informacion);

            informacion = fichero.leer(insertFichero);
            actualizar(informacion);
        }
    }

    /**
     * Funcion encargada de crear y devolver una conexion a la base de datos
     * 
     * @return Connection
     * @throws PersistenciaException error a controlar en caso de que falle la conexion
     */
    protected Connection getConnection() throws PersistenciaException {
        Connection connection = null;
        try {
            Class.forName(driver);
            if (usuario == null || password == null) {
                connection = DriverManager.getConnection(url);
            } else {
                DriverManager.getConnection(url, usuario, password);
            }
        } catch (Exception exception) {
            throw new PersistenciaException("Se ha producido un error al conectar a la base de datos ", exception);
        }

        return connection;

    }

    /**
     * Metodo que cierra la conexion con la base de datos
     * 
     * @param connection
     * @param statement
     * @param resultSet
     * @throws PersistenciaException error a controlar al cerrar la conexion
     */
    protected void closeConnection(Connection connection, Statement statement, ResultSet resultSet)
            throws PersistenciaException {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            throw new PersistenciaException("Se ha producido un error cerrando la conexion");
        }

    }

    /**
     * Metodo encargado de realizar la actualizacion de la BBDD
     * 
     * @param sql a ejecutar
     * @throws PersistenciaException error controlado
     */
    protected void actualizar(String sql) throws PersistenciaException {
        Statement statement = null;
        Connection connection = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (Exception exception) {
            throw new PersistenciaException("Se ha producido un error realizando la consulta", exception);
        } finally {
            closeConnection(connection, statement, null);
        }

    }
}
