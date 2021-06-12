<%@page contentType="text/html" pageEncoding="UTF-8" errorPage = "include/error/showError.jsp" %>
<%@ page import='es.iespuertodelacruz.bait.api.personas.Usuario' %>
    
<%@include file="include/header.jsp" %>
<%@include file="include/footer.jsp" %>


<jsp:useBean id="controller" class="es.iespuertodelacruz.bait.controlador.personasController.UsuarioController" />

<% String nombreUsuario = request.getParameter("nombreUsuario"); %>
<%  String password = request.getParameter("password"); %>
<%  String rol = request.getParameter("rol"); %>
<% Usuario usuario = controller.login(nombreUsuario,password,rol); %>

<% if (usuario.getRol().equals("Admin")){
    response.sendRedirect("http://localhost:8080/admin-page.jsp"); 
}
%>
<% if (usuario.getRol().equals("Cliente")){
    response.sendRedirect("http://localhost:8080/index.jsp"); 
}
%>

           