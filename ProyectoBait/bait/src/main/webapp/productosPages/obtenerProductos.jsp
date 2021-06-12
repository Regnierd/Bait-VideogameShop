<%@ page import='es.iespuertodelacruz.bait.api.productos.Producto' %>
<%@page import='java.util.ArrayList' %>

<jsp:useBean id="productosController" class="es.iespuertodelacruz.bait.controlador.productosController.ProductoController" />
<% ArrayList<Producto> productos = productosController.obtenerListado(); %>

<% for(Producto producto : productos){ %>  
    <table>
        <tr>
            <td>IdProducto</td>
            <td><%= producto.getIdProducto() %></td>
        </tr>
        <tr>
            <td>Nombre</td>
            <td><%= producto.getNombre() %></td>
        </tr>
        <tr>
            <td>Precio</td>
            <td><%= producto.getPrecio() %></td>
        </tr>
        <tr>
            <td>Descripcion</td>
            <td><%= producto.getDescripcion() %></td>
        </tr>
        <tr>
            <td>Stock</td>
            <td><%= producto.getStock() %></td>
        </tr>
        <tr>
            <td>Categoria</td>
            <td><%= producto.getCategoria().getIdCategoria() %></td>
        </tr>
        <tr>
            <td>Marca</td>
            <td><%= producto.getMarca().getIdMarca() %></td>
        </tr>                  
    </table>
<% } %>
           
 