package es.iespuertodelacruz.bait.api.productos;

public class Periferico extends Producto{
    protected enum Tipo {TECLADO, RATON, AURICULAR, PANTALLA, MANDO};
    private Tipo tipo;

    /**
     * Constructor por defecto vacio
     */
    public Periferico() {
    }

    /**
     * Constructor con todos los atributos
     * @param nombre del periferico
     * @param categoria del periferico
     * @param precio del periferico
     * @param descripcion del periferico
     * @param stock del periferico
     * @param marca del periferico
     * @param tipo del periferico
     */
    public Periferico(String nombre, Categoria categoria, float precio, String descripcion, int stock, Marca marca, Tipo tipo) {
        super(nombre, categoria, precio, descripcion, stock, marca);
        this.tipo = tipo;
    }

    //Getters y Setter
    public Tipo getTipo() {
        return this.tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return getTipo().toString();
    }


}
