package es.iespuertodelacruz.bait.api.personas;

public class Cliente extends Persona{
    private String idCliente;
    private float saldo;

    /**
     * Constructor basico de la clase
     */
    public Cliente() {
    }

    /**
     * Constructor con parametros basicos de la calse
     * @param dni de la cliente 
     * @param nombre de la cliente 
     * @param apellidos de la cliente 
     * @param direccion de la cliente 
     * @param saldo del cliente
     */
    public Cliente(String dni, String nombre, String apellidos, String direccion, float saldo) {
        super(dni, nombre, apellidos, direccion);
        //this.idCliente = idCliente;
        this.saldo = saldo;
    }

    //GETTERS Y SETTERS 

    public String getIdCliente() {
        return this.idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public float getSaldo() {
        return this.saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return getIdCliente() + DELIMITADOR
        + getSaldo() + DELIMITADOR
        + getIdPersona();
    }

}
