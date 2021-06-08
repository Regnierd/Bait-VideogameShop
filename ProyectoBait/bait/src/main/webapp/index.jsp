<%@page contentType="text/html" pageEncoding="UTF-8" errorPage = "include/error/showError.jsp" %>

    <!DOCTYPE html>
    <html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>menuPrincipal</title>
        <link rel="stylesheet" href="css/estilo.css">
        <%@include file="include/header.jsp" %>
        <%@include file="include/spam.jsp" %>
    </head>
    <body>
        <div class="page" id="page">
            <h1>Menu Principal</h1>
            <p>Seleccion que rol quieres iniciar sesion</p>
    
            <form method="post" action="action-login.jsp">
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
        
    </body>
    <%@include file="include/footer.jsp" %>
    </html>