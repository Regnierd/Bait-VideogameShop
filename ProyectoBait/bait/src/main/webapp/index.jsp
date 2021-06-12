<%@page contentType="text/html" pageEncoding="UTF-8" errorPage = "include/error/showError.jsp" %>

    <!DOCTYPE html>
    <html>

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>menuPrincipal</title>
            <link rel="stylesheet" href="css/estilo.css">
            <link rel="stylesheet" href="css/tablasProductos.css">

            <%@include file="include/header.jsp" %>
            <%@include file="include/spam.jsp" %>
        </head>
        <body>
            <div class="page">
                <div class="tablas">
                    <%@include file="productosPages/obtenerProductos.jsp" %>
                </div>
                
            </div>
            
            
            <%@include file="include/footer.jsp" %>
        </body>
    </html>