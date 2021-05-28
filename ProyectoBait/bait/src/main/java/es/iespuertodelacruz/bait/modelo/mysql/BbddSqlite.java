package es.iespuertodelacruz.bait.modelo.mysql;

import es.iespuertodelacruz.bait.exceptions.PersistenciaException;

public class BbddSqlite extends Bbdd{
    private static String driver = "org.sqlite.JDBC";
    private static String url = "jdbc:sqlite:./resorce/bait.db";
    private static String usuario = null;
    private static String password = null;


    public BbddSqlite(String tablaName, String driver, String url, String usuario, String password) throws PersistenciaException {
        super(tablaName, driver, url, usuario, password);
    }

    public BbddSqlite(String tablaName, String usuario, String password) throws PersistenciaException {
        super(tablaName, driver, url, usuario, password);
    }

}
