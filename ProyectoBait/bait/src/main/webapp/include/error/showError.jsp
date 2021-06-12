<%@page import="es.iespuertodelacruz.bait.exceptions.ApiException"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Message</title>
        <link rel="stylesheet" href="css/estilo.css">
        
        <%@include file="../header.jsp" %>
        <%@include file="../spam.jsp" %>
    </head>

    <body>

        <form action="" method="post" >
                <br><br>
                <table class="exception">
                    <tr>
                        <td>
                            <% 
                                ApiException e = (ApiException) exception;
                                String message = e.getMessage();
                            %>

                            <label><%=message %></label>
                        </td>
                    </tr>
                </table>
        </form>
        <%@include file="../footer.jsp" %>
    </body>
    
</html>