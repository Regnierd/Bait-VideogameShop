package es.iespuertodelacruz.bait.controlador.productosController;

import java.util.ArrayList;

import es.iespuertodelacruz.bait.api.productos.Categoria;
import es.iespuertodelacruz.bait.exceptions.ApiException;
import es.iespuertodelacruz.bait.exceptions.PersistenciaException;
import es.iespuertodelacruz.bait.modelo.productosModelo.CategoriaModelo;

public class CategoriaController {
    CategoriaModelo categoriaModelo;

    /**
     * Constructor basio de la clase
     * @throws PersistenciaException
     */
    public CategoriaController() throws PersistenciaException {
        categoriaModelo = new CategoriaModelo();
    }

    /**
     * Metodo que valida un objeto Categoria
     * @param categoria que se va a validar
     * @throws CategoriaException error a controlar
     */
    public void validar(Categoria categoria) throws ApiException{
        String mensaje = "";
        if(categoria == null){
            mensaje = "La categoria no puede ser nulo";
            throw new ApiException(mensaje);
        }
        if(categoria.getIdCategoria() == null || categoria.getIdCategoria().isEmpty()){
            mensaje += "El IdCategoria no puede ser nulo o vacio";
        }
        if(categoria.getNombre()== null || categoria.getNombre().isEmpty()){
            mensaje += "El nombre no pueden ser nulo o vacio";
        }

        if(!mensaje.isEmpty()){
            throw new ApiException(mensaje);
        }
    }

    /**
     * Metodo que inserta una categoria
     * @param categoria que se va a insertar
     * @throws CategoriaException error a controlar
     * @throws PersistenciaException error a controlar
     */
    public void insertar(Categoria categoria) throws ApiException, PersistenciaException{
        validar(categoria);
        if (existe(categoria)) {
           throw new ApiException("La categoria indicada ya existe.");
        }
        categoriaModelo.inserta(categoria);
    }

    /**
     * Metodo que eliminar una categoria
     * @param idCategoria de la categoria que se va aborrar
     * @throws PersistenciaException error a controlar
     */
    public void eliminar(String idCategoria) throws PersistenciaException {
        categoriaModelo.eliminar(idCategoria);
    }

    /**
     * Funcion que comprueba si una categoria existe
     * @param categoria que se va comprar 
     * @return verdadero/falso
     * @throws PersistenciaException
     */
    private boolean existe(Categoria categoria) throws PersistenciaException {
        boolean encontrada = false;
        Categoria categoriaEncontrada = null;
   
        categoriaEncontrada = buscar(categoria.getIdCategoria());
        if (categoriaEncontrada != null) {
           encontrada = true;
        }  

        return encontrada;
    }

    /**
     * Funcion que busca una categoria y la devuelve
     * @param idCategoria de la categoria que se va a buscar
     * @return la categoria encontrada
     * @throws PersistenciaException error a controlar
     */
    public Categoria buscar(String idCategoria) throws PersistenciaException {
        Categoria categoria = null;
        categoria = categoriaModelo.buscar(idCategoria);
        
        return categoria;
    }

    /**
     * Metodo que modfica una categoria 
     * @param categoria con las cambios 
     * @throws CategoriaException error a controlar
     * @throws PersistenciaException error a controlar
     */
    public void modficar(Categoria categoria) throws ApiException, PersistenciaException {
        validar(categoria);
        if (!existe(categoria)) {
            throw new ApiException("La categoria que quiere modifcar no exite.");
        }
        categoriaModelo.modificar(categoria);
    }

        /**
     * Funcion que obtiene la lista de categorias y la devuelve
     * @return la lista de categorias
     * @throws PersistenciaException error a controlar
     */
    public ArrayList<Categoria> obtenerListado() throws PersistenciaException {
        ArrayList<Categoria> categorias;
        categorias = categoriaModelo.obtenerListado();
        
        return categorias;
        
    }
}
