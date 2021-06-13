<%@ page import='es.iespuertodelacruz.bait.api.movimientos.Pedido' %>

<jsp:useBean id="pedidoController" class="es.iespuertodelacruz.bait.controlador.movimientosController.PedidoController" />
<% ArrayList<Pedido> pedidos = pedidoController.obtenerListado(); %>
<% for(Pedido pedidoLista : pedidos){ %>  
    <table>
        <tr>
            <td>IdPedido</td>
            <td><%= pedidoLista.getIdPedido() %></td>
        </tr>
        <tr>
            <td>Unidades</td>
            <td><%= pedidoLista.getUnidades() %></td>
        </tr>
        <tr>
            <td>Total</td>
            <td><%= pedidoLista.getTotal() %></td>
        </tr>
        <tr>
            <td>Fecha Pedido</td>
            <td><%= pedidoLista.getFechaPedido() %></td>
        </tr>
        <tr>
            <td>Usuario</td>
            <td><%= pedidoLista.getUsuario().getDni() %></td>
        </tr>
        <tr>
            <td>Producto</td>
            <td><%= pedidoLista.getProducto().getIdProducto() %></td>
        </tr>
    </table>   
<% } %>