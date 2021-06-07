<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import='es.iespuertodelacruz.bait.api.personas.Usuario' %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pagina de verificacion de usuario</title>
        <style>
            table, th,td{
                border: 1px solid black;
                border-radius: 2px;
                margin 1px;
                padding: 1px;
            }
        </style>
    </head>

    <body>
        <h1>Verificacion Usuario</h1>

        <jsp:useBean id="controller" class="es.iespuertodelacruz.bait.controlador.personasController.UsuarioController" />

        <% String nombreUsuario = request.getParameter("nombreUsuario"); %>
        <%  String password = request.getParameter("password"); %>
        <%  String rol = request.getParameter("rol"); %>
        <% Usuario usuario = controller.login(nombreUsuario,password,rol); %>
        
        <table style>
            <tr>
                <th>DNI</th>
                <th>Nombre</th>
                <th>Apellidos</th>
                <th>Correo</th>
                <th>Direccion</th>
                <th>Telefono</th>
                <th>Pais</th>
                <th>Codigo Postal</th>
                <th>Provincia</th>
                <th>User</th>
                <th>Password</th>
                <th>Rol</th>
                <th>Saldo</th>
            </tr>
            <tr>
                <td><%= usuario.getDni()%></td>
                <td><%= usuario.getNombre()%></td>
                <td><%= usuario.getApellidos()%></td>
                <td><%= usuario.getEmail()%></td>
                <td><%= usuario.getDireccion()%></td>
                <td><%= usuario.getTelefono()%></td>
                <td><%= usuario.getPais()%></td>
                <td><%= usuario.getCodigoPostal()%></td>
                <td><%= usuario.getProvincia()%></td>
                <td><%= usuario.getNombreUsuario()%></td>
                <td><%= usuario.getPassword()%></td>
                <td><%= usuario.getRol()%></td>
                <td><%= usuario.getSaldo()%></td>
            </tr>
        </table>
    </body>

    </html>