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
import tanim.model.Exam;
import tanim.model.Reads;
import tanim.model.Takes;

/**
 *
 * @author khair
 */
@WebServlet(name = "ExamProcess", urlPatterns = {"/ExamProcess"})
public class ExamProcess extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession();
        String id = (String)session.getAttribute("id");
        String examRow = request.getParameter("examRow");
        String examType = request.getParameter("examType");
        
        DataAccess db= new DataAccess();
        RequestDispatcher rd = request.getRequestDispatcher("exam.jsp");
         
        ArrayList<Takes> takes = db.getTakes(id);
        String msg="";
        
            boolean break2=false;
            if(takes!=null|| !takes.isEmpty()){
                int count=0;
                for(Takes take:takes){
                    ArrayList<Exam> exams = db.getExams(take.course_id);
                    for(Exam e:exams){
                        count++;
                        if(parseInt(examRow)==count){
                            if(examType.equals("delete")){
                                db.deleteExams(e.course_id,e.exam_time);
                                msg="Successfully deleted";
                            }
                            else {
                                session.setAttribute("course_id",e.course_id);
                                session.setAttribute("exam_time",e.exam_time);
                                rd = request.getRequestDispatcher("mark.jsp");
                            }
                            break2=true;
                            break;
                        }
                    }
                    if(break2)break;
                }
              }
       
        session.setAttribute("msg", msg);
        
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
