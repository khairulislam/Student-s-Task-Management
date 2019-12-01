<%-- 
    Document   : Home
    Created on : Dec 11, 2016, 5:06:12 PM
    Author     : khair
--%>

<%@page import="tanim.db.DataAccess"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <a href="logout.jsp">Log out</a>
     <%
     String name = (String)session.getAttribute("name");
     String id = (String)session.getAttribute("id");
     String userType = (String)session.getAttribute("userType");
            
     out.println(String.format("<h1>Welcome to Home page %s</h1>",name));
     out.println("<form method=\"post\" action=\"HomeProcess\">");
     out.println("<input type=\"radio\" name=\"tableType\" value=\"exam\"/>Exam</br>");
     out.println("<input type=\"radio\" name=\"tableType\" value=\"course\"/>Course</br>");
     out.println("<input type=\"radio\" name=\"tableType\" value=\"contest\"/>Contest</br>");
     out.println("<input type=\"radio\" name=\"tableType\" value=\"tour\"/>Tour</br>");
     
     if(userType.equals("student")){
         out.println("<input type=\"radio\" name=\"tableType\" value=\"books\"/>Books</br>");
     out.println("<input type=\"radio\" name=\"tableType\" value=\"tuition\"/>Tuition</br>");
     out.println("<input type=\"radio\" name=\"tableType\" value=\"media\"/>Media</br>");    
     }
     out.println("</br><input type=\"submit\" value=\"Select\" /></form></br>");
     
     
     out.println("<form method=\"post\" action=\"UpdateAccount\"><pre>");
     out.println("<h3>Your personal info</h3>");
     
     DataAccess db = new DataAccess();
     String s[] = db.existUser(id, userType);
     out.println(String.format("Your name    : <input type=\"text\" name=\"name\" value=%s /></br>",name ));
     if(userType.equals("student"))
         out.println(String.format("Semester     : <input type=\"text\" name=\"semester\" value=%s /></br>",s[3] ));
     out.println("New password : <input type=\"text\" name=\"password\"/></br>");
     out.println("</br><input type=\"submit\" value=\"Update\" /></pre></form>");
     
     %>
     
    </body>
    
</html>
