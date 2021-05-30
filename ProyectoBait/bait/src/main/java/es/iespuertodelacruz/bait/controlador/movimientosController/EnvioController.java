package es.iespuertodelacruz.bait.controlador.movimientosController;

import java.util.ArrayList;

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
            mensaje = "El envio que intenta validar es nulo";
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
        if(existe(envio.getIdEnvio())){
            throw new ApiException("El pedido ya existe");          
        }
        envioModelo.insertar(envio);
    }

    /**
     * Metodo encargado de eliminar
     * @param idEnvio a eliminar
     * @throws PersistenciaException con mensaje controlado
     * @throws ApiException
     */
    public void eliminar(String idEnvio) throws PersistenciaException, ApiException{
        if(!existe(idEnvio)){
            throw new ApiException("El envio que quiere eliminar no existe");
        }
        envioModelo.eliminar(idEnvio);
    }

     /**
     * Metodo encargado de buscar por idEnvio
     * @param idEnvio del pedido
     * @return Envio
     * @throws PersistenciaException con mensaje controlado
     * @throws ApiException
     */
    public Envio buscar(String idEnvio) throws PersistenciaException, ApiException{
        Envio envio = null;

        envio = envioModelo.buscaPorIdentificador(idEnvio);
        
        if (envio == null) {
            throw new ApiException("El envio que quiere buscar no existe.");
        }

        return envio;
    }

     /**
    * Metodo encargado de realizar la modificacion de un envio
    * @param envio a modficar
    * @throws ApiException con mensaje controlado
    * @throws PersistenciaException con mensaje controlado
    */
    public void modificar(Envio envio) throws ApiException, PersistenciaException {
        validar(envio);
        if (!existe(envio.getIdEnvio())) {
            throw new ApiException("El envio que intenta modificar no existe");
        }
        envioModelo.modificar(envio);
    }

    /**
     * Funcion encargada de verificar si existe el envio
     * @param envio a encontrar
     * @return boolean
     * @throws PersistenciaException
     */
    private boolean existe(String idEnvio) throws PersistenciaException{
        boolean encontrada = false;
        Envio envioEncontrado = null;

        envioEncontrado = envioModelo.buscaPorIdentificador(idEnvio);
        if(envioEncontrado != null){
            encontrada = true;
        }
        
        return encontrada;
    }

    /**
     * Funcion que obtiene la lista de envios y la devuelve
     * @return la lista de envios
     * @throws PersistenciaException error controlado
     */
    public ArrayList<Envio> obtenerListado() throws PersistenciaException, ApiException{
        ArrayList<Envio> envios = null;
        envios = envioModelo.obtenerListado();
        if(envios == null || envios.isEmpty()){
            throw new ApiException("La lista de envios es vacia o nula");
        }
        return envios;
        
    }

    /**
     * Funcion que obtiene la lista de envios y la devuelve
     * @return la lista de envios
     * @throws PersistenciaException error controlado
     */
    public ArrayList<Envio> obtenerListado(String dni) throws PersistenciaException, ApiException{
        ArrayList<Envio> envios = null;
        envios = envioModelo.obtenerListado(dni);
        if(envios == null || envios.isEmpty()){
            throw new ApiException("La lista de envios es vacia o nula");
        }
        return envios;
        
    }
}
