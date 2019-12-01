<%-- 
    Document   : contest
    Created on : Dec 12, 2016, 3:10:08 PM
    Author     : khair
--%>

<%@page import="tanim.model.Participates"%>
<%@page import="tanim.model.Contest"%>
<%@page import="java.util.ArrayList"%>
<%@page import="tanim.db.DataAccess"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Contest Page</title>
    </head>
    <body>
    <jsp:include page="navigation.jsp"/>
        
        <h1>Contest page </h1>
         <%
            String id = (String)session.getAttribute("id");
            DataAccess db= new DataAccess();    
            
            ArrayList<Contest> contests = db.getContests();
            out.println("<h2>Available Departmental Contests </h2>");
             
            if(contests==null || contests.isEmpty())out.println("<h3>No upcoming contests</h3>");
            else {
                out.println("<table border=\"1\">");
                out.println("<tr><td>Contest id</td><td>Site</td><td>Contest Time</td></tr>");
                
                for(Contest c:contests){
                    out.println(String.format("<tr><td>%s</td><td>%s</td><td>%s</td></tr>",c.contest_id,c.site,c.contest_time));                                                         
                }
                out.println("</table>");
            }
            
            ArrayList<Participates> pars = db.getParticipates(id);
            out.println("<h2>Registered Contests </h2>");
             
            if(pars==null || pars.isEmpty())out.println("<h3>No registered contests</h3>");
            else {
                out.println("<form><table border=\"1\">");
                out.println("<h2>All registered contests</h2>");
                out.println("<tr><td></td><td>Contest id</td><td>Contestant_id</td><td>Team_name</td><td>Result</td></tr>");
                
                int count=0;
                for(Participates p: pars){
                    count++;
                    out.println(String.format("<tr><td><input type=\"checkbox\" name=\"readsRow\" value=%d /></td>",count));
                    out.println(String.format("<td>%s</td><td>%s</td><td>%s</td></tr>",p.contest_id,p.contestant_id,p.team_name,p.result));                                                         
                }
                out.println("</table></br><input type=\"submit\" value=\"Unregister\" /></form>");
            }
            
           String msg = (String)session.getAttribute("msg"); 
           if(!msg.isEmpty())out.println(String.format("<h2>%s</h2>",msg));
     
         %>
         <form method="post" action="RegisterContest">
        <pre>
        <h2>Register to a contest : </h2>
        Contest_id    : <input type="text" name="contest_id" />(Must not be blank )</br>
        Contestant id : <input type="text" name="contestant_id" />(Must not be blank)</br>
        Team name     : <input type="text" name="team_name" /> optional</br> 
        Result        : <input type="text" name="result" /> optional</br> 
        <h3> For non departmental contests : (optional)</h3>
        Contest site  : <input type="text" name="site" /></br> 
        Contest Time  : <input type="text" name="contest_time" /></br> 
       
        <input type="submit" value="Register" /></br>
            </pre>
        </form>
    </body>
</html>
