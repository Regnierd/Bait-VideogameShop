<%@ page import='es.iespuertodelacruz.bait.api.productos.Marca' %>

<jsp:useBean id="marcaController" class="es.iespuertodelacruz.bait.controlador.productosController.MarcaController" />
<% ArrayList<Marca> marcas = marcaController.obtenerListado(); %>
<% for(Marca marcaLista : marcas){ %>  
    <table>
        <tr>
            <th>IdMarca</th>
            <td><%= marcaLista.getIdMarca()%></td>
        </tr>
        <tr>
            <th>Nombre</th>
            <td><%= marcaLista.getNombre()%></td>
        </tr>
    </table>   
<% } %>