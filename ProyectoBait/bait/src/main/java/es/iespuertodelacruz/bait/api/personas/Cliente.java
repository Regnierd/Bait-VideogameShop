package es.iespuertodelacruz.bait.api.personas;

public abstract class Cliente {
    protected static final String DELIMITADOR = ",";
    private String dni;
    private String nombre;
    private String apellidos;
    private String email;
    private String direccion;
    private String telefono;
    private String pais;
    private String codigoPostal;
    private String provincia;
    private String nombreUsuario;
    private String password;
    private float saldo;

    /**
     * Consturctor vacio de la clase
     */
    public Cliente() {
    }

    /**
     * Constructor con todos los parametros
     * @param dni del cliente
     * @param nombre del cliente
     * @param apellidos del cliente
     * @param email del cliente
     * @param direccion del cliente
     * @param telefono del cliente
     * @param pais del cliente
     * @param codigoPostal del cliente
     * @param provincia del cliente
     * @param nombreUsuario del cliente
     * @param password del cliente
     */
    public Cliente(String dni, String nombre, String apellidos, String email, String direccion, String telefono, String pais, String codigoPostal, String provincia, String nombreUsuario, String password, float saldo) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.direccion = direccion;
        this.telefono = telefono;
        this.pais = pais;
        this.codigoPostal = codigoPostal;
        this.provincia = provincia;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.saldo = saldo;
    }
    


    // GETTERS Y SETTERS

    public String getDni() {
        return this.dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return this.apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPais() {
        return this.pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCodigoPostal() {
        return this.codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getProvincia() {
        return this.provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getNombreUsuario() {
        return this.nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public float getSaldo() {
        return this.saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return getDni() + DELIMITADOR 
        + getNombre() + DELIMITADOR 
        + getApellidos() + DELIMITADOR 
        + getEmail() + DELIMITADOR 
        + getDireccion() + DELIMITADOR 
        + getTelefono() + DELIMITADOR 
        + getPais() + DELIMITADOR
        + getCodigoPostal() + DELIMITADOR 
        + getProvincia() + DELIMITADOR 
        + getNombreUsuario() + DELIMITADOR 
        + getPassword() + DELIMITADOR
        + getSaldo();
    }

}
