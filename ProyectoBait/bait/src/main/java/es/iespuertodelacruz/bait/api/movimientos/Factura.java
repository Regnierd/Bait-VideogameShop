package es.iespuertodelacruz.bait.api.movimientos;

public abstract class Factura {
    private int unidades;
    private float costoUnidad;
    private float total;
    protected static final String DELIMITADOR = ",";

    /**
     * Constructor por defecto vacio
     */
    protected Factura() {
    }

    /**
     * Constructor con todos los atributos
     * @param unidades de la factura
     * @param costoUnidad de la factura
     * @param total de la factura
     */
    protected Factura(int unidades, float costoUnidad, float total) {
        this.unidades = unidades;
        this.costoUnidad = costoUnidad;
        this.total = total;
    }

    //Getters y Setters
    public int getUnidades() {
        return this.unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public float getCostoUnidad() {
        return this.costoUnidad;
    }

    public void setCostoUnidad(float costoUnidad) {
        this.costoUnidad = costoUnidad;
    }

    public float getTotal() {
        return this.total;
    }

    public void setTotal(float total) {
        this.total = total;
    }


    @Override
    public String toString() {
        return getUnidades() + DELIMITADOR + getCostoUnidad() + DELIMITADOR +getTotal() ;
    }

}
