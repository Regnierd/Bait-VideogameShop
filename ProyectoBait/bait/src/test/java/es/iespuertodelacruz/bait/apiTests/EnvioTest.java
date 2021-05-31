package es.iespuertodelacruz.bait.apiTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.iespuertodelacruz.bait.api.movimientos.Envio;
import es.iespuertodelacruz.bait.api.movimientos.Pedido;

public class EnvioTest {
    private static final String IDENVIO = "idEnvio";
    Envio envio = new Envio();
    Pedido pedido = new Pedido();
    @BeforeEach
    public void setUp() {
        envio = crear(IDENVIO,pedido, "2021-05-26","Enviado");
    }

    @Test
    public void crearTest() {
        assertEquals(IDENVIO,envio.getIdEnvio(),"El envio no se creado correctamente");
    }

    @Test
    public void mostrarTest() {
        String informacion = envio.toString();
        assertTrue(informacion.contains(IDENVIO),"No esta mostrado el envio correcto");
    }

    /**
     * Funcion que crear y retorna una envio
     * @param idEnvio del envio
     * @param pedido del envio
     * @param fechaEnvio del envio
     * @param estado del envio
     * @return el envio creado
     */
    private Envio crear(String idEnvio,Pedido pedido, String fechaEnvio, String estado) {
        envio.setIdEnvio(idEnvio);
        envio.setPedido(pedido);
        envio.setFechaEnvio(fechaEnvio);
        envio.setEstado(estado);

        return new Envio(idEnvio,pedido, fechaEnvio, estado);
    }
}
