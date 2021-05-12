package es.iespuertodelacruz.bait.exceptions;

public class UsuarioException extends Exception{
    /**
     * Constructor de la clase con mensaje de error
     * @param mensaje del error lanzado
     */
    public UsuarioException(String mensaje) {
        super(mensaje);
    }

    /**
     * Constructorde la clase con mensae y causa del error
     * @param mensaje del error lanzado 
     * @param causa excepcion lanzada 
     */
    public UsuarioException(String mensaje, Exception causa) {
        super(mensaje, causa);
    }
}
