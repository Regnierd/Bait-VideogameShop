package es.iespuertodelacruz.bait.controlador.movimientosController;

import java.time.LocalDate;
import java.util.ArrayList;

import es.iespuertodelacruz.bait.api.movimientos.Envio;
import es.iespuertodelacruz.bait.api.movimientos.Pedido;
import es.iespuertodelacruz.bait.api.personas.Usuario;
import es.iespuertodelacruz.bait.api.productos.Producto;
import es.iespuertodelacruz.bait.controlador.productosController.ProductoController;
import es.iespuertodelacruz.bait.exceptions.ApiException;
import es.iespuertodelacruz.bait.exceptions.PersistenciaException;
import es.iespuertodelacruz.bait.modelo.movimientosModelo.PedidoModelo;

public class PedidoController {

    PedidoModelo pedidoModelo;
    ProductoController productoController;
    EnvioController envioController;

    public PedidoController() throws PersistenciaException{
        pedidoModelo = new PedidoModelo();
        productoController = new ProductoController();
        envioController = new EnvioController();
    }

    /**
     * Metodo encargado de validar un pedido
     * @param pedido a validar
     */
    public void validar(Pedido pedido) throws ApiException{
        String mensaje = "";

        if(pedido == null){
            mensaje = "El pedido que se intenta validar es nulo";
            throw new ApiException(mensaje);
        }

        if(pedido.getIdPedido() == null || pedido.getIdPedido().isEmpty()){
            mensaje = "El idPedido del pedido es nulo o vacio";
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

        if(pedido.getUsuario() == null){
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
        if(existe(pedido.getIdPedido())){
            throw new ApiException("El pedido ya existe");          
        }
        pedidoModelo.insertar(pedido);
    }

    /**
     * Metodo encargado de eliminar
     * @param idPedido a eliminar
     * @throws PersistenciaException con mensaje controlado
     */
    public void eliminar(String idPedido) throws PersistenciaException, ApiException{
        if (!existe(idPedido)) {
            throw new ApiException("El pedido que quiere eliminar no existe");
        }
        pedidoModelo.eliminar(idPedido);
    }

    /**
     * Metodo encargado de buscar por idPedido
     * @param idPedido del pedido
     * @return Pedido que se encuentra
     * @throws PersistenciaException error controlado
     * @throws ApiException error controlado
     */
    public Pedido buscar(String idPedido) throws PersistenciaException, ApiException{
        Pedido pedido = null;
        pedido = pedidoModelo.buscaPorIdentificador(idPedido);

        if (pedido == null) {
            throw new ApiException("El pedido que quiere buscar no existe.");
        }

        return pedido;
    }

     /**
    * Metodo encargado de realizar la modificacion de un pedido
    * @param pedido a modficar
    * @throws ApiException con mensaje controlado
    * @throws PersistenciaException con mensaje controlado
    */
    public void modificar(Pedido pedido) throws ApiException, PersistenciaException {        
        validar(pedido);
        if (!existe(pedido.getIdPedido())) {
        throw new ApiException("El pedido que quiere modficar no existe");
        }
        pedidoModelo.modificar(pedido);
    }

    /**
     * Funcion encargada de verificar si existe el pedido
     * @param pedido a encontrar
     * @return vedadero/falso
     * @throws ApiException
     * @throws BbddException error a controlar
     */
    private boolean existe(String idPedido) throws PersistenciaException, ApiException{
        boolean encontrada = false;
        Pedido pedidoEncontrado;

        pedidoEncontrado = pedidoModelo.buscaPorIdentificador(idPedido);
        if(pedidoEncontrado != null){
            encontrada = true;
        }
        
        return encontrada;
    }

    /**
     * Funcion que obtiene una lista de todos los pedidos
     * @return la lista de pedidos
     * @throws PersistenciaException error controlar
     * @throws ApiException error a controlar
     */
    public ArrayList<Pedido> obtenerListado() throws PersistenciaException, ApiException{
        ArrayList<Pedido> pedidos = null;
        pedidos = pedidoModelo.obtenerListado();
        if (pedidos == null || pedidos.isEmpty()) {
            throw new ApiException("La lista de pedidos es vacia o nula");
        }
        return pedidos;

    }

    /**
     * Metodo que realiza un pedido y luego envio con los datos dados por el usuario
     * @param usuario que realiza el pedido
     * @param idProducto del producto que va a comprar
     * @param unidades del producto a comprar
     * @throws PersistenciaException error controlado
     * @throws ApiException error controlado
     */
    public void realizarPedido(Usuario usuario, String idProducto, int unidades) throws PersistenciaException, ApiException {
        Pedido pedido;
        Producto producto;
        Envio envio;
        String idPedido; 
        String fechaPedido; 
        String idEnvio;
        
        idPedido = idProducto +"-"+ usuario.getDni();
        producto = productoController.buscar(idProducto);
        fechaPedido = LocalDate.now().toString();
        float total = producto.getPrecio() * unidades;

        pedido = new Pedido(idPedido, unidades, total, fechaPedido, usuario, producto);

        productoController.reducirStock(idProducto, unidades);

        idEnvio = "env_"+idPedido;
        envio = new Envio(idEnvio, pedido, fechaPedido, "Enviado");
        
        insertar(pedido);
        envioController.insertar(envio);
    }
    
}
