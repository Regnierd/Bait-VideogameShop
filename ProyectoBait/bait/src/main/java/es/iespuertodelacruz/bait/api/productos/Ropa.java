package es.iespuertodelacruz.bait.api.productos;

public class Ropa extends Producto { 
    private String talla;

    /**
     * Constructor por defecto vacio
     */
    public Ropa() {
    }

    /**
     * Constructor con todos los atributos
     * @param nombre de la ropa
     * @param categoria de la ropa
     * @param precio de la ropa
     * @param descripcion de la ropa
     * @param stock de la ropa 
     * @param marca de la ropa 
     * @param talla de la ropa
     */
    public Ropa(String nombre, Categoria categoria, float precio, String descripcion, int stock, Marca marca, String talla) {
        super(nombre, categoria, precio, descripcion, stock, marca);
        this.talla = talla;
    }
        
    //Getters y Setters
    public String getTalla() {
        return this.talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    @Override
    public String toString() {
        return  getTalla();
    }

}
