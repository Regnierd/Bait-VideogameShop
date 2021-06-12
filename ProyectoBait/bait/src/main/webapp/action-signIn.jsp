<%@page contentType="text/html" pageEncoding="UTF-8" errorPage = "include/error/showError.jsp" %>
<%@ page import='es.iespuertodelacruz.bait.api.personas.Usuario' %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Verificacion</title>
        <link rel="stylesheet" href="css/estilo.css">
        <%@include file="include/header.jsp" %>
        <%@include file="include/footer.jsp" %>
    </head>

    <body>
        <h1>Pagina del usuario</h1>

        <jsp:useBean id="controller" class="es.iespuertodelacruz.bait.controlador.personasController.UsuarioController" />

        <% String dni = request.getParameter("dni"); %>
        <% String nombre = request.getParameter("nombre"); %>
        <% String apellidos = request.getParameter("apellidos"); %>
        <% String correo = request.getParameter("correo"); %>
        <% String direccion = request.getParameter("direccion"); %>
        <% String telefono = request.getParameter("telefono"); %>
        <% String pais = request.getParameter("pais"); %>
        <% String codigoPostal = request.getParameter("codigoPostal"); %>
        <% String provincia = request.getParameter("provincia"); %>
        <% String nombreUsuario = request.getParameter("nombreUsuario"); %>
        <% String password = request.getParameter("password"); %>
        <% Usuario usuario = new Usuario(dni, nombre, apellidos, correo, direccion, telefono, pais, codigoPostal, provincia, nombreUsuario, password, "Cliente", 0f); %>
        <% controller.insertar(usuario); %>
        <% Usuario usuarioRegistrado = controller.buscar(dni); %>
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
                <td><%= usuarioRegistrado.getDni()%></td>
                <td><%= usuarioRegistrado.getNombre()%></td>
                <td><%= usuarioRegistrado.getApellidos()%></td>
                <td><%= usuarioRegistrado.getEmail()%></td>
                <td><%= usuarioRegistrado.getDireccion()%></td>
                <td><%= usuarioRegistrado.getTelefono()%></td>
                <td><%= usuarioRegistrado.getPais()%></td>
                <td><%= usuarioRegistrado.getCodigoPostal()%></td>
                <td><%= usuarioRegistrado.getProvincia()%></td>
                <td><%= usuarioRegistrado.getNombreUsuario()%></td>
                <td><%= usuarioRegistrado.getPassword()%></td>
                <td><%= usuarioRegistrado.getRol()%></td>
                <td><%= usuarioRegistrado.getSaldo()%></td>
            </tr>
        </table>
    </body>

    </html>