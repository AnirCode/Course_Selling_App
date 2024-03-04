<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.learn.csa.course_selling_app.entity.Student"%>
<%@page import="com.learn.csa.course_selling_app.entity.Purchase"%>
<%@page import="com.learn.csa.course_selling_app.dao.CourseDAO"%>
<%@page import="com.learn.csa.course_selling_app.dao.PurchaseDAO"%>
<%@page import="com.learn.csa.course_selling_app.entity.Course"%>
<%@page import="java.util.List"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%
    Student loggedinUser = (Student) session.getAttribute("loggedUser");
    if(loggedinUser == null){
        response.sendRedirect("login.jsp");
    }
    else if(loggedinUser.getS_ID() == 1  ){
        response.sendRedirect("admin.jsp");
    }
    else
    {
        List<Purchase> purchases = PurchaseDAO.getAllPurchasesByUserId(loggedinUser.getS_ID());    
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home | CSA</title>


        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">

        <!--Font Awesome CDN-->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" integrity="sha512-..." crossorigin="anonymous" />

        <!-- Add icon library -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

        <style>
            .custom-color{
                background-color: #000000 !important;
            }


            .checked {
                color: orange;
            }

            body {
                min-height: 75rem;
                padding-top: 4.5rem;
            }
        </style>

    </head>
    <body>

        <!--Nav bar-->
        <%@include file="loggedinNav.jsp" %>
        <!--Nav bar Ends-->

        <!--Main Content-->
        <div class="container">
            <%@include file="courseContents.jsp" %>
            <!--Main Content End-->
        </div>



        <!--Purchase Modal-->
        <div class="modal fade" id="purchase-modal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-xl">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Your Purchases</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <table class="table" id="paymentTable">
                            <thead class="text-dark">
                                <tr>
                                    <th scope="col">Course Name</th>
                                    <th scope="col">Amount</th>
                                    <th scope="col">Payment Id</th>
                                    <th scope="col">Purchase Date</th>
                                    <th scope="col">Course Status</th>
                                    <th scope="col">Course Starting</th>
                                    <th scope="col">Executive Contact</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% for(Purchase p : purchases) {
                                
                                    Course c = CourseDAO.getCourseByCourseId(p.getCourseId());
                                    
                                    String cStartingDate = c.getC_starting();
                                    
                                    // Parse the input date string to a LocalDate object
                                    LocalDate startingDate = LocalDate.parse(cStartingDate, DateTimeFormatter.ISO_DATE);
                                    
                                    int cDuration = c.getC_duration();
                                    
                                    // Add the duration to the starting date
                                    LocalDate endDate = startingDate.plusDays(cDuration);
                                    
                                    //currentDate
                                    LocalDate currentDate = LocalDate.now();
                                    
                                    String status = "UPCOMING";
                                    
                                    if(currentDate.isBefore(startingDate)){
                                        status = "UPCOMING";
                                    }
                                    else if(currentDate.isAfter(endDate)){
                                        status = "COMPLETED";
                                    }
                                    else{
                                        status = "ONGOING";
                                    }
                                
                                %>
                                <tr>
                                    <td><%= c.getC_name() %></td>
                                    <td><%= p.getAmount() %></td>
                                    <td class="paymentIdCell" data-payment-id="<%= p.getPayment_id() %>"><%= p.getPayment_id() %></td>
                                    <td><%= p.getPurchaseDate()%></td>
                                    <td><%= status %></td>
                                    <td><%= c.getC_starting() %></td>
                                    <td><%= p.getExecutiveContact() %></td>
                                </tr>
                                <% } %>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <!--Purchase Modal End-->



    </body>


    <!-- Option 1: jQuery and Bootstrap Bundle (includes Popper) -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>


    <!--payment-id click event-->
    <script>
        document.addEventListener("DOMContentLoaded", function () {
            var paymentIdCells = document.querySelectorAll(".paymentIdCell");
            paymentIdCells.forEach(function (cell) {
                cell.addEventListener("click", function () {
                    // Retrieve the payment ID and other data from the clicked row
                    var paymentId = cell.getAttribute("data-payment-id");
                    var courseName = cell.parentNode.querySelector("td:first-child").textContent;
                    var amount = cell.parentNode.querySelector("td:nth-child(2)").textContent;
                    let purchaseDate = cell.parentNode.querySelector("td:nth-child(4)").textContent;
                    // Add more variables for other data as needed

                    // Now you can use the retrieved data in your JavaScript code
                    console.log("Payment ID: " + paymentId);
                    console.log("Course Name: " + courseName);
                    console.log("Amount: " + amount);
                    console.log("date: " + purchaseDate);
                    // You can perform further actions with the data here
                    const d = {
                        "paymentId": paymentId,
                        "courseName": courseName,
                        "amount": amount,
                        "purchaseDate": purchaseDate

                    };
                    /*
                     $.ajax({
                     url: "invoiceServlet",
                     type: "GET",
                     data:d,
                     success: function (data) {
                     // Handle the Razorpay API response data here
                     console.log(data);
                     },
                     error: function (error) {
                     console.error("Failed to retrieve receipt:", error);
                     }
                     });
                     */


                });
            });
        });
    </script>



</html>
<%}%>