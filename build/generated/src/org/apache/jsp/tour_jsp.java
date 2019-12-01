package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import tanim.model.Tour;
import tanim.db.DataAccess;
import java.util.ArrayList;

public final class tour_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("    ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "navigation.jsp", out, false);
      out.write("\n");
      out.write("    \n");
      out.write("        <h1>Tour page!</h1>\n");
      out.write("        <h2>Add a new tour </h2>\n");
      out.write("        <form>\n");
      out.write("        <pre>\n");
      out.write("        Place : <input type=\"text\" name=\"book\" />(Must not be blank )</br>\n");
      out.write("        Date  : <input type=\"text\" name=\"start\" /></br>\n");
      out.write("        \n");
      out.write("        <input type=\"submit\" value=\"Add\" /></br>\n");
      out.write("        </pre>\n");
      out.write("        </form>\n");
      out.write("        \n");
      out.write("        ");

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
                out.println("<form method=\"post\" action=\"CreateTour\">>");
                
                out.println("<table border=\"1\" style=\"text-align:center\">");
                out.println("<tr><td>Serial</td><td>User ID</td><td>Tour place</td><td>Date</td></tr>");
                int count=0;
                for(Tour r:reads){
                    count++;
                    out.println(String.format("<tr><td><input type=\"checkbox\" name=\"toursRow\" value=%d /></td>",count));
                    out.println(String.format("<td>%s</td><td>%s</td><td>%s</td></tr>",r.user_id,r.tour_place,r.tour_date));
                                        
                }
                out.println("</table>");
                out.println("<input type=\"submit\" value=\"Delete\"/>");
               out.println("</form>");
            }
            
         
      out.write("\n");
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
