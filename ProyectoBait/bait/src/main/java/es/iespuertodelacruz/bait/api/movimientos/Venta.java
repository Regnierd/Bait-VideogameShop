package es.iespuertodelacruz.bait.api.movimientos;

import es.iespuertodelacruz.bait.api.personas.Cliente;

public class Venta {
    private String DELIMITADOR = ",";
    private String fechaVenta;
    private float subTotal;
    private float impuesto;
    private float costoVenta;
    private Cliente cliente;

    /**
     * Constructor basico de la clase
     */
    public Venta() {
    }

    /**
     * Constructro con todos los paramteros de la clase
     * @param fechaVenta de la venta
     * @param subTotal de la venta 
     * @param impuesto sobre la venta
     * @param costoVenta de la venta
     * @param cliente que hace la compra
     */
    public Venta(String fechaVenta, float subTotal, float impuesto, float costoVenta, Cliente cliente) {
        this.fechaVenta = fechaVenta;
        this.subTotal = subTotal;
        this.impuesto = impuesto;
        this.costoVenta = costoVenta;
        this.cliente = cliente;
    }

    //GETTERS Y SETTERS

    public String getFechaVenta() {
        return this.fechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public float getSubTotal() {
        return this.subTotal;
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }

    public float getImpuesto() {
        return this.impuesto;
    }

    public void setImpuesto(float impuesto) {
        this.impuesto = impuesto;
    }

    public float getCostoVenta() {
        return this.costoVenta;
    }

    public void setCostoVenta(float costoVenta) {
        this.costoVenta = costoVenta;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


    @Override
    public String toString() {
        return getFechaVenta() + DELIMITADOR +
        getSubTotal() + DELIMITADOR + 
        getImpuesto() + DELIMITADOR + 
        getCostoVenta() + DELIMITADOR + 
        getCliente().getDni();
    }

}
