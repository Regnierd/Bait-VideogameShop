package es.iespuertodelacruz.bait.api.productos;

import java.util.Objects;

import es.iespuertodelacruz.bait.api.GeneradorId;

public class Producto extends GeneradorId{
    private static final String DELIMITADOR = ",";
    private String idProducto;
    private String nombre;
    private float precio;
    private String descripcion;
    private int stock;
    private Categoria categoria;
    private Marca marca;

    /**
     * Constructor por defecto vacio
     */
    public Producto() {
    }

    /**
     * Constructor con todos los atributos 
     * @param idProducto del producto
     * @param nombre del producto
     * @param precio del producto
     * @param descripcion del producto
     * @param stock del producto
     * @param categoria del producto 
     * @param marca del producto
     */
    public Producto(String idProducto, String nombre, Categoria categoria, float precio, String descripcion, int stock, Marca marca) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.descripcion = descripcion;
        this.stock = stock;
        this.marca = marca;
    }

    /**
     * Constructor con el id generado automatico
     * @param nombre del producto
     * @param categoria del producto
     * @param precio del producto
     * @param descripcion del producto
     * @param stock del producto
     * @param marca del producto
     */
    public Producto(String nombre, Categoria categoria, float precio, String descripcion, int stock, Marca marca) {
        this.idProducto = getIdAleatorio("pro");
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.descripcion = descripcion;
        this.stock = stock;
        this.marca = marca;
    }

    //Getters y Setters
    public String getIdProducto() {
        return this.idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public float getPrecio() {
        return this.precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getStock() {
        return this.stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Marca getMarca() {
        return this.marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Producto)) {
            return false;
        }
        Producto producto = (Producto) o;
        return Objects.equals(idProducto, producto.idProducto) && Objects.equals(nombre, producto.nombre) && precio == producto.precio && Objects.equals(descripcion, producto.descripcion) && stock == producto.stock && Objects.equals(categoria, producto.categoria) && Objects.equals(marca, producto.marca);
    }
    
    @Override
    public String toString() {
        return getIdProducto() + DELIMITADOR +
            getNombre() + DELIMITADOR + 
            getCategoria().toString() + DELIMITADOR +
            getPrecio() + DELIMITADOR +
            getDescripcion() + DELIMITADOR +
            getStock() + DELIMITADOR +
            getCategoria().getIdCategoria() + DELIMITADOR + 
            getMarca().getIdMarca();
    }

}
