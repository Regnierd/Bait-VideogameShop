<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import='es.iespuertodelacruz.bait.api.personas.Usuario' %>
<%@ page errorPage = "../include/error/showError.jsp" %>
    
<jsp:useBean id="controller" class="es.iespuertodelacruz.bait.controlador.personasController.UsuarioController" />

<% String nombreUsuario = request.getParameter("nombreUsuario"); %>
<%  String password = request.getParameter("password"); %>
<%  String rol = request.getParameter("rol"); %>
<% Usuario usuario = controller.login(nombreUsuario,password,rol); %>

<%  session.setAttribute("usuario", usuario); %>


<% if (usuario.getRol().equals("Admin")){
    response.sendRedirect("http://localhost:8080/usuariosPages/admin-page.jsp"); 
}
%>

<% if (usuario.getRol().equals("Cliente")){
    response.sendRedirect("http://localhost:8080/usuariosPages/cliente.jsp"); 
}
%>

           