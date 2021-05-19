package es.iespuertodelacruz.bait.api.productos;

public class Categoria {
    private final String DELIMITADOR = ",";
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
    public String toString() {
        return getIdCategoria() + DELIMITADOR + getNombre();
    }

}
