package com.learn.csa.course_selling_app.dao;

import com.learn.csa.course_selling_app.entity.Course;
import com.learn.csa.course_selling_app.helper.Connection_Provider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class CourseDAO 
{
    
    public static boolean saveCourse(Course course){
        
        boolean f = false;
        try
        {
            Connection con = Connection_Provider.getConnection();
            
            String sql = "INSERT INTO courses (C_NAME,C_FEES,C_DURATION,C_DETAILS,C_IMG,C_STARTING) VALUES(?,?,?,?,?,?)";
            
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, course.getC_name());
            pstmt.setString(2, course.getC_fees());
            pstmt.setInt(3, course.getC_duration());
            pstmt.setString(4, course.getC_details());
            pstmt.setString(5, course.getC_img());
            pstmt.setString(6, course.getC_starting());
            
            pstmt.executeUpdate();
            
            f=true;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return f;
        
    }
    
    
    public static List<Course> getAllCourses()
    {
        List<Course> courses=new ArrayList<>();//blank arraylist is created to return every course
        try{
            Connection con= Connection_Provider.getConnection();
            String sql="Select * from courses";
            Statement statement=con.createStatement();//blank satatement returned
            ResultSet set= statement.executeQuery(sql);
            
            while(set.next())
            {
                Course course=new Course(set.getInt(1),set.getString(2),set.getString(3),set.getInt(4),set.getString(5),set.getString(6), set.getString(7));
                courses.add(course);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return courses;//if no course and get into the database then size will be returned as 0
    }
    
    
    public static Course getCourseByCourseId(int id){
        Course course = null;
        
        try{
            Connection con= Connection_Provider.getConnection();
            String sql="Select * from courses where c_id = "+id;
            Statement statement=con.createStatement();//blank satatement returned
            ResultSet set= statement.executeQuery(sql);
            
            if(set.next())
            {
                course=new Course(set.getInt(1),set.getString(2),set.getString(3),set.getInt(4),set.getString(5),set.getString(6), set.getString(7));
                
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return course;
        
    }
    
    
}
