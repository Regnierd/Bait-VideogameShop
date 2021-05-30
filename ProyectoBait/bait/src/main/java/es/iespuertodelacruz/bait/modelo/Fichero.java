package es.iespuertodelacruz.bait.modelo;

import java.io.*;
import java.util.Scanner;

import es.iespuertodelacruz.bait.exceptions.PersistenciaException;

public class Fichero { 
   private static final String RETORNO_CARRO = "\n";
   
   /**
    * Funcion encargada de leer un fichero
    * 
    * @param nombre nombre del fichero a leer
    * @throws PersistenciaException Error controlado en la lectura del fichero
    */
   public String leer(String nombre) throws PersistenciaException {
      StringBuilder informacion = null;
      File fichero = null;
      Scanner scanner = null;

      try {
         fichero = new File(nombre);
         if (!validarFichero(fichero)) {
            throw new PersistenciaException("El fichero a leer no existe");
         }
         informacion = new StringBuilder();
         scanner = new Scanner(fichero);

         while (scanner.hasNextLine()) {
            String linea = scanner.nextLine();
            informacion.append(linea + RETORNO_CARRO);
         }
      } catch (PersistenciaException e) {  
            throw e;
      }catch (Exception e) {
            throw new PersistenciaException("Se ha producido un error en la lectura del fichero", e);
      } finally {
         if (scanner != null) {
            scanner.close();
         }
      }
      return informacion.toString();
   }

   /**
    * Funcion que verifica si el fichero existo
    * @param fichero
    * @return
    */
   public boolean validarFichero(File fichero) {
      return fichero.exists();
   }

}
