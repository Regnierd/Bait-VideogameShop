package es.iespuertodelacruz.bait.modelo;

import java.sql.*;
import java.util.ArrayList;

import es.iespuertodelacruz.bait.exceptions.BbddException;

public class Bbdd {
    private String driver;
    private String url;
    private String usuario;
    private String password;

    /**
     * Constructor basico de la clase Bbdd
     * @param driver que usuamos para trabajar con Bbdd
     * @param url de la base de datos o fichero db 
     * @param usuario con acceso a la base de datos 
     * @param password del usuario de la base de datos 
     */
    public Bbdd(String driver, String url, String usuario, String password) {
        this.driver = driver;
        this.url = url;
        this.usuario = usuario;
        this.password = password;
    }

    /**
     * Funcion encargada de crear y devolver una conexion a la 
     * base de datos 
     * @return Connection 
     * @throws BbddException error a controlar en caso de que falle la conexion
     */
    private Connection getConexion() throws BbddException{
        Connection connection = null;
        try {
            Class.forName(driver);
            if (usuario  == null || password == null) {
                
            } else { 
                DriverManager.getConnection(url, usuario, password);
            }     
        } catch (Exception exception) {
            throw new BbddException("Se ha producido un error al conectar a la base de datos ", exception);
        }

        return connection;
        
    }

    //CRUD
    public void insertar() {
        Connection connection = getConexion();
        Statement statement = connection.createStatement();
    }

    public void eliminar() {
        
    }

    public void modificar() {
        
    }

    public ArrayList<Object> obtenerListado(String sql) throws BbddException {
        ArrayList<Object> frutas = new ArrayList<>();
        Fruta fruta = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = getConexion();
            statement =  getConexion().createStatement();
            resultSet = statement.executeQuery(sql);    
            while (resultSet.next()) {
                String identificador = resultSet.getString("identificador");
                String nombre = resultSet.getString("nombre");
                float precio = resultSet.getFloat("precio");
                float coste = resultSet.getFloat("coste");
                fruta = new Fruta(identificador, nombre, precio, coste);
                frutas.add(fruta);
            }      
        } catch (Exception e) {
            throw new BbddException("Se ha producido realizando la consulta", e);
        } finally {
            closeConnection(connection, statement, resultSet);
        }
        
        return frutas;
   
    }

    public ArrayList<Fruta> obtenerListado() throws BbddException {
        String sql = "SELECT * From Fruta";
        return obtenerListado(sql);
   
    }

    public Fruta obtenerFruta(String indentificador) throws BbddException {
        Fruta fruta = null;
        ArrayList<Fruta> listaFrutas = null;
        String sql = "SELECT * FROM Fruta WHRE identidicador =";
        sql = sql + "'" + indentificador + "'";
        listaFrutas = obtenerListado();
        if (!listaFrutas.isEmpty()) {
            fruta = listaFrutas.get(0);
        }

        return fruta;
    }

    private void closeConnection(Connection connection, Statement statement, ResultSet resultSet) throws BbddException{
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
}
