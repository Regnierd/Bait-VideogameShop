package es.iespuertodelacruz.bait.modelo.mysql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

import es.iespuertodelacruz.bait.api.personas.Cliente;
import es.iespuertodelacruz.bait.exceptions.BbddException;

public class SQLCliente extends Bbdd{
    private static Prueba prueba = new Prueba("Cliente", "dni, nombre, apellidos, direccion" +
    ", telefono, codigoPostal, provincia, nombreUsuario, password");
    

    /**
     * Constructor basico de la clase 
     */
    public SQLCliente(String driver, String url, String usuario, String password) {
        super(driver, url, usuario, password);
    }


    /**
     * Metodo que inserta un Cliente en la base de datos
     * @param cliente que va a insertar en la base de datos
     * @throws BbddException error a controlar
     * @throws SQLException
     */
    public void insertar(Cliente cliente) throws BbddException, SQLException{
        Connection connection;
        PreparedStatement preparedStatement;

        connection = getConnection();
        preparedStatement = connection.prepareStatement(prueba.getINSERT());
        //preparedStatement = connection.prepareStatement(INSERTPERSONA);
        preparedStatement.setString(1, cliente.getDni());
        preparedStatement.setString(2, cliente.getNombre());
        preparedStatement.setString(3, cliente.getApellidos());
        preparedStatement.setString(4, cliente.getDireccion());
        preparedStatement.setString(5, cliente.getTelefono());
        preparedStatement.setString(6, cliente.getCodigoPostal());
        preparedStatement.setString(7, cliente.getProvincia());
        preparedStatement.setString(8, cliente.getNombreUsuario());
        preparedStatement.setString(9, cliente.getPassword());

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
        //
    }

    public void buscar(String dni){
        //
    }

    public ArrayList<Cliente> obtenerListado(){
        return null;
        //
    }
}
