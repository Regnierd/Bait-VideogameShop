package es.iespuertodelacruz.bait.modelo.mysql;

import es.iespuertodelacruz.bait.exceptions.PersistenciaException;

public class BbddSqlite extends Bbdd{
    private static String driver = "org.sqlite.JDBC";
    private static String url = "jdbc:sqlite:bait.db";
    private static String usuario;
    private static String password;


    public BbddSqlite(String driver, String url, String usuario, String password) throws PersistenciaException {
        super(driver, url, usuario, password);
    }

    public BbddSqlite(String usuario, String password) throws PersistenciaException {
        super(driver, url, usuario, password);
    }

}
