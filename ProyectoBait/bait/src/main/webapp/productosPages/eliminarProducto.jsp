<%@page contentType="text/html" pageEncoding="UTF-8" errorPage = "../include/error/showError.jsp" %>
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
        <jsp:useBean id="productoController" class="es.iespuertodelacruz.bait.controlador.productosController.ProductoController" />

        <% String idProducto = request.getParameter("idProducto"); %>
        <% productoController.eliminar(idProducto); %>
        <h3>Eliminado corretamente</h3>
    </div>
</body>
<%@include file="../include/footer.jsp" %>
</html>