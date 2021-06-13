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
        <div class="cuerpo">          
            <form method="post" action="#">
                <label>Introduce la cantidad de saldo:</label> <input type="text" name="saldo" placeholder="20.0"><br>
                <input type="submit" value="Añadir"> <br>
            </form>
            <jsp:useBean id="usuarioController" class="es.iespuertodelacruz.bait.controlador.personasController.UsuarioController" />
            <% Usuario usuario = (Usuario) session.getAttribute("usuario"); %>
            <% String parameter = request.getParameter("saldo");
            if (parameter != null){
                float saldo = Float.parseFloat(parameter);
                usuarioController.añadirSaldo(usuario, saldo);
            %>    <h3>Añadido correctamente</h3>
            <%}%>
        </div>     
    </div>
</body>
<%@include file="../include/footer.jsp" %>
</html>