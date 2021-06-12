<%@page contentType="text/html" pageEncoding="UTF-8" errorPage = "../include/error/showError.jsp" %>
<%@ page import='es.iespuertodelacruz.bait.api.productos.Producto' %>
<%@ page import='es.iespuertodelacruz.bait.api.productos.Marca' %>
<%@ page import='es.iespuertodelacruz.bait.api.productos.Categoria' %>
<%@ page import='java.util.ArrayList' %>

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
        <jsp:useBean id="productoController" class="es.iespuertodelacruz.bait.controlador.productosController.ProductoController" />
        <jsp:useBean id="categoriaController" class="es.iespuertodelacruz.bait.controlador.productosController.CategoriaController" />
        <jsp:useBean id="marcaController" class="es.iespuertodelacruz.bait.controlador.productosController.MarcaController" />

        <% String idProducto = request.getParameter("idProducto"); %>
        <% String nombre = request.getParameter("nombreProducto"); %>
        <% String idCategoria = request.getParameter("idCategoria"); %>
        <% float precio = Float.parseFloat(request.getParameter("precio")); %>
        <% String descripcion = request.getParameter("descripcion"); %>
        <% int stock = Integer.parseInt(request.getParameter("stock")); %>
        <% String idMarca = request.getParameter("idMarca"); %>

        <% Categoria categoria = categoriaController.buscar(idCategoria); %>
        <% Marca marca = marcaController.buscar(idMarca); %>
        <% Producto producto = new Producto(idProducto, nombre, categoria, precio, descripcion, stock, marca); %>
        <% productoController.modificar(producto); %>

        <% Producto productoModificado = productoController.buscar(producto.getIdProducto()); %>
        <h3>Modificado corretamente</h3>
                <table>
                    <tr>
                        <td>IdProducto</td>
                        <td><%= productoModificado.getIdProducto() %></td>
                    </tr>
                    <tr>
                        <td>Nombre</td>
                        <td><%= productoModificado.getNombre() %></td>
                    </tr>
                    <tr>
                        <td>Precio</td>
                        <td><%= productoModificado.getPrecio() %></td>
                    </tr>
                    <tr>
                        <td>Descripcion</td>
                        <td><%= productoModificado.getDescripcion() %></td>
                    </tr>
                    <tr>
                        <td>Stock</td>
                        <td><%= productoModificado.getStock() %></td>
                    </tr>
                    <tr>
                        <td>Categoria</td>
                        <td><%= productoModificado.getCategoria().getIdCategoria() %></td>
                    </tr>
                    <tr>
                        <td>Marca</td>
                        <td><%= productoModificado.getMarca().getIdMarca() %></td>
                    </tr>
                </table>

    </div>
</body>
<%@include file="../include/footer.jsp" %>
</html>