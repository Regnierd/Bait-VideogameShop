package es.iespuertodelacruz.bait.api.movimientos;

import java.util.Objects;

public class Compra {
    private static final String DELIMITADOR = ",";
    private String idCompra;
    private Pedido pedido;
    private float totalCompra;
    

    /**
     * Constructor basico de la clase
     */
    public Compra() {
    }

    /**
     * Constructor con todos los parametros 
     * @param idCompra de la compra
     * @param pedido de la compra
     */
    public Compra(String idCompra, float totalCompra, Pedido pedido) {
        this.idCompra = idCompra;
        this.pedido = pedido;
        this.totalCompra = totalCompra;
    }


    //GETTERS Y SETTERS 

    public String getIdCompra() {
        return this.idCompra;
    }

    public void setIdCompra(String idCompra) {
        this.idCompra = idCompra;
    }

    public Pedido getPedido() {
        return this.pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public float getTotalCompra() {
        return this.totalCompra;
    }

    public void setTotalCompra(float totalCompra) {
        this.totalCompra = totalCompra;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Compra)) {
            return false;
        }
        Compra compra = (Compra) o;
        return Objects.equals(idCompra, compra.idCompra) && Objects.equals(pedido, compra.pedido) && totalCompra == compra.totalCompra;
    }


    @Override
    public String toString() {
        return getIdCompra() + DELIMITADOR + 
        getPedido().getIdPedido() + DELIMITADOR +
        getTotalCompra();
    }

}
