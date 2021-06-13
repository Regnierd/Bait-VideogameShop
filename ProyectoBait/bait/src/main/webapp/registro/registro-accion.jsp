<%@page contentType="text/html" pageEncoding="UTF-8" errorPage = "../include/error/showError.jsp" %>
<%@ page import='es.iespuertodelacruz.bait.api.personas.Usuario' %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Verificacion</title>
        <link rel="stylesheet" href="../css/estilo.css">
        <%@include file="../include/header.jsp" %>
        <%@include file="../include/footer.jsp" %>
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
        <% usuario = controller.buscar(usuario.getDni()); %>
        <%  session.setAttribute("usuario", usuario); %>
        <% response.sendRedirect("http://localhost:8080/usuariosPages/cliente.jsp"); %>
        
    </body>

    </html>