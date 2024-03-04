
package com.learn.csa.course_selling_app.entity;


public class Course 
{
    private int c_id;
    private String c_name;
    private String c_fees;
    private int c_duration;
    private String c_details;
    private String c_img;
    private String c_starting;

    public Course() {
    }

    public Course(String c_name, String c_fees, int c_duration, String c_details,String c_img, String c_starting) {
        this.c_name = c_name;
        this.c_fees = c_fees;
        this.c_duration = c_duration;
        this.c_details = c_details;
        this.c_img = c_img;
        this.c_starting = c_starting;
    }

    public Course(int c_id, String c_name, String c_fees, int c_duration, String c_details, String c_img,String c_starting) {
        this.c_id = c_id;
        this.c_name = c_name;
        this.c_fees = c_fees;
        this.c_duration = c_duration;
        this.c_details = c_details;
        this.c_img = c_img;
        this.c_starting = c_starting;
    }

    public int getC_id() {
        return c_id;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public String getC_fees() {
        return c_fees;
    }

    public void setC_fees(String c_fees) {
        this.c_fees = c_fees;
    }

    public int getC_duration() {
        return c_duration;
    }

    public void setC_duration(int c_duration) {
        this.c_duration = c_duration;
    }

    public String getC_details() {
        return c_details;
    }

    public void setC_details(String c_details) {
        this.c_details = c_details;
    }
    
    public String getC_img() {
        return c_img;
    }

    public void setC_img(String c_img) {
        this.c_img = c_img;
    }

    public String getC_starting() {
        return c_starting;
    }

    public void setC_starting(String c_starting) {
        this.c_starting = c_starting;
    }

    @Override
    public String toString() {
        return "Course{" + "c_id=" + c_id + ", c_name=" + c_name + ", c_fees=" + c_fees + ", c_duration=" + c_duration + ", c_details=" + c_details + ", c_img=" + c_img + ", c_starting=" + c_starting + '}';
    }
    
    

    
    
}
