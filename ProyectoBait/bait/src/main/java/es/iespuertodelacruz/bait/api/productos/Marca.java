package es.iespuertodelacruz.bait.api.productos;

public class Marca {
    private final String DELIMITADOR = ",";
    private String idMarca;
    private String nombre;

    /**
     * Constructor por defecto vacio
     */
    public Marca() {
    }

    /**
     * Constructor con todos los atributos 
     * @param nombre de la marca
     */
    public Marca(String nombre) {
        this.nombre = nombre;
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
    public String toString() {
        return getIdMarca() + DELIMITADOR + getNombre();
    }

}
