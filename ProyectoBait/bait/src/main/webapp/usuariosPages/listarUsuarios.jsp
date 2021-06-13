<% ArrayList<Usuario> usuarios = usuarioController.obtenerListado(); %>
<% for(Usuario usuarioLista : usuarios){ %>  
    <table>
        <tr>
            <td>Dni</td>
            <td><%= usuarioLista.getDni()%></td>
        </tr>
        <tr>
            <td>Nombre</td>
            <td><%= usuarioLista.getNombre()%></td>
        </tr>
        <tr>
            <td>Apellidos</td>
            <td><%= usuarioLista.getApellidos()%></td>
        </tr>
        <tr>
            <td>Email</td>
            <td><%= usuarioLista.getEmail()%></td>
        </tr>
        <tr>
            <td>Dirección</td>
            <td><%= usuarioLista.getDireccion()%></td>
        </tr>
        <tr>
            <td>Telefono</td>
            <td><%= usuarioLista.getTelefono()%></td>
        </tr>
        <tr>
            <td>Pais</td>
            <td><%= usuarioLista.getPais()%></td>
        </tr>
        <tr>
            <td>Codigo Postal</td>
            <td><%= usuarioLista.getCodigoPostal()%></td>
        </tr>
        <tr>
            <td>Provincia</td>
            <td><%= usuarioLista.getProvincia()%></td>
        </tr>
        <tr>
            <td>Nombre Usuario</td>
            <td><%= usuarioLista.getNombreUsuario()%></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><%= usuarioLista.getPassword()%></td>
        </tr>
        <tr>
            <td>Rol</td>
            <td><%= usuarioLista.getRol()%></td>
        </tr>
        <tr>
            <td>Saldo</td>
            <td><%= usuarioLista.getSaldo()%></td>
        </tr>
    </table>
<% } %>
           
 