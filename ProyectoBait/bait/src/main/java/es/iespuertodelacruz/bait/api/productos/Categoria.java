package es.iespuertodelacruz.bait.api.productos;

import java.util.Objects;

import es.iespuertodelacruz.bait.api.Validar;

public class Categoria extends Validar{
    private String idCategoria;
    private String nombre;

    /**
     * Constructor basico de la clase
     */
    public Categoria() {
    }

    /**
     * Cosntructor con todos los parametros de la clase
     * @param idCategoria de la categoria
     * @param nombre de la categoria
     */
    public Categoria(String idCategoria, String nombre) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
    }

    /**
     * Constructor con el generador aleatorio
     * @param idCategoria de la categoria
     * @param nombre de la categoria
     */
    public Categoria(String nombre) {
        this.nombre = nombre;
        this.idCategoria = "cat_"+nombre;
    }

    //GETTERS Y SETTERS
    public String getIdCategoria() {
        return this.idCategoria;
    }

    public void setIdCategoria(String idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Categoria)) {
            return false;
        }
        Categoria categoria = (Categoria) o;
        return Objects.equals(idCategoria, categoria.idCategoria) && Objects.equals(nombre, categoria.nombre);
    }

    @Override
    public String toString() {
        return "IdCategoria: "+getIdCategoria() + DELIMITADOR + "Nombre: "+ getNombre();
    }

}
