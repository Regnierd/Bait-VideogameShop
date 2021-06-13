<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import='es.iespuertodelacruz.bait.api.personas.Usuario' %>

    <!DOCTYPE html>
    <html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>menuPrincipal</title>
        <link rel="stylesheet" href="../css/estilo.css">
        <link rel="stylesheet" href="../css/menuAdmin.css">
        <%@include file="../include/header.jsp" %>
        <%@include file="../include/spam.jsp" %>
        <jsp:useBean id="categoriaController" class="es.iespuertodelacruz.bait.controlador.productosController.CategoriaController" />

    </head>
    <body>
        <div class="page">
                <ul class="nav">
                    <li><a href="">Inicio</a></li>          
                    <li><a href="">Usuarios</a>
                        <ul>
                            <li><a href="#insertarUsuario">Insertar usuario</a></li>
                            <li><a href="#eliminarUsuario">Eliminar usuario</a></li>
                            <li><a href="#modificarUsuario">Modificar usuario</a></li>
                            <li><a href="#buscarUsuario">Buscar usuario</a></li>
                        </ul>
                    </li>
                    <li><a href="">Productos</a>
                        <ul>
                            <li><a href="#insertarProducto">Insertar productos</a></li>
                            <li><a href="#eliminarProducto">Eliminar productos</a></li>
                            <li><a href="#modificarProducto">Modificar productos</a></li>
                            <li><a>Buscar productos</a>
                                <ul>
                                    <li><a href="#buscarProductoNombre">Buscar productos nombre</a></li>
                                    <li><a href="#buscarProductoCategoria">Buscar productos categoria</a></li>
                                    <li><a href="#buscarProductoMarca">Buscar productos marca</a></li>
                                </ul>
                            </li>
                        </ul>
                    </li>
                    <li><a href="">Categorias</a>
                        <ul>
                            <li><a href="#insertarCategoria">Insertar categorias</a></li>
                            <li><a href="#eliminarCategoria">Eliminar categorias</a></li>
                            <li><a href="#modificarCategoria">Modificar categorias</a></li>
                            <li><a href="#buscarCategoria">Buscar categorias</a></li>
                        </ul>
                    </li>
                    <li><a href="">Marcas</a>
                        <ul>
                            <li><a href="#insertarMarca">Insertar marcas</a></li>
                            <li><a href="#eliminarMarca">Eliminar marcas</a></li>
                            <li><a href="#modificarMarca">Modificar marcas</a></li>
                            <li><a href="#buscarMarca">Buscar marcas</a></li>
                        </ul>
                    </li>
                    <li><a href="">Pedidos</a>
                        <ul>
                            <li><a href="#eliminarPedido">Eliminar pedido</a></li>
                            <li><a href="#modificarPedido">Modificar pedido</a></li>
                            <li><a href="#buscarPedido">Buscar pedido</a></li>
                        </ul>
                    </li>
                    <li><a href="" >Envios</a>
                        <ul class="envios">
                            <li><a href="#eliminarEnvio">Eliminar envio</a></li>
                            <li><a href="#modificarEnvio">Modificar envio</a></li>
                            <li><a href="#buscarEnvio">Buscar envio</a></li>
                        </ul>
                    </li>
                </ul>  
            <br/>
            <br/>
    
            <div class="cuerpo">
                <% Usuario usuario = (Usuario) session.getAttribute("usuario"); %>
                <h1> Bienvenido <%= usuario.getNombre() %></h1>
                <br/>
                <!--Formularios de usuarios-->
                <a name="insertarUsuario"></a>
                <div class="formulario">
                    <h2>Registrar un nuevo usuario</h2>
                    <form method="post" action="../usuariosPages/insertarUsuario.jsp">
                        <label>Dni:</label>  <input type="text" name="dni" placeholder="78548922L"><br>
                        <label>Nombre:</label> <input type="text" name="nombre" placeholder="Jose"><br>
                        <label>Apellidos:</label> <input type="text" name="apellidos" placeholder="Perez"><br>
                        <label>Correo:</label> <input type="text" name="email" placeholder="jose.perez@gmail.com"><br>
                        <label>Direccion:</label> <input type="text" name="direccion" placeholder="Urb. La cuesta 4"><br>
                        <label>Telefono</label><input type="text" name="telefono" placeholder="+34 629608152"><br>
                        <label>Pais</label><input type="text" name="pais" placeholder="España"><br>
                        <label>Codigo Posta:</label> <input type="text" name="codigoPostal" placeholder="38312"><br>
                        <label>Provincia</label><input type="text" name="provincia" placeholder="Santa Cruz de Tenerife"><br>
                        <label>Nombre Usuario:</label> <input type="text" name="nombreUsuario" placeholder="jose" required><br>
                        <label>Password:</label> <input type="text" name="password" placeholder="**********" required><br>
                        <div class="rol">
                            <label for="cliente">Cliente</label>
                            <input type="radio" id="cliente" name="rol" value="Cliente">
                            <label for="admin">Admin</label>
                            <input type="radio" id="admin" name="rol" value="Admin">      
                        </div>                        
                        <input type="submit" value="Insertar"> <br>
                    </form>
                </div>    
                <a name="eliminarUsuario"></a>
                <div class="formulario">
                    <h2>Eliminar un usuario</h2>
                    <form method="post" action="../usuariosPages/eliminarUsuario.jsp">
                        <label>Introduce el dni del usuario a eliminar: </label><input type="text" name="dni" placeholder="78648921P"><br>
                        <input type="submit" value="Eliminar"> <br>
                    </form>
                </div> 
                <a name="modificarUsuario"></a>
                <div class="formulario">
                    <h2>Modificar un usuario</h2>
                    <form method="post" action="../usuariosPages/modificarUsuario.jsp">
                        <label>Dni:</label>  <input type="text" name="dni" placeholder="78548922L"><br>
                        <label>Nombre:</label> <input type="text" name="nombre" placeholder="Jose"><br>
                        <label>Apellidos:</label> <input type="text" name="apellidos" placeholder="Perez"><br>
                        <label>Correo:</label> <input type="text" name="email" placeholder="jose.perez@gmail.com"><br>
                        <label>Direccion:</label> <input type="text" name="direccion" placeholder="Urb. La cuesta 4"><br>
                        <label>Telefono</label><input type="text" name="telefono" placeholder="+34 629608152"><br>
                        <label>Pais</label><input type="text" name="pais" placeholder="España"><br>
                        <label>Codigo Posta:</label> <input type="text" name="codigoPostal" placeholder="38312"><br>
                        <label>Provincia</label><input type="text" name="provincia" placeholder="Santa Cruz de Tenerife"><br>
                        <label>Nombre Usuario:</label> <input type="text" name="nombreUsuario" placeholder="jose" required><br>
                        <label>Password:</label> <input type="text" name="password" placeholder="**********" required><br>
                        <div class="rol">
                            <label for="cliente">Cliente</label>
                            <input type="radio" id="cliente" name="rol" value="Cliente">
                            <label for="admin">Admin</label>
                            <input type="radio" id="admin" name="rol" value="Admin">      
                        </div>                        
                        <input type="submit" value="Modificar"> <br>
                    </form>
                </div>  
                <a name="buscarUsuario"></a>
                <div class="formulario">
                    <h2>Buscar un usuario</h2>
                    <form method="post" action="../usuariosPages/buscarUsuario.jsp">
                        <label>Introduce el dni del usuario a buscar: </label><input type="text" name="dni" placeholder="78648921P"><br>
                        <input type="submit" value="Buscar"> <br>
                    </form>
                </div>

                <!--Formularios de productos-->
                <a name="insertarProducto"></a>
                <div class="formulario">
                    <h2>Registrar un nuevo producto</h2>
                    <form method="post" action="../productosPages/insertarProducto.jsp">
                        <label>Nombre:</label> <input type="text" name="nombreProducto" placeholder="ordenador gaming"><br>
                        <label>Categoria:</label>  <input type="text" name="idCategoria" placeholder="cat_pc"><br>
                        <label>Precio:</label> <input type="text" name="precio" placeholder="20,00"><br>  
                        <label>Descripcion:</label> <textarea id="descripcion" name="descripcion" rows="6" cols="40">Descripcion del producto</textarea><br>  
                        <label>Stock:</label> <input type="text" name="stock" placeholder="20"><br>    
                        <label>Marca:</label> <input type="text" name="idMarca" placeholder="mar_msi"><br>  
                        <input type="submit" value="Registrar"> <br>
                    </form>                    
                </div>    
                <a name="eliminarProducto"></a>
                <div class="formulario">
                    <h2>Eliminar un producto</h2>
                    <form method="post" action="../productosPages/eliminarProducto.jsp">
                        <label>Introduce el  id del producto a eliminar: </label><input type="text" name="idProducto" placeholder="pro_00000001"><br>
                        <input type="submit" value="Eliminar"> <br>
                    </form>
                </div> 
                <a name="modificarProducto"></a>
                <div class="formulario">
                    <h2>Modificar un producto</h2>
                    <form method="post" action="../productosPages/modificarProducto.jsp">
                        <label>idProducto:</label> <input type="text" name="idProducto" placeholder="pro_00000006"><br>
                        <label>Nombre:</label> <input type="text" name="nombreProducto" placeholder="ordenador gaming"><br>
                        <label>Categoria:</label>  <input type="text" name="idCategoria" placeholder="cat_pc"><br>
                        <label>Precio:</label> <input type="text" name="precio" placeholder="20,00"><br>  
                        <label>Descripcion:</label> <textarea id="descripcion" name="descripcion" rows="6" cols="40">Descripcion del producto</textarea><br>  
                        <label>Stock:</label> <input type="text" name="stock" placeholder="20"><br>    
                        <label>Marca:</label> <input type="text" name="idMarca" placeholder="mar_msi"><br>  
                        <input type="submit" value="Registrar"> <br>
                    </form> 
                </div>  
                <a name="buscarProductoNombre"></a>
                <div class="formulario">
                    <h2>Buscar un producto</h2>
                    <form method="post" action="../productosPages/buscarProducto.jsp">
                        <label>Introduce el nombre del producto a buscar:</label> <input type="text" name="nombre" placeholder="The Last of Us"><br>
                        <input type="submit" value="Buscar"> <br>
                    </form>
                </div> 
                <a name="buscarProductoCategoria"></a>
                <div class="formulario">
                    <h2>Buscar un producto</h2>
                    <form method="post" action="../productosPages/buscarProducto.jsp">
                        <label>Introduce la categoria del producto a buscar:</label> <input type="text" name="idCategoria" placeholder="cat_game"><br>
                        <input type="submit" value="Buscar"> <br>
                    </form>
                </div> 
                <a name="buscarProductoMarca"></a>
                <div class="formulario">
                    <h2>Buscar un producto</h2>
                    <form method="post" action="../productosPages/buscarProducto.jsp">
                        <label>Introduce la marca del producto a buscar:</label> <input type="text" name="idMarca" placeholder="mar_sony"><br>
                        <input type="submit" value="Buscar"> <br>
                    </form>
                </div> 

                <!--Formularios de categorias-->   
                <a name="insertarCategoria"></a>
                <div class="formulario">
                    <h2>Insertar una categoria</h2>
                    <form method="post" action="../categoriasPages/insertarCategoria.jsp">
                        <label>Nombre de la categoria:</label> <input type="text" name="nombreCategoria" placeholder="Videojuego"><br>
                        <input type="submit" value="Registrar"> <br>
                    </form>
                </div>    
                <a name="eliminarCategoria"></a>
                <div class="formulario">
                    <h2>Eliminar una categoria</h2>
                    <form method="post" action="../categoriasPages/eliminarCategoria.jsp">
                        <label>Introduce el id de la categoria a eliminar:</label> <input type="text" name="idCategoria" placeholder="cat_pc"><br>
                        <input type="submit" value="Eliminar"> <br>
                    </form>
                </div> 
                <a name="modificarCategoria"></a>
                <div class="formulario">
                    <h2>Modificar una categoria</h2>
                    <form method="post" action="../categoriasPages/modificarCategoria.jsp">
                        <label>Introduce el id de la categoria a modificar:</label> <input type="text" name="idCategoria" placeholder="cat_pc"><br>
                        <label>Nombre:</label> <input type="text" name="nombreCategoria" placeholder="Ordenadores"><br>
                        <input type="submit" value="Modificar"> <br>
                    </form>
                </div>  
                <a name="buscarCategoria"></a>
                <div class="formulario">
                    <h2>Buscar una categoria</h2>
                    <form method="post" action="../categoriasPages/buscarCategoria.jsp">
                        <label>Introduce el id de la categoria a buscar:</label> <input type="text" name="idCategoria" placeholder="cat_pc"><br>
                        <input type="submit" value="Buscar"><br>
                    </form>                        
                </div>  

                <!--Formularios de marcas-->       
                <a name="insertarMarca"></a>
                <div class="formulario">
                    <h2>Registrar una nueva marca</h2>
                    <form method="post" action="../marcasPages/insertarMarca.jsp">
                        <label>Nombre de la marca:</label> <input type="text" name="nombreMarca" placeholder="SONY"><br>
                        <input type="submit" value="Registrar"> <br>
                    </form>
                </div>    
                <a name="eliminarMarca"></a>
                <div class="formulario">
                    <h2>Eliminar una marca</h2>
                    <form method="post" action="../marcasPages/eliminarMarca.jsp">
                        <label>Introduce el id de la marca a eliminar:</label> <input type="text" name="idMarca" placeholder="mar_sony"><br>
                        <input type="submit" value="Eliminar"> <br>
                    </form>
                </div> 
                <a name="modificarMarca"></a>
                <div class="formulario">
                    <h2>Modificar una marca</h2>
                    <form method="post" action="../marcasPages/modificarMarca.jsp">
                        <label>Introduce el id de la marca a modificar:</label> <input type="text" name="idMarca" placeholder="mar_sony"><br>
                        <label>Nombre:</label> <input type="text" name="nombreMarca" placeholder="SONY"><br>
                        <input type="submit" value="Modificar"> <br>
                    </form>
                </div>  
                <a name="buscarMarca"></a>
                <div class="formulario">
                    <h2>Buscar una marca</h2>
                        <form method="post" action="../marcasPages/buscarMarca.jsp">
                        <label>Introduce el id de la marca a buscar:</label> <input type="text" name="idMarca" placeholder="mar_sony"><br>
                        <input type="submit" value="Buscar"><br>
                    </form>  
                </div> 

                <!--Formularios de pedidos-->   
                <a name="eliminarPedido"></a>
                <div class="formulario">
                    <h2>Eliminar un pedido</h2>
                    <form method="post" action="../pedidosPages/eliminarPedido.jsp">
                        <label>Introduce el id del pedido a eliminar:</label> <input type="text" name="idPedido" placeholder="ped_xxxxxxxx"><br>
                        <input type="submit" value="Eliminar"> <br>
                    </form>                
                </div> 
                <a name="modificarPedido"></a>
                <div class="formulario">
                    <h2>Modificar un pedido</h2>
                    <form method="post" action="../pedidosPages/modificarPedido.jsp">
                        <label>IdPedido:</label> <input type="text" name="idPedido" placeholder="ped_xxxxxxxx"><br>
                        <label>Unidades:</label> <input type="text" name="unidades" placeholder="2"><br>
                        <label>Total:</label> <input type="text" name="total" placeholder="20.50"><br>
                        <label>Fecha del pedido:</label> <input type="date" name="fechaPedido"><br>
                        <label>DNI del cliente:</label> <input type="text" name="dni" placeholder="78965432L"><br>
                        <label>Id del producto:</label> <input type="text" name="idProducto" placeholder="pro_00000001"><br>
                        <input type="submit" value="Modificar"> <br>
                    </form>
                </div>  
                <a name="buscarPedido"></a>
                <div class="formulario">
                    <h2>Buscar un pedido</h2>
                    <form method="post" action="../pedidosPages/buscarPedido.jsp">
                        <label>Introduce el id del pedido a buscar:</label> <input type="text" name="idPedido" placeholder="ped_xxxxxxxx"><br>
                        <input type="submit" value="Buscar"> <br>
                    </form>                 
                </div> 

                <!--Formularios de envios-->  
                <a name="eliminarEnvio"></a>
                <div class="formulario">
                    <h2>Eliminar un envio</h2>
                    <form method="post" action="../enviosPages/eliminarEnvio.jsp">
                        Introduce el id del envio a eliminar: <input type="text" name="idEnvio" placeholder="env_xxxxxxxx"><br>
                        <input type="submit" value="Eliminar"> <br>
                    </form>
                </div> 
                <a name="modificarEnvio"></a>
                <div class="formulario">
                    <h2>Modificar un envio</h2>   
                    <form method="post" action="../enviosPages/modificarEnvio.jsp">
                        Id del envio: <input type="text" name="idEnvio" placeholder="env_xxxxxxxx"><br>
                        Id del pedido: <input type="text" name="idPedido" placeholder="ped_xxxxxxxx"><br>
                        Fecha del envio: <input type="date" name="fechaEnvio" placeholder="2021-06-12"><br>
                        Estado <input type="text" name="estado" placeholder="Enviado"><br>
                        <input type="submit" value="Registrar"> <br>
                    </form>              

                </div>  
                <a name="buscarEnvio"></a>
                <div class="formulario">
                    <h2>Buscar un envio</h2>
                    <form method="post" action="../enviosPages/buscarEnvio.jsp">
                        Introduce el id del envio a buscar: <input type="text" name="idEnvio" placeholder="env_xxxxxxxx"><br>
                        <input type="submit" value="Buscar"> <br>
                    </form>
                </div> 
            </div>
            
        </div>   
    </body>
    <%@include file="../include/footer.jsp" %>
    </html>
