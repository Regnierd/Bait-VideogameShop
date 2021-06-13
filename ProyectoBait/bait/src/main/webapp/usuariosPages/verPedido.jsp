<%@page contentType="text/html" pageEncoding="UTF-8" errorPage = "../include/error/showError.jsp" %>
<%@ page import='es.iespuertodelacruz.bait.api.movimientos.Pedido' %>
<%@ page import='es.iespuertodelacruz.bait.api.personas.Usuario' %>
<%@page import='java.util.ArrayList' %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="../css/estilo.css">
    <link rel="stylesheet" href="../css/tablasProductos.css">
    <%@include file="../include/header.jsp" %>
    <%@include file="../include/spam.jsp" %>
</head>
<body>
    <div class="page">
        <div class="tablas">
            <jsp:useBean id="pedidoController" class="es.iespuertodelacruz.bait.controlador.movimientosController.PedidoController" />
            <% Usuario usuario = (Usuario) session.getAttribute("usuario"); %>                 
            <% ArrayList<Pedido> pedidos = pedidoController.obtenerListado(usuario.getDni()); %>
            <% for(Pedido pedido : pedidos){ %> 
                <table>
                    <tr>
                        <td>IdPedido</td>
                        <td><%= pedido.getIdPedido() %></td>
                    </tr>
                    <tr>
                        <td>Unidades</td>
                        <td><%= pedido.getUnidades() %></td>
                    </tr>
                    <tr>
                        <td>Total</td>
                        <td><%= pedido.getTotal() %></td>
                    </tr>
                    <tr>
                        <td>Fecha Pedido</td>
                        <td><%= pedido.getFechaPedido() %></td>
                    </tr>
                    <tr>
                        <td>Usuario</td>
                        <td><%= pedido.getUsuario().getDni() %></td>
                    </tr>
                    <tr>
                        <td>Categoria</td>
                        <td><%= pedido.getProducto().getIdProducto() %></td>
                    </tr>
                </table>       
            <%}%> 
        </div>
    </div>
</body>
<%@include file="../include/footer.jsp" %>
</html>
