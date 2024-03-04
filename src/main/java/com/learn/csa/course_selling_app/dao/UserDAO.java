package com.learn.csa.course_selling_app.dao;

import com.learn.csa.course_selling_app.entity.Student;
import com.learn.csa.course_selling_app.helper.Connection_Provider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

    public static boolean registerUser(Student student) {
        boolean f = false;
        try {
            Connection con = Connection_Provider.getConnection();
            String query = "INSERT INTO STUDENTS(S_NAME,S_EMAIL,S_PASSWORD) VALUES(?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, student.getS_Name());
            pstmt.setString(2, student.getS_Email());
            pstmt.setString(3, student.getS_Password());

            pstmt.executeUpdate();

            f = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;

    }
    
    public static Student loginUser(String userEmail, String userPwd){
        
        Student s = null;
        try
        {
            Connection con = Connection_Provider.getConnection();
            String sql = "SELECT * FROM STUDENTS WHERE S_EMAIL=? AND S_PASSWORD=?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, userEmail);
            pstmt.setString(2, userPwd);
            ResultSet set = pstmt.executeQuery();
            
            if(set.next()){
                s = new Student(set.getInt(1), set.getString(2), set.getString(3),set.getString(4),set.getString(5),set.getString(6),set.getString(7),set.getString(8));
            }
            
            
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return s;
    }
}
