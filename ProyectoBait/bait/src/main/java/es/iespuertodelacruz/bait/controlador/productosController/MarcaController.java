package es.iespuertodelacruz.bait.controlador.productosController;

import java.util.ArrayList;

import es.iespuertodelacruz.bait.api.productos.Marca;
import es.iespuertodelacruz.bait.exceptions.ApiException;
import es.iespuertodelacruz.bait.exceptions.PersistenciaException;
import es.iespuertodelacruz.bait.modelo.productosModelo.MarcaModelo;

public class MarcaController {
    MarcaModelo marcaModelo;

    /**
     * Constructor basio de la clase
     * @throws PersistenciaException
     */
    public MarcaController() throws PersistenciaException {
        marcaModelo = new MarcaModelo();
    }

    /**
     * Metodo que valida un objeto marca
     * @param marca que se va a validar
     * @throws CategoriaException error a controlar
     */
    public void validar(Marca marca) throws ApiException{
        String mensaje = "";
        if(marca == null){
            mensaje = "La marca no puede ser nulo";
            throw new ApiException(mensaje);
        }
        if(marca.getIdMarca() == null || marca.getIdMarca().isEmpty()){
            mensaje += "El idMarca no puede ser nulo o vacio";
        }
        if(marca.getNombre()== null || marca.getNombre().isEmpty()){
            mensaje += "El nombre no pueden ser nulo o vacio";
        }

        if(!mensaje.isEmpty()){
            throw new ApiException(mensaje);
        }
    }

    /**
     * Metodo que inserta una marca
     * @param marca que se va a insertar
     * @throws ApiException error a controlar
     * @throws PersistenciaException error a controlar
     */
    public void insertar(Marca marca) throws ApiException, PersistenciaException{
        validar(marca);
        if (existe(marca.getIdMarca())) {
           throw new ApiException("La marca indicada ya existe.");
        }
        marcaModelo.insertar(marca);
    }

    /**
     * Metodo que eliminar una marca
     * @param idMarca de la categoria que se va aborrar
     * @throws ApiException error a controlar
     * @throws PersistenciaException error a controlar
     */
    public void eliminar(String idMarca) throws PersistenciaException, ApiException {
        if(!existe(idMarca)){
            throw new ApiException("La marca que quiere eliminar no existe");
        }
        marcaModelo.eliminar(idMarca);
    }

    /**
     * Funcion que comprueba si una marca existe
     * @param marca que se va comprar 
     * @return verdadero/falso
     * @throws PersistenciaException
     */
    private boolean existe(String idMarca) throws PersistenciaException {
        boolean encontrada = false;
        Marca marcaEncontrada;
   
        marcaEncontrada = buscar(idMarca);
        if (marcaEncontrada != null) {
           encontrada = true;
        }  

        return encontrada;
    }

    /**
     * Funcion que busca una marca y la devuelve
     * @param idMarca de la marca que se va a buscar
     * @return la marca encontrada
     * @throws PersistenciaException error a controlar
     */
    public Marca buscar(String idMarca) throws PersistenciaException {
        Marca marca = null;
        marca = marcaModelo.buscarPorId(idMarca);
        
        return marca;
    }

    /**
     * Metodo que modfica una marca 
     * @param marca con las cambios 
     * @throws ApiException error a controlar
     * @throws PersistenciaException error a controlar
     */
    public void modificar(Marca marca) throws ApiException, PersistenciaException {
        validar(marca);
        if (!existe(marca.getIdMarca())) {
            throw new ApiException("La categoria que quiere modifcar no existe.");
        }
        marcaModelo.modificar(marca);
    }

    /**
     * Funcion que obtiene la lista de marcas y la devuelve
     * @return la lista de marcas
     * @throws PersistenciaException error a controlar
     * @throws ApiException error a controlar
     */
    public ArrayList<Marca> obtenerListado() throws PersistenciaException, ApiException {
        ArrayList<Marca> marcas;
        marcas = marcaModelo.obtenerListado();
        if ( marcas == null || marcas.isEmpty()) {
            throw new ApiException("La lista de marcas es vacia o nula");
        }
        return marcas;
        
    }
}
