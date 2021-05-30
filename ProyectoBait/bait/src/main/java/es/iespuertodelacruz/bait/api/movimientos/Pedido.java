package es.iespuertodelacruz.bait.api.movimientos;

import java.util.Objects;

import es.iespuertodelacruz.bait.api.GeneradorId;
import es.iespuertodelacruz.bait.api.personas.Usuario;
import es.iespuertodelacruz.bait.api.productos.Producto;

public class Pedido extends GeneradorId{
    private static final String DELIMITADOR = ",";
    private String idPedido;
    private int unidades;
    private float total;
    private String fechaPedido;
    private Usuario usuario;
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
     * @param usuario del pedido
     * @param producto del pedido
     */
    public Pedido(String idPedido, int unidades, float total, String fechaPedido, Usuario usuario, Producto producto) {
        this.idPedido = idPedido;
        this.unidades = unidades;
        this.total = total;
        this.fechaPedido = fechaPedido;
        this.usuario = usuario;
        this.producto = producto;
    }

    /**
     * Contructor con generador aleatorio
     * @param unidades del pedido
     * @param total del pedido
     * @param fechaPedido del pedido
     * @param usuario del pedido
     * @param producto del pedido
     */
    public Pedido(int unidades, float total, String fechaPedido, Usuario usuario, Producto producto) {
        this.idPedido = getIdAleatorio("ped");
        this.unidades = unidades;
        this.total = total;
        this.fechaPedido = fechaPedido;
        this.usuario = usuario;
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

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setCliente(Usuario usuario) {
        this.usuario = usuario;
    }

    public Producto getProducto() {
        return this.producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Pedido)) {
            return false;
        }
        Pedido pedido = (Pedido) o;
        return Objects.equals(idPedido, pedido.idPedido) && unidades == pedido.unidades && total == pedido.total && Objects.equals(fechaPedido, pedido.fechaPedido) && Objects.equals(usuario, pedido.usuario) && Objects.equals(producto, pedido.producto);
    }

    @Override
    public String toString() {
        return getIdPedido() + DELIMITADOR +
        getUnidades() + DELIMITADOR + 
        getTotal() + DELIMITADOR +
        getFechaPedido() + DELIMITADOR +
        getUsuario().getDni() + DELIMITADOR +
        getProducto().getIdProducto();
    }

}
