package es.iespuertodelacruz.bait.controlador.personasController;

import es.iespuertodelacruz.bait.api.personas.Usuario;
import es.iespuertodelacruz.bait.exceptions.PersistenciaException;
import es.iespuertodelacruz.bait.exceptions.ApiException;
import es.iespuertodelacruz.bait.modelo.personasModelo.UsuarioModelo;

public class UsuarioController {
    UsuarioModelo usuarioModelo;

    public UsuarioController() {
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
            mensaje += "El DNI no puede ser nulo o vacio";
        }
        if(usuario.getNombre() == null || usuario.getNombre().isEmpty()){
            mensaje += "El nombre no pueden ser nulo o vacio";
        }
        if(usuario.getApellidos() == null || usuario.getApellidos().isEmpty()){
            mensaje += "Los apellidos no pueden ser nulo o vacio";
        }
        if(usuario.getEmail() == null || usuario.getEmail().isEmpty()){
            mensaje += "El email no puede ser nulo o vacio";
        }
        if(usuario.getDireccion() == null || usuario.getDireccion().isEmpty()){
            mensaje += "La direccion no puede ser nula o vacia";
        }   
        if(usuario.getTelefono() == null || usuario.getTelefono().isEmpty()){
            mensaje += "El telefono no puede ser nulo o vacio";
        }
        if(usuario.getCodigoPostal() == null || usuario.getCodigoPostal().isEmpty()){
            mensaje += "El codigo postal no puede ser nulo o vacio";
        }
        if(usuario.getProvincia() == null || usuario.getProvincia().isEmpty()){
            mensaje += "La provincia no puede ser nulo o vacio";
        }
        if(usuario.getNombreUsuario() == null || usuario.getNombreUsuario().isEmpty()){
            mensaje += "El nombre de usuario no puede ser nulo o vacio";
        }
        if(usuario.getPassword() == null || usuario.getPassword().isEmpty()){
            mensaje += "La contraseña no puede ser nulo o vacio";
        }
        if(!mensaje.isEmpty()){
            throw new ApiException(mensaje);
        }
    }

    /**
     * Funcion que busca un usuario por su dni y lo retorn
     * @param dni del usuario que se va a buscar
     * @return el usuario encontrado
     * @throws PersistenciaException error a controlar
     */
    public Usuario buscar(String dni) throws PersistenciaException {
        Usuario usuario = null;
        usuario = usuarioModelo.buscar(dni);
        
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
        usuario = usuarioModelo.login(nombreUsuario);

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
        if (existe(usuario)) {
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
        if (!existe(buscar(dni))) {
            throw new ApiException("El usuario que quiere borrar no existe");
        }
        usuarioModelo.eliminar(dni);
    }

    /**
     * Comrpueba si el usuario exite
     * @param usuario que se va a buscar
     * @return verdadero/falso
     * @throws PersistenciaException
     */
    private boolean existe(Usuario usuario) throws PersistenciaException {
        boolean encontrada = false;
        Usuario usuarioEncontrado;
   
        usuarioEncontrado = buscar(usuario.getDni());
        if (usuarioEncontrado != null) {
           encontrada = true;
        }  

        return encontrada;
    }

    /**
     * Funcion que añade saldo a un usuario
     * @param usuario que se le va a añadir saldo
     * @param saldo que se va a sumar al actula del usuario
     * @throws ApiException error a controlar
     * @throws PersistenciaException error a controlar
     */
    public void añadirSaldo(Usuario usuario,float saldo) throws ApiException, PersistenciaException{
        float saldoActual = usuario.getSaldo();
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
        if (!existe(usuario)) {
            throw new ApiException("El usuario que quiere modificar no existe.");
        }
        usuarioModelo.modificar(usuario);
    }
   
}
