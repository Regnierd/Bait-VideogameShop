<%@page contentType="text/html" pageEncoding="UTF-8" errorPage = "../include/error/showError.jsp" %>
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
        <jsp:useBean id="usuarioController" class="es.iespuertodelacruz.bait.controlador.personasController.UsuarioController" />

        <% String dni = request.getParameter("dni"); %>
        <% Usuario usuario = usuarioController.buscar(dni); %>
        
        <table>
            <tr>
                <td>Dni</td>
                <td><%= usuario.getDni()%></td>
            </tr>
            <tr>
                <td>Nombre</td>
                <td><%= usuario.getNombre()%></td>
            </tr>
            <tr>
                <td>Apellidos</td>
                <td><%= usuario.getApellidos()%></td>
            </tr>
            <tr>
                <td>Email</td>
                <td><%= usuario.getEmail()%></td>
            </tr>
            <tr>
                <td>Dirección</td>
                <td><%= usuario.getDireccion()%></td>
            </tr>
            <tr>
                <td>Telefono</td>
                <td><%= usuario.getTelefono()%></td>
            </tr>
            <tr>
                <td>Pais</td>
                <td><%= usuario.getPais()%></td>
            </tr>
            <tr>
                <td>Codigo Postal</td>
                <td><%= usuario.getCodigoPostal()%></td>
            </tr>
            <tr>
                <td>Provincia</td>
                <td><%= usuario.getProvincia()%></td>
            </tr>
            <tr>
                <td>Nombre Usuario</td>
                <td><%= usuario.getNombreUsuario()%></td>
            </tr>
            <tr>
                <td>Password</td>
                <td><%= usuario.getPassword()%></td>
            </tr>
            <tr>
                <td>Rol</td>
                <td><%= usuario.getRol()%></td>
            </tr>
            <tr>
                <td>Saldo</td>
                <td><%= usuario.getSaldo()%></td>
            </tr>
        </table>

    </div>
</body>
<%@include file="../include/footer.jsp" %>
</html>