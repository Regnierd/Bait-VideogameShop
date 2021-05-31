package es.iespuertodelacruz.bait.api;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validar{
    protected static final String DELIMITADOR = " | ";
    String idAleatorio;

    /**
     * Funcion que devuelve un idAleatorio
     * @param etiqueta cataracter inicial del id
     * @return el idAleatorio
     */
    public String getIdAleatorio(String etiqueta) {
        String numeroAleatorio = "" + (int) (100000000*Math.random());
        return etiqueta + "_" + numeroAleatorio;
    }

    /**
     * Funcion que validad un elemento a partir de una expresion regular 
     * pasada por parametros
     * @param expresion reguñar para validar el elemento
     * @param elemento que se va a validar
     * @return verdadero/falso
     */
    private boolean validarElemento(String expresion, String elemento) {
        boolean valido = false;
        Pattern patron = Pattern.compile(expresion);
        Matcher matcher = patron.matcher(elemento);        
        if (matcher.matches()) {
            valido = true;   
        }
        return valido;
    }

    /**
     * Funcion que comprueba un dni 
     * @param dni que se va a comprobar
     * @return vardadero/Falso
     */
    public boolean validarDni(String dni) {
        String expresion = "^[0-9]{8,8}[A-Za-z]$";
        return validarElemento(expresion, dni);
    }   

    /**
     * Funcion que comprueba una fecha
     * @param fecha que se va a comprobar
     * @return vardadero/Falso
     */
    public boolean validarFecha(String fecha) {
        String expresion = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$";
        return validarElemento(expresion, fecha);
    } 

    /**
     * Funcion que comprueba un emai
     * @param email que se va a comprobar
     * @return vardadero/Falso
     */
    public boolean validarEmail(String email) {
        String expresion = "^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        return validarElemento(expresion, email);
    }

}
