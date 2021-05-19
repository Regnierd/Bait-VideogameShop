package es.iespuertodelacruz.bait.controlador.personasController;

import es.iespuertodelacruz.bait.api.personas.Usuario;
import es.iespuertodelacruz.bait.exceptions.UsuarioException;

public class UsuarioController {
    
    /**
     * Metodo encargado de validar todos los atributos de un usuario
     * @param usuario a validar
     * @throws UsuarioException mensaje controlado
     */
    public void validar(Usuario usuario) throws UsuarioException{
        String mensaje = "";
        if(usuario == null){
            mensaje = "El usuario no puede ser nulo";
            throw new UsuarioException(mensaje);
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
            throw new UsuarioException(mensaje);
        }
    }
    public Usuario buscarUsuario(String nombreUsuario, String contraseña, String rol) throws UsuarioException{
        //codigo
        return null;
    }

    public void insertar(Usuario usuario) throws UsuarioException{
        //codigo
    }

    public void añadirSaldo(float saldo) throws UsuarioException{
        //codigo
    }

    public void modificar(Usuario nuevoUsuario) throws UsuarioException{
        validar(nuevoUsuario);
    }
   
}
