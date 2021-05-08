package es.iespuertodelacruz.bait.api.movimientos;

import es.iespuertodelacruz.bait.api.personas.Empleado;
import es.iespuertodelacruz.bait.api.personas.Proveedor;

public class Compra {
    private String DELIMITADOR = ",";
    private String fechaCompra;
    private float costeCompra;
    Empleado empleado;
    Proveedor proveedor;

    /**
     * Contructor basico de la clase
     */
    public Compra() {
    }

    /**
     * Contructor con todos los parametros de la calse
     * @param fechaCompra fecha en la que se realiza la compra
     * @param costeCompra coste de la compra
     * @param empleado empleados que realiza la compra
     * @param proveedor proveedor que nos vende el producto
     */
    public Compra(String fechaCompra, float costeCompra, Empleado empleado, Proveedor proveedor) {
        this.fechaCompra = fechaCompra;
        this.costeCompra = costeCompra;
        this.empleado = empleado;
        this.proveedor = proveedor;
    }

    //GETTERS Y SETTERS 

    public String getFechaCompra() {
        return this.fechaCompra;
    }

    public void setFechaCompra(String fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public float getCosteCompra() {
        return this.costeCompra;
    }

    public void setCosteCompra(float costeCompra) {
        this.costeCompra = costeCompra;
    }

    public Empleado getEmpleado() {
        return this.empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Proveedor getProveedor() {
        return this.proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    @Override
    public String toString() {
        return getFechaCompra() + DELIMITADOR + 
        getCosteCompra() + DELIMITADOR +
        getEmpleado().getDni() + DELIMITADOR + 
        getProveedor().getDni();
    }

}
