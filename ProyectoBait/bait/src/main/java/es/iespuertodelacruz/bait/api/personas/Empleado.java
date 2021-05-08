package es.iespuertodelacruz.bait.api.personas;

public class Empleado extends Persona{
    private float sueldo;
    private String fechaInicio;
    private String fechaFin;
    private String cargo;

    /**
     * Constructor basico de la clase
     */
    public Empleado() {
    }

    /**
     * Constructor con los parametros basicos de la clase
     * @param dni del empleado
     * @param nombre del empleado 
     * @param apellidos del empleado
     * @param direccion del empleado 
     * @param sueldo del empleado
     * @param fechaInicio del empleado
     * @param cargo del empleado
     */
    public Empleado(String dni, String nombre, String apellidos, String direccion, float sueldo, String fechaInicio, String cargo) {
        super(dni, nombre, apellidos, direccion);
        this.sueldo = sueldo;
        this.fechaInicio = fechaInicio;
        this.cargo = cargo;
    }

    //GETTERS Y SETTERS 
    public float getSueldo() {
        return this.sueldo;
    }

    public void setSueldo(float setsueldo) {
        this.sueldo = setsueldo;
    }

    public String getFechaInicio() {
        return this.fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return this.fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getCargo() {
        return this.cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }


    @Override
    public String toString() {
        return getSueldo() + DELIMITADOR
        + getFechaInicio() + DELIMITADOR
        + getFechaFin() + DELIMITADOR
        + getCargo() + DELIMITADOR
        + getDni();
    }

}
