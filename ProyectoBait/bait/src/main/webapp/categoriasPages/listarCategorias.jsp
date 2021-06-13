<%@ page import='es.iespuertodelacruz.bait.api.productos.Categoria' %>
<%@page import='java.util.ArrayList' %>

<jsp:useBean id="categoriaController" class="es.iespuertodelacruz.bait.controlador.productosController.CategoriaController" />
<% ArrayList<Categoria> categorias = categoriaController.obtenerListado(); %>
<% for(Categoria categoriaLista : categorias){ %>  
    <table>
        <tr >
            <th>IdCategoria</th>
            <td><%= categoriaLista.getIdCategoria()%></td>
        </tr>
        <tr>
            <th>Nombre</th>
            <td><%= categoriaLista.getNombre()%></td>
        </tr>
    </table> 
<% } %>
           
 