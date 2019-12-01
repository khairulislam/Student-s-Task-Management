package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import tanim.model.Participates;
import tanim.model.Contest;
import java.util.ArrayList;
import tanim.db.DataAccess;

public final class contest_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Contest Page</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("    <a href=\"logout.jsp\">Log out</a>\n");
      out.write("        \n");
      out.write("        <h1>Contest page </h1>\n");
      out.write("         ");

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
                out.println("<table border=\"1\">");
                out.println("<h2>All registered contests</h2>");
                out.println("<tr><td>Contest id</td><td>Contestant_id</td><td>Team_name</td><td>Result</td></tr>");
                
                for(Participates p: pars){
                    out.println(String.format("<tr><td>%s</td><td>%s</td><td>%s</td></tr>",p.contest_id,p.contestant_id,p.team_name,p.result));                                                         
                }
                out.println("</table>");
            }
            
           String msg = (String)session.getAttribute("msg"); 
           if(!msg.isEmpty())out.println(String.format("<h2>%s</h2>",msg));
     
         
      out.write("\n");
      out.write("         <form method=\"post\" action=\"RegisterContest\">\n");
      out.write("        <pre>\n");
      out.write("        Contest_id    : <input type=\"text\" name=\"contest_id\" />(Must not be blank )</br>\n");
      out.write("        Contestant id : <input type=\"text\" name=\"contestant_id\" />(Must not be blank)</br>\n");
      out.write("        Team name     : <input type=\"text\" name=\"team_name\" /> optional</br> \n");
      out.write("        Result        : <input type=\"text\" name=\"result\" /> optional</br> \n");
      out.write("        <h3> For non departmental contests : (optional)</h3>\n");
      out.write("        Contest site  : <input type=\"text\" name=\"site\" /></br> \n");
      out.write("        Contest Time  : <input type=\"text\" name=\"contest_time\" /></br> \n");
      out.write("       \n");
      out.write("        <input type=\"submit\" value=\"Register\" /></br>\n");
      out.write("            </pre>\n");
      out.write("        </form>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
