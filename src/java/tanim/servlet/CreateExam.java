/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanim.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tanim.db.DataAccess;
import tanim.model.Takes;

/**
 *
 * @author khair
 */
@WebServlet(name = "CreateExam", urlPatterns = {"/CreateExam"})
public class CreateExam extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        
        String id = (String)session.getAttribute("id");
        
        String course_id = (String)request.getParameter("course_id");
        String exam_time = (String)request.getParameter("exam_time");
        String exam_type = (String)request.getParameter("exam_type");
        String syllabus = (String)request.getParameter("syllabus");
        
        String msg;
        if(course_id.isEmpty()||exam_time.isEmpty())msg="Please fill in Course ID and Exam Time";
        else {
            DataAccess db = new DataAccess();
            
            ArrayList<Takes> takes=  db.getTakes(id);
            
            boolean exist=false;
            for(Takes take:takes)if(take.course_id.equals(course_id)){
                exist=true;break;
            }
            if(!exist)msg="Sorry , you aren't authorized to add exam for "+course_id;
            else {
                msg = db.createExams(course_id, exam_time, exam_type, syllabus);
                System.out.println("Result :"+msg);
            }
        }
        session.setAttribute("msg", msg);
        
        RequestDispatcher rd = request.getRequestDispatcher("exam.jsp");
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
