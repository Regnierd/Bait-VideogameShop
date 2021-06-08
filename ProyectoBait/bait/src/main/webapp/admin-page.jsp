<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>menuPrincipal</title>
        <link rel="stylesheet" href="css/estilo.css">
        <link rel="stylesheet" href="css/menuAdmin.css">
        <%@include file="include/cabecera.jsp" %>
    </head>
    <body> 
        <div class="header">
            <ul class="nav">
                <li><a href="">Inicio</a></li>          
                <li><a href="">Usuarios</a>
                    <ul>
                        <li><a href="#insertarU">Insertar usuario</a></li>
                        <li><a href="#eliminarU">Eliminar usuario</a></li>
                        <li><a href="#modificarU">Modificar usuario</a></li>
                        <li><a href="#buscarU">Buscar usuario</a></li>
                    </ul>
                </li>
                <li><a href="">Productos</a>
                    <ul>
                        <li><a href="#insertarP">Insertar productos</a></li>
                        <li><a href="#eliminarP">Eliminar productos</a></li>
                        <li><a href="#modificarP">Modificar productos</a></li>
                        <li><a href="#buscarP">Buscar productos</a></li>
                    </ul>
                </li>
                <li><a href="">Categorias</a>
                    <ul>
                        <li><a href="#insertarC">Insertar categorias</a></li>
                        <li><a href="#eliminarC">Eliminar categorias</a></li>
                        <li><a href="#modificarC">Modificar categorias</a></li>
                        <li><a href="#buscarC">Buscar categorias</a></li>
                    </ul>
                </li>
                <li><a href="">Marcas</a>
                    <ul>
                        <li><a href="#insertarM">Insertar marcas</a></li>
                        <li><a href="#eliminarM">Eliminar marcas</a></li>
                        <li><a href="#modificarM">Modificar marcas</a></li>
                        <li><a href="#buscarM">Buscar marcas</a></li>
                    </ul>
                </li>
                <li><a href="">Pedidos</a>
                    <ul>
                        <li><a href="#insertarPe">Insertar pedido</a></li>
                        <li><a href="#eliminarPe">Eliminar pedido</a></li>
                        <li><a href="#modificarPe">Modificar pedido</a></li>
                        <li><a href="#buscarPe">Buscar pedido</a></li>
                    </ul>
                </li>
                <li><a href="">Envios</a>
                    <ul>
                        <li><a href="#insertarE">Insertar envio</a></li>
                        <li><a href="#eliminarE">Eliminar envio</a></li>
                        <li><a href="#modificarE">Modificar envio</a></li>
                        <li><a href="#buscarE">Buscar envio</a></li>
                    </ul>
                </li>
            </ul>  
        </div>

        <br/>
        <br/>

        <div class="cuerpo">
            <!--Formularios de usuarios-->
            <a name="insertarU"></a>
            <div class="formulario">
                <h3>Registrar un nuevo usuario</h3>
                <%@include file="usuariosPages/formularioUsuario.jsp" %>
            </div>    
            <a name="eliminarU"></a>
            <div class="formulario">
                <h3>Eliminar un usuario</h3>
                <%@include file="usuariosPages/eliminarUsuario.jsp" %>
            </div> 
            <a name="modificarU"></a>
            <div class="formulario">
                <h3>Modificar un usuario</h3>
                <%@include file="usuariosPages/formularioUsuario.jsp" %>
            </div>  
            <a name="buscarU"></a>
            <div class="formulario">
                <h3>Buscar un usuario</h3>
                <%@include file="usuariosPages/buscarUsuario.jsp" %>
            </div>
            <!--Formularios de productos-->
            <a name="insertarP"></a>
            <div class="formulario">
                <h3>Registrar un nuevo producto</h3>
                <%@include file="productosPages/formularioProducto.jsp" %>
            </div>    
            <a name="eliminarP"></a>
            <div class="formulario">
                <h3>Eliminar un uproductosuario</h3>
                <%@include file="productosPages/eliminarProducto.jsp" %>
            </div> 
            <a name="modificarP"></a>
            <div class="formulario">
                <h3>Modificar un producto</h3>
                <%@include file="productosPages/formularioProducto.jsp" %>
            </div>  
            <a name="buscarP"></a>
            <div class="formulario">
                <h3>Buscar un producto</h3>
                <%@include file="productosPages/buscarProducto.jsp" %>
            </div> 
            <!--Formularios de categorias-->    
            <a name="insertarC"></a>
            <div class="formulario">
                <h3>Registrar una nueva categoria</h3>
                <%@include file="categoriasPages/formularioCategoria.jsp" %>
            </div>    
            <a name="eliminarC"></a>
            <div class="formulario">
                <h3>Eliminar una categoria</h3>
                <%@include file="categoriasPages/eliminarCategoria.jsp" %>
            </div> 
            <a name="modificarC"></a>
            <div class="formulario">
                <h3>Modificar una categoria</h3>
                <%@include file="categoriasPages/formularioCategoria.jsp" %>
            </div>  
            <a name="buscarC"></a>
            <div class="formulario">
                <h3>Buscar una categoria</h3>
                <%@include file="categoriasPages/buscarCategoria.jsp" %>
            </div>  
            <!--Formularios de marcas-->       
            <a name="insertarM"></a>
            <div class="formulario">
                <h3>Registrar una nueva marca</h3>
                <%@include file="marcasPages/formularioMarca.jsp" %>
            </div>    
            <a name="eliminarM"></a>
            <div class="formulario">
                <h3>Eliminar una marca</h3>
                <%@include file="marcasPages/eliminarMarca.jsp" %>
            </div> 
            <a name="modificarM"></a>
            <div class="formulario">
                <h3>Modificar una marca</h3>
                <%@include file="marcasPages/formularioMarca.jsp" %>
            </div>  
            <a name="buscarM"></a>
            <div class="formulario">
                <h3>Buscar una marca</h3>
                <%@include file="marcasPages/buscarMarca.jsp" %>
            </div> 
            <!--Formularios de pedidos-->
            <a name="insertarPe"></a>
            <div class="formulario">
                <h3>Registrar un nuevo pedido</h3>
                <%@include file="pedidosPages/formularioPedido.jsp" %>
            </div>    
            <a name="eliminarPe"></a>
            <div class="formulario">
                <h3>Eliminar un pedido</h3>
                <%@include file="pedidosPages/eliminarPedido.jsp" %>
            </div> 
            <a name="modificarPe"></a>
            <div class="formulario">
                <h3>Modificar un pedido</h3>
                <%@include file="pedidosPages/formularioPedido.jsp" %>
            </div>  
            <a name="buscarPe"></a>
            <div class="formulario">
                <h3>Buscar un pedido</h3>
                <%@include file="pedidosPages/buscarPedido.jsp" %>
            </div> 
            <!--Formularios de envios-->
            <a name="insertarE"></a>
            <div class="formulario">
                <h3>Registrar un nuevo envio</h3>
                <%@include file="enviosPages/formularioEnvio.jsp" %>
            </div>    
            <a name="eliminarE"></a>
            <div class="formulario">
                <h3>Eliminar un envio</h3>
                <%@include file="enviosPages/eliminarEnvio.jsp" %>
            </div> 
            <a name="modificarE"></a>
            <div class="formulario">
                <h3>Modificar un envio</h3>
                <%@include file="enviosPages/formularioEnvio.jsp" %>
            </div>  
            <a name="buscarE"></a>
            <div class="formulario">
                <h3>Buscar un envio</h3>
                <%@include file="enviosPages/buscarEnvio.jsp" %>
            </div> 
        </div>
        
        
    </body>

    </html>