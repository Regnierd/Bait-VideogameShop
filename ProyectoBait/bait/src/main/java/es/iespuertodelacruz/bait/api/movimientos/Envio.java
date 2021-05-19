package es.iespuertodelacruz.bait.api.movimientos;

public class Envio {
    protected static final String DELIMITADOR = ",";
    private String idEnvio;
    private Pedido pedido;
    private String fechaEnvio;
    private String estado;

    /**
     * Constructor vacio por defecto
     */
    public Envio() {
    }

    /**
     * Constructor con todos los parametros de la clase
     * @param idEnvio del envio
     * @param pedido del envio
     * @param fechaEnvio del envio
     * @param estado del envio
     */
    public Envio(String idEnvio, Pedido pedido, String fechaEnvio, String estado) {
        this.idEnvio = idEnvio;
        this.pedido = pedido;
        this.fechaEnvio = fechaEnvio;
        this.estado = estado;
    }

    //Getters y setters
    public String getIdEnvio() {
        return this.idEnvio;
    }

    public void setIdEnvio(String idEnvio) {
        this.idEnvio = idEnvio;
    }

    public Pedido getPedido() {
        return this.pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public String getFechaEnvio() {
        return this.fechaEnvio;
    }

    public void setFechaEnvio(String fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


    @Override
    public String toString() {
        return getIdEnvio() +  DELIMITADOR +
        getPedido() + DELIMITADOR +
        getFechaEnvio() + DELIMITADOR +
        getEstado();
    }

}
