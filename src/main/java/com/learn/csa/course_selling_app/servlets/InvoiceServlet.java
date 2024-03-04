package com.learn.csa.course_selling_app.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.FileInputStream;
import java.io.FileOutputStream;

@WebServlet(name = "invoiceServlet", urlPatterns = {"/invoiceServlet"})
public class InvoiceServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String paymentId = request.getParameter("paymentId");
            String courseName = request.getParameter("courseName");
            String amount = request.getParameter("amount");
            String purchaseDate = request.getParameter("purchaseDate");

            String inputfile = "D:\\jsp_servlet_projects\\course_selling_app\\src\\main\\webapp\\invoice_template\\invoice_template.docx";
            String outputfile = "D:\\jsp_servlet_projects\\course_selling_app\\src\\main\\webapp\\invoices\\invoice_" + paymentId + ".docx";

            // Load Word template
            FileInputStream templateFile = new FileInputStream(inputfile);
            XWPFDocument doc = new XWPFDocument(templateFile);

            // Replace static words with dynamic values
            for (XWPFParagraph paragraph : doc.getParagraphs()) {
                StringBuilder paragraphText = new StringBuilder();

                for (XWPFRun run : paragraph.getRuns()) {
                    paragraphText.append(run.getText(0));
                }

                String text = paragraphText.toString();
                if (text.contains("PAYMENT_ID")) {
                    text = text.replace("PAYMENTID", paymentId);
                }
                if (text.contains("DATE")) {
                    text = text.replace("DATE", purchaseDate);
                }
                if (text.contains("ITEM_NAME")) {
                    text = text.replace("ITEMNAME", courseName);
                }
                if (text.contains("PRICE")) {
                    text = text.replace("PRICE", amount);
                }

                for (int i = 0; i < paragraph.getRuns().size(); i++) {
                    paragraph.getRuns().get(i).setText(i == 0 ? text : "", 0);
                }
            }

            // Save the modified document
            FileOutputStream outputStream = new FileOutputStream(outputfile);
            doc.write(outputStream);
            outputStream.close();

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
