<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import='es.iespuertodelacruz.bait.api.productos.Producto' %>
<%@page import='java.util.ArrayList' %>
<%@page errorPage = "../include/error/showError.jsp" %>
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

    <jsp:useBean id="productoController" class="es.iespuertodelacruz.bait.controlador.productosController.ProductoController" />

    <% 
        ArrayList<Producto> productos = null;
        String nombreProducto = request.getParameter("nombre");
        String idCategoria = request.getParameter("idCategoria");
        String idMarca = request.getParameter("idMarca");

        if(nombreProducto != null){
            productos = productoController.buscarPorNombre(nombreProducto);
        }

        if(idCategoria != null){
            productos = productoController.buscarPorCategoria(idCategoria);
        }

        if(idMarca != null){
            productos = productoController.buscarPorMarca(idMarca);
        }
    %>
    <div class="page">
        <table>
            <% for( Producto producto : productos){ %>
                <tr>
                    <th>IdProducto</th>
                    <td><%= producto.getIdProducto()%></td>
                </tr>
                <tr>
                    <th>Nombre</th>
                    <td><%= producto.getNombre()%></td>
                </tr>
                <tr>
                    <th>Precio</th>
                    <td><%= producto.getPrecio()%></td>
                </tr>
                <tr>
                    <th>Descripcion</th>
                    <td><%= producto.getDescripcion()%></td>
                </tr>
                <tr>
                    <th>Stock</th>
                    <td><%= producto.getStock()%></td>
                </tr>
                <tr>
                    <th>IdCategoria</th>
                    <td><%= producto.getCategoria().getIdCategoria()%></td>
                </tr>
                <tr>
                    <th>IdMarca</th>
                    <td><%= producto.getMarca().getIdMarca()%></td>
                </tr>
            <% } %> 
        </table>
    </div>
</body>
<%@include file="../include/footer.jsp" %>
</html>