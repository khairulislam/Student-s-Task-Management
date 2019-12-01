<%-- 
    Document   : course
    Created on : Dec 12, 2016, 3:08:31 PM
    Author     : khair
--%>

<%@page import="tanim.model.Course"%>
<%@page import="java.util.ArrayList"%>
<%@page import="tanim.model.Registers"%>
<%@page import="tanim.db.DataAccess"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Course page</title>
    </head>
    <body>
    <jsp:include page="navigation.jsp"/>
    
        <h1>Your registered courses</h1>
        <%
            String id = (String)session.getAttribute("id");
            DataAccess db= new DataAccess();    
            
            ArrayList<Course> courses = db.getCourses();
            out.println("<h2>Available Courses </h2>");
            
            if(courses==null || courses.isEmpty())out.println("<h2>No available course</h2>");
            else {
                out.println("<form method=\"post\" action=\"CourseProcess\">");
                for(Course c:courses)out.println(String.format("<input type=\"radio\" name=\"courseType\" value=\"%s\"/>%s : %s</br>",c.course_id,c.course_id ,c.course_name));
                
                out.println("</br><input type=\"radio\" name=\"registerType\" value=\"register\">Register ");
                out.println("<input type=\"radio\" name=\"registerType\" value=\"unregister\">Unregister </br>");
                
                out.println("</br><input type=\"submit\" value=\"Submit\" /></br></br>");
                out.println("</form>");
            }
            
             ArrayList<Registers> registers = db.getRegisters(id);
            if(registers==null || registers.isEmpty())out.println("<h2>You haven't registered any course yet</h2>");
            else {
                out.println("<table border=\"1\">");
                out.println("<h2>Your registered courses</h2>");
                out.println("<tr><td>Course id</td><td>Grade</td></tr>");
                
                for(Registers r:registers){
                    out.println(String.format("<tr><td>%s</td><td>%s</td></tr>",r.course_id,r.grade));                                                         
                }
                out.println("</table>");
            }
     
         %>
    </body>
</html>
