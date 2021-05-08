package es.iespuertodelacruz.bait.api.personas;

public class Proveedor extends Persona{
    private String idProveerdor;

    /**
     * Constructor basicos de la clase
     */
    public Proveedor() {
    }

    /**
     * Constructor con los parametros basicos de la clase
     * @param dni del proveedor
     * @param nombre del proveedor
     * @param apellidos del proveedor
     * @param direccion del proveedor
     * @param idProveerdor del proveedor
     */
    public Proveedor(String dni, String nombre, String apellidos, String direccion, String idProveerdor) {
        this.idProveerdor = idProveerdor;
    }

    //GETTERS Y SETTERS
    public String getIdProveerdor() {
        return this.idProveerdor;
    }

    public void setIdProveerdor(String idProveerdor) {
        this.idProveerdor = idProveerdor;
    }

    @Override
    public String toString() {
        return getIdProveerdor() + DELIMITADOR
        + getDni();
    }

}
