package es.iespuertodelacruz.bait.modelo.mysql;

public class SQLCompra extends Bbdd {
    UtilidadesSQL utilidadesSQL = new UtilidadesSQL("Compra", "idCompra, totalCompra, pedido");

    /**
     * Constructor basico de la clase 
     */
    public SQLCompra(String driver, String url, String usuario, String password) {
        super(driver, url, usuario, password);
        
    }
    
}
