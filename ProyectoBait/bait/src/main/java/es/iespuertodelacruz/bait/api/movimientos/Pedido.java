package es.iespuertodelacruz.bait.api.movimientos;

import es.iespuertodelacruz.bait.api.personas.Cliente;
import es.iespuertodelacruz.bait.api.productos.Producto;

public class Pedido {
    private final String DELIMITADOR = ",";
    private String idPedido;
    private int unidades;
    private float total;
    private String fechaPedido;
    private Cliente cliente;
    private Producto producto;

    /**
     * Constructor basico de la clase
     */
    public Pedido() {
    }

    /**
     * Contructor con todos los parametros
     * @param idPedido del pedido
     * @param unidades del pedido
     * @param total del pedido
     * @param fechaPedido del pedido
     * @param cliente del pedido
     * @param producto del pedido
     */
    public Pedido(String idPedido, int unidades, float total, String fechaPedido, Cliente cliente, Producto producto) {
        this.idPedido = idPedido;
        this.unidades = unidades;
        this.total = total;
        this.fechaPedido = fechaPedido;
        this.cliente = cliente;
        this.producto = producto;
    }

    //GETTERS Y SETTERS

    public String getIdPedido() {
        return this.idPedido;
    }

    public void setIdPedido(String idPedido) {
        this.idPedido = idPedido;
    }

    public int getUnidades() {
        return this.unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public float getTotal() {
        return this.total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getFechaPedido() {
        return this.fechaPedido;
    }

    public void setFechaPedido(String fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Producto getProducto() {
        return this.producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }


    @Override
    public String toString() {
        return getIdPedido() + DELIMITADOR +
        getUnidades() + DELIMITADOR + 
        getTotal() + DELIMITADOR +
        getFechaPedido() + DELIMITADOR +
        getCliente().getDni() + DELIMITADOR +
        getProducto().getIdProducto();
    }

}
