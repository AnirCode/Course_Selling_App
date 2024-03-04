
package com.learn.csa.course_selling_app.servlets;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.csa.course_selling_app.dao.OrderDAO;
import com.learn.csa.course_selling_app.entity.Student;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;



@WebServlet(name = "checkoutServlet", urlPatterns = {"/checkoutServlet"})
public class CheckoutServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            String data = request.getParameter("courses");
            //System.out.println("data");
            
            HttpSession httpSession = request.getSession();
            Student s = (Student) httpSession.getAttribute("loggedUser");


            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonArray = objectMapper.readTree(data);
            
            //Iterating each row..........
            
            int totalPrice= 0;  
            for (JsonNode row : jsonArray) {
                totalPrice+= row.get(2).asInt();
            }
            
            Order rOrder = null;
            try{
                
                RazorpayClient client = new RazorpayClient("rzp_test_ZnrDw2paOVbmsr", "9VqeUdcoz4uyiAffw23khxxN");
                JSONObject options = new JSONObject();
                options.put("amount", totalPrice*100);   //need to pass as Paisa
                options.put("currency", "INR");
                options.put("receipt", "txn_CSA_123456");
                rOrder = client.orders.create(options);
                
                for (JsonNode row : jsonArray) {
                    com.learn.csa.course_selling_app.entity.Order order = new com.learn.csa.course_selling_app.entity.Order(rOrder.toJson().get("id").toString(), rOrder.toJson().get("created_at").toString(), totalPrice+"", null, rOrder.toJson().get("status").toString(), null, s.getS_ID(), row.get(0).asInt());
                    
                    OrderDAO.saveOrder(order);
                }     
                
                out.println(rOrder);
                
            }
            catch(Exception e){
                e.printStackTrace();
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
