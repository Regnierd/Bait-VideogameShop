package es.iespuertodelacruz.bait.api.personas;

public class Empleado{
    protected static final String DELIMITADOR = ",";
    private String idEmpleado;
    private String nombreAcceso;
    private String password;

    /**
     * Constructor basico de la clase
     */
    public Empleado() {
    }

    /**
     * Constructor con todos los parametros
     * @param idEmpleado del empleado
     * @param nombreAcceso del empleado
     * @param password del empleado
     */
    public Empleado(String idEmpleado, String nombreAcceso, String password) {
        this.idEmpleado = idEmpleado;
        this.nombreAcceso = nombreAcceso;
        this.password = password;
    }

    //GETTERS Y SETTERS 
    public String getIdEmpleado() {
        return this.idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombreAcceso() {
        return this.nombreAcceso;
    }

    public void setNombreAcceso(String nombreAcceso) {
        this.nombreAcceso = nombreAcceso;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return getIdEmpleado() + DELIMITADOR +
        getNombreAcceso() + DELIMITADOR +
        getPassword();
    }
}
