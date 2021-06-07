<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import='es.iespuertodelacruz.bait.api.personas.Usuario' %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pagina de verificacion de usuario</title>
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
        </style>
    </head>

    <body>
        <div class="cabecera" id="cabecera">
            <h1>BAIT</h1>
            <div class="divBusqueda" id="divBusqueda">
                <input type="text" id="barraBusqueda" placeholder="¿Qué buscas?" />
                <input type="image" src="http://assets.stickpng.com/images/59cfc4d2d3b1936210a5ddc7.png" alt="Submit"
                    width="15px" height="15px"/>
            </div>
        </div>
        <h1>Pagina del usuario</h1>

        <jsp:useBean id="controller" class="es.iespuertodelacruz.bait.controlador.personasController.UsuarioController" />

        <% String dni = request.getParameter("dni"); %>
        <% String nombre = request.getParameter("nombre"); %>
        <% String apellidos = request.getParameter("apellidos"); %>
        <% String correo = request.getParameter("correo"); %>
        <% String direccion = request.getParameter("direccion"); %>
        <% String telefono = request.getParameter("telefono"); %>
        <% String pais = request.getParameter("pais"); %>
        <% String codigoPostal = request.getParameter("codigoPostal"); %>
        <% String provincia = request.getParameter("provincia"); %>
        <% String nombreUsuario = request.getParameter("nombreUsuario"); %>
        <% String password = request.getParameter("password"); %>
        <% Usuario usuario = new Usuario(dni, nombre, apellidos, correo, direccion, telefono, pais, codigoPostal, provincia, nombreUsuario, password, "Cliente", 0f); %>
        <% controller.insertar(usuario); %>
        <% Usuario usuarioRegistrado = controller.buscar(dni); %>
        <table style>
            <tr>
                <th>DNI</th>
                <th>Nombre</th>
                <th>Apellidos</th>
                <th>Correo</th>
                <th>Direccion</th>
                <th>Telefono</th>
                <th>Pais</th>
                <th>Codigo Postal</th>
                <th>Provincia</th>
                <th>User</th>
                <th>Password</th>
                <th>Rol</th>
                <th>Saldo</th>
            </tr>
            <tr>
                <td><%= usuarioRegistrado.getDni()%></td>
                <td><%= usuarioRegistrado.getNombre()%></td>
                <td><%= usuarioRegistrado.getApellidos()%></td>
                <td><%= usuarioRegistrado.getEmail()%></td>
                <td><%= usuarioRegistrado.getDireccion()%></td>
                <td><%= usuarioRegistrado.getTelefono()%></td>
                <td><%= usuarioRegistrado.getPais()%></td>
                <td><%= usuarioRegistrado.getCodigoPostal()%></td>
                <td><%= usuarioRegistrado.getProvincia()%></td>
                <td><%= usuarioRegistrado.getNombreUsuario()%></td>
                <td><%= usuarioRegistrado.getPassword()%></td>
                <td><%= usuarioRegistrado.getRol()%></td>
                <td><%= usuarioRegistrado.getSaldo()%></td>
            </tr>
        </table>
    </body>

    </html>