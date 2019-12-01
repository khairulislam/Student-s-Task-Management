package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import tanim.model.Takes;
import tanim.model.Course;
import java.util.ArrayList;
import tanim.model.Registers;
import tanim.db.DataAccess;

public final class course_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Course page</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("    ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "navigation.jsp", out, false);
      out.write("\n");
      out.write("    \n");
      out.write("        <h1>Your registered courses</h1>\n");
      out.write("        ");

            String id = (String)session.getAttribute("id");
            String userType = (String)session.getAttribute("userType");
            DataAccess db= new DataAccess();    
            
            ArrayList<Course> courses = db.getCourses();
            out.println("<h2>Available Courses </h2>");
            
            if(courses==null || courses.isEmpty())out.println("<h2>No available course</h2>");
            else {
                out.println("<form method=\"post\" action=\"CourseProcess\">");
                for(Course c:courses)out.println(String.format("<input type=\"radio\" name=\"courseType\" value=\"%s\"/>%s : %s</br>",c.course_id,c.course_id ,c.course_name));
                
                out.println("</br><input type=\"radio\" name=\"registerType\" value=\"register\">Register ");
                out.println("<input type=\"radio\" name=\"registerType\" value=\"unregister\">Unregister </br>");
                
                out.println("</br><input type=\"submit\" value=\"Submit\" /></br></br>");
                out.println("</form>");
            }
            
            if(userType.equals("student")) {
                ArrayList<Registers> registers = db.getRegisters(id);
                if(registers==null || registers.isEmpty())out.println("<h2>You aren't registered to any course yet</h2>");
                else {
                    out.println("<table border=\"1\">");
                    out.println("<h2>Your registered courses</h2>");
                    out.println("<tr><td>Course id</td><td>Grade</td></tr>");
                
                    for(Registers r:registers){
                        out.println(String.format("<tr><td>%s</td><td>%s</td></tr>",r.course_id,r.grade));                                                         
                    }
                    out.println("</table>");
                }
            }
            else {
                ArrayList<Takes> takes = db.getTakes(id);
                if(takes==null || takes.isEmpty())out.println("<h2>You aren't taking any course yet</h2>");
                else {
                    out.println("<table border=\"1\">");
                    out.println("<h2>Your registered courses</h2>");
                    out.println("<tr><td>Course id</td></tr>");
                
                    for(Takes r:takes){
                        out.println(String.format("<tr><td>%s</td></tr>",r.course_id));                                                         
                    }
                    out.println("</table>");
                }
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
