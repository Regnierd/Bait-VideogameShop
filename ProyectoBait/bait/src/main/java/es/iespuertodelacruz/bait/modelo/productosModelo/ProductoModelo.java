package es.iespuertodelacruz.bait.modelo.productosModelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import es.iespuertodelacruz.bait.api.productos.Categoria;
import es.iespuertodelacruz.bait.api.productos.Marca;
import es.iespuertodelacruz.bait.api.productos.Producto;
import es.iespuertodelacruz.bait.exceptions.PersistenciaException;
import es.iespuertodelacruz.bait.modelo.mysql.BbddSqlite;
import es.iespuertodelacruz.bait.modelo.mysql.UtilidadesSQL;

public class ProductoModelo {
    public static final String TABLE_NAME = "PRODUCTOS";
    private static final String IDENTIFICADOR = "idProducto";
    private static UtilidadesSQL utilidadesSQL = new UtilidadesSQL(TABLE_NAME,"idProducto, nombre, precio, descripcion"
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
     * @param producto que va a insertar en la base de datos
     * @throws PersistenciaException error a controlar
     */
    public void insertar(Producto producto) throws PersistenciaException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = persistencia.getConnection();
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
            preparedStatement = connection.prepareStatement(utilidadesSQL.setDelete(IDENTIFICADOR));
            preparedStatement.setString(1, idProducto);

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new PersistenciaException("Ha ocurrido un error al eliminar un producto", e);
        }finally{
            persistencia.closeConnection(connection, preparedStatement, null);
        }

    }

    /**
     * Funcion que realiza un consulta y devuelve una lista de productos
     * @param sql consulta que se va a realizar
     * @param valor del campo a filtrar
     * @return una lista de productos
     * @throws PersistenciaException error a controlar
     */
    private ArrayList<Producto> buscarPorElemento(String sql, String valor) throws PersistenciaException{
        ResultSet resultSet;
        ArrayList<Producto> lista = new ArrayList<>();

        resultSet = persistencia.buscarElemento(sql, valor);

        try {
            while (resultSet.next()){
                String idProducto = resultSet.getString("idProducto");
                String nombre = resultSet.getString("nombre");
                float precio = resultSet.getFloat("precio");
                String descripcion = resultSet.getString("descripcion");
                int stock = resultSet.getInt("stock");
                String idCategoria = resultSet.getString("idCategoria");
                String idMarca = resultSet.getString("idMarca");

                Categoria categoria = categoriaModelo.buscarPorId(idCategoria);
                Marca marca = marcaModelo.buscarPorId(idMarca);

                Producto producto = new Producto(idProducto, nombre, categoria, precio, descripcion, stock, marca);
                lista.add(producto);
            }
        } catch (SQLException e) {
            throw new PersistenciaException(e.getMessage());
        }

        return lista;

    }

    /**
     * Funcion que busca un producto por su idProducto
     * @param idProducto del producto que se va buscar
     * @return el producto encontrado
     * @throws PersistenciaException error a controlar
     */
    public Producto buscarPorId(String idProducto) throws PersistenciaException {
        ArrayList<Producto> lista;
        Producto producto = null;
        String sql = utilidadesSQL.setSelectOne(IDENTIFICADOR);
        lista = buscarPorElemento(sql, idProducto); 

        if (!lista.isEmpty()) {
            producto = lista.get(0);
        }

        return producto;
    }

    /**
     * Funcion que busca productos por su categoria
     * @param idProducto del producto que se va buscar
     * @return la lista de producto encontrado
     * @throws PersistenciaException error a controlar
     */
    public ArrayList<Producto> buscarPorNombre(String nombre) throws PersistenciaException {
        ArrayList<Producto> lista = null;
        String sql = "SELECT * FROM PRODUCTOS WHERE nombre LIKE '%"+nombre+"%';";

        lista = buscarPorElemento(sql, "");

        return lista;
    }

    /**
     * Funcion que busca productos por su categoria
     * @param idCategoria del producto que se va buscar
     * @return la lista de producto encontrado
     * @throws PersistenciaException error a controlar
     */
    public ArrayList<Producto> buscarPorCategoria(String idCategoria) throws PersistenciaException {
        ArrayList<Producto> lista = null;
        String sql = utilidadesSQL.setSelectOne("idCategoria");
        lista = buscarPorElemento(sql, idCategoria); 

        return lista;
    }

    /**
     * Funcion que busca productos por su categoria
     * @param idMarca del producto que se va buscar
     * @return la lista de producto encontrado
     * @throws PersistenciaException error a controlar
     */
    public ArrayList<Producto> buscarPorMarca(String idMarca) throws PersistenciaException {
        ArrayList<Producto> lista = null;
        String sql = utilidadesSQL.setSelectOne("idMarca");
        lista = buscarPorElemento(sql, idMarca); 

        return lista;
    }


    /**
     * Funcion que obtiene un listado de los productos y los devuelve
     * @return la lista de categorias
     * @throws PersistenciaException error a controlar
    */
    public ArrayList<Producto> obtenerListado() throws PersistenciaException {
        ArrayList<Producto> lista = null;
        String sql = utilidadesSQL.getSELECTALL();

        lista = buscarPorElemento(sql, "");

        return lista;
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

}
