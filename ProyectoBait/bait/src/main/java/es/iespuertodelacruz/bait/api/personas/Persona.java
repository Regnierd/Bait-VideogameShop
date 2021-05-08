package es.iespuertodelacruz.bait.api.personas;

public abstract class Persona {
    protected static final String DELIMITADOR = ",";
    private String dni;
    private String nombre;
    private String apellidos;
    private String direccion;
    private String telefono;
    private String codigoPostal;
    private String provincia;
    private String nombreUsuario;
    private String password;

    /**
     * Constructor basico de la clase persona
     */
    protected Persona() {
    }

    /**
     * Constructor con todos los parametros de la clase persona
     * @param dni de la persona 
     * @param nombre de la persona 
     * @param apellidos de la persona 
     * @param direccion de la persona 
     */
    protected Persona(String dni, String nombre, String apellidos, String direccion) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
    }

    /**
     * Cosntructor con todos los parametros
     * @param dni de la persona 
     * @param nombre de la persona 
     * @param apellidos de la persona 
     * @param direccion de la persona 
     * @param telefono de la persona 
     * @param codigoPostal de la persona 
     * @param provincia de la persona 
     * @param nombreUsuariode la persona 
     * @param password de la persona 
     */
    protected Persona(String dni, String nombre, String apellidos, String direccion, String telefono, String codigoPostal, String provincia, String nombreUsuario, String password) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.telefono = telefono;
        this.codigoPostal = codigoPostal;
        this.provincia = provincia;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
    }


    //GETTERS Y SETTERS
    protected String getDni() {
        return this.dni;
    }

    protected void setDni(String dni) {
        this.dni = dni;
    }

    protected String getNombre() {
        return this.nombre;
    }

    protected void setNombre(String nombre) {
        this.nombre = nombre;
    }

    protected String getApellidos() {
        return this.apellidos;
    }

    protected void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    protected String getDireccion() {
        return this.direccion;
    }

    protected void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    protected String getTelefono() {
        return this.telefono;
    }

    protected void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    protected String getCodigoPostal() {
        return this.codigoPostal;
    }

    protected void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    protected String getProvincia() {
        return this.provincia;
    }

    protected void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    protected String getNombreUsuario() {
        return this.nombreUsuario;
    }

    protected void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    protected String getPassword() {
        return this.password;
    }

    protected void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return getDni() + DELIMITADOR
        + getNombre() + DELIMITADOR
        + getApellidos() + DELIMITADOR
        + getDireccion() + DELIMITADOR
        + getTelefono() + DELIMITADOR
        + getCodigoPostal() + DELIMITADOR
        + getProvincia() + DELIMITADOR
        + getNombreUsuario() + DELIMITADOR
        + getPassword();
    }


}
