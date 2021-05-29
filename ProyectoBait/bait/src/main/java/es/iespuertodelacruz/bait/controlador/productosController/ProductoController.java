package es.iespuertodelacruz.bait.controlador.productosController;

import java.util.ArrayList;

import es.iespuertodelacruz.bait.api.productos.Producto;
import es.iespuertodelacruz.bait.exceptions.ApiException;
import es.iespuertodelacruz.bait.exceptions.PersistenciaException;
import es.iespuertodelacruz.bait.modelo.productosModelo.ProductoModelo;

public class ProductoController {
    ProductoModelo productoModelo;
    CategoriaController categoriaController;
    MarcaController marcaController;
    /**
     * Constructor basico de la clase
     * @throws PersistenciaException
     */
    public ProductoController() throws PersistenciaException {
        categoriaController = new CategoriaController();
        marcaController = new MarcaController();
        productoModelo = new ProductoModelo();
    }

    public void validar(Producto producto) throws ApiException{
        String mensaje = "";
        if(producto == null){
            mensaje = "El producto no puede ser nulo";
            throw new ApiException(mensaje);
        }
        if(producto.getIdProducto() == null || producto.getIdProducto().isEmpty()){
            mensaje += "El idProducto no puede ser nulo o vacio";
        }
        if(producto.getNombre() == null || producto.getNombre().isEmpty()){
            mensaje += "El nombre no pueden ser nulo o vacio";
        }
        if(producto.getPrecio() <=0){
            mensaje += "El precio no pueden ser 0 o negativo.";
        }
        if (producto.getDescripcion() == null || producto.getDescripcion().isEmpty()) {
            mensaje += "La descripcion no pueden ser nulo o vacio";
        }
        if (producto.getStock() < 0) {
            mensaje += "El stock no puede ser negativo";
        }
        if (producto.getCategoria() == null) {
            mensaje += "La categoria no puede ser nula.";
        }
        if (producto.getMarca() == null) {
            mensaje += "La Marca no puede ser nula.";
        }

        if(!mensaje.isEmpty()){
            throw new ApiException(mensaje);
        }
    }

    /**
     * Funcion que comprueba si un producto existe
     * 
     * @param producto que se va a comprobar
     * @return verdadero/falso
     * @throws PersistenciaException error a controlar
     */
    private boolean existe(String identificador) throws PersistenciaException {
        boolean encontrado = false;
        Producto productoEncontrado = null;

        productoEncontrado = productoModelo.buscarPorId(identificador);
        if (productoEncontrado != null) {
            encontrado = true;
        }

        return encontrado;
    }

    /**
     * Metodo que insertar un producto
     * 
     * @param producto que se va a insertar
     * @throws ApiException          error a controlar
     * @throws PersistenciaException error a controlar
     */
    public void insertar(Producto producto) throws ApiException, PersistenciaException {
        validar(producto);
        if (existe(producto.getIdProducto())) {
            throw new ApiException("El producto que quiere insertar ya existe.");
        }
        productoModelo.insertar(producto);
    }

    /**
     * Metodo que elimina un producto
     * @param idProducto identificador del producto a borrar
     * @throws PersistenciaException error a controlar
     * @throws ApiException
     */
    public void eliminar(String idProducto) throws PersistenciaException, ApiException {
        if(!existe(idProducto)){
            throw new ApiException("El producto que quiere eliminar no existe");
        }
        productoModelo.eliminar(idProducto);
    }

    /**
     * Funcion que busca un producto por su identificador y lo 
     * devuelve
     * @param idProducto identificador del producto que se va a buscar
     * @return el producto encontrado
     * @throws PersistenciaException error a controlar
     */
    public Producto buscar(String idProducto) throws PersistenciaException {
        Producto producto = null;
        producto = productoModelo.buscarPorId(idProducto);

        return producto;
    }

    /**
     * Metodo que modifica un producto 
     * @param producto con los nuevos cambios 
     * @throws ApiException error a controlar
     * @throws PersistenciaException error a controlar
     */
    public void modificar(Producto producto) throws ApiException, PersistenciaException {
        validar(producto);
        if (!existe(producto.getIdProducto())) {
            throw new ApiException("El producto que intenta modificar no existe");
        }
        productoModelo.modificar(producto);
    }

    /**
     * Funcion que devuelve un lista de productos que tiene una misma categoria
     * @param idCategoria identificador de la categoria que tienen los productos
     * @return la lista de productos filtrados por una categoria
     * @throws ApiException error a controlar
     * @throws PersistenciaException error a controlar
     */
    public ArrayList<Producto> buscarPorCategoria(String idCategoria) throws ApiException, PersistenciaException {
        ArrayList<Producto> productos = null;
        productos = productoModelo.buscarPorCategoria(idCategoria);

        if (productos.isEmpty()) {
            throw new ApiException("No existe productos con esa categoria");
        }
        return productos;
    }

    /**
     * Funcion que devuelve un lista de productos que tiene la misma marca
     * @param idMarca que tiene los productos
     * @return la lista de productos filtrados por un marca
     * @throws ApiException error a controlar
     * @throws PersistenciaException error a controlar
     */
    public ArrayList<Producto> buscarPorMarca(String idMarca) throws ApiException, PersistenciaException {
        ArrayList<Producto> productos = null;
        productos = productoModelo.buscarPorMarca(idMarca);

        if (productos.isEmpty()) {
            throw new ApiException("No existe productos con esa marca");
        }
        return productos;
    }

    /**
     * Funcion que devuelve un lista de productos que tiene el nombre o parte de el
     * @param nombre que tiene el producto o productos
     * @return la lista de productos filtrados por un nombre
     * @throws ApiException error a controlar
     * @throws PersistenciaException error a controlar
     */
    public ArrayList<Producto> buscarPorNombre(String nombre) throws ApiException, PersistenciaException {
        ArrayList<Producto> productos = null;
        productos = productoModelo.buscarPorNombre(nombre);

        if (productos.isEmpty()) {
            throw new ApiException("No existe productos con ese nombre");
        }
        return productos;
    }

    /**
     * Funcion que obtiene la lista de productos y la devuelve
     * @return la lista de prodcutos
     * @throws PersistenciaException
     */
    public ArrayList<Producto> obtenerListado() throws PersistenciaException, ApiException{
        ArrayList<Producto> productos = null;
        productos = productoModelo.obtenerListado();
        if(productos == null || productos.isEmpty()){
            throw new ApiException("La lista de productos es vacia o nula");
        }
        return productos;
        
    }

}
