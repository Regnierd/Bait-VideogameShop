package es.iespuertodelacruz.bait.modelo.productosModelo;

import java.util.ArrayList;

import es.iespuertodelacruz.bait.api.productos.Marca;
import es.iespuertodelacruz.bait.exceptions.PersistenciaException;
import es.iespuertodelacruz.bait.modelo.mysql.SQL.SQLMarca;

public class MarcaModelo {
    SQLMarca persistencia;

    /**
     * Constructor basico de la clase
     */
    public MarcaModelo() {
        persistencia = new SQLMarca("org.sqlite.JDBC", "jdbc:sqlite:bait.db", null, null);
    }

    /**
     * Metodo que inserta un marca en la base de datos
     * @param marca que se va a insertar en la base de datos
     * @throws PersistenciaException error a controlar
     */
    public void insertar(Marca marca) throws PersistenciaException {
        persistencia.insertar(marca);
    }

    /**
     * Metodo que elimina una marca en la base datos
     * @param idMarca de la marca que se va a borrar
     * @throws PersistenciaException error a controlar
     */
    public void eliminar(String idMarca) throws PersistenciaException {
        persistencia.eliminar(idMarca);
    }

    /**
     * Funcion que buscca una marca por su idMarca en la base datos
     * @param idMarca de la marca que se va a buscar
     * @return la marca encontrada
     * @throws PersistenciaException error a controlar
     */
    public Marca buscar(String idMarca) throws PersistenciaException {
        Marca marca;
        marca = persistencia.buscar(idMarca);

        return marca;
    }
    
    /**
     * Metodo que modifica una marca en la base datos
     * @param marca con los nuevos cambios
     * @throws PersistenciaException error a controlar
     */
    public void modificar(Marca marca) throws PersistenciaException {
        persistencia.modificar(marca);
    }

    /**
     * Funcion que devuelve un lista de las marcas 
     * @return la lista de las marcas
     * @throws PersistenciaException error a controlar
     */
    public ArrayList<Marca> obtenerListado() throws PersistenciaException {
        ArrayList<Marca> marcas;
        marcas = persistencia.obtenerListado();

        return marcas;
    }
}
