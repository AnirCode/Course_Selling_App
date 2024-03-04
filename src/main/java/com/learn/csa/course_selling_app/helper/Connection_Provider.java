package com.learn.csa.course_selling_app.helper;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connection_Provider {
    
     private static Connection con=null;
    
    public static Connection getConnection()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String username="root";
            String pwd="8372038023";
            String url="jdbc:mysql://localhost:3306/CSA";
            
            con = DriverManager.getConnection(url, username, pwd);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return con;
    }
    
}
