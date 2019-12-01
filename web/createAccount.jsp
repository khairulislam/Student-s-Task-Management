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
        <a href="login.jsp" >Already have an account ? Go to log in page."</a>
        <h1>Create a new account</h1>
        <form method="post" action="CreateAccount">
            <pre>
            <h2>Students must fill all the boxes bellow :</h2>
            Name     : <input type="text" name="name" />(20 letters at most)<br>
            Id       : <input type="text" name="id" />( Must be unique)<br/>
            Password : <input type="password" name="password" />(Don't keep empty)<br/>
            <h3>Not applicable for teachers :</h3>
            Level    : <input type="text" name="level" />(1 or 2)<br/>
            Term     : <input type="text" name="term" />(1 or 2)<br/>
            <input type="radio" name="userType" value="student"/>Student
            <input type="radio" name="userType" value="teacher"/>Teacher<br/>
            <input type="submit" value="Create" />
            </pre>
        </form>
        <%
            String msg = (String)session.getAttribute("msg");
            if(msg!=null && !msg.isEmpty()){
                out.println("<h3>"+msg+"</h3>");
                session.setAttribute("msg", "");
            }
            %>   
    </body>
</html>
