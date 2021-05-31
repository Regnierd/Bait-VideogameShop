package es.iespuertodelacruz.bait.modelo.personasModelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import es.iespuertodelacruz.bait.api.personas.Usuario;
import es.iespuertodelacruz.bait.exceptions.PersistenciaException;
import es.iespuertodelacruz.bait.modelo.mysql.BbddSqlite;
import es.iespuertodelacruz.bait.modelo.mysql.UtilidadesSQL;

public class UsuarioModelo {
    BbddSqlite persistencia;
    public static final String TABLE_NAME = "USUARIOS";
    private static UtilidadesSQL utilidadesSQL = new UtilidadesSQL(TABLE_NAME, "dni, nombre, apellidos, email, direccion"
    + ", telefono, pais, codigoPostal, provincia, nombreUsuario, password, rol, saldo");

    /**
     * Constructor basico de la clase
     * @throws PersistenciaException error a controlar
     */
    public UsuarioModelo() throws PersistenciaException {
        persistencia = new BbddSqlite(TABLE_NAME,null, null);
    }

    /**
     * Metodo que inserta un usuario en la base de datos
     * @param usuario que se va a insertar en la base de datos
     * @throws PersistenciaException error a controlar
     */
    public void insertar(Usuario usuario) throws PersistenciaException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = persistencia.getConnection();
            preparedStatement = connection.prepareStatement(utilidadesSQL.getINSERT());
            preparedStatement.setString(1, usuario.getDni());
            preparedStatement.setString(2, usuario.getNombre());
            preparedStatement.setString(3, usuario.getApellidos());
            preparedStatement.setString(4, usuario.getEmail());
            preparedStatement.setString(5, usuario.getDireccion());
            preparedStatement.setString(6, usuario.getTelefono());
            preparedStatement.setString(7, usuario.getPais());
            preparedStatement.setString(8, usuario.getCodigoPostal());
            preparedStatement.setString(9, usuario.getProvincia());
            preparedStatement.setString(10, usuario.getNombreUsuario());
            preparedStatement.setString(11, usuario.getPassword());
            preparedStatement.setString(12, usuario.getRol());
            preparedStatement.setFloat(13, usuario.getSaldo());
            
            preparedStatement.execute();
        } catch (Exception e) {
            throw new PersistenciaException("Ha ocurrido un error al insertar un usuario", e);
        }finally{
            persistencia.closeConnection(connection, preparedStatement, null);
        }
    }

    /**
     * Metodo que elimina un usuario en la base datos
     * @param dni del usuario que se va a borrar
     * @throws PersistenciaException error a controlar
     */
    public void eliminar(String dni) throws PersistenciaException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = persistencia.getConnection();
            preparedStatement = connection.prepareStatement(utilidadesSQL.setDelete("dni"));
            preparedStatement.setString(1, dni);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenciaException("Ha ocurrido un error al eliminar un usuario", e);
        }finally{
            persistencia.closeConnection(connection, preparedStatement, null);
        }
    }

    /**
     * Funcion que realiza un consulta y devuelve una lista de usuario
     * @param sql consulta que se va a realizar
     * @param valor del campo patron
     * @return una lista de usuario 
     * @throws PersistenciaException error a controlar
     */
    private ArrayList<Usuario> buscarPorElemento(String sql, String valor) throws PersistenciaException{
        ResultSet resultSet;
        ArrayList<Usuario> lista = new ArrayList<>();

        resultSet = persistencia.buscarElemento(sql, valor);

        try {
            while (resultSet.next()){
                String dni = resultSet.getString("dni");
                String nombre = resultSet.getString("nombre");
                String apellidos = resultSet.getString("apellidos");
                String email = resultSet.getString("email");
                String direccion = resultSet.getString("direccion");
                String telefono = resultSet.getString("telefono");
                String pais = resultSet.getString("pais");
                String codigoPostal = resultSet.getString("codigoPostal");
                String provincia = resultSet.getString("provincia");
                String nombreUsuario = resultSet.getString("nombreUsuario");
                String password = resultSet.getString("password");
                String rol = resultSet.getString("rol");
                float saldo = resultSet.getFloat("saldo");

                Usuario usuario = new Usuario(dni, nombre, apellidos, email, direccion, telefono, pais, codigoPostal, provincia, nombreUsuario, password, rol, saldo);
                lista.add(usuario);
            }
        } catch (SQLException e) {
            throw new PersistenciaException(e.getMessage());
        }

        return lista;

    }

    /**
     * Funcion que busca un usuario por su dni
     * @param dni de la persona que se va abuscar
     * @return el usuario encontrado
     * @throws PersistenciaException error a controlar
     */
    public Usuario buscaPorDni(String dni) throws PersistenciaException {
        ArrayList<Usuario> lista;
        Usuario usuario = null;
        String sql = utilidadesSQL.setSelectOne("dni");
        lista = buscarPorElemento(sql, dni); 
        
        if (!lista.isEmpty()) {
            usuario = lista.get(0);
        }

        return usuario;
    }

    /**
     * Funcion que buscar un usuario por su nombre de usuario
     * @param nombreUsuario del usuario que se va a buscar
     * @return el usuario encontrado
     * @throws PersistenciaException error a controlar
     */
    public Usuario buscaPorNombreUsuario(String nombreUsuario) throws PersistenciaException {
        ArrayList<Usuario> lista;
        Usuario usuario = null;
        String sql = utilidadesSQL.setSelectOne("nombreUsuario");
        lista = buscarPorElemento(sql, nombreUsuario); 

        if (!lista.isEmpty()) {
            usuario = lista.get(0);
        }

        return usuario;
    }

    /**
     * Funcion que obtiene un listado de los usuarios y los devuelve
     * @return la lista de usuarios
     * @throws PersistenciaException error a controlar
    */
    public ArrayList<Usuario> obtenerListado() throws PersistenciaException {
        ArrayList<Usuario> lista;
        String sql = utilidadesSQL.getSELECTALL();

        lista = buscarPorElemento(sql, "");

        return lista;
    }
    
    /**
     * Metodo que modifica un usuario en la base datos
     * @param usuario con los nuevos cambios
     * @throws PersistenciaException error a controlar
     */
    public void modificar(Usuario usuario) throws PersistenciaException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = persistencia.getConnection();
            preparedStatement = connection.prepareStatement(utilidadesSQL.setUpdate());
            preparedStatement.setString(1, usuario.getDni());
            preparedStatement.setString(2, usuario.getNombre());
            preparedStatement.setString(3, usuario.getApellidos());
            preparedStatement.setString(4, usuario.getEmail());
            preparedStatement.setString(5, usuario.getDireccion());
            preparedStatement.setString(6, usuario.getTelefono());
            preparedStatement.setString(7, usuario.getPais());
            preparedStatement.setString(8, usuario.getCodigoPostal());
            preparedStatement.setString(9, usuario.getProvincia());
            preparedStatement.setString(10, usuario.getNombreUsuario());
            preparedStatement.setString(11, usuario.getPassword());
            preparedStatement.setString(12, usuario.getRol());
            preparedStatement.setFloat(13, usuario.getSaldo());
            preparedStatement.setString(14, usuario.getDni());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenciaException("Ha ocurrido un error al modificar un usuario", e);
        }finally{
            persistencia.closeConnection(connection, preparedStatement, null);
        }
    }

}
