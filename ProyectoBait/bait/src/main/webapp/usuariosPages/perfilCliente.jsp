<%@ page import='es.iespuertodelacruz.bait.api.personas.Usuario' errorPage = "../include/error/showError.jsp" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pagina de verificacion de usuario</title>
        <link rel="stylesheet" href="../css/estilo.css">
        <link rel="stylesheet" href="../css/menuAdmin.css">
        <link rel="stylesheet" href="../css/perfilCliente.css">
        <%@include file="../include/header.jsp" %>
        <%@include file="../include/spam.jsp" %>
        
    </head>
    <body>
        <div class="page"> 
            <div class="cuerpo">
                <jsp:useBean id="usuarioController" class="es.iespuertodelacruz.bait.controlador.personasController.UsuarioController" />
                
                <h2>Perfil: </h2>
                <% Usuario usuario = (Usuario) session.getAttribute("usuario"); %>                 
                <table>
                    <tr>
                        <td>Dni</td>
                        <td><%= usuario.getDni()%></td>
                    </tr>
                    <tr>
                        <td>Nombre</td>
                        <td><%= usuario.getNombre()%></td>
                    </tr>
                    <tr>
                        <td>Apellidos</td>
                        <td><%= usuario.getApellidos()%></td>
                    </tr>
                    <tr>
                        <td>Email</td>
                        <td><%= usuario.getEmail()%></td>
                    </tr>
                    <tr>
                        <td>Direccion</td>
                        <td><%= usuario.getDireccion()%></td>
                    </tr>
                    <tr>
                        <td>Telefono</td>
                        <td><%= usuario.getTelefono()%></td>
                    </tr>
                    <tr>
                        <td>Pais</td>
                        <td><%= usuario.getPais()%></td>
                    </tr>
                    <tr>
                        <td>Codigo Postal</td>
                        <td><%= usuario.getCodigoPostal()%></td>
                    </tr>
                    <tr>
                        <td>Provincia</td>
                        <td><%= usuario.getProvincia()%></td>
                    </tr>
                    <tr>
                        <td>Nombre Usuario</td>
                        <td><%= usuario.getNombreUsuario()%></td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td><%= usuario.getPassword()%></td>
                    </tr>
                    <tr>
                        <td>Rol</td>
                        <td><%= usuario.getRol()%></td>
                    </tr>
                    <tr>
                        <td>Saldo</td>
                        <td><%= usuario.getSaldo()%></td>
                    </tr>
                </table>
                <br/>
                <a class="modificar" href="#modificarPerfil">Modificar perfil</a>
                <a name="modificarPerfil"></a>
                <div class="formulario">
                    <form method="post" action="../registro/registro-accion.jsp">
                        <label>Dni:</label>  <input type="text" name="dni" placeholder="78548922L"><br>
                        <label>Nombre:</label> <input type="text" name="nombre" placeholder="Jose"><br>
                        <label>Apellidos:</label> <input type="text" name="apellidos" placeholder="Perez"><br>
                        <label>Correo:</label> <input type="text" name="correo" placeholder="jose.perez@gmail.com"><br>
                        <label>Direccion:</label> <input type="text" name="direccion" placeholder="Urb. La cuesta 4"><br>
                        <label>Telefono</label><input type="text" name="telefono" placeholder="+34 629608152"><br>
                        <label>Pais</label><input type="text" name="pais" placeholder="España"><br>
                        <label>Codigo Posta:</label> <input type="text" name="codigoPostal" placeholder="38312"><br>
                        <label>Provincia</label><input type="text" name="provincia" placeholder="Santa Cruz de Tenerife"><br>
                        <label>Nombre Usuario:</label> <input type="text" name="nombreUsuario" placeholder="jose" required><br>
                        <label>Password:</label> <input type="text" name="password" placeholder="**********" required><br>
            
                        <input type="submit" value="Guardar"> <br>
                    </form>
                </div>
                
            </div>        
        </div>    
    </body>
    <%@include file="../include/footer.jsp" %>
</html>