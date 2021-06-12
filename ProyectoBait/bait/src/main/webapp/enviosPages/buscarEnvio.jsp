<%@page contentType="text/html" pageEncoding="UTF-8" errorPage = "../include/error/showError.jsp" %>
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
        <jsp:useBean id="envioController" class="es.iespuertodelacruz.bait.controlador.movimientosController.EnvioController" />

        <% String idEnvio = request.getParameter("idEnvio"); %>
        <% Envio envio = envioController.buscar(idEnvio); %>
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
    </div>
</body>
<%@include file="../include/footer.jsp" %>
</html>