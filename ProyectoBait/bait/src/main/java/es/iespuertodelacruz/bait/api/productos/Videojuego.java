package es.iespuertodelacruz.bait.api.productos;

public class Videojuego extends Producto{
    private String genero;
    private int edad;

    /**
     * Constructor por defecto vacio
     */
    public Videojuego() {
    }

    /**
     * Constructor con todos los atributos
     * @param nombre del videojuego
     * @param categoria del videojuego
     * @param precio del videojuego
     * @param descripcion del videojuego
     * @param stock del videojuego
     * @param marca del videojuego
     * @param genero del videojuego
     * @param edad del videojuego
     */
    public Videojuego(String nombre, Categoria categoria, float precio, String descripcion, int stock, Marca marca, String genero, int edad) {
        super(nombre, categoria, precio, descripcion, stock, marca);
        this.genero = genero;
        this.edad = edad;
    }

    //Getters y Setters
    public String getGenero() {
        return this.genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getEdad() {
        return this.edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return getGenero() + DELIMITADOR +getEdad() ;
    }


}
