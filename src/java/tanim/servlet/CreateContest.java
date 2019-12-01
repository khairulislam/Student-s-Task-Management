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
import tanim.model.Arranges;

/**
 *
 * @author khair
 */
@WebServlet(name = "CreateContest", urlPatterns = {"/CreateContest"})
public class CreateContest extends HttpServlet {

     
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       response.setContentType("text/html;charset=UTF-8");
       HttpSession session = request.getSession();
       String id = (String)session.getAttribute("id");
        
       String contest_id = request.getParameter("contest_id");
       String site = request.getParameter("site");
       String contest_time = request.getParameter("contest_time");
       
       String msg="";
       if(contest_id.isEmpty()||contest_time.isEmpty()||site.isEmpty())msg="Please fill all boxes !";
       else {
           DataAccess db= new DataAccess();
           
           ArrayList<Arranges> arranges = db.getArranges(id);
           
           boolean exist=false;
           for(Arranges arrange:arranges)if(arrange.contest_id.equals(contest_id)){
               exist=true;
               break;
           }
           
           if(exist)msg = "Contest already included";
           else {
               if(db.createArranges(id, contest_id)==1&&db.createContests(id, contest_id, site, contest_time,"y")==1)msg="Contest successfully added .";
                else msg="Contest adding failed !";
           }
       }
       
       session.setAttribute("msg",msg);
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
