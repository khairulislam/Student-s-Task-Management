/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanim.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tanim.db.DataAccess;
import tanim.model.Attends;

/**
 *
 * @author khair
 */
@WebServlet(name = "MarksProcess", urlPatterns = {"/MarksProcess"})
public class MarksProcess extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession();
        String course_id = (String)session.getAttribute("course_id");
        String exam_time = (String)session.getAttribute("exam_time");
            
       DataAccess db = new DataAccess();
        ArrayList<Attends> attends = db.getAttends(course_id, exam_time);
       
        String studentRow[] = request.getParameterValues("studentRow");
        String marks[]=  request.getParameterValues("mark");
        
        if(studentRow!=null && attends !=null ){
            int i=0,count=0;
            for(Attends attend:attends){
                count++;
                if(parseInt(studentRow[i])==count){
                    //System.out.println(attend.student_id+" "+marks[i]);
                    db.addMarks(attend.student_id, marks[i], exam_time, course_id);
                    i++;
                    if(i==studentRow.length)break;
                }
            }
        }
        
        
        RequestDispatcher rd = request.getRequestDispatcher("mark.jsp");
        rd.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
