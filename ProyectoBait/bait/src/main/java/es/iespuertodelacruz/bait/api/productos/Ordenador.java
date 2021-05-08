package es.iespuertodelacruz.bait.api.productos;

public class Ordenador extends Producto{
    private String especificaciones;
    private boolean portatil;

    /**
     * Constructor por defecto vacio
     */
    public Ordenador() {
    }

    /**
     * Constructor con todos los atributos
     * @param nombre del ordenador
     * @param categoria del ordenador
     * @param precio del ordenador
     * @param descripcion del ordenador
     * @param stock del ordenador
     * @param marca del ordenador 
     * @param especificaciones del ordenador
     * @param portatil del ordenador
     */
    public Ordenador(String nombre, Categoria categoria, float precio, String descripcion, int stock, Marca marca, String especificaciones, boolean portatil) {
        super(nombre, categoria, precio, descripcion, stock, marca);
        this.especificaciones = especificaciones;
        this.portatil = portatil;
    }


    public String getEspecificaciones() {
        return this.especificaciones;
    }

    public void setEspecificaciones(String especificaciones) {
        this.especificaciones = especificaciones;
    }

    public boolean isPortatil() {
        return this.portatil;
    }

    public boolean getPortatil() {
        return this.portatil;
    }

    public void setPortatil(boolean portatil) {
        this.portatil = portatil;
    }


    @Override
    public String toString() {
        return getEspecificaciones() + DELIMITADOR +
                isPortatil();
    }


}
