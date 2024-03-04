
package com.learn.csa.course_selling_app.servlets;

import com.learn.csa.course_selling_app.dao.CourseDAO;
import com.learn.csa.course_selling_app.entity.Course;
import com.learn.csa.course_selling_app.helper.Utilities;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;

@MultipartConfig
@WebServlet(name = "addCourseServlet", urlPatterns = {"/addCourseServlet"})
public class AddCourseServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            
            String course_name = request.getParameter("course_name");
            String course_fees = request.getParameter("course_fees");
            int course_duration = Integer.parseInt(request.getParameter("course_duration"));
            String course_description = request.getParameter("course_description");
            String course_starting = request.getParameter("course_starting");
            
            Part part = request.getPart("course_pic");
            String pic_name = part.getSubmittedFileName();
            

            String path = "D:\\Documents\\course_selling_app\\src\\main\\webapp\\course_pics"+File.separator+pic_name;
            System.out.println(path);
            
            
            
            Course course = new Course(course_name, course_fees, course_duration, course_description, pic_name, course_starting);
            
            if(CourseDAO.saveCourse(course))
            {
               // Course Details saved on DB now save the file to the "course_pics" folder
                
                
                if(Utilities.saveFile(part.getInputStream(), path)){
                    //File Got save into the "course_pics" folder
                    response.sendRedirect("admin.jsp");
                }
                else
                {
//                    File couldn't save into the "course_pics" folder
                }
                
            }
            else
            {
                //Course Details couldn't be saved on DB
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
