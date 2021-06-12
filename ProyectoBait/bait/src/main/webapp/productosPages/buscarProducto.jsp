<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import='es.iespuertodelacruz.bait.api.productos.Producto' %>
<%@page import='java.util.ArrayList' %>
<%@page errorPage = "include/error/showError.jsp" %>


<jsp:useBean id="productoController" class="es.iespuertodelacruz.bait.controlador.productosController.ProductoController" />

<% ArrayList<Producto> productos = productoController.obtenerListado(); %>
<table style="border: 1px;">
    <% for(int i = 0; i >= productos.size(); i++){ %>
        <% Producto producto = productos.get(i); %>
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
