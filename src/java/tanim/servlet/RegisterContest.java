/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanim.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tanim.db.DataAccess;

/**
 *
 * @author khair
 */
@WebServlet(name = "RegisterContest", urlPatterns = {"/RegisterContest"})
public class RegisterContest extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
       String contest_id = request.getParameter("contest_id");
       String contestant_id = request.getParameter("contestant_id");
       String team_name = request.getParameter("team_name");
       String result = request.getParameter("result");
       
       String site = request.getParameter("site");
       String contest_time = request.getParameter("contest_time");
       
       HttpSession session = request.getSession();
       
       String id = (String)session.getAttribute("id");
       String msg="";
       
       if(contest_id.isEmpty()||contestant_id.isEmpty()){
           msg= "Please fill the contest and contestant id carefully !";
       }
       else{
           DataAccess db= new DataAccess();
           
           db.createContests(id, contest_id, site, contest_time,"");
           
           if(db.createParticipates(id, contest_id, contestant_id, result, team_name)==1)
               msg="Successfully added";
           else msg="Adding failed .";
       }
       
        session.setAttribute("msg", msg);
        RequestDispatcher rd = request.getRequestDispatcher("contest.jsp");
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
