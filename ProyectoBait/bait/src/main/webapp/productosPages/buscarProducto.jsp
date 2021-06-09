<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import='es.iespuertodelacruz.bait.api.productos.Producto' %>
<%@page import='java.util.ArrayList' %>
<%@page errorPage = "include/error/showError.jsp" %>
<form method="post">
    Introduce el id del producto a buscar: <input type="text" name="id" placeholder="pro_00000001"><br>
    <input type="submit" value="Buscar"> <br>
</form>

<jsp:useBean id="productoController" class="es.iespuertodelacruz.bait.controlador.productosController.ProductoController" />

<% ArrayList<Producto> productos = productoController.obtenerListado(); %>
<table style="border: 1px;">
    <% for(int i = 0; i >= productos.size(); i++){ %>
        <% Producto producto = productos.get(i); %>
        <tr>
            <th>IdProducto</th>
            <th>Nombre</th>
            <th>Precio</th>
            <th>Descripcion</th>
            <th>Stock</th>
            <th>IdCategoria</th>
            <th>IdMarca</th>
        </tr>
        <tr>
            <td><%= producto.getIdProducto()%></td>
            <td><%= producto.getNombre()%></td>
            <td><%= producto.getPrecio()%></td>
            <td><%= producto.getDescripcion()%></td>
            <td><%= producto.getStock()%></td>
            <td><%= producto.getCategoria().getIdCategoria()%></td>
            <td><%= producto.getMarca().getIdMarca()%></td>
        </tr>
    <% } %> 
</table>
