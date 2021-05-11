package es.iespuertodelacruz.bait.modelo.mysql;


public class Prueba  {
    private String tabla;
    private String parametro;
    private String atributos;
    private String INSERT = "INSERT INTO " + tabla + " ("+parametro+") VALUES ("+atributos+");";
    //private String INSERTCLIENTE = "INSERT INTO Cliente (idCliente, saldo, dni) VALUES ( ?, ? ,? );";
    private String SELECTALL = "SELECT * FROM " + this.tabla;

    

    public Prueba(String tabla, String parametro) {
        this.tabla = tabla;
        this.parametro = parametro;
        int atributo = this.parametro.split(",").length+1;
        this.atributos = pintarAtributos(atributo);
    }

    public String getINSERT() {
        return this.INSERT;
    }

    public String getSELECTALL() {
        return this.SELECTALL;
    }

    private String pintarAtributos(int atributos){
        String resultado = "";
        for (int i = 0; i < atributos; i++) {
            resultado += "?,";
        }
        return resultado;
    }

    public String setDelete(String atributo){
        return "DELETE FROM "+this.tabla + where(atributo);
    }

    public String setSelectOne(String atributo){
        return "SELECT * FROM " + tabla + where(atributo);
    }

    public String setUpdate(String atributo){
        return "UPDATE Cliente SET ("+parametro+") VALUES ("+atributos+")"+ where(atributo);
    }
    
    private String where(String atributo){
        return  " WHERE " + atributo + "= ?";
    }



    

}
