package org.apache.jsp.WEB_002dINF;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class Home_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("     ");

     String name = (String)session.getAttribute("name");
     String id = (String)session.getAttribute("id");
     String userType = (String)session.getAttribute("userType");
            
     out.println(String.format("<h1>Welcome to Home page %s</h1>",name));
     out.println("<form method=\"post\" action=\"HomeProcess\">");
     out.println("<input type=\"radio\" name=\"tableType\" value=\"exam\"/>Exam</br>");
     out.println("<input type=\"radio\" name=\"tableType\" value=\"course\"/>Course</br>");
     out.println("<input type=\"radio\" name=\"tableType\" value=\"contest\"/>Contest</br>");
     out.println("<input type=\"radio\" name=\"tableType\" value=\"tour\"/>Tour</br>");
     
     if(userType.equals("student")){
         out.println("<input type=\"radio\" name=\"tableType\" value=\"books\"/>Books</br>");
     out.println("<input type=\"radio\" name=\"tableType\" value=\"tution\"/>Tution</br>");
     out.println("<input type=\"radio\" name=\"tableType\" value=\"media\"/>Media</br>");    
     }
     
     
     out.println("</br><input type=\"submit\" value=\"Select\" />");
     
     
      out.write("\n");
      out.write("    </body>\n");
      out.write("    \n");
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
