package es.iespuertodelacruz.bait.modelo.productosModelo;

import java.util.ArrayList;

import es.iespuertodelacruz.bait.api.productos.Categoria;
import es.iespuertodelacruz.bait.exceptions.PersistenciaException;
import es.iespuertodelacruz.bait.modelo.mysql.SQL.SQLCategoria;

public class CategoriaModelo {
    SQLCategoria persistencia;

    /**
     * Constructor basico de la clase
     */
    public CategoriaModelo() {
        persistencia = new SQLCategoria("org.sqlite.JDBC", "jdbc:sqlite:bait.db", null, null);
    }

    /**
     * Metodo que inserta una categoria en la base de datos
     * @param categoria que se va a insertar en la base de datos
     * @throws PersistenciaException error a controlar
     */
    public void insertar(Categoria categoria) throws PersistenciaException {
        persistencia.inserta(categoria);
    }

    /**
     * Metodo que elimina una categoria en la base datos
     * @param idCategoria de la categoria que se va a borrar
     * @throws PersistenciaException error a controlar
     */
    public void eliminar(String idCategoria) throws PersistenciaException {
        persistencia.eliminar(idCategoria);
    }

    /**
     * Funcion que buscca una categoria por su idCategoria en la base datos
     * @param idCategoria de la categoria que se va a buscar
     * @return la categoria encontrada
     * @throws PersistenciaException error a controlar
     */
    public Categoria buscar(String idCategoria) throws PersistenciaException {
        Categoria categoria;
        categoria = persistencia.buscar(idCategoria);

        return categoria;
    }
    
    /**
     * Metodo que modifica una categoria en la base datos
     * @param categoria con los nuevos cambios
     * @throws PersistenciaException error a controlar
     */
    public void modificar(Categoria categoria) throws PersistenciaException {
        persistencia.modificar(categoria);
    }

    /**
     * Funcion que devuelve un lista de las categorias 
     * @return la lista de las categorias
     * @throws PersistenciaException error a controlar
     */
    public ArrayList<Categoria> obtenerListado() throws PersistenciaException {
        ArrayList<Categoria> categorias;
        categorias = persistencia.obtenerListado();

        return categorias;
    }
}
