<%-- 
    Document   : books
    Created on : Dec 12, 2016, 3:18:34 PM
    Author     : khair
--%>

<%@page import="tanim.db.DataAccess"%>
<%@page import="java.util.ArrayList"%>
<%@page import="tanim.model.Reads"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body style="text-align: start;">
        <jsp:include page="navigation.jsp"/>
    
        <h1>Books page!</h1>
        <h2>Add a row </h2>
        
        <form method="post" action="CreateBook">
        <pre>
        Book name : <input type="text" name="book" />(Must not be blank )</br>
        From      : <input type="text" name="start" /></br>
        To        : <input type="text" name="end" /></br>
        
        </br><h2>This part is optional :</h2></br>
        Time      : <input type="text" name="date" /></br>
        <input type="submit" value="Sumbit" /></br>
            </pre>
        </form>
        <%
            String msg = (String)session.getAttribute("msg");
            if(!msg.isEmpty()){
                out.println(String.format("<h2>%s</h2>", msg));
                msg="";
            }
                        
            ArrayList<Reads> reads= new ArrayList<Reads>();
            DataAccess db = new DataAccess();
            String id = (String)session.getAttribute("id");
            reads = db.getReads(id);
            
            if(reads.isEmpty()){
                out.println("No data to show");
            }
            else {
                out.println("<form method=\"post\" action=\"DeleteBook\">");
                
                out.println("<table border=\"1\" style=\"text-align:center\">");
                out.println("<tr><td>Serial</td><td>Book name</td><td>Time</td><td>Start</td><td>End</td></tr>");
                int count=0;
                for(Reads r:reads){
                    count++;
                    out.println(String.format("<tr><td><input type=\"checkbox\" name=\"readsRow\" value=%d /></td>",count));
                    out.println(String.format("<td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>",r.book_name,r.entry_time,r.starting,r.ending));
                                        
                }
                out.println("</table>");
                out.println("<input type=\"submit\" value=\"Delete\"/>");
                out.println("</form>");
            }
            
         %>
         
    </body>
</html>
