<%-- 
    Document   : logout
    Created on : Sep 10, 2016, 5:56:47 PM
    Author     : DHRUV
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            session.removeAttribute("a_id");
            response.sendRedirect("../login.jsp");
        %>
    </body>
</html>
