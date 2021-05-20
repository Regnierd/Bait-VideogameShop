package es.iespuertodelacruz.bait.modelo.movimientosModelo;

import java.util.ArrayList;

import es.iespuertodelacruz.bait.api.movimientos.Compra;
import es.iespuertodelacruz.bait.exceptions.PersistenciaException;
import es.iespuertodelacruz.bait.modelo.mysql.SQL.SQLCompra;

public class CompraModelo {
    SQLCompra persistencia;

    /**
     * Construcor basico de la clase
     */
    public CompraModelo(){
        persistencia = new SQLCompra("org.sqlite.JDBC", "jdbc:sqlite:bait.db", null, null);
    }

    /**
     * Metodo encargado de insertar una compra en la base de datos
     * @param compra a insertar
     * @throws PersistenciaException error a controlar
     */
    public void insertar(Compra compra) throws PersistenciaException {
        persistencia.insertar(compra);
    }

    /**
     * Metodo encargado de eliminar una compra de la base de datos
     * @param idCompra compra a eliminar
     * @throws PersistenciaException error a controlar
     */
    public void eliminar(String idCompra) throws PersistenciaException{
        persistencia.eliminar(idCompra);
    }

    /**
     * Funcion encargada de buscar una compra en la base de datos
     * @param idCompra a buscar 
     * @return Compra
     * @throws PersistenciaException error a controlar
     */
    public Compra buscar(String idCompra) throws PersistenciaException{
        Compra compra;
        compra = persistencia.buscar(idCompra);

        return compra;
    }

    /**
     * Metodo encargado de modificar una compra en la base de datos
     * @param compra a modificar
     * @throws PersistenciaException error a controlar
     */
    public void modificar(Compra compra) throws PersistenciaException {
        persistencia.modificar(compra);
    }

    /**
     * Funcion encargada de mostrar todos las compras
     * @return ArrayList de Compras
     * @throws PersistenciaException error a controlar
     */
    public ArrayList<Compra> obtenerListado() throws PersistenciaException{
        ArrayList<Compra> compras;
        compras = persistencia.obtenerListado();
        
        return compras;
    }

    /**
     * Funcion encargada de mostrar todos los envios 
     * @param idEnvio del envio
     * @return ArrayList de Pedidos
     * @throws PersistenciaException error a controlar
     */
    public ArrayList<Compra> obtenerListadoPorPedido(String idPedido) throws PersistenciaException{
        ArrayList<Compra> comprasPedidos;
        comprasPedidos = persistencia.obtenerListadoPorPedido(idPedido);
        
        return comprasPedidos;
    }
}
