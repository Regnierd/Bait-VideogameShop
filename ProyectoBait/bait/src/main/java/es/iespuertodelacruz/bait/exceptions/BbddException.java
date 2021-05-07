package es.iespuertodelacruz.bait.exceptions;

public class BbddException extends Exception{

    /**
     * Constructor de la clase con mensaje de error
     * @param mensaje del error lanzado
     */
    public BbddException(String mensaje) {
        super(mensaje);
    }

    /**
     * Constructorde la clase con mensae y causa del error
     * @param mensaje del error lanzado 
     * @param causa excepcion lanzada 
     */
    public BbddException(String mensaje, Exception causa) {
        super(mensaje, causa);
    }



}
