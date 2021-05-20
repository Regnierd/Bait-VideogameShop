package es.iespuertodelacruz.bait.modelo.movimientosModelo;

import java.util.ArrayList;

import es.iespuertodelacruz.bait.api.movimientos.Envio;
import es.iespuertodelacruz.bait.exceptions.PersistenciaException;
import es.iespuertodelacruz.bait.modelo.mysql.SQL.SQLEnvio;

public class EnvioModelo {
    SQLEnvio persistencia;

    /**
     * Constructor basico de la clase
     */
    public EnvioModelo(){
        persistencia = new SQLEnvio("org.sqlite.JDBC", "jdbc:sqlite:bait.db", null, null);
    }

    /**
     * Metodo encargado de insertar un envio en la base de datos
     * @param envio a insertar
     * @throws PersistenciaException error a controlar
     */
    public void insertar(Envio envio) throws PersistenciaException {
        persistencia.insertar(envio);
    }

    /**
     * Metodo encargado de eliminar un envio de la base de datos
     * @param idEnvio del envio a eliminar
     * @throws PersistenciaException error a controlar
     */
    public void eliminar(String idEnvio) throws PersistenciaException{
        persistencia.eliminar(idEnvio);
    }

    /**
     * Funcion encargada de buscar un envio en la base de datos
     * @param idEnvio a buscar 
     * @return Envio
     * @throws PersistenciaException error a controlar
     */
    public Envio buscar(String idEnvio) throws PersistenciaException{
        Envio envio;
        envio = persistencia.buscar(idEnvio);

        return envio;
    }

    /**
     * Metodo encargado de modificar un envio en la base de datos
     * @param envio a modificar
     * @throws PersistenciaException error a controlar
     */
    public void modificar(Envio envio) throws PersistenciaException {
        persistencia.modificar(envio);
    }

    /**
     * Funcion encargada de mostrar todos los envios 
     * @param idEnvio del envio
     * @return ArrayList de Pedidos
     * @throws PersistenciaException error a controlar
     */
    public ArrayList<Envio> obtenerListado() throws PersistenciaException{
        ArrayList<Envio> envios;
        envios = persistencia.obtenerListado();
        
        return envios;
    }

    /**
     * Funcion encargada de mostrar todos los envios 
     * @param idEnvio del envio
     * @return ArrayList de Pedidos
     * @throws PersistenciaException error a controlar
     */
    public ArrayList<Envio> obtenerListadoPorPedido(String idPedido) throws PersistenciaException{
        ArrayList<Envio> enviosPedidos;
        enviosPedidos = persistencia.obtenerListadoPorPedido(idPedido);
        
        return enviosPedidos;
    }

   


}
