package es.iespuertodelacruz.bait.controlador.movimientosController;

import es.iespuertodelacruz.bait.api.movimientos.Pedido;
import es.iespuertodelacruz.bait.modelo.movimientosModelo.PedidoModelo;

public class PedidoController {

    PedidoModelo pedidoModelo;

    public PedidoController(){
        pedidoModelo = new PedidoModelo();
    }

    /**
     * Metodo encargado de validar un pedido
     * @param pedido a validar
     */
    public void validar(Pedido pedido) ApiException{
        String mensaje = "";

        if(pedido == null){
            mensaje = "Se esta intentando validar un objeto vacio";
            throw new ApiException(mensaje);
        }

        if(pedido.getIdPedido() == null || pedido.getIdPedido().isEmpty()){
            mensaje = "El idPedido del pedido es nulo o vacio \n";
        }

        if(pedido.getUnidades() <= 0){
            mensaje += "Las unidades del pedido es 0 o menor";
        }
        
        if(pedido.getTotal() <= 0){
            mensaje += "El total del pedido es 0 o menor";
        }

        if(pedido.getFechaPedido() == null || pedido.getFechaPedido().isEmpty()){
            mensaje += "La fecha del pedido es nulo o vacio";
        }

        if(pedido.getCliente() == null){
            mensaje += "Se esta intentando validar un usuario vacio";
        }

        if(pedido.getProducto() == null){
            mensaje += "Se esta intentando validar un producto vacio";
        }

        if(!mensaje.isEmpty()){
            throw new ApiException(mensaje);
        }
    }

    /**
     * Metodo encargado de insertar
     * @param pedido a insertar
     * @throws ApiException con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void insertar(Pedido pedido) throws ApiException, PersistenciaException{
        validar(pedido);
        if(existe(pedido)){
            throw new ApiException("El pedido ya existe");          
        }
        pedidoModelo.insertar(pedido);
    }

    /**
     * Metodo encargado de eliminar
     * @param pedido a eliminar
     * @throws PersistenciaException con mensaje controlado
     */
    public void eliminar(String idPedido) throws PersistenciaException{
        pedidoModelo.eliminar(idPedido);
    }

    /**
     * Metodo encargado de buscar por identificador
     * @param identificador para localizar la fruta
     * @return fruta a traves del identificador
     * @throws PersistenciaException con mensaje controlado
     */
    public Pedido buscar(String idPedido) throws PersistenciaException{
        Pedido pedido = null;
        pedido = pedidoModelo.buscar(idPedido);
        return pedido;
    }

     /**
    * Metodo encargado de realizar la modificacion de un pedido
    * @param pedido a modficar
    * @throws ApiException con mensaje controlado
    * @throws PersistenciaException con mensaje controlado
    */
    public void modificar(Pedido pedido) throws ApiException, PersistenciaException {
        Pedido pedidoAlmacenado;
        
        validar(pedido);
        pedidoAlmacenado = buscar(pedido.getIdPedido());
        if (pedidoAlmacenado == null) {
        throw new ApiException("El pedido indicado no existe");
        }
        pedidoModelo.modificar(pedidoAlmacenado);
    }

    /**
     * Funcion encargada de verificar si existe el pedido
     * @param pedido a encontrar
     * @return boolean
     * @throws BbddException
     */
    private boolean existe(Pedido pedido) throws PersistenciaException{
        boolean encontrada = false;
        Pedido pedidoEncontrado;

        pedidoEncontrado = buscar(pedido.getIdPedido());
        if(pedidoEncontrado != null){
            encontrada = true;
        }
        
        return encontrada;
    }


































    public void realizarPedido(String idProducto, int unidades) {
        //codigo
    }
    
}
