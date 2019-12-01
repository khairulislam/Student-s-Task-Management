<%-- 
    Document   : exam
    Created on : Dec 12, 2016, 3:06:47 PM
    Author     : khair
--%>

<%@page import="tanim.model.Registers"%>
<%@page import="tanim.model.Exam"%>
<%@page import="java.util.ArrayList"%>
<%@page import="tanim.db.DataAccess"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Exam Page</title>
    </head>
    <body>
    <jsp:include page="navigation.jsp"/>
    
        <h1>Exam page!</h1>
        <%
            String id = (String)session.getAttribute("id");
            DataAccess db= new DataAccess(); 
            
            
            out.println("<table border=\"1\" style=\"text-align:center\">");
            out.println("<h2>Exams on your registered courses</h2>");
            out.println("<tr><td>Course id</td><td>Exam Date</td><td>Exam Type</td><td>Syllabus</td></tr>");
               
               ArrayList<Registers> registers = db.getRegisters(id);
               
               if(registers==null||registers.isEmpty()){}
               else {
                   for(Registers r:registers){
                        ArrayList<Exam> exams = db.getExams(r.course_id);
                        for(Exam e:exams){
                        out.println(String.format("<tr><td>%s</td><td>%s</td>",e.course_id,e.exam_time));
                        out.println(String.format("<td>%s</td><td>%s</td></tr>",e.exam_type,e.syllabus));
                        }
                   
                    }   
               }
                         
                out.println("</table>");
         %>
    </body>
</html>
