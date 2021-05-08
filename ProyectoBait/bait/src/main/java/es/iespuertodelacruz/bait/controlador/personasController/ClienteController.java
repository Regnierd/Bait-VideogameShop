import es.iespuertodelacruz.bait.api.personas.Cliente;
import es.iespuertodelacruz.bait.modelo.personasModelo.ClienteModelo;

public class ClienteController {
    ClienteModelo clienteModelo;


    public ClienteController() {
        if (clienteModelo == null) {
            clienteModelo = new ClienteModelo();
        }
    }

    public void insertar(Cliente cliente) {
        clienteModelo.insertar(cliente);
    }

    public Cliente buscarCliente(String nombreUsuario, String password) {
        return null;
    }

    public void comprar(String idProducto, Cliente cliente) {
    }
    
}
