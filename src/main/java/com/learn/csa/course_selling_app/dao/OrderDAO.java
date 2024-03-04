package com.learn.csa.course_selling_app.dao;

import com.learn.csa.course_selling_app.entity.Course;
import com.learn.csa.course_selling_app.entity.Order;
import com.learn.csa.course_selling_app.helper.Connection_Provider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    public static boolean saveOrder(Order order) {

        boolean f = false;
        try {
            Connection con = Connection_Provider.getConnection();

            String sql = "INSERT INTO ORDERS(O_ID, order_on, o_amount, payment_id, status, student_id, course_Id) VALUES(?,FROM_UNIXTIME(?),?,?,?,?,?)";

            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, order.getO_ID());
            pstmt.setString(2, order.getOrder_on());
            pstmt.setString(3, order.getO_amount());
            pstmt.setString(4, order.getPayment_id());
            pstmt.setString(5, order.getStatus());
            pstmt.setInt(6, order.getStudent_id());
            pstmt.setInt(7, order.getCourse_Id());

            pstmt.executeUpdate();

            f = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;

    }
    
    
    public static List<Order> getAllOrders()
    {
        List<Order> orders=new ArrayList<>();//blank arraylist is created to return every course
        try{
            Connection con= Connection_Provider.getConnection();
            String sql="Select * from ORDERS";
            Statement statement=con.createStatement();//blank satatement returned
            ResultSet set= statement.executeQuery(sql);
            
            while(set.next())
            {
                Order order=new Order(set.getString(1),set.getString(2),set.getString(3),set.getString(4),set.getString(5),set.getString(6), set.getInt(7), set.getInt(8));
                orders.add(order);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return orders;//if no course and get into the database then size will be returned as 0
    }
    
    
    public static boolean updateTransactionDetailsonOrders(String orderId, String transactionId){
        boolean f = false;
        
        try {
            
            Connection con = Connection_Provider.getConnection();
            String query = "update orders set payment_id = ?, status = ? where o_id = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, transactionId);
            pstmt.setString(2, "Paid");
            pstmt.setString(3, orderId);

            pstmt.executeUpdate();

            f = true;
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return f;
        
        
        
        
    }
    

}
