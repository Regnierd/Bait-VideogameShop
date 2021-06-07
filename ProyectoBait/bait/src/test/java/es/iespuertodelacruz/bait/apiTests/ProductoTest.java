package es.iespuertodelacruz.bait.apiTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.iespuertodelacruz.bait.api.productos.Categoria;
import es.iespuertodelacruz.bait.api.productos.Marca;
import es.iespuertodelacruz.bait.api.productos.Producto;

public class ProductoTest {
    private static final String IDPRODUCTO = "idProducto";
    Producto producto = new Producto();
    Producto producto2 = new Producto();
    Categoria categoria = new Categoria();
    Marca marca = new Marca();

    @BeforeEach
    public void setUp() {
        producto = crear(IDPRODUCTO, "nombre", categoria, 100f, "descripcion", 10, marca);
        producto2 = new Producto("nombre", categoria, 100f, "descripcion", 10, marca);
    }

    @Test
    public void crearTest() {
        assertEquals(IDPRODUCTO,producto.getIdProducto(),"El producto no se creao correctamente");
    }

    @Test
    public void mostrarTest() {
        String informacion = producto.toString();
        assertTrue(informacion.contains(IDPRODUCTO),"No esta mostrado el producto correcto");
    }

    /**
     * Funcion que crear y retorna un producto
     * @param idProducto del producto
     * @param nombre del producto
     * @param categoria del producto
     * @param precio del producto
     * @param descripcion del producto
     * @param stock del producto
     * @param marca del producto
     * @return el producto
     */
    private Producto crear(String idProducto, String nombre, Categoria categoria, float precio, String descripcion, int stock, Marca marca) {
        producto.setIdProducto(idProducto);
        producto.setNombre(nombre);
        producto.setCategoria(categoria);
        producto.setPrecio(precio);
        producto.setPrecio(precio);
        producto.setDescripcion(descripcion);
        producto.setStock(stock);
        producto.setMarca(marca);

        return new Producto(idProducto, nombre, categoria, precio, descripcion, stock, marca);
    }
}
