package es.iespuertodelacruz.bait.modelo.productosModelo;

import java.util.ArrayList;

import es.iespuertodelacruz.bait.api.productos.Producto;
import es.iespuertodelacruz.bait.exceptions.PersistenciaException;
import es.iespuertodelacruz.bait.modelo.mysql.SQL.SQLProducto;

public class ProductoModelo {
    SQLProducto persistencia;

    /**
     * Constructor basico de la clase
     */
    public ProductoModelo() {
        persistencia = new SQLProducto("org.sqlite.JDBC", "jdbc:sqlite:bait.db", null, null);
    }

    /**
     * Metodo que inserta un producto en la base de datos
     * @param producto que se va a insertar en la base de datos
     * @throws PersistenciaException error a controlar
     */
    public void insertar(Producto producto) throws PersistenciaException {
        persistencia.insertar(producto);
    }

    /**
     * Metodo que elimina un producto en la base datos
     * @param idProducto del producto que se va a borrar
     * @throws PersistenciaException error a controlar
     */
    public void eliminar(String idProducto) throws PersistenciaException {
        persistencia.eliminar(idProducto);
    }

    /**
     * Funcion que buscca un producto por su idProducto en la base datos
     * @param idProducto del producto que se va a buscar
     * @return el producto encontrado
     * @throws PersistenciaException error a controlar
     */
    public Producto buscar(String idProducto) throws PersistenciaException {
        Producto producto;
        producto = persistencia.buscar(idProducto);

        return producto;
    }
    
    /**
     * Metodo que modifica un producto en la base datos
     * @param prodcuto con los nuevos cambios
     * @throws PersistenciaException error a controlar
     */
    public void modificar(Producto producto) throws PersistenciaException {
        persistencia.modificar(producto);
    }

    /**
     * Funcion que devuelve un lista de los productos 
     * @return la lista de los productos
     * @throws PersistenciaException error a controlar
     */
    public ArrayList<Producto> obtenerListado() throws PersistenciaException {
        ArrayList<Producto> productos;
        productos = persistencia.obtenerListado();

        return productos;
    }

    public ArrayList<Producto> buscarPorCategoria(String idCategoria) throws PersistenciaException {
        ArrayList<Producto> productos;
        productos = persistencia.buscarPorCategoria(idCategoria);
        return productos;
    }

    public ArrayList<Producto> buscarPorMarca(String idMarca) throws PersistenciaException {
        ArrayList<Producto> productos;
        productos = persistencia.buscarPorMarca(idMarca);
        return productos;
    }

    public ArrayList<Producto> buscarPorNombre(String nombre) throws PersistenciaException {
        ArrayList<Producto> productos;
        productos = persistencia.buscarPorNombre(nombre);
        return productos;
    }
}
