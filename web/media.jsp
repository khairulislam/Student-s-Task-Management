<%-- 
    Document   : media
    Created on : Dec 12, 2016, 3:08:22 PM
    Author     : khair
--%>

<%@page import="tanim.model.Watches"%>
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
    
        <h1>Watches page!</h1>
        <h2>Add a row </h2>
        
        <form method="post" action="MediaProcess">
        <pre>
        Watched   : <input type="text" name="media_name" />(Must not be blank )</br>
        Time         : <input type="text" name="watch_date" /></br>
                
        </br><h2>This part is optional :</h2></br>
        Type         : <input type="text" name="media_type" /></br>
        Genre        : <input type="text" name="genre" /></br>
        Release date : <input type="text" name="release" /></br>
        <input type="submit" value="Sumbit" /></br>
            </pre>
        </form>
        
        <%
            String msg = (String)session.getAttribute("msg");
            if(!msg.isEmpty()){
                out.println(String.format("<h2>%s</h2>", msg));
                msg="";
                session.setAttribute("msg",msg);
            }
            
            ArrayList<Watches> watches= new ArrayList<Watches>();
            DataAccess db = new DataAccess();
            String id = (String)session.getAttribute("id");
            watches = db.getWatches(id);
            
            if(watches.isEmpty()){
                out.println("No data to show");
            }
            else {
                out.println("<form method=\"post\" action=\"MediaProcess\">");
                
                out.println("<table border=\"1\" style=\"text-align:center\">");
                out.println("<tr><td>Serial</td><td>Media name</td><td>Watch Date</td></tr>");
                int count=0;
                for(Watches w:watches){
                    count++;
                    out.println(String.format("<tr><td><input type=\"checkbox\" name=\"watchesRow\" value=%d /></td>",count));
                    out.println(String.format("<td>%s</td><td>%s</td></tr>",w.media_name,w.watch_date));
                                        
                }
                out.println("</table>");
                out.println("<input type=\"submit\" value=\"Delete\"/>");
                out.println("</form>");
            }
            
          %>
    </body>
</html>
