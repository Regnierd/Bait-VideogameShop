<%@page contentType="text/html" pageEncoding="UTF-8"  %>
<%@ page import='es.iespuertodelacruz.bait.api.personas.Usuario' %>
    <!DOCTYPE html>
    <html>

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>menuPrincipal</title>
            <link rel="stylesheet" href="../css/estilo.css">
            <link rel="stylesheet" href="../css/tablasProductos.css">
            <link rel="stylesheet" href="../css/cliente.css">

            <%@include file="../include/header.jsp" %>
            <%@include file="../include/spam.jsp" %>
        </head>
        <body>
            <div class="page">
                <div class="cuerpoCliente">
                    <div class="tablas">
                        <%@include file="../productosPages/obtenerProductos.jsp" %>
                    </div>
                    <ul class="perfil">
                        <% Usuario usuario = (Usuario) session.getAttribute("usuario"); %>
                        <li><%= usuario.getNombre() %></li>
                        <li><a href="perfilCliente.jsp">Perfil</a></li>
                        <li><a href="ingresarDinero.jsp">Añadir saldo</a></li>
                        <li><a href="hacerPedido.jsp">Realizar pedido</a></li>
                        <li><a href="verPedido.jsp">Ver pedido</a></li>
                        <li><a href="verEnvio.jsp">Ver envio</a></li>
                    </ul>
                </div>
                
                
            </div>
            
            
            <%@include file="../include/footer.jsp" %>
        </body>
    </html>