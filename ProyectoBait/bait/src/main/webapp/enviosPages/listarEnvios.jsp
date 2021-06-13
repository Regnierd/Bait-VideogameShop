<%@ page import='es.iespuertodelacruz.bait.api.movimientos.Envio' %>

<jsp:useBean id="envioController" class="es.iespuertodelacruz.bait.controlador.movimientosController.EnvioController" />
<% ArrayList<Envio> envios = envioController.obtenerListado(); %>
<% for(Envio envio : envios){ %>  
    <table>
        <tr>
            <td>IdEnvio</td>
            <td><%= envio.getIdEnvio() %></td>
        </tr>
        <tr>
            <td>IdPedido</td>
            <td><%= envio.getPedido().getIdPedido() %></td>
        </tr>
        <tr>
            <td>Fecha Envio</td>
            <td><%= envio.getFechaEnvio() %></td>
        </tr>
        <tr>
            <td>Estado</td>
            <td><%= envio.getEstado() %></td>
        </tr>
    </table>    
<% } %>
           
 