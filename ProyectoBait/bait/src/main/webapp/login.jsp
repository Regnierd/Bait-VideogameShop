<%@page contentType="text/html" pageEncoding="UTF-8" errorPage = "include/error/showError.jsp" %>

    <!DOCTYPE html>
    <html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>menuPrincipal</title>
        <link rel="stylesheet" href="css/estilo.css">
        <link rel="stylesheet" href="css/login.css">
        <%@include file="include/header.jsp" %>
        <%@include file="include/spam.jsp" %>
    </head>
    <body>

        <div class="login-box">
            <img class="avatar" src="img/logotipoBait.png" alt="logo bait">
            <h1>Iniciar sesion</h1>
            <form method="post" action="action-login.jsp">
                <div class="rol">
                    <input type="radio" id="cliente" name="rol" value="Cliente">
                    <label for="cliente">Cliente</label><br>
                    <input type="radio" id="admin" name="rol" value="Admin">
                    <label for="admin">Admin</label><br>
                </div>
                <!--Nombre de usuario-->
                <label for="nombreUsuario">Nombre de usuario</label>
                <input type="text" placeholder="Nombre usuario">

                <!--Contraseña-->
                <label for="password">Contraseña</label>
                <input type="password" placeholder="Contraseña">

                <input type="submit" value="Entrar">

                <a href="registro.jsp">Registrarse</a><br/>
                <a href="#">Olvidó su Contraseña?</a>

            </form>
        </div>
    </body>
    <%@include file="include/footer.jsp" %>
    </html>
