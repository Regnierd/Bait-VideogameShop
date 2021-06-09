<%@ page errorPage = "include/error/showError.jsp" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pagina de verificacion de usuario</title>
        <link rel="stylesheet" href="css/estilo.css">
        <%@include file="include/header.jsp" %>
        <%@include file="include/spam.jsp" %>
    </head>
    <body>
        <div class="page">
            <h3>Datos para el registro</h3>
            <form method="post" action="registro-accion.jsp">
                Dni: <input type="text" name="dni" placeholder="78548922L"><br>
                Nombre: <input type="text" name="nombre" placeholder="Jose"><br>
                Apellidos: <input type="text" name="apellidos" placeholder="Perez"><br>
                Correo: <input type="text" name="correo" placeholder="jose.perez@gmail.com"><br>
                Direccion: <input type="text" name="direccion" placeholder="Urb. La cuesta 4"><br>
                Telefono<input type="text" name="telefono" placeholder="+34 629608152"><br>
                Pais<input type="text" name="pais" placeholder="España"><br>
                Codigo Posta: <input type="text" name="codigoPostal" placeholder="38312"><br>
                Provincia<input type="text" name="provincia" placeholder="Santa Cruz de Tenerife"><br>
                Nombre Usuario: <input type="text" name="nombreUsuario" placeholder="jose" required><br>
                Password: <input type="text" name="password" placeholder="**********" required><br>

                <input type="submit" value="Continuar"> <br>
            </form>
        </div>
        <%@include file="include/footer.jsp" %>
    </body>

</html>