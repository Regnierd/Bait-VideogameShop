<%@page contentType="text/html" pageEncoding="UTF-8" errorPage = "../include/error/showError.jsp" %>
<%@ page import='es.iespuertodelacruz.bait.api.movimientos.Pedido' %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="../css/estilo.css">
    <link rel="stylesheet" href="../css/menuAdmin.css">
    <%@include file="../include/header.jsp" %>
    <%@include file="../include/spam.jsp" %>
</head>
<body>
    <div class="page">
        <jsp:useBean id="pedidoController" class="es.iespuertodelacruz.bait.controlador.movimientosController.PedidoController" />

        <% String idPedido = request.getParameter("idPedido"); %>
        <% Pedido pedido = pedidoController.buscar(idPedido); %>
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
                <td>Producto</td>
                <td><%= pedido.getProducto().getIdProducto() %></td>
            </tr>
        </table>   
    </div>
</body>
<%@include file="../include/footer.jsp" %>
</html>