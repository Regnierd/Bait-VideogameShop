package es.iespuertodelacruz.bait.modelo.mysql;


public class UtilidadesSQL  {
    private String tabla;
    private String parametro;
    private String atributos;
    private String INSERT = "INSERT INTO " + tabla + " ("+parametro+") VALUES ("+atributos+");";
    //private String INSERTCLIENTE = "INSERT INTO Cliente (idCliente, saldo, dni) VALUES ( ?, ? ,? );";
    private String SELECTALL = "SELECT * FROM " + this.tabla;

    /**
     * Constructor con dos atributos de la clase
     * @param tabla de la base de datos
     * @param parametro de la tabla
     */
    public UtilidadesSQL(String tabla, String parametro) {
        this.tabla = tabla;
        this.parametro = parametro;
        int atributo = this.parametro.split(",").length+1;
        this.atributos = pintarAtributos(atributo);
    }

    //Getters
    public String getINSERT() {
        return this.INSERT;
    }

    public String getSELECTALL() {
        return this.SELECTALL;
    }

    /**
     * Funcion encargada de devolver ? por cada atributo en la tabla
     * @param atributos de la tabla
     * @return String
     */
    private String pintarAtributos(int atributos){
        String resultado = "";
        for (int i = 0; i < atributos; i++) {
            resultado += "?,";
        }
        return resultado;
    }

    /**
     * Funcion que devuelve la consulta SQL para eliminar un registro de la tabla que se le pase
     * @param atributo a filtrar
     * @return String
     */
    public String setDelete(String atributo){
        return "DELETE FROM "+this.tabla + where(atributo);
    }

    /**
     * Funcion que devuelve la consulta SQL para mostrar todos los registros de la tabla que se le pase
     * @param atributo a filtrar
     * @return String
     */
    public String setSelectOne(String atributo){
        return "SELECT * FROM " + tabla + where(atributo);
    }

    /**
     * Funcion que devuelve la consulta SQL para actualizar un registro de la tabla que se le pase
     * @param atributo a filtrar
     * @return String
     */
    public String setUpdate(String atributo){
        return "UPDATE Cliente SET ("+parametro+") VALUES ("+atributos+")"+ where(atributo);
    }
    
    /**
     * Funcion que devuelve el filtro WHERE pasandole un atributo
     * @param atributo a filtrar
     * @return String
     */
    private String where(String atributo){
        return  " WHERE " + atributo + "= ?";
    }



    

}
