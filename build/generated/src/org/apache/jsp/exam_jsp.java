package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import tanim.model.Takes;
import tanim.model.Registers;
import tanim.model.Exam;
import java.util.ArrayList;
import tanim.db.DataAccess;

public final class exam_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("        <title>Exam Page</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("    ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "navigation.jsp", out, false);
      out.write("\n");
      out.write("    \n");
      out.write("        <h1>Exam page!</h1>\n");
      out.write("        ");

            String id = (String)session.getAttribute("id");
            String userType = (String)session.getAttribute("userType");
            DataAccess db= new DataAccess(); 
            
            String msg = (String)session.getAttribute("msg");
            if(!msg.isEmpty()){
                out.println(String.format("<h2>%s</h2>", msg));
                msg="";
                session.setAttribute("msg",msg);
            }
            
            if(userType.equals("student")){
                out.println("<table border=\"1\" style=\"text-align:center\">");
                out.println("<h2>Exams on your registered courses</h2>");
                out.println("<tr><td>Course id</td><td>Exam Date</td><td>Exam Type</td><td>Syllabus</td></tr>");
               
               ArrayList<Registers> registers = db.getRegisters(id);
               
               if(registers==null||registers.isEmpty()){}
               else {
                   for(Registers r:registers){
                        ArrayList<Exam> exams = db.getExams(r.course_id);
                        for(Exam e:exams){
                        out.println(String.format("<tr><td>%s</td><td>%s</td>",e.course_id,e.exam_time));
                        out.println(String.format("<td>%s</td><td>%s</td></tr>",e.exam_type,e.syllabus));
                        }
                   
                    }   
               }      
                out.println("</table>");
            }
            else {
                
                out.println("<form method=\"post\" action=\"ExamProcess\">");
                out.println("<table border=\"1\" style=\"text-align:center\">");
                out.println("<h2>Exams on your registered courses</h2>");
                
                out.println("<tr><td></td><td>Course id</td><td>Exam Date</td><td>Exam Type</td><td>Syllabus</td></tr>");
               
               ArrayList<Takes> takes = db.getTakes(id);
               
               if(takes==null||takes.isEmpty()){}
               else {
                   int count=0;
                   for(Takes r:takes){
                        ArrayList<Exam> exams = db.getExams(r.course_id);
                        for(Exam e:exams){
                        count++;
                        out.println(String.format("<tr><td><input type=\"radio\" name=\"examRow\" value=%d /></td><td>%s</td>",count,e.course_id));
                        out.println(String.format("<td>%s</td><td>%s</td><td>%s</td></tr>",e.exam_time,e.exam_type,e.syllabus));
                        }
                    }   
               }      
                out.println("</table></br>");
                
                out.println("<input type=\"radio\" name=\"examType\" value=delete />Delete <input type=\"radio\" name=\"examType\" value=addMarks/>Add marks</br>");
                out.println("<input type=\"submit\" value=\"Submit\"/>");
                out.println("</form>");
                
               out.println("<h2>Add a row </h2>"); 
        
                out.println("<form method=\"post\" action=\"CreateExam\">");

                out.println("<pre>");

                out.println("Course ID : <input type=\"text\" name=\"course_id\" />(Must not be blank )</br>");
                out.println("Exam Time : <input type=\"text\" name=\"exam_time\" />(Must not be blank )</br>");

                out.println("</br><h2>This part is optional :</h2></br>");
                out.println("Exam Type : <input type=\"text\" name=\"exam_date\" /></br>");

                out.println("Syllabus  : <input type=\"text\" name=\"syllabus\" /></br>");
                out.println("<input type=\"submit\" value=\"Add\" /></br>");

                out.println("</pre></form>");
        
            }
               
                
         
      out.write("\n");
      out.write("     \n");
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
