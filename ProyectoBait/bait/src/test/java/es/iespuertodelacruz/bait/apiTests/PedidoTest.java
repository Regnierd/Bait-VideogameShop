package es.iespuertodelacruz.bait.apiTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.iespuertodelacruz.bait.api.movimientos.Pedido;
import es.iespuertodelacruz.bait.api.personas.Usuario;
import es.iespuertodelacruz.bait.api.productos.Producto;

public class PedidoTest {
    private static final String IDPEDIDO = "idPedido";
    Pedido pedido = new Pedido();
    Producto producto = new Producto();
    Usuario usuario = new Usuario();
    
    @BeforeEach
    public void setUp() {
        pedido = crear(IDPEDIDO, 20, 0, "2021-05-26", usuario, producto);
    }

    @Test
    public void crearTest() {
        assertEquals(IDPEDIDO,pedido.getIdPedido(),"El pedido no se creado correctamente");
    }

    @Test
    public void mostrarTest() {
        String informacion = pedido.toString();
        assertTrue(informacion.contains(IDPEDIDO),"No esta mostrado el pedido correcto");
    }

    /**
     * Funcion que crear y retorna una pedido
     * @param idEnvio del envio
     * @param pedido del envio
     * @param fechaEnvio del envio
     * @param estado del envio
     * @return el envio creado
     */
    private Pedido crear(String idPedido, int unidades, float total, String fechaPedido, Usuario usuario, Producto producto) {
        pedido.setIdPedido(idPedido);
        pedido.setUnidades(unidades);
        pedido.setTotal(total);
        pedido.setFechaPedido(fechaPedido);
        pedido.setCliente(usuario);
        pedido.setProducto(producto);

        return new Pedido(idPedido, unidades, total, fechaPedido, usuario, producto);
    }
}
