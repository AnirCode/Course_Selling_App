package com.learn.csa.course_selling_app.servlets;

import com.learn.csa.course_selling_app.dao.UserDAO;
import com.learn.csa.course_selling_app.entity.Student;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet(name = "registerServlet", urlPatterns = {"/registerServlet"})
public class RegisterServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            String userName = request.getParameter("name");
            String userEmail = request.getParameter("email");
            String userPwd = request.getParameter("pwd");
            String userRPwd = request.getParameter("rpwd");
            
            
            if(userPwd.equals(userRPwd)){
                //Save data into DB and redirect to login.jsp
                Student student = new Student();
                student.setS_Name(userName);
                student.setS_Email(userEmail);
                student.setS_Password(userPwd);
                
                if(UserDAO.registerUser(student)){
                    response.sendRedirect("login.jsp");
                }
                else{
                    response.sendRedirect("register.jsp");
                }
                
            }
            else
            {
                //redirect back to rgister.jsp
                response.sendRedirect("register.jsp");
            }
            
            
            
        }
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
