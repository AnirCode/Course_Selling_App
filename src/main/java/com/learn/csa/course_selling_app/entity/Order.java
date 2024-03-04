package com.learn.csa.course_selling_app.entity;

public class Order {
    private String o_ID;
    private String order_on;
    private String o_amount;
    private String payment_id;
    private String status;
    private String lastUpdatedAt = null;
    private int student_id;
    private int course_Id;
    
    
    public Order() {
    }

    public Order(String order_on, String o_amount, String payment_id, String status, String lastUpdatedAt, int student_id, int course_Id) {
        this.order_on = order_on;
        this.o_amount = o_amount;
        this.payment_id = payment_id;
        this.status = status;
        this.lastUpdatedAt = lastUpdatedAt;
        this.student_id = student_id;
        this.course_Id = course_Id;
        
    }

    public Order(String o_ID, String order_on, String o_amount, String payment_id, String status, String lastUpdatedAt, int student_id, int course_Id) {
        this.o_ID = o_ID;
        this.order_on = order_on;
        this.o_amount = o_amount;
        this.payment_id = payment_id;
        this.status = status;
        this.lastUpdatedAt = lastUpdatedAt;
        this.student_id = student_id;
        this.course_Id = course_Id;
    }

    public String getO_ID() {
        return o_ID;
    }

    public void setO_ID(String o_ID) {
        this.o_ID = o_ID;
    }

    public String getOrder_on() {
        return order_on;
    }

    public void setOrder_on(String order_on) {
        this.order_on = order_on;
    }

    public String getO_amount() {
        return o_amount;
    }

    public void setO_amount(String o_amount) {
        this.o_amount = o_amount;
    }

    public String getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(String payment_id) {
        this.payment_id = payment_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(String lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getCourse_Id() {
        return course_Id;
    }

    public void setCourse_Id(int course_Id) {
        this.course_Id = course_Id;
    }

    @Override
    public String toString() {
        return "Order{" + "o_ID=" + o_ID + ", order_on=" + order_on + ", o_amount=" + o_amount + ", payment_id=" + payment_id + ", status=" + status + ", lastUpdatedAt=" + lastUpdatedAt + ", student_id=" + student_id + ", course_Id=" + course_Id + '}';
    }
    
    

    
    
    

   

    
    
    

   

}