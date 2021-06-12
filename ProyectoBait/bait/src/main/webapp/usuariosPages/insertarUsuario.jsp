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
        <% String nombre = request.getParameter("nombre"); %>
        <% String apellidos = request.getParameter("apellidos"); %>
        <% String email = request.getParameter("email"); %>
        <% String direccion = request.getParameter("direccion"); %>
        <% String telefono = request.getParameter("telefono"); %>
        <% String pais = request.getParameter("pais"); %>
        <% String codigoPostal = request.getParameter("codigoPostal"); %>
        <% String provincia = request.getParameter("provincia"); %>
        <% String nombreUsuario = request.getParameter("nombreUsuario"); %>
        <% String password = request.getParameter("password"); %>
        <% String rol = request.getParameter("rol"); %>

        <% Usuario usuario = new Usuario(dni, nombre, apellidos, email, direccion, telefono, pais, codigoPostal, provincia, nombreUsuario, password, rol, 0f); %>
        <% usuarioController.insertar(usuario); %>

        <% Usuario usuarioInsertado = usuarioController.buscar(usuario.getDni()); %>
        <h3>Insertado corretamente</h3>
                <table>
                    <tr>
                        <td>Dni</td>
                        <td><%= usuarioInsertado.getDni()%></td>
                    </tr>
                    <tr>
                        <td>Nombre</td>
                        <td><%= usuarioInsertado.getNombre()%></td>
                    </tr>
                    <tr>
                        <td>Apellidos</td>
                        <td><%= usuarioInsertado.getApellidos()%></td>
                    </tr>
                    <tr>
                        <td>Email</td>
                        <td><%= usuarioInsertado.getEmail()%></td>
                    </tr>
                    <tr>
                        <td>Dirección</td>
                        <td><%= usuarioInsertado.getDireccion()%></td>
                    </tr>
                    <tr>
                        <td>Telefono</td>
                        <td><%= usuarioInsertado.getTelefono()%></td>
                    </tr>
                    <tr>
                        <td>Pais</td>
                        <td><%= usuarioInsertado.getPais()%></td>
                    </tr>
                    <tr>
                        <td>Codigo Postal</td>
                        <td><%= usuarioInsertado.getCodigoPostal()%></td>
                    </tr>
                    <tr>
                        <td>Provincia</td>
                        <td><%= usuarioInsertado.getProvincia()%></td>
                    </tr>
                    <tr>
                        <td>Nombre Usuario</td>
                        <td><%= usuarioInsertado.getNombreUsuario()%></td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td><%= usuarioInsertado.getPassword()%></td>
                    </tr>
                    <tr>
                        <td>Rol</td>
                        <td><%= usuarioInsertado.getRol()%></td>
                    </tr>
                    <tr>
                        <td>Saldo</td>
                        <td><%= usuarioInsertado.getSaldo()%></td>
                    </tr>
                </table>

    </div>
    </div>
</body>
<%@include file="../include/footer.jsp" %>
</html>





