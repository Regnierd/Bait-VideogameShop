package es.iespuertodelacruz.bait.controlador.productosController;

import es.iespuertodelacruz.bait.api.productos.Marca;
import es.iespuertodelacruz.bait.exceptions.ApiException;
import es.iespuertodelacruz.bait.exceptions.PersistenciaException;
import es.iespuertodelacruz.bait.modelo.productosModelo.MarcaModelo;

public class MarcaController {
    MarcaModelo marcaModelo;

    /**
     * Constructor basio de la clase
     */
    public MarcaController() {
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
            mensaje = "El marca no puede ser nulo";
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
        if (existe(marca)) {
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
    public void eliminar(String idMarca) throws PersistenciaException {
        marcaModelo.eliminar(idMarca);
    }

    /**
     * Funcion que comprueba si una marca existe
     * @param marca que se va comprar 
     * @return verdadero/falso
     * @throws PersistenciaException
     */
    private boolean existe(Marca marca) throws PersistenciaException {
        boolean encontrada = false;
        Marca marcaEncontrada;
   
        marcaEncontrada = buscar(marca.getIdMarca());
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
        marca = marcaModelo.buscar(idMarca);
        
        return marca;
    }

    /**
     * Metodo que modfica una marca 
     * @param marca con las cambios 
     * @throws ApiException error a controlar
     * @throws PersistenciaException error a controlar
     */
    public void modficar(Marca marca) throws ApiException, PersistenciaException {
        validar(marca);
        if (!existe(marca)) {
            throw new ApiException("La categoria que quiere modifcar no exite.");
        }
        marcaModelo.modificar(marca);
    }
}
