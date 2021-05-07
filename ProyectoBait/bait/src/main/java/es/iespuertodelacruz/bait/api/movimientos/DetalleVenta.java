package es.iespuertodelacruz.bait.api.movimientos;

public class DetalleVenta extends Factura{
    private float descuentoUnidad;
    
    /**
     * Constructor por defecto vacio
     */
    public DetalleVenta() {
    }

    /**
     * Constructor con todos los atributos
     * @param unidades de la detalle venta
     * @param costoUnidad de la detalle venta
     * @param total de la detalle venta
     * @param descuentoUnidad de la detalle venta
     */
    public DetalleVenta(int unidades, float costoUnidad, float total, float descuentoUnidad) {
        super(unidades, costoUnidad, total);
        this.descuentoUnidad = descuentoUnidad;
    }

    //Getters y Setters
    public float getDescuentoUnidad() {
        return this.descuentoUnidad;
    }

    public void setDescuentoUnidad(float descuentoUnidad) {
        this.descuentoUnidad = descuentoUnidad;
    }


    @Override
    public String toString() {
        return getDescuentoUnidad() + "";
    } 

}
