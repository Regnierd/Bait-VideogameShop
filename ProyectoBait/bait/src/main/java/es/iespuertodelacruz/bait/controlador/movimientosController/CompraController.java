package es.iespuertodelacruz.bait.controlador.movimientosController;

import java.util.ArrayList;

import es.iespuertodelacruz.bait.api.movimientos.Compra;
import es.iespuertodelacruz.bait.exceptions.ApiException;
import es.iespuertodelacruz.bait.exceptions.PersistenciaException;
import es.iespuertodelacruz.bait.modelo.movimientosModelo.CompraModelo;

public class CompraController {
    CompraModelo compraModelo;

    public CompraController(){
        compraModelo = new CompraModelo();
    }

    public void validar(Compra compra) throws ApiException{
        String mensaje = "";

        if(compra == null){
            mensaje = "Se esta intentado validar un objeto vacio";
            throw new ApiException(mensaje);
        }
        if(compra.getIdCompra() == null || compra.getIdCompra().isEmpty()){
            mensaje += "El idCompra de la compra esta nulo o vacio";
        }
        if(compra.getPedido() == null){
            mensaje += "El pedido de la compra esta nulo";
        }
        if(compra.getTotalCompra() <= 0){
            mensaje += "El total de la compra es 0 o menor";
        }
        if(!mensaje.isEmpty()){
            throw new ApiException(mensaje);
        }
    }

    /**
     * Metodo encargado de insertar
     * @param compra a insertar
     * @throws ApiException con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void insertar(Compra compra) throws ApiException, PersistenciaException{
        validar(compra);
        if(existe(compra)){
            throw new ApiException("La compra ya existe");          
        }
        compraModelo.insertar(compra);
    }

    /**
     * Metodo encargado de eliminar
     * @param idCompra a eliminar
     * @throws PersistenciaException con mensaje controlado
     */
    public void eliminar(String idCompra) throws PersistenciaException{
        compraModelo.eliminar(idCompra);
    }

    /**
     * Metodo encargado de buscar por idCompra
     * @param idCompra de la compra
     * @return Compra
     * @throws PersistenciaException con mensaje controlado
     */
    public Compra buscar(String idCompra) throws PersistenciaException{
        Compra compra = null;
        compra = compraModelo.buscar(idCompra);
        return compra;
    }

    /**
    * Metodo encargado de realizar la modificacion de una compra
    * @param compra a modficar
    * @throws ApiException con mensaje controlado
    * @throws PersistenciaException con mensaje controlado
    */
    public void modificar(Compra compra) throws ApiException, PersistenciaException {
        Compra compraAlmacenada;
        
        validar(compra);
        compraAlmacenada = buscar(compra.getIdCompra());
        if (compraAlmacenada == null) {
        throw new ApiException("El pedido indicado no existe");
        }
        compraModelo.modificar(compraAlmacenada);
    }

    /**
     * Funcion encargada de verificar si existe la compra
     * @param compra a encontrar
     * @return boolean
     * @throws BbddException
     */
    private boolean existe(Compra compra) throws PersistenciaException{
        boolean encontrada = false;
        Compra compraEncontrada;

        compraEncontrada = buscar(compra.getIdCompra());
        if(compraEncontrada != null){
            encontrada = true;
        }
        
        return encontrada;
    }

    /**
     * Funcion encargada de mostrar todos las compras
     * @return ArrayList de Compras
     * @throws PersistenciaException error a controlar
     */
    public ArrayList<Compra> obtenerListado() throws PersistenciaException{
        ArrayList<Compra> compras;
        compras = compraModelo.obtenerListado();
        return compras;

    }
}
