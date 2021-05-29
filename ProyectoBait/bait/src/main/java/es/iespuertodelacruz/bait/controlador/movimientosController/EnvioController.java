package es.iespuertodelacruz.bait.controlador.movimientosController;

import es.iespuertodelacruz.bait.api.movimientos.Envio;
import es.iespuertodelacruz.bait.exceptions.ApiException;
import es.iespuertodelacruz.bait.exceptions.PersistenciaException;
import es.iespuertodelacruz.bait.modelo.movimientosModelo.EnvioModelo;

public class EnvioController {
    EnvioModelo envioModelo;

    public EnvioController() throws PersistenciaException{
        envioModelo = new EnvioModelo();
    }

    public void validar(Envio envio) throws ApiException{
        String mensaje = "";

        if(envio == null){
            mensaje = "Se esta intentando validar un objeto vacio";
            throw new ApiException(mensaje);
        }
        if(envio.getIdEnvio() == null || envio.getIdEnvio().isEmpty()){
            mensaje += "El idEnvio del envio es nulo o vacio";
        }
        if(envio.getPedido() == null){
            mensaje += "Se esta intentando validar un objeto vacio";
        }
        if(envio.getFechaEnvio() == null || envio.getFechaEnvio().isEmpty()){
            mensaje += "La fecha del envio es nulo o vacio";
        }
        if(envio.getEstado() == null || envio.getEstado().isEmpty()){
            mensaje += "El estado del envio es nulo o vacio";
        }
        if(!mensaje.isEmpty()){
            throw new ApiException(mensaje);
        }
    }

    /**
     * Metodo encargado de insertar
     * @param envio a insertar
     * @throws ApiException con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void insertar(Envio envio) throws ApiException, PersistenciaException{
        validar(envio);
        if(existe(envio)){
            throw new ApiException("El pedido ya existe");          
        }
        envioModelo.insertar(envio);
    }

    /**
     * Metodo encargado de eliminar
     * @param idEnvio a eliminar
     * @throws PersistenciaException con mensaje controlado
     */
    public void eliminar(String idEnvio) throws PersistenciaException{
        envioModelo.eliminar(idEnvio);
    }

     /**
     * Metodo encargado de buscar por idEnvio
     * @param idEnvio del pedido
     * @return Envio
     * @throws PersistenciaException con mensaje controlado
     */
    public Envio buscar(String idEnvio) throws PersistenciaException{
        Envio envio = null;
        envio = envioModelo.buscaPorIdentificador(idEnvio);
        return envio;
    }

     /**
    * Metodo encargado de realizar la modificacion de un envio
    * @param envio a modficar
    * @throws ApiException con mensaje controlado
    * @throws PersistenciaException con mensaje controlado
    */
    public void modificar(Envio envio) throws ApiException, PersistenciaException {
        Envio envioAlmacenado;
        
        validar(envio);
        envioAlmacenado = buscar(envio.getIdEnvio());
        if (envioAlmacenado == null) {
        throw new ApiException("El pedido indicado no existe");
        }
        envioModelo.modificar(envioAlmacenado);
    }

    /**
     * Funcion encargada de verificar si existe el envio
     * @param envio a encontrar
     * @return boolean
     * @throws PersistenciaException
     */
    private boolean existe(Envio envio) throws PersistenciaException{
        boolean encontrada = false;
        Envio envioEncontrado;

        envioEncontrado = buscar(envio.getIdEnvio());
        if(envioEncontrado != null){
            encontrada = true;
        }
        
        return encontrada;
    }
}
