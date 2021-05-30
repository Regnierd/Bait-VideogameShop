package es.iespuertodelacruz.bait.api;

public class GeneradorId {
    String idAleatorio;

    /**
     * Funcion que devuelve un idAleatorio
     * @param etiqueta cataracter inicial del id
     * @return el idAleatorio
     */
    public String getIdAleatorio(String etiqueta) {
        String numeroAleatorio = "" + (int) (100000000*Math.random());;
        return etiqueta + "_" + numeroAleatorio;
    }

}
