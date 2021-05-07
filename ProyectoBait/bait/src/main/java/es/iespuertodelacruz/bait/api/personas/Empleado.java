package es.iespuertodelacruz.bait.api.personas;

public class Empleado extends Persona{
    private String idEmpleado; 
    private float Sueldo;
    private String fechaInicio;
    private String fechaFin;
    private String cargo;


    public Empleado() {
    }


    public Empleado(String dni, String nombre, String apellidos, String direccion, float Sueldo, String fechaInicio, String fechaFin, String cargo) {
        super(dni, nombre, apellidos, direccion);
        //this.idEmpleado = idEmpleado;
        this.Sueldo = Sueldo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.cargo = cargo;
    }

    //GETTERS Y SETTERS 
    public String getIdEmpleado() {
        return this.idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public float getSueldo() {
        return this.Sueldo;
    }

    public void setSueldo(float Sueldo) {
        this.Sueldo = Sueldo;
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
        return getIdEmpleado() + DELIMITADOR
        + getSueldo() + DELIMITADOR
        + getFechaInicio() + DELIMITADOR
        + getFechaFin() + DELIMITADOR
        + getCargo() + DELIMITADOR
        + getIdPersona();
    }





}
