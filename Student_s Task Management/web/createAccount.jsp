<%-- 
    Document   : createAccount
    Created on : Dec 11, 2016, 8:15:53 AM
    Author     : khair
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body style="text-align: start">        
        <h1>Create a new account</h1>
        <form method="post" action="CreateAccount">
            <pre>
            Name     : <input type="text" name="name" />(20 letters at most)<br>
            Id       : <input type="text" name="id" />( Must be unique)<br/>
            Level    : <input type="text" name="level" />(empty , 1 or 2)<br/>
            Term     : <input type="text" name="term" />(empty ,1 or 2)<br/>
            Password : <input type="password" name="password" />(Don't keep empty)<br/>
            <input type="radio" name="userType" value="student"/>Student
            <input type="radio" name="userType" value="teacher"/>Teacher<br/>
            <input type="submit" value="Create" />
            </pre>
        </form>
    </body>
</html>
