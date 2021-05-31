package es.iespuertodelacruz.bait.api.movimientos;

import java.util.Objects;

import es.iespuertodelacruz.bait.api.Validar;

public class Envio extends Validar{
    protected static final String DELIMITADOR = " | ";
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
        if(validarFecha(fechaEnvio)){
            this.fechaEnvio = fechaEnvio;
        }
        this.estado = estado;
    }

    /**
     * Constructor con generadorId
     * @param pedido del envio
     * @param fechaEnvio del envio
     * @param estado del envio
     */
    public Envio( Pedido pedido, String fechaEnvio, String estado) {
        this.idEnvio = getIdAleatorio("env");
        this.pedido = pedido;
        if(validarFecha(fechaEnvio)){
            this.fechaEnvio = fechaEnvio;
        }
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
        if(validarFecha(fechaEnvio)){
            this.fechaEnvio = fechaEnvio;
        }
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Envio)) {
            return false;
        }
        Envio envio = (Envio) o;
        return Objects.equals(idEnvio, envio.idEnvio) && Objects.equals(pedido, envio.pedido) && Objects.equals(fechaEnvio, envio.fechaEnvio) && Objects.equals(estado, envio.estado);
    }

    @Override
    public String toString() {
        return "IdEnvio: " +getIdEnvio() +  DELIMITADOR +
        "IdPedido:" +getPedido().getIdPedido() + DELIMITADOR +
        "FechaEnvio: " + getFechaEnvio() + DELIMITADOR +
        "Estado: " + getEstado();
    }

}
