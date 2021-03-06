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
    private String tableName;

    /**
     * Constructor basico de la clase Bbdd
     * 
     * @param tableName nombre de la tabla
     * @param driver   que usuamos para trabajar con Bbdd
     * @param url      de la base de datos o fichero db
     * @param usuario  con acceso a la base de datos
     * @param password del usuario de la base de datos
     * @throws PersistenciaException error a controlar
     */
    public Bbdd(String tableName, String driver, String url, String usuario, String password) throws PersistenciaException {
        this.driver = driver;
        this.url = url;
        this.usuario = usuario;
        this.password = password;
        this.tableName = tableName;
        init(this.tableName);
    }

    /**
     * Constructor con 3 paramentros
     * @param tableName nombre de la tabla
     * @param usuario usuario de la base de datos
     * @param password contraseña de la base de datos
     * @throws PersistenciaException error a controlar
     */
    public Bbdd(String tableName , String usuario, String password) throws PersistenciaException {
        this.usuario = usuario;
        this.password = password;
        this.tableName = tableName;
        init(this.tableName);
    }

    /**
     * Metodo que crear la base de datos con sus correspondientes tablas 
     * si no existen
     * @throws PersistenciaException error a controlar
     */
    private void init(String tableName) throws PersistenciaException {
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

            crearTabla(tableName, listaTablas);
        } catch (Exception e) {
            throw new PersistenciaException("Se ha producido un error en la inicializacion de la BBDD", e);
        } finally {
            closeConnection(connection, null, null);
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
        String informacionTablas;
        String informacionInsert;

        informacionTablas = fichero.leer(tablaFichero);
        informacionInsert = fichero.leer(insertFichero);

        if (!listaTablas.contains(tabla) || !informacionInsert.isEmpty() ) { 
            actualizar(informacionTablas);
            actualizar(informacionInsert);
        }
    }

    /**
     * Funcion encargada de crear y devolver una conexion a la base de datos
     * 
     * @return Connection
     * @throws PersistenciaException error a controlar en caso de que falle la conexion
     */
    public Connection getConnection() throws PersistenciaException {
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
     * @param connection conexion
     * @param statement statement
     * @param resultSet resulset
     * @throws PersistenciaException error a controlar al cerrar la conexion
     */
    public void closeConnection(Connection connection, Statement statement, ResultSet resultSet)
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
    public void actualizar(String sql) throws PersistenciaException {
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

    /**
    * Funcion que se encarga de realizar un lanzar un consulta y devolver 
    * el resultado obtenido de la base de datos 
    * @param sql consulta que se va a realizar 
    * @param valor valor de la sentencia sql a buscar
    * @throws PersistenciaException error a controlar
    * @return ResulSet
    */
   public ResultSet buscarElemento(String sql, String valor) throws PersistenciaException {
    Connection connection = null;
    PreparedStatement preparedStatement;
    ResultSet resultSet = null;
    try {
       connection = getConnection();
       preparedStatement = connection.prepareStatement(sql);
       if (!valor.isEmpty()) {
        preparedStatement.setString(1, valor);
       } 
       resultSet = preparedStatement.executeQuery();
    } catch (SQLException e) {
       throw new PersistenciaException("Se ha producido un error en la busqueda.",e);
    } finally {
       //closeConecction(connection, preparedStatement, null);
    }

    return resultSet;
 }
}
