<%@page import="es.iespuertodelacruz.bait.exceptions.ApiException"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true"%>
<html>
<head>
    <title>Error Message</title>
    <link rel="stylesheet" href="css/estilo.css">
</head>

<body>

<form action="" method="post" >
        <br><br>
        <table class="exception" width="90%" height="100px"
               style="  -moz-border-radius: 8px;  -webkit-border-radius: 8px;border-radius: 8px;border: 2px solid #467aa7;">
            <tr>
                <td style="color:#467aa7;">
                    <% 
                        ApiException e = (ApiException) exception;
                        String message = e.getMessage();
                    %>

                    <label><%=message %></label>
                </td>
            </tr>
        </table>
</form>
</body>
</html>