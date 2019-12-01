<%-- 
    Document   : contest
    Created on : Dec 12, 2016, 3:10:08 PM
    Author     : khair
--%>

<%@page import="tanim.model.Arranges"%>
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
            String userType = (String)session.getAttribute("userType");
            String msg = (String)session.getAttribute("msg"); 
            
            if(!msg.isEmpty()){
                out.println(String.format("<h2>%s</h2>",msg));
                msg="";
                session.setAttribute("msg",msg);
            }
            
            DataAccess db= new DataAccess();    
            
            
            if(userType.equals("student")){
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
                    out.println("<form method=\"post\" action=\"UnRegisterContest\" ><table border=\"1\">");
                    out.println("<h2>All registered contests</h2>");
                    out.println("<tr><td></td><td>Contest id</td><td>Contestant_id</td><td>Team_name</td><td>Result</td></tr>");

                    int count=0;
                    for(Participates p: pars){
                        count++;
                        out.println(String.format("<tr><td><input type=\"radio\" name=\"contestRow\" value=%d /></td>",count));
                        out.println(String.format("<td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>",p.contest_id,p.contestant_id,p.team_name,p.result));                                                         
                    }
                    out.println("</table></br><input type=\"submit\" value=\"Unregister\" /></form>");
                }
                
                out.println("<form method=\"post\" action=\"RegisterContest\">");
                out.println("<pre>");
                out.println("<h2>Register to a contest : </h2>");
                out.println("Contest_id    : <input type=\"text\" name=\"contest_id\" />(Must not be blank )</br>");
                out.println("Contestant id : <input type=\"text\" name=\"contestant_id\" />(Must not be blank)</br>");
                out.println("Team name     : <input type=\"text\" name=\"team_name\" /> optional</br>");
                out.println("Result        : <input type=\"text\" name=\"result\" /> optional</br>");
                out.println("<h3> For non departmental contests : (optional)</h3>");
                out.println("Contest site  : <input type=\"text\" name=\"site\" /></br>");
                out.println("Contest Time  : <input type=\"text\" name=\"contest_time\" /></br> ");
                out.println("<input type=\"submit\" value=\"Register\" /></br>");
                out.println("</form>");
                
            }
            else {
                ArrayList<Arranges> arranges = db.getArranges(id);
                out.println("<h2>Departmental Contests arranged by you: </h2>");
             
                if(arranges==null || arranges.isEmpty())out.println("<h3>No contests to show</h3>");
                else {
                    out.println("<form method=\"post\" action=\"DeleteContest\">");
                    
                    out.println("<table border=\"1\">");
                    out.println("<tr><td></td><td>Contest id</td><td>Site</td><td>Contest Time</td></tr>");
                    
                    int count=0;
                    for(Arranges c:arranges){
                        count++;
                        Contest contest = db.getContestsById(c.contest_id,id);
                        out.println(String.format("<tr><td><input type=\"checkbox\" name=\"contestRow\" value=%d /></td>", count));
                        out.println(String.format("<td>%s</td><td>%s</td><td>%s</td></tr>",c.contest_id,contest.site,contest.contest_time));                                                         
                    }
                    out.println("</table></br>");
                    
                    out.println("<input type=\"submit\" value=\"Delete\"/>");
                    out.println("</form>");
                }
                
                out.println("<form method=\"post\" action=\"CreateContest\">");
                out.println("<pre>");
                out.print("<h2>Create a contest : </h2>");
                out.println("<h3>Please fill all the boxes bellow </h3>");
                out.println("Contest_id    : <input type=\"text\" name=\"contest_id\" /></br>");
                out.println("Contest site  : <input type=\"text\" name=\"site\" /></br>");
                out.println("Contest Time  : <input type=\"text\" name=\"contest_time\" /></br> ");
                out.println("<input type=\"submit\" value=\"Add\" /></br>");
                out.println("</form>");
            }
 
         %>
         
    </body>
</html>
