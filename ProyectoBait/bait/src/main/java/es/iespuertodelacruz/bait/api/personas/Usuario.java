package es.iespuertodelacruz.bait.api.personas;

import java.util.Objects;

import es.iespuertodelacruz.bait.api.Validar;

public class Usuario extends Validar{
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
    private String rol;
    private float saldo;


    /**
     * Consturctor vacio de la clase
     */
    public Usuario() {
    }

    /**
     * Constructor con todos los parametros
     * @param dni del usuario
     * @param nombre del usuario
     * @param apellidos del usuario
     * @param email del usuario
     * @param direccion del usuario
     * @param telefono del usuario
     * @param pais del usuario
     * @param codigoPostal del usuario
     * @param provincia del usuario
     * @param nombreUsuario del usuario
     * @param password del usuario
     * @param rol del usuario
     * @param saldo del usuario
     */
    public Usuario(String dni, String nombre, String apellidos, String email, String direccion, String telefono, String pais, String codigoPostal, String provincia, String nombreUsuario, String password, String rol, float saldo ) {
        if (validarDni(dni)) {
            this.dni = dni;
        }
        this.nombre = nombre;
        this.apellidos = apellidos;
        if (validarEmail(email)) {
            this.email = email;
        }
        this.direccion = direccion;
        this.telefono = telefono;
        this.pais = pais;
        this.codigoPostal = codigoPostal;
        this.provincia = provincia;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.rol = rol;
        this.saldo = saldo;  
    }
    


    // GETTERS Y SETTERS

    public String getDni() {
        return this.dni;
    }

    public void setDni(String dni) {
        if (validarDni(dni)) {
            this.dni = dni;
        }
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
        if (validarEmail(email)) {
            this.email = email;
        }
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

    public String getRol() {
        return this.rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Usuario)) {
            return false;
        }
        Usuario usuario = (Usuario) o;
        return Objects.equals(dni, usuario.dni) && Objects.equals(nombre, usuario.nombre) && Objects.equals(apellidos, usuario.apellidos) && Objects.equals(email, usuario.email) && Objects.equals(direccion, usuario.direccion) && Objects.equals(telefono, usuario.telefono) && Objects.equals(pais, usuario.pais) && Objects.equals(codigoPostal, usuario.codigoPostal) && Objects.equals(provincia, usuario.provincia) && Objects.equals(nombreUsuario, usuario.nombreUsuario) && Objects.equals(password, usuario.password) && Objects.equals(rol, usuario.rol) && saldo == usuario.saldo;
    }

    @Override
    public String toString() {
        return "Dni: "+getDni() + DELIMITADOR 
        + "Nombre: "+ getNombre() + DELIMITADOR 
        + "Apellidos: "+ getApellidos() + DELIMITADOR 
        + "Email: "+ getEmail() + DELIMITADOR 
        + "Direccion: "+ getDireccion() + DELIMITADOR 
        + "Telefono: "+ getTelefono() + DELIMITADOR 
        + "Pais: "+ getPais() + DELIMITADOR
        + "CodigoPostal: "+ getCodigoPostal() + DELIMITADOR 
        + "Provincia: "+ getProvincia() + DELIMITADOR 
        + "NombreUsuario: "+ getNombreUsuario() + DELIMITADOR 
        + "Password: "+ getPassword() + DELIMITADOR
        + "Saldo: "+ getSaldo() + DELIMITADOR 
        + "Rol: "+ getRol();
    }

}
