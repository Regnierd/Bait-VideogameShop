<%@page contentType="text/html" pageEncoding="UTF-8" errorPage = "../include/error/showError.jsp"%>
<%@ page import='es.iespuertodelacruz.bait.api.movimientos.Pedido' %>
<%@ page import='es.iespuertodelacruz.bait.api.productos.Producto' %>
<%@ page import='es.iespuertodelacruz.bait.api.personas.Usuario' %>


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
        <jsp:useBean id="productoController" class="es.iespuertodelacruz.bait.controlador.productosController.ProductoController" />
        <jsp:useBean id="usuarioController" class="es.iespuertodelacruz.bait.controlador.personasController.UsuarioController" />

        <% String idPedido = request.getParameter("idPedido"); %>
        <% int unidades = Integer.parseInt(request.getParameter("unidades")); %>
        <% float total = Float.parseFloat(request.getParameter("total")); %>
        <% String fechaPedido = "" + request.getParameter("fechaPedido"); %>
        <% String dni = request.getParameter("dni");%>
        <% String idProducto = request.getParameter("idProducto");%>
        <% Usuario usuario = usuarioController.buscar(dni); %>
        <% Producto producto = productoController.buscar(idProducto); %>


        <% Pedido pedido = new Pedido(idPedido, unidades, total, fechaPedido, usuario, producto); %>
        <% pedidoController.modificar(pedido); %>

        <% pedido = pedidoController.buscar(idPedido); %>
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
    </div>
</body>
<%@include file="../include/footer.jsp" %>
</html>
