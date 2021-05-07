package es.iespuertodelacruz.bait.api.productos;

public class Figura extends Producto{
    private float tamanio;

    /**
     * Constructor por defecto vacio
     */
    public Figura() {
    }

    /**
     * Constructor con todos los atributos
     * @param nombre de la figura
     * @param categoria de la figura
     * @param precio de la figura
     * @param descripcion de la figura
     * @param stock de la figura
     * @param marca de la figura
     * @param tamanio de la figura
     */
    public Figura(String nombre, Categoria categoria, float precio, String descripcion, int stock, Marca marca, float tamanio) {
        super(nombre, categoria, precio, descripcion, stock, marca);
        this.tamanio = tamanio;
    }

    //Getters y setters
    public float getTamanio() {
        return this.tamanio;
    }

    public void setTamanio(float tamanio) {
        this.tamanio = tamanio;
    }


    @Override
    public String toString() {
        return getTamanio() + "";
    }


}
