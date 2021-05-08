package es.iespuertodelacruz.bait.modelo.mysql;

import java.sql.*;

import es.iespuertodelacruz.bait.exceptions.BbddException;

public class Bbdd {
    private String driver;
    private String url;
    private String usuario;
    private String password;

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
     * Funcion encargada de crear y devolver una conexion a la base de datos
     * 
     * @return Connection
     * @throws BbddException error a controlar en caso de que falle la conexion
     */
    protected Connection getConnection() throws BbddException {
        Connection connection = null;
        try {
            Class.forName(driver);
            if (usuario == null || password == null) {
                connection = DriverManager.getConnection(url);
            } else {
                DriverManager.getConnection(url, usuario, password);
            }
        } catch (Exception exception) {
            throw new BbddException("Se ha producido un error al conectar a la base de datos ", exception);
        }

        return connection;

    }

    /**
     * Metodo que cierra la conexion con la base de datos
     * 
     * @param connection
     * @param statement
     * @param resultSet
     * @throws BbddException error a controlar al cerrar la conexion
     */
    protected void closeConnection(Connection connection, Statement statement, ResultSet resultSet) throws BbddException {
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
            throw new BbddException("Se ha producido un error cerrando la conexion");
        }

    }

    /**
     * Metodo encargado de realizar la actualizacion de la BBDD
     * 
     * @param sql a ejecutar
     * @throws BbddException error controlado
     */
    protected void actualizar(String sql) throws BbddException {
        Statement statement = null;
        Connection connection = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (Exception exception) {
            throw new BbddException("Se ha producido un error realizando la consulta", exception);
        } finally {
            closeConnection(connection, statement, null);
        }

    }
}
