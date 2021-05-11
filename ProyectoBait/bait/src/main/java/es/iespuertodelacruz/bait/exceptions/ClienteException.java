package es.iespuertodelacruz.bait.exceptions;

public class ClienteException extends Exception{
    /**
     * Constructor de la clase con mensaje de error
     * @param mensaje del error lanzado
     */
    public ClienteException(String mensaje) {
        super(mensaje);
    }

    /**
     * Constructorde la clase con mensae y causa del error
     * @param mensaje del error lanzado 
     * @param causa excepcion lanzada 
     */
    public ClienteException(String mensaje, Exception causa) {
        super(mensaje, causa);
    }
}
