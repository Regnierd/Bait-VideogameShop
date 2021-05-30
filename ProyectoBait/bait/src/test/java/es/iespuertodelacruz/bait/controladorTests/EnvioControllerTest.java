package es.iespuertodelacruz.bait.controladorTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.iespuertodelacruz.bait.api.movimientos.Envio;
import es.iespuertodelacruz.bait.api.movimientos.Pedido;
import es.iespuertodelacruz.bait.api.personas.Usuario;
import es.iespuertodelacruz.bait.api.productos.Categoria;
import es.iespuertodelacruz.bait.api.productos.Marca;
import es.iespuertodelacruz.bait.api.productos.Producto;
import es.iespuertodelacruz.bait.controlador.movimientosController.EnvioController;
import es.iespuertodelacruz.bait.controlador.movimientosController.PedidoController;
import es.iespuertodelacruz.bait.controlador.personasController.UsuarioController;
import es.iespuertodelacruz.bait.controlador.productosController.CategoriaController;
import es.iespuertodelacruz.bait.controlador.productosController.MarcaController;
import es.iespuertodelacruz.bait.controlador.productosController.ProductoController;
import es.iespuertodelacruz.bait.exceptions.ApiException;
import es.iespuertodelacruz.bait.exceptions.PersistenciaException;

public class EnvioControllerTest {
    private static final String IDENVIO = "env_idEnvio";
    private static final String IDENVIO_INEXISTENTE = "env_idEnvioInexistente";
    private static final String IDPEDIDO = "ped_idPedido";
    private static final String DNI = "43387932L";
    private static final String IDPRODUCTO = "pro_idProducto";
    private static final String NOMBRE_USUARIO = "usuario";
    private static final String IDCATEGORIA = "idCategoria";
    private static final String IDMARCA = "idMarca";

    EnvioController envioController;
    PedidoController pedidoController;
    UsuarioController usuarioController;
    CategoriaController categoriaController;
    MarcaController marcaController;
    ProductoController productoController;
    Pedido pedido;
    Usuario usuario;
    Producto producto;
    Categoria categoria;
    Marca marca;
    Envio envio;

    @BeforeEach
    public void setUp(){
        try {
            if (envioController == null) {               
                envioController = new EnvioController();                    
            }
            if (pedidoController == null) {                
                pedidoController = new PedidoController();    
            }          
            if (usuarioController == null) {                
                usuarioController = new UsuarioController();                      
            }
            if (categoriaController == null) {               
                categoriaController = new CategoriaController();                     
            }
            if (marcaController == null) {               
                marcaController = new MarcaController();                    
            }
            if (productoController == null) {               
                productoController = new ProductoController();                    
            }
        } catch (PersistenciaException e) {
            fail(e.getMessage());
        }
        
        usuario = new Usuario(DNI, "nombre", "apellidos", "email", "direccion", "telefono", "pais", "codigoPostal", "provincia", NOMBRE_USUARIO, "password", "Admin", 0f);
        categoria = new Categoria(IDCATEGORIA, "nombre");
        marca = new Marca(IDMARCA, "nombre");
        producto = new Producto(IDPRODUCTO, "nombre", categoria, 10f, "descripcion", 15, marca);
        pedido = new Pedido(IDPEDIDO, 2, 20, "20-05-2021", usuario, producto);
        envio = new Envio(IDENVIO, pedido, "21-05-2021", "Enviado");

        try {
            usuarioController.insertar(usuario);
            categoriaController.insertar(categoria);
            marcaController.insertar(marca);
            productoController.insertar(producto);
            pedidoController.insertar(pedido);
            envioController.insertar(envio);
        } catch (PersistenciaException | ApiException e) {
            fail(e.getMessage());
        }
    
    }

    @AfterEach
    public void after() {
        try {
            usuarioController.eliminar(DNI);
            categoriaController.eliminar(IDCATEGORIA);
            marcaController.eliminar(IDMARCA);
            productoController.eliminar(IDPRODUCTO);
            pedidoController.eliminar(IDPEDIDO);
            envioController.eliminar(IDENVIO);
        } catch (PersistenciaException | ApiException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void validarEnvio() {
        Envio envioVacio = new Envio();
        try {
            envioController.validar(envioVacio);

        } catch (ApiException e) {
            assertTrue(e.getMessage().contains("nulo o vacio"));
        }

        Envio envioNulo = null;
        try {
            envioController.validar(envioNulo);
        } catch (Exception e) {
            assertTrue(e.getMessage().contains(" es nulo"));
        }
    }

    @Test
    public void insertarErrorTest(){
        try {
            envioController.insertar(envio);
        } catch (PersistenciaException | ApiException e) {
            assertTrue(e.getMessage().contains("ya existe"));
        }
    }

    @Test
    public void eliminarErrorTest() {
        try {
            envioController.eliminar(IDENVIO_INEXISTENTE);
        } catch (PersistenciaException | ApiException e) {
            assertTrue(e.getMessage().contains("no existe"));
        }
    }

    @Test
    public void buscarTest() {
        Envio envioBuscado;
        try {
            envioBuscado = envioController.buscar(IDENVIO);
            assertEquals(envio, envioBuscado, "Los envios deberian ser iguales");
        } catch (PersistenciaException | ApiException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void buscarErrorTest() {
        Envio envioBuscado;
        try {
            envioBuscado = envioController.buscar(IDENVIO_INEXISTENTE);
        } catch (PersistenciaException | ApiException e) {
            assertTrue(e.getMessage().contains("no existe"));
        }
    }

    @Test
    public void modificarTest() {
        Envio envioEncontrado;
        envio.setEstado("Entregado");
        try {
            envioController.modificar(envio);
            envioEncontrado = envioController.buscar(IDENVIO);
            assertEquals(envio, envioEncontrado, "Los envios deberian ser iguales");
        } catch (PersistenciaException | ApiException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void modificarErrorTest() {
        Envio envioInexistente = new Envio(IDENVIO_INEXISTENTE, pedido, "21-05-2021", "Enviado");
        try {
            envioController.modificar(envioInexistente);
        } catch (PersistenciaException | ApiException e) {
            assertTrue(e.getMessage().contains("no existe"));
        }
    }

    @Test
    public void obtenerListadoTest() {
        ArrayList<Envio> lista;
        try {
            lista = envioController.obtenerListado();
            assertTrue(lista.contains(envio), "La lista no contiene el envio correcto");
        } catch (PersistenciaException | ApiException e) {
            fail(e.getMessage());
        }
    }

}
