 package es.iespuertodelacruz.bait.controlador.personasController;

import java.util.ArrayList;

import es.iespuertodelacruz.bait.api.Validar;
import es.iespuertodelacruz.bait.api.personas.Usuario;
import es.iespuertodelacruz.bait.exceptions.PersistenciaException;
import es.iespuertodelacruz.bait.exceptions.ApiException;
import es.iespuertodelacruz.bait.modelo.personasModelo.UsuarioModelo;

public class UsuarioController extends Validar{
    UsuarioModelo usuarioModelo;

    public UsuarioController() throws PersistenciaException {
        usuarioModelo = new UsuarioModelo();
    }

    /**
     * Metodo encargado de validar todos los atributos de un usuario
     * @param usuario a validar
     * @throws ApiException mensaje controlado
     */
    public void validar(Usuario usuario) throws ApiException{
        String mensaje = "";
        if(usuario == null){
            mensaje = "El usuario no puede ser nulo";
            throw new ApiException(mensaje);
        }
        if(usuario.getDni() == null || usuario.getDni().isEmpty()){
            mensaje += "El DNI no puede ser nulo o vacio o invalido, ";
        }
        if(usuario.getNombre() == null || usuario.getNombre().isEmpty()){
            mensaje += "El nombre no pueden ser nulo o vacio, ";
        }
        if(usuario.getApellidos() == null || usuario.getApellidos().isEmpty()){
            mensaje += "Los apellidos no pueden ser nulo o vacio, ";
        }
        if(usuario.getEmail() == null || usuario.getEmail().isEmpty()){
            mensaje += "El email no puede ser nulo o vacio o invalido, ";
        }
        if(usuario.getDireccion() == null || usuario.getDireccion().isEmpty()){
            mensaje += "La direccion no puede ser nula o vacia, ";
        }   
        if(usuario.getTelefono() == null || usuario.getTelefono().isEmpty()){
            mensaje += "El telefono no puede ser nulo o vacio, ";
        }
        if(usuario.getCodigoPostal() == null || usuario.getCodigoPostal().isEmpty()){
            mensaje += "El codigo postal no puede ser nulo o vacio, ";
        }
        if(usuario.getProvincia() == null || usuario.getProvincia().isEmpty()){
            mensaje += "La provincia no puede ser nulo o vacio, ";
        }
        if(usuario.getNombreUsuario() == null || usuario.getNombreUsuario().isEmpty()){
            mensaje += "El nombre de usuario no puede ser nulo o vacio, ";
        }
        if(usuario.getPassword() == null || usuario.getPassword().isEmpty()){
            mensaje += "La contraseña no puede ser nulo o vacio";
        }
        if(!mensaje.isEmpty()){
            throw new ApiException(mensaje);
        }
    }

    /**
     * Funcion que busca un usuario por su dni y lo retorna
     * @param dni del usuario que se va a buscar
     * @return el usuario encontrado
     * @throws PersistenciaException error a controlar
     * @throws ApiException error a controlar
     */
    public Usuario buscar(String dni) throws PersistenciaException, ApiException {
        Usuario usuario = null;
        if (!validarDni(dni)) {
            throw new ApiException("Formato del dni incorrecto");
        }

        usuario = usuarioModelo.buscaPorDni(dni);

        if (usuario == null) {
            throw new ApiException("El usuario que quiere buscar no existe.");
        }

        return usuario;
    }

    /**
     * Funcion que comprueba los datos introducidos por el usuario para realizar ç
     * el loin
     * @param nombreUsuario del usuario
     * @param password del usuario
     * @param rol del usuario
     * @return el usuario si es valido
     * @throws PersistenciaException
     * @throws ApiException
     */
    public Usuario login(String nombreUsuario, String password, String rol) throws PersistenciaException, ApiException{
        Usuario usuario = null;
        usuario = usuarioModelo.buscaPorNombreUsuario(nombreUsuario);
        if (usuario == null) {
            throw new ApiException("El usuario que se a introducido no existe");
        }
        if (!usuario.getPassword().equals(password)  || !usuario.getRol().equals(rol)) {
            throw new ApiException("Las credenciales introducidas son incorrectas");
        }

        return usuario;
    }

    /**
     * Metodo que inserta un usuario 
     * @param usuario que se va a insertar
     * @throws ApiException error a controlar
     * @throws PersistenciaException error a controlar
     */
    public void insertar(Usuario usuario) throws ApiException, PersistenciaException{
        validar(usuario);
        if (existe(usuario.getDni())) {
           throw new ApiException("El usuario indicado ya existe.");
        }
        usuarioModelo.insertar(usuario); 
    }

    /**
     * Metodo que elimina un usuario
     * @param dni del usuario qu ese va a borrar
     * @throws PersistenciaException
     * @throws ApiException
     */
    public void eliminar(String dni) throws PersistenciaException, ApiException {
        if (!validarDni(dni)) {
            throw new ApiException("Formato del dni incorrecto");
        }
        if (!existe(dni)) {
            throw new ApiException("El usuario que quiere eliminar no existe");
        }
        usuarioModelo.eliminar(dni);
    }

    /**
     * Comrpueba si el usuario exite
     * @param usuario que se va a buscar
     * @return verdadero/falso
     * @throws PersistenciaException
     * @throws ApiException
     */
    private boolean existe(String dni) throws PersistenciaException, ApiException {
        boolean encontrada = false;
        Usuario usuarioEncontrado;
     
        usuarioEncontrado = usuarioModelo.buscaPorDni(dni);
        if (usuarioEncontrado != null) {
           encontrada = true;
        }  

        return encontrada;
    }

    /**
     * Funcion que añade saldo a un usuario
     * @param usuario que se le va a añadir saldo
     * @param saldo que se va a sumar al actula del usuario
     * @throws PersistenciaException error a controlar
     * @throws ApiException error a controlar
     */
    public void añadirSaldo(Usuario usuario,float saldo) throws PersistenciaException, ApiException{
        validar(usuario);
        float saldoActual;
        saldoActual = usuario.getSaldo();

        if (saldo <= 0) {
            throw new ApiException("El saldo a añadir no puede menor o igual que 0.");
        }

        usuario.setSaldo(saldoActual + saldo);
        modificar(usuario);
    }

    /**
     * Metodo que modfica un usuario en la base datos 
     * @param usuario con los cambios que se van a añadir
     * @throws ApiException error a controlar
     * @throws PersistenciaException error a controlar
     */
    public void modificar(Usuario usuario) throws ApiException, PersistenciaException{
        validar(usuario);
        if (!existe(usuario.getDni())) {
            throw new ApiException("El usuario que quiere modificar no existe.");
        }
        usuarioModelo.modificar(usuario);
    }

    /**
     * Funcion que obtiene una lista de todos los usuarios
     * @return lista de usuario
     * @throws PersistenciaException error controlar
     * @throws ApiException error a controlar
     */
    public ArrayList<Usuario> obtenerListado() throws PersistenciaException, ApiException {
        ArrayList<Usuario> usuarios = null;
        usuarios = usuarioModelo.obtenerListado();
        if ( usuarios == null || usuarios.isEmpty()) {
            throw new ApiException("La lista de usuario es vacia o nula");
        }
        return usuarios;
    }

    /**
     * Metodo que reduce el saldo de un usuario
     * @param usuario que se va a reducir el saldo
     * @param total que se va a reducir del saldo
     * @throws ApiException error controlado
     * @throws PersistenciaException error controlado
     */
    public void reducirSaldo(Usuario usuario, float total) throws ApiException, PersistenciaException {
        float nuevoSaldo = usuario.getSaldo() - total;
        if (nuevoSaldo < 0) {
            throw new ApiException("No tiene saldo suficiente");
        }
        usuario.setSaldo(nuevoSaldo);
        modificar(usuario);
    }
   
}
