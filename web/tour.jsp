<%-- 
    Document   : tour
    Created on : Dec 12, 2016, 3:19:05 PM
    Author     : khair
--%>

<%@page import="tanim.model.Tour"%>
<%@page import="tanim.db.DataAccess"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <jsp:include page="navigation.jsp"/>
    
        <h1>Tour page!</h1>
        <h2>Add a new tour </h2>
        <form method="post" action="TourProcess">
        <pre>
        Place : <input type="text" name="book" />(Must not be blank )</br>
        Date  : <input type="text" name="start" /></br>
        
        <input type="submit" value="Add" /></br>
        </pre>
        </form>
        
        <%
            String msg = (String)session.getAttribute("msg");
            if(!msg.isEmpty()){
                out.println(String.format("<h2>%s</h2>", msg));
                msg="";
                session.setAttribute("msg",msg);
            }
            
            DataAccess db = new DataAccess();
            String id = (String)session.getAttribute("id");
            ArrayList<Tour> reads = db.getTours();
            
            if(reads.isEmpty()){
                out.println("No data to show");
            }
            else {
                out.println("<form method=\"post\" action=\"TourProcess\">");
                
                out.println("<table border=\"1\" style=\"text-align:center\">");
                out.println("<tr><td>Serial</td><td>User ID</td><td>Tour place</td><td>Date</td></tr>");
                int count=0;
                for(Tour r:reads){
                    count++;
                    out.println(String.format("<tr><td><input type=\"checkbox\" name=\"toursRow\" value=%d /></td>",count));
                    out.println(String.format("<td>%s</td><td>%s</td><td>%s</td></tr>",r.user_id,r.tour_place,r.tour_date));
                                        
                }
                out.println("</table></br>");
                out.println("<input type=\"submit\" value=\"Delete\"/>");
               out.println("</form>");
            }
            
         %>
    </body>
</html>
