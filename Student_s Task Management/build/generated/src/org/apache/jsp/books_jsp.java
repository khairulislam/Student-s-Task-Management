package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import tanim.db.DataAccess;
import java.util.ArrayList;
import tanim.model.Reads;

public final class books_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("    <body style=\"text-align: start;\">\n");
      out.write("        <h1>Books page!</h1>\n");
      out.write("        <h2>Add a row </h2>\n");
      out.write("        \n");
      out.write("        <form method=\"post\" action=\"CreateBook\">\n");
      out.write("       \n");
      out.write("       <input type=\"radio\" name=\"opType\" value=\"add\"/>Add\n");
      out.write("       <input type=\"radio\" name=\"opType\" value=\"delete\"/>Delete</br>\n");
      out.write("        <pre>\n");
      out.write("        Book name : <input type=\"text\" name=\"book\" />(Must not be blank )</br>\n");
      out.write("        From      : <input type=\"text\" name=\"start\" /></br>\n");
      out.write("        To        : <input type=\"text\" name=\"end\" /></br>\n");
      out.write("        \n");
      out.write("        </br><h2>This part is optional :</h2></br>\n");
      out.write("        Date      :<input type=\"text\" name=\"date\" />dd/mon/yyyy</br>\n");
      out.write("        <input type=\"submit\" value=\"Sumbit\" /></br>\n");
      out.write("            </pre>\n");
      out.write("        </form>\n");
      out.write("        ");

            String msg = (String)session.getAttribute("msg");
            if(!msg.isEmpty())out.println(String.format("<h2>%s</h2>", msg));
                        
            ArrayList<Reads> reads= new ArrayList<Reads>();
            DataAccess db = new DataAccess();
            String id = (String)session.getAttribute("id");
            reads = db.getReads(id);
            
            if(reads.isEmpty()){
                out.println("No data to show");
            }
            else {
                out.println("<table border=\"1\" style=\"text-align:center\">");
                out.println("<tr><td>Serial</td><td>Book name</td><td>Date</td><td>Start</td><td>End</td></tr>");
                int count=0;
                for(Reads r:reads){
                    count++;
                    out.println(String.format("<tr><td>%d</td><td>%s</td>",count,r.book_name));
                    out.println(String.format("<td>%s</td><td>%s</td><td>%s</td></tr>",r.entry_date,r.starting,r.ending));
                                        
                }
                out.println("</table>");
            }
            
            
         
      out.write("\n");
      out.write("         <form>\n");
      out.write("Male <input type=\"checkbox\" name=\"sex\" value=\"male\" /> </br>\n");
      out.write("Female <input type=\"checkbox\" name=\"sex\" value=\"female\" /> </br>\n");
      out.write("shemale <input type=\"checkbox\" name=\"sex\" value=\"shemale\" /> </br>\n");
      out.write("<input type=\"submit\" value=\"Submit\"/>\n");
      out.write("</form>\n");
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
