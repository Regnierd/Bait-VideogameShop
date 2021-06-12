<%@ page errorPage = "../include/error/showError.jsp" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pagina de verificacion de usuario</title>
        <link rel="stylesheet" href="../css/estilo.css">
        <link rel="stylesheet" href="../css/menuAdmin.css">
        <%@include file="../include/header.jsp" %>
        <%@include file="../include/spam.jsp" %>
        
    </head>
    <body>
        <div class="page">     
            <div class="cuerpo">
                <h2>Datos para el registro</h2>
                <form method="post" action="registro-accion.jsp">
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
        
                    <input type="submit" value="Continuar"> <br>
                </form>
            </div>        
        </div>    
    </body>
    <%@include file="../include/footer.jsp" %>
</html>