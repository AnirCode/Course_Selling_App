
package com.learn.csa.course_selling_app.dao;

import com.learn.csa.course_selling_app.entity.Purchase;
import com.learn.csa.course_selling_app.helper.Connection_Provider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class PurchaseDAO {
    
    public static boolean savePurchase(String orderId) {

        boolean f = false;
        try {
            Connection con = Connection_Provider.getConnection();

            String sql = "INSERT INTO PURCHASES(purchaseDate, amount, payment_id, student_id, course_Id) SELECT lastUpdatedAt, o_amount, payment_id, student_id, course_id FROM ORDERS WHERE O_ID = ?";

            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, orderId);
            
            pstmt.executeUpdate();

            f = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;

    }
    
    
    public static List<Purchase> getAllPurchases()
    {
        List<Purchase> orders=new ArrayList<>();//blank arraylist is created to return every course
        try{
            Connection con= Connection_Provider.getConnection();
            String sql="Select * from Purchases";
            Statement statement=con.createStatement();//blank satatement returned
            ResultSet set= statement.executeQuery(sql);
            
            while(set.next())
            {
                Purchase purchase=new Purchase(set.getInt(1),set.getString(2),set.getString(3),set.getString(4), set.getInt(5), set.getInt(6),set.getString(7));
                orders.add(purchase);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return orders;//if no course and get into the database then size will be returned as 0
    }
    
    public static List<Purchase> getAllPurchasesByUserId(int userId)
    {
        List<Purchase> orders=new ArrayList<>();//blank arraylist is created to return every course
        try{
            Connection con= Connection_Provider.getConnection();
            String sql="Select * from Purchases where student_id="+userId;
            Statement statement=con.createStatement();//blank satatement returned
            ResultSet set= statement.executeQuery(sql);
            
            while(set.next())
            {
                Purchase purchase=new Purchase(set.getInt(1),set.getString(2),set.getString(3),set.getString(4), set.getInt(5), set.getInt(6), set.getString(7));
                orders.add(purchase);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return orders;//if no course and get into the database then size will be returned as 0
    }
    
}
