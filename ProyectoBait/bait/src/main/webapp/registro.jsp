<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pagina de registro</title>
    <style>
        body {
            background-color: #2C4A76;
            text-align: center;
        }

        .cabecera {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: space-between;
            border-radius: 5px;
            background-color: #638CCA;
        }

        form > input {
            margin: 10px;
            padding:  10;;
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
</body>

</html>