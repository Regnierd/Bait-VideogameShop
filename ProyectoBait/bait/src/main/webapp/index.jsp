<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>menuPrincipal</title>
        <style>
            body {
                background-color: #2C4A76;
            }

            .cabecera {
                display: flex;
                flex-direction: row;
                align-items: center;
                justify-content: space-between;
                border-radius: 5px;
                background-color:#638CCA;
            }

            .page {
                text-align: center;
                margin-left: 500px;
                margin-right: 500px;

            }

            .rol{
                display: flex;
                justify-content: center;
                align-items: center;
                flex-direction: row;
                margin: 10px;
            }

            .login {
                display: flex;
                justify-content: center;
                align-items: center;
                flex-direction: column;
                margin: 10px;
            }

            .login > input {
                width: 120px;
                margin: 5px;
                border-radius: 5px;
                margin: 5px;
            }

            .row{
                margin: 10px;
                display: flex;
                flex-direction: row;
                align-items: center;
                justify-content: center;
            }

            .row > div{
                margin: 10px;
            }
        </style>
    </head>

    <body>
        <div class="cabecera" id="cabecera">
            <h1>BAIT</h1>
            <div class="divBusqueda" id="divBusqueda">
                <input type="text" id="barraBusqueda" placeholder="¿Qué buscas?" />
                <input type="image" src="http://assets.stickpng.com/images/59cfc4d2d3b1936210a5ddc7.png" alt="Submit"
                    width="15px" height="15px"></input>
            </div>
        </div>
        <br>
        <div class="page" id="page">
            <h1>Menu Principal</h1>
            <p>Seleccion que rol quieres iniciar sesion</p>
    
            <form method="post" action="action-page.jsp">
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

    </html>