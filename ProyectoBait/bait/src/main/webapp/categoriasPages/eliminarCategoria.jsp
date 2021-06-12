<%@page contentType="text/html" pageEncoding="UTF-8" errorPage = "include/error/showError.jsp" %>
<%@ page import='es.iespuertodelacruz.bait.api.productos.Categoria' %>
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
        <jsp:useBean id="categoriaController" class="es.iespuertodelacruz.bait.controlador.productosController.CategoriaController" />

        <% String idCategoria = request.getParameter("idCategoria"); %>
        <% categoriaController.eliminar(idCategoria); %>
        <h3>Eliminado corretamente</h3>
    </div>
</body>
<%@include file="../include/footer.jsp" %>
</html>