package es.iespuertodelacruz.bait.modelo.mysql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

import es.iespuertodelacruz.bait.api.personas.Usuario;
import es.iespuertodelacruz.bait.exceptions.BbddException;

public class SQLUsuario extends Bbdd{
    private static Prueba prueba = new Prueba("Cliente", "dni, nombre, apellidos, direccion" +
    ", telefono, codigoPostal, provincia, nombreUsuario, password");
    

    /**
     * Constructor basico de la clase 
     */
    public SQLUsuario(String driver, String url, String usuario, String password) {
        super(driver, url, usuario, password);
    }


    /**
     * Metodo que inserta un Usuario en la base de datos
     * @param usuario que va a insertar en la base de datos
     * @throws BbddException error a controlar
     * @throws SQLException
     */
    public void insertar(Usuario usuario) throws BbddException, SQLException{
        Connection connection;
        PreparedStatement preparedStatement;

        connection = getConnection();
        preparedStatement = connection.prepareStatement(prueba.getINSERT());
        preparedStatement.setString(1, usuario.getDni());
        preparedStatement.setString(2, usuario.getNombre());
        preparedStatement.setString(3, usuario.getApellidos());
        preparedStatement.setString(4, usuario.getDireccion());
        preparedStatement.setString(5, usuario.getTelefono());
        preparedStatement.setString(6, usuario.getCodigoPostal());
        preparedStatement.setString(7, usuario.getProvincia());
        preparedStatement.setString(8, usuario.getNombreUsuario());
        preparedStatement.setString(9, usuario.getPassword());

        preparedStatement.executeUpdate();

        closeConnection(connection, preparedStatement, null);
    }

    

    public void eliminar(String dni) throws SQLException, BbddException{
        Connection connection;
        PreparedStatement preparedStatement;

        connection = getConnection();
        preparedStatement = connection.prepareStatement(prueba.setDelete("dni"));
        preparedStatement.setString(1, dni);
        
        preparedStatement.executeUpdate();

        closeConnection(connection, preparedStatement, null);
    }

    public void modificar(){
        //codigo
    }

    public void buscar(String dni){
        //codigo
    }

    public ArrayList<Usuario> obtenerListado(){
        //codigo
        return null;
    }
}
