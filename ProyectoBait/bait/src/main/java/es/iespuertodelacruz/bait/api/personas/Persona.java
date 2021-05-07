package es.iespuertodelacruz.bait.api.personas;

public class Persona {
    protected static final String DELIMITADOR = ",";
    private String idPersona;
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
    public Persona() {
    }

    /**
     * Constructor con todos los parametros de la clase persona
     * @param dni de la persona 
     * @param nombre de la persona 
     * @param apellidos de la persona 
     * @param direccion de la persona 
     */
    public Persona(String dni, String nombre, String apellidos, String direccion) {
        //this.idPersona = idpersona;
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
    }

    //GETTERS Y SETTERS
    public String getIdPersona() {
        return this.idPersona;
    }

    public void setIdPersona(String idPersona) {
        this.idPersona = idPersona;
    }

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


    @Override
    public String toString() {
        return getIdPersona() + DELIMITADOR
        + getDni() + DELIMITADOR
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
