<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import='es.iespuertodelacruz.bait.api.productos.Producto' %>
<%@page import='es.iespuertodelacruz.bait.api.productos.Categoria' %>
<%@page import='es.iespuertodelacruz.bait.api.productos.Marca' %>
<%@page errorPage = "include/error/showError.jsp" %>

<%@include file="include/header.jsp" %>
<%@include file="include/footer.jsp" %>

<jsp:useBean id="productoController" class="es.iespuertodelacruz.bait.controlador.productosController.ProductoController" />
<jsp:useBean id="categoriaController" class="es.iespuertodelacruz.bait.controlador.productosController.CategoriaController" />
<jsp:useBean id="marcaController" class="es.iespuertodelacruz.bait.controlador.productosController.MarcaController" />

<% String nombre = request.getParameter("nombre"); %>
<% String idCategoria = request.getParameter("idCategoria"); %>
<% float precio = request.getParameter("precio"); %>
<% String descripcion = request.getParameter("descripcion"); %>
<% int stock = request.getParameter("stock"); %>
<% String idMarca = request.getParameter("idMarca"); %>

<% Categoria categoria = categoriaController.buscar(idCategoria); %>
<% Marca marca = marcaController.buscar(idMarca); %>

<% Producto producto = new Producto(nombre, categoria, precio, descripcion, stock, marca); %>

