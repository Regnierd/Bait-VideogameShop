package es.iespuertodelacruz.bait.api.productos;

public class Consola extends Producto{
    private String nombreConsola;

    /**
     * Constructor por defecto vacio
     */
    public Consola() {
    }

    /**
     * Constructor con todos los atributos
     * @param nombre de la consola
     * @param categoria de la consola
     * @param precio de la consola
     * @param descripcion de la consola
     * @param stock de la consola
     * @param marca de la consola 
     * @param consola de la consola
     */
    public Consola(String nombre, Categoria categoria, float precio, String descripcion, int stock, Marca marca, String nombreConsola) {
        super(nombre, categoria, precio, descripcion, stock, marca);
        this.nombreConsola = nombreConsola;
    }

    //Getters y Setters
    public String getNombreConsola() {
        return this.nombreConsola;
    }

    public void setNombreConsola(String nombreConsola) {
        this.nombreConsola = nombreConsola;
    }

    @Override
    public String toString() {
        return  getNombreConsola();
    }


}
