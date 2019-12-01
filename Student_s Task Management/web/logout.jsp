<%-- 
    Document   : logout
    Created on : Dec 16, 2016, 12:09:16 PM
    Author     : khair
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Logout Page</title>
        <%
            session = request.getSession();
            session.setAttribute("id","");
            session.setAttribute("msg","");
            session.setAttribute("userType","");
            
        RequestDispatcher rd = request.getRequestDispatcher("index.html");
        rd.forward(request, response);
         %>
    </head>
    <body>
        <h1>Hello World!</h1>
    </body>
</html>
