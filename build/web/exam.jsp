<%-- 
    Document   : exam
    Created on : Dec 12, 2016, 3:06:47 PM
    Author     : khair
--%>

<%@page import="tanim.model.Attends"%>
<%@page import="tanim.model.Takes"%>
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
            String userType = (String)session.getAttribute("userType");
            DataAccess db= new DataAccess(); 
            
            String msg = (String)session.getAttribute("msg");
            if(!msg.isEmpty()){
                out.println(String.format("<h2>%s</h2>", msg));
                msg="";
                session.setAttribute("msg",msg);
            }
            
            if(userType.equals("student")){
                out.println("<table border=\"1\" style=\"text-align:center\">");
                out.println("<h2>Exams on your registered courses</h2>");
                out.println("<tr><td>Course id</td><td>Exam Time</td><td>Exam Type</td><td>Syllabus</td><td>Marks</td></tr>");
               
               ArrayList<Registers> registers = db.getRegisters(id);
               
               if(registers==null||registers.isEmpty()){}
               else {
                   for(Registers r:registers){
                        ArrayList<Exam> exams = db.getExams(r.course_id);
                        for(Exam e:exams){
                        String marks= db.getMarksById(id, e.course_id, e.exam_time);
                        out.println(String.format("<tr><td>%s</td><td>%s</td>",e.course_id,e.exam_time));
                        out.println(String.format("<td>%s</td><td>%s</td><td>%s</td></tr>",e.exam_type,e.syllabus,marks));
                        }
                   
                    }   
               }      
                out.println("</table>");
            }

            else {
                
                out.println("<form method=\"post\" action=\"ExamProcess\">");
                out.println("<table border=\"1\" style=\"text-align:center\">");
                out.println("<h2>Exams on your registered courses</h2>");
                
                out.println("<tr><td></td><td>Course id</td><td>Exam Date</td><td>Exam Type</td><td>Syllabus</td></tr>");
               
               ArrayList<Takes> takes = db.getTakes(id);
               
               if(takes==null||takes.isEmpty()){}
               else {
                   int count=0;
                   for(Takes r:takes){
                        ArrayList<Exam> exams = db.getExams(r.course_id);
                        for(Exam e:exams){
                        count++;
                        out.println(String.format("<tr><td><input type=\"radio\" name=\"examRow\" value=%d /></td><td>%s</td>",count,e.course_id));
                        out.println(String.format("<td>%s</td><td>%s</td><td>%s</td></tr>",e.exam_time,e.exam_type,e.syllabus));
                        }
                    }   
               }      
                out.println("</table></br>");
                
                out.println("<input type=\"radio\" name=\"examType\" value=delete />Delete <input type=\"radio\" name=\"examType\" value=addMarks/>Add marks</br>");
                out.println("<input type=\"submit\" value=\"Submit\"/>");
                out.println("</form>");
                
               out.println("<h2>Add a row </h2>"); 
        
                out.println("<form method=\"post\" action=\"CreateExam\">");

                out.println("<pre>");

                out.println("Course ID : <input type=\"text\" name=\"course_id\" />(Must not be blank )</br>");
                out.println("Exam Time : <input type=\"text\" name=\"exam_time\" />(Must not be blank )</br>");

                out.println("</br><h2>This part is optional :</h2></br>");
                out.println("Exam Type : <input type=\"text\" name=\"exam_date\" /></br>");

                out.println("Syllabus  : <input type=\"text\" name=\"syllabus\" /></br>");
                out.println("<input type=\"submit\" value=\"Add\" /></br>");

                out.println("</pre></form>");
        
            }
               
                
         %>
     
    </body>
</html>
