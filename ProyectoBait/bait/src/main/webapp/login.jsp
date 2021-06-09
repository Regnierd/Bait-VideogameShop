<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>InicioSesion</title>
    <link rel="stylesheet" href="css/estilo.css">
    <%@include file="include/header.jsp" %>
    <%@include file="include/spam.jsp" %>
</head>
<body>
    <div class="page">
        <h1>Inicio Sesion</h1>
        <p>Seleccion que rol quieres iniciar sesion</p>

        <form method="post" action="login/action-login.jsp">
            <div class="rol">
                <input type="radio" id="cliente" name="rol" value="Cliente">
                <label for="cliente">Cliente</label><br>
                <input type="radio" id="admin" name="rol" value="Admin">
                <label for="admin">Admin</label><br>
            </div>
            <div class="login">
                <input type="text" name="nombreUsuario" placeholder="Username" required>
                <input type="password" name="password" placeholder="Password" required>
            </div>
            <input type="submit" value="Continuar"> <br>
        </form>

        <div class="bottom-container">
            <div class="row">
                <div class="col">
                    <a href="registro.jsp" style="color:black" class="btn">Registrarse</a>
                </div>
                <div class="col">
                    <a href="#" style="color:black" class="btn">olvido su Contraseña?</a>
                </div>
            </div>
        </div>
    </div>
    <%@include file="include/footer.jsp" %>
</body>
</html>