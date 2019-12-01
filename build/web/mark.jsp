<%-- 
    Document   : mark
    Created on : Dec 17, 2016, 12:39:37 PM
    Author     : khair
--%>

<%@page import="tanim.model.Attends"%>
<%@page import="java.util.ArrayList"%>
<%@page import="tanim.db.DataAccess"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body style="text-align: start;">
        <jsp:include page="navigation.jsp"/>
        <h1>Marks Page!</h1>
        <%            
            String course_id = (String)session.getAttribute("course_id");
            String exam_time = (String)session.getAttribute("exam_time");
            
            DataAccess db = new DataAccess();
            ArrayList<Attends> attends = db.getAttends(course_id, exam_time);
            
            if(attends==null || attends.isEmpty())out.println("<h2>No registered students</h2>");
            else {
                out.println("<form method=\"post\" action=\"MarksProcess\">");
                
                out.println("<table border=\"1\" style=\"text-align:center\">");
                out.println("<h2>Marks Sheet</h2>");
                out.println("<tr><td>Select</td><td>Student Id</td><td>Marks obtains</td><td>Add Marks</td></tr>");
                
                int count=0;
                for(Attends a:attends){
                        count++;
                        out.println(String.format("<tr><td><input type=\"checkbox\" name=\"studentRow\" value=\"%d\" /></td>",count));
                        out.println(String.format("<td>%s</td><td>%s</td>",a.student_id,a.marks));
                        out.println(String.format("<td><input type=\"text\" name=\"mark\"/></td></tr>"));                                                         
                    }
                    out.println("</table></br>");
                    
                out.println("<input type=\"submit\" value=\"Submit\"/>");
                out.println("</form>");
            }

        %>
    </body>
</html>
