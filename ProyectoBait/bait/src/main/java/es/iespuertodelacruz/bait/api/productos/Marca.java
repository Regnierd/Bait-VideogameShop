package es.iespuertodelacruz.bait.api.productos;

import java.util.Objects;

import es.iespuertodelacruz.bait.api.Validar;

public class Marca extends Validar{
    private String idMarca;
    private String nombre;

    /**
     * Constructor por defecto vacio
     */
    public Marca() {
    }

    /**
     * Constructor con todos los atributos de la clase
     * @param idMarca de la marca
     * @param nombre de la marca
     */
    public Marca(String idMarca, String nombre) {
        this.idMarca = idMarca;
        this.nombre = nombre;
    }

    /**
     * Constructor con el generador aleatorio
     * @param idMarca de la marca
     * @param nombre de la marca
     */
    public Marca(String nombre) {
        this.nombre = nombre;
        this.idMarca = "mar_" + nombre;
    }

    //Getters y Setters
    public String getIdMarca() {
        return this.idMarca;
    }

    public void setIdMarca(String idMarca) {
        this.idMarca = idMarca;
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
        if (!(o instanceof Marca)) {
            return false;
        }
        Marca marca = (Marca) o;
        return Objects.equals(idMarca, marca.idMarca) && Objects.equals(nombre, marca.nombre);
    }


    @Override
    public String toString() {
        return "IdMarca: "+getIdMarca() + DELIMITADOR + "Nombre: "+ getNombre();
    }

}
