<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.learn.csa.course_selling_app.entity.Student"%>
<%@page import="com.learn.csa.course_selling_app.entity.Student"%>
<%@page import="com.learn.csa.course_selling_app.dao.CourseDAO"%>
<%@page import="com.learn.csa.course_selling_app.entity.Course"%>
<%@page import="java.util.List"%>
<%
    Student loggedinUser = (Student) session.getAttribute("loggedUser");
    if(loggedinUser == null){
        response.sendRedirect("login.jsp");
    }
    else if(loggedinUser.getS_ID() != 1  ){
        response.sendRedirect("home.jsp");
    }
    else{    
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Home | CSA</title>


        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">

        <!--Font Awesome CDN-->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" integrity="sha512-..." crossorigin="anonymous" />

    </head>
    <body>

        <!--Nav bar-->
        <%@include file="adminNav.jsp" %>
        <!--Nav bar Ends-->



        <!--Main Content-->
        <div class="container mt-2">
            <%@include file="courseContents.jsp" %>
        <!--Main Content End-->



        <!-- Add Course Modal -->
        <div class="modal fade" id="addCourseModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Add Course</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <form action="addCourseServlet" method="post" enctype="multipart/form-data">
                        <div class="modal-body">
                            <div class="row">
                                <div class="col-sm-12">
                                    <div class="form-group">
                                        <label for="exampleInputEmail1">Course Name</label>
                                        <input type="text" class="form-control" id="course_name" name="course_name" aria-describedby="emailHelp">
                                    </div>
                                    <div class="form-group">
                                        <label for="exampleInputPassword1">Fees</label>
                                        <input type="text" class="form-control" id="course_fees" name="course_fees">
                                    </div>
                                    <div class="form-group">
                                        <label for="exampleInputPassword1">Duration in days</label>
                                        <input type="text" class="form-control" id="course_duration" name="course_duration">
                                    </div>
                                    <div class="form-group">
                                        <label for="exampleInputPassword1">Starting On</label>
                                        <input type="date" class="form-control" id="course_starting" name="course_starting">
                                    </div>
                                    <div class="form-group">
                                        <label for="exampleInputPassword1">Course Details</label>
                                        <textarea class="form-control" rows="3" cols="3" id="course_description" name="course_description"></textarea>
                                    </div>
                                    <div class="form-group">
                                        <input id="course_pic" name="course_pic" type="file" class="btn btn-primary">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer d-flex justify-content-center">
                            <button type="submit" class="btn btn-success">Save</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- Add Course Modal End-->


    </body>


    <!-- Option 1: jQuery and Bootstrap Bundle (includes Popper) -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>
</html>
<%}%>