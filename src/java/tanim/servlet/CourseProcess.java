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
import tanim.model.Registers;
import tanim.model.Takes;

/**
 *
 * @author khair
 */
@WebServlet(name = "CourseProcess", urlPatterns = {"/CourseProcess"})
public class CourseProcess extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        
        String id = (String)session.getAttribute("id");
        String userType = (String)session.getAttribute("userType");
        String cid= (String)request.getParameter("courseType");
        String registerType= (String)request.getParameter("registerType");
        
        DataAccess db= new DataAccess();
        if(registerType.equals("register")){
            if(userType.equals("student")){
                ArrayList<Registers> registers = db.getRegisters(id);
                int exist=0;
                for(Registers reg:registers)if(cid.equals(reg.course_id)) {exist=1;break;}

                if(exist==0){db.register(id, cid); System.out.println(id+ " registered in "+cid); }
                else System.out.println(id+ " already registered in "+cid);
            }
            else{
                ArrayList<Takes> takes = db.getTakes(id);
                int exist=0;
                for(Takes take:takes)if(cid.equals(take.course_id)) {exist=1;break;}

                if(exist==0){db.createTakes(id, cid); System.out.println(id+ " registered in "+cid); }
                else System.out.println(id+ " already registered in "+cid);
            }
        
        }
        else if(cid!=null||cid.isEmpty()==false){
            if(userType.equals("student"))db.unRegister(id, cid);
            else db.deleteTakes(id,cid);
        }
       
        
        RequestDispatcher rd = request.getRequestDispatcher("course.jsp");
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
