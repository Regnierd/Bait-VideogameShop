package es.iespuertodelacruz.bait.modelo.mysql;

import java.util.Arrays;
import java.util.List;

public class UtilidadesSQL  {
    private String tabla;
    private String parametros;
    private String atributos;
    int atributo;
    private String SELECTALL;

    /**
     * Constructor con dos atributos de la clase
     * 
     * @param tabla     de la base de datos
     * @param parametro de la tabla
     */
    public UtilidadesSQL(String tabla, String parametro) {
        this.tabla = tabla;
        this.parametros = parametro;
        this.atributo = this.parametros.split(",").length;
        this.atributos = pintarAtributos(atributo);
        SELECTALL = "SELECT * FROM " + tabla;
    }

    // Getters
    public String getINSERT() {
        String INSERT = "INSERT INTO " + tabla + " (" + parametros + ") VALUES (" + atributos + ");";
        return INSERT;
    }

    public String getSELECTALL() {
        return this.SELECTALL;
    }

    /**
     * Funcion encargada de devolver ? por cada atributo en la tabla
     * 
     * @param atributos de la tabla
     * @return String
     */
    private String pintarAtributos(int atributos) {
        String resultado = "";
        for (int i = 0; i < atributos; i++) {
            if (i == atributos - 1) {
                resultado += "?";
            } else {
                resultado += "?,";
            }
        }
        return resultado;
    }

    /**
     * Funcion que devuelve la consulta SQL para eliminar un registro de la tabla
     * que se le pase
     * 
     * @param atributo a filtrar
     * @return String
     */
    public String setDelete(String atributo) {
        return "DELETE FROM " + this.tabla + where(atributo);
    }

    /**
     * Funcion que devuelve la consulta SQL para mostrar todos los registros de la
     * tabla que se le pase
     * 
     * @param atributo a filtrar
     * @return String
     */
    public String setSelectOne(String atributo) {
        return "SELECT * FROM " + tabla + where(atributo);
    }


    /**
     * Funcion que devuelve la consulta SQL para actualizar un registro de la tabla
     * que se le pase
     *
     * @return String
     */
    public String setUpdate() {
        String update = "UPDATE " + this.tabla + " SET ";
        List<String> campos = Arrays.asList(parametros.split(","));
        for (int i = 0; i < atributo; i++) {
            if (i == atributo - 1) {
                update += campos.get(i) + " = ?";
            } else {
                update += campos.get(i) + " = ?,";
            }
        }

        update += where(campos.get(0));

        return update;
    }

    /**
     * Funcion que devuelve el filtro WHERE pasandole un atributo
     * 
     * @param atributo a filtrar
     * @return String
     */
    private String where(String atributo) {
        return " WHERE " + atributo + " = ?";
    }

    

}
