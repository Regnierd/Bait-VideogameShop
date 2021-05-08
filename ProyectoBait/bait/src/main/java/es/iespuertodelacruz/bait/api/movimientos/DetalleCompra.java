package es.iespuertodelacruz.bait.api.movimientos;

public class DetalleCompra extends Factura{
    
    /**
     * Constructor por defecto vacio
     */
    public DetalleCompra() {
    }

    /**
     * Constructor con todos los atributos
     * @param unidades de la detalle compra
     * @param costoUnidad de la detalle compra
     * @param total de la detalle compra
     */
    public DetalleCompra(int unidades, float costoUnidad, float total) {
        super(unidades, costoUnidad, total);
    }


    @Override
    public String toString() {
        return super.toString();
    }


}
