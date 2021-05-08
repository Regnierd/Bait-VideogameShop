package es.iespuertodelacruz.bait.api.personas;

public class Cliente extends Persona{
    private float saldo;
    /**
     * Constructor basico de la clase
     */
    public Cliente() {
    }

    /**
     * Constructor con parametros basicos de la clase
     * @param dni del cliente 
     * @param nombre del cliente 
     * @param apellidos del cliente 
     * @param direccion del cliente 
     * @param saldo del cliente
     */
    public Cliente(String dni, String nombre, String apellidos, String direccion, float saldo) {
        super(dni, nombre, apellidos, direccion);
        this.saldo = saldo;
    }

    /**
     * Constructor con todos los parametros de la clase
     * @param dni del cliente 
     * @param nombre del cliente
     * @param apellidos del cliente
     * @param direccion del cliente
     * @param telefono del cliente
     * @param codigoPostal del cliente
     * @param provincia del cliente
     * @param nombreUsuario del cliente
     * @param password del cliente
     * @param saldo del cliente
     */
    public Cliente(String dni, String nombre, String apellidos, String direccion, String telefono, String codigoPostal, String provincia, String nombreUsuario, String password, float saldo) {
        super(dni, nombre, apellidos, direccion, telefono, codigoPostal, provincia, nombreUsuario, password);
        this.saldo = saldo;
    }


    //GETTERS Y SETTERS 
    public float getSaldo() {
        return this.saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return getSaldo() + DELIMITADOR
        + getDni();
    }

}
