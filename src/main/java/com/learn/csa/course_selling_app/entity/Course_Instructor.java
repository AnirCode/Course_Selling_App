package com.learn.csa.course_selling_app.entity;


public class Course_Instructor {

 private int ci_ID;
    private int c_ID;
    private int i_ID;

    public Course_Instructor() {
    }

    public Course_Instructor(int c_ID, int i_ID) {
        this.c_ID = c_ID;
        this.i_ID = i_ID;
    }

    public Course_Instructor(int ci_ID, int c_ID, int i_ID) {
        this.ci_ID = ci_ID;
        this.c_ID = c_ID;
        this.i_ID = i_ID;
    }

    public int getCi_ID() {
        return ci_ID;
    }

    public int getC_ID() {
        return c_ID;
    }

    public void setC_ID(int c_ID) {
        this.c_ID = c_ID;
    }

    public int getI_ID() {
        return i_ID;
    }

    public void setI_ID(int i_ID) {
        this.i_ID = i_ID;
    }

    @Override
    public String toString() {
        return "Course_Instructor{" + "ci_ID=" + ci_ID + ", c_ID=" + c_ID + ", i_ID=" + i_ID + '}';
    }
    
 
    
    
}
