<%@page contentType="text/html" pageEncoding="UTF-8" errorPage = "../include/error/showError.jsp" %>
<%@ page import='es.iespuertodelacruz.bait.api.productos.Marca'%>
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
        <jsp:useBean id="marcaController" class="es.iespuertodelacruz.bait.controlador.productosController.MarcaController" />
        <% String idMarca = request.getParameter("idMarca"); %>
        <% String nombreMarca = request.getParameter("nombreMarca"); %>
        <% Marca marca = new Marca(idMarca,nombreMarca); %>
        <% marcaController.modificar(marca); %>
        <h3>Modificado corretamente</h3>
        <%marca = marcaController.buscar(idMarca); %>
        <table>
            <tr>
                <th>IdMarca</th>
                <td><%= marca.getIdMarca()%></td>
            </tr>
            <tr>
                <th>Nombre</th>
                <td><%= marca.getNombre()%></td>
            </tr>
        </table>  
    </div>
</body>
<%@include file="../include/footer.jsp" %>
</html>