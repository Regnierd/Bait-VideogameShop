package es.iespuertodelacruz.bait.modelo.productosModelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import es.iespuertodelacruz.bait.api.productos.Categoria;
import es.iespuertodelacruz.bait.api.productos.Marca;
import es.iespuertodelacruz.bait.api.productos.Producto;
import es.iespuertodelacruz.bait.exceptions.PersistenciaException;
import es.iespuertodelacruz.bait.modelo.mysql.BbddSqlite;
import es.iespuertodelacruz.bait.modelo.mysql.UtilidadesSQL;

public class ProductoModelo {
    private static final String ID_PRODUCTO = "idProducto";
    public static final String TABLE_NAME = "PRODUCTOS";
    private static UtilidadesSQL utilidadesSQL = new UtilidadesSQL(TABLE_NAME,"idProducto, nombre, precio, descripcion "
    + ", stock, idCategoria, idMarca");
    BbddSqlite persistencia;
    MarcaModelo marcaModelo;
    CategoriaModelo categoriaModelo;
    /**
     * Constructor basico de la clase
     * @throws PersistenciaException
     */
    public ProductoModelo() throws PersistenciaException {
        categoriaModelo = new CategoriaModelo();
        marcaModelo = new MarcaModelo();
        persistencia = new BbddSqlite(TABLE_NAME, null, null);
    }

        /**
     * Metodo que inserta un producto en la base de datos
     * 
     * @param producto que va a insertar en la base de datos
     * @throws PersistenciaException error a controlar
     */
    public void insertar(Producto producto) throws PersistenciaException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = persistencia.getConnection();//MIRAR REFACTORIZAR
            preparedStatement = connection.prepareStatement(utilidadesSQL.getINSERT());
            preparedStatement.setString(1, producto.getIdProducto());
            preparedStatement.setString(2, producto.getNombre());
            preparedStatement.setFloat(3, producto.getPrecio());
            preparedStatement.setString(4, producto.getDescripcion());
            preparedStatement.setInt(5, producto.getStock());
            preparedStatement.setString(6, producto.getCategoria().getIdCategoria());
            preparedStatement.setString(7, producto.getMarca().getIdMarca());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new PersistenciaException("Ha ocurrido un error al insertar un producto", e);
        }finally{
            persistencia.closeConnection(connection, preparedStatement, null);
        }
     
    }

    /**
     * Metodo encargado de eliminar un producto en la base de datos
     * 
     * @param idProducto del producto
     * @throws PersistenciaException error a controlar
     */
    public void eliminar(String idProducto) throws PersistenciaException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = persistencia.getConnection();
            preparedStatement = connection.prepareStatement(utilidadesSQL.setDelete(ID_PRODUCTO));
            preparedStatement.setString(1, idProducto);

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new PersistenciaException("Ha ocurrido un error al eliminar un producto", e);
        }finally{
            persistencia.closeConnection(connection, preparedStatement, null);
        }

    }

    /**
     * Metodo encargado de modificar un producto en la base de datos
     * @param producto a modificar
     * @throws PersistenciaException error a controlar
     */
    public void modificar(Producto producto) throws PersistenciaException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = persistencia.getConnection();
            preparedStatement = connection.prepareStatement(utilidadesSQL.setUpdate());
            preparedStatement.setString(1, producto.getIdProducto());
            preparedStatement.setString(2, producto.getNombre());
            preparedStatement.setFloat(3, producto.getPrecio());
            preparedStatement.setString(4, producto.getDescripcion());
            preparedStatement.setInt(5, producto.getStock());
            preparedStatement.setString(6, producto.getCategoria().getIdCategoria());
            preparedStatement.setString(7, producto.getMarca().getIdMarca());
            preparedStatement.setString(8, producto.getIdProducto());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new PersistenciaException("Ha ocurrido un error al modificar un producto", e);
        }finally{
            persistencia.closeConnection(connection, preparedStatement, null);
        }
        
    }

    /**
     * Funcion que busca un producto en la base de datos y lo devuelve
     * 
     * @param idProducto del producto que se va a buscar
     * @return producto 
     * @throws PersistenciaException error a controlar
     */
    public Producto buscar(String idProducto) throws PersistenciaException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Categoria categoria = null;
        Marca marca = null;
        Producto producto = null;
        try {
            connection = persistencia.getConnection();
            preparedStatement = connection.prepareStatement(utilidadesSQL.setSelectOne(ID_PRODUCTO));
            preparedStatement.setString(1, idProducto);
            resultSet = preparedStatement.executeQuery();
            //REFACTORIZAR
            String nombre = resultSet.getString("nombre");
            float precio = resultSet.getFloat("precio");
            String descripcion = resultSet.getString("descripcion");
            int stock = resultSet.getInt("stock");
            String idCategoria = resultSet.getString("idCategoria");
            String idMarca = resultSet.getString("idMarca");

            categoria = categoriaModelo.buscar(idCategoria);
            marca = marcaModelo.buscar(idMarca);

            producto = new Producto(idProducto, nombre, categoria, precio, descripcion, stock, marca);

        } catch (SQLException e) {
            throw new PersistenciaException("Ha ocurrido un error al buscar el producto", e);
        } finally {
            persistencia.closeConnection(connection, preparedStatement, resultSet);
        }
   
        return producto;
    }

    /**
     * Funcion que obtiene un listado de los producto y los devuelve
     * 
     * @return la lista de productos
     * @throws PersistenciaException error a controlar
     */
    public ArrayList<Producto> obtenerListado() throws PersistenciaException {
        Connection connection = null;
        ArrayList<Producto> productos = new ArrayList<>();
        ResultSet resultSet = null;
        Statement statement = null;

        try {
            connection = persistencia.getConnection();
            statement = connection.createStatement();
            statement.setMaxRows(30);

            resultSet = statement.executeQuery(utilidadesSQL.getSELECTALL());

            while (resultSet.next()) {
                String idProducto = resultSet.getString(ID_PRODUCTO);
                String nombre = resultSet.getString("nombre");
                float precio = resultSet.getFloat("precio");
                String descripcion = resultSet.getString("descripcion");
                int stock = resultSet.getInt("stock");
                String idCategoria = resultSet.getString("idCategoria");
                String idMarca = resultSet.getString("idMarca");

                Categoria categoria = categoriaModelo.buscar(idCategoria);
                Marca marca = marcaModelo.buscar(idMarca);

                Producto producto = new Producto(idProducto, nombre, categoria, precio, descripcion, stock, marca);
                
                productos.add(producto);
            }
        } catch (Exception e) {
            throw new PersistenciaException("Ha ocurrido un error al obtener el listado de todos los productos", e);
        }finally{
            persistencia.closeConnection(connection, statement, resultSet);
        }

        return productos;
    }

    /**
     * Obtener listado pasandole el campo y el valor
     * @param atributo nombre campo
     * @param valor valor del campo
     * @return lista de productos
     * @throws PersistenciaException error a controlar
     */
    public ArrayList<Producto> obtenerListado(String atributo, String valor) throws PersistenciaException {
        //REFACTORIZAR
        Connection connection = null;
        ArrayList<Producto> productos = new ArrayList<>();
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = persistencia.getConnection();
            preparedStatement = connection.prepareStatement(utilidadesSQL.setSelectOneLike(atributo));
            preparedStatement.setString(1, valor);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String idProducto = resultSet.getString(ID_PRODUCTO);
                String nombre = resultSet.getString("nombre");
                float precio = resultSet.getFloat("precio");
                String descripcion = resultSet.getString("descripcion");
                int stock = resultSet.getInt("stock");
                String idCategoria = resultSet.getString("idCategoria");
                String idMarca = resultSet.getString("idMarca");

                Categoria categoria = categoriaModelo.buscar(idCategoria);
                Marca marca = marcaModelo.buscar(idMarca);

                Producto producto = new Producto(idProducto, nombre, categoria, precio, descripcion, stock, marca);
                
                productos.add(producto);
            }
        } catch (Exception e) {
            throw new PersistenciaException("Ha ocurrido un error al obtener el listado de todos los productos", e);
        }finally{
            persistencia.closeConnection(connection, preparedStatement, resultSet);
        }

        return productos;
    }

    public ArrayList<Producto> buscarPorCategoria(String idCategoria) throws PersistenciaException {
        String atributo = "idCategoria";
        ArrayList<Producto> productos = null;
        productos = obtenerListado(atributo, idCategoria);
        
        return productos;
    }

    public ArrayList<Producto> buscarPorMarca(String idMarca) throws PersistenciaException {
        String atributo = "idMarca";
        ArrayList<Producto> productos = null;
        productos = obtenerListado(atributo, idMarca);
        
        return productos;
    }

    public ArrayList<Producto> buscarPorNombre(String nombre) throws PersistenciaException {
        String atributo = "nombre";
        ArrayList<Producto> productos = null;
        productos = obtenerListado(atributo, nombre);
        
        return productos;
    }
}
