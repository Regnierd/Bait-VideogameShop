<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import='es.iespuertodelacruz.bait.api.personas.Usuario' %>
<%@ page import='es.iespuertodelacruz.bait.api.movimientos.Pedido' %>
<%@ page import='es.iespuertodelacruz.bait.api.movimientos.Envio' %>

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
        <form method="post" action="#">
            <label>Introduce el idProducto:</label> <input type="text" name="idProducto" placeholder="pro_00000001"><br>
            <label>Introduce las unidades que quiere comprar:</label> <input type="text" name="unidades" placeholder="2"><br>
            <input type="submit" value="Añadir"><br>
        </form>
        <jsp:useBean id="pedidoController" class="es.iespuertodelacruz.bait.controlador.movimientosController.PedidoController" />
        <% Usuario usuario = (Usuario) session.getAttribute("usuario"); %>
        <% String idProducto = request.getParameter("idProducto"); %>
        <% if(idProducto != null ){
            int unidades = Integer.parseInt(request.getParameter("unidades"));
            pedidoController.realizarPedido(usuario, idProducto, unidades);
        %> 
        <h3>realizado correctamente</h3>
        <% } %>

    </div>
</body>
<%@include file="../include/footer.jsp" %>
</html>