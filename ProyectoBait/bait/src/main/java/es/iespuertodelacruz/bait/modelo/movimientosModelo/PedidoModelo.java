package es.iespuertodelacruz.bait.modelo.movimientosModelo;

import java.util.ArrayList;

import es.iespuertodelacruz.bait.api.movimientos.Pedido;
import es.iespuertodelacruz.bait.exceptions.PersistenciaException;
import es.iespuertodelacruz.bait.modelo.mysql.SQL.SQLPedido;

public class PedidoModelo {
    
    SQLPedido persistencia;

    /**
     * Constructor basico de la clase
     */
    public PedidoModelo(){
        persistencia = new SQLPedido("org.sqlite.JDBC", "jdbc:sqlite:bait.db", null, null);
    }

    /**
     * Metodo encargado de insertar un pedido en la base de datos
     * @param pedido a insertar
     * @throws PersistenciaException error a controlar
     */
    public void insertar(Pedido pedido) throws PersistenciaException {
        persistencia.insertar(pedido);
    }

    /**
     * Metodo encargado de eliminar un pedido de la base de datos
     * @param idPedido del pedido a eliminar
     * @throws PersistenciaException error a controlar
     */
    public void eliminar(String idPedido) throws PersistenciaException{
        persistencia.eliminar(idPedido);
    }

    /**
     * Funcion encargada de buscar un pedido en la base de datos
     * @param idPedido a buscar 
     * @return Pedido
     * @throws PersistenciaException error a controlar
     */
    public Pedido buscar(String idPedido) throws PersistenciaException{
        Pedido pedido;
        pedido = persistencia.buscar(idPedido);

        return pedido;
    }

    /**
     * Metodo encargado de modificar un pedido en la base de datos
     * @param pedido a modificar
     * @throws PersistenciaException error a controlar
     */
    public void modificar(Pedido pedido) throws PersistenciaException {
        persistencia.modificar(pedido);
    }

    /**
     * Funcion encargada de mostrar todos los pedidos de un usuario
     * @param dni del usuario
     * @return ArrayList de Pedidos
     * @throws PersistenciaException error a controlar
     */
    public ArrayList<Pedido> obtenerListado(String dni) throws PersistenciaException{
        ArrayList<Pedido> pedidos;
        pedidos = persistencia.obtenerListado(dni);
        
        return pedidos;
    }


}
