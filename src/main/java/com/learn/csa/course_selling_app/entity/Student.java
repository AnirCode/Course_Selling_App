package com.learn.csa.course_selling_app.entity;

public class Student {
    private int s_ID;
    private String s_Name;
    private String s_Dept;
    private String s_PassingYear;
    private String s_College;
    private String s_Email;
    private String s_Password;
    private String s_Phone;

    public Student() {
    }

    public Student(String s_Name, String s_Dept, String s_PassingYear, String s_College, String s_Email, String s_Password, String s_Phone) {
        this.s_Name = s_Name;
        this.s_Dept = s_Dept;
        this.s_PassingYear = s_PassingYear;
        this.s_College = s_College;
        this.s_Email = s_Email;
        this.s_Password = s_Password;
        this.s_Phone = s_Phone;
    }

    public Student(int s_ID, String s_Name, String s_Dept, String s_PassingYear, String s_College, String s_Email, String s_Password, String s_Phone) {
        this.s_ID = s_ID;
        this.s_Name = s_Name;
        this.s_Dept = s_Dept;
        this.s_PassingYear = s_PassingYear;
        this.s_College = s_College;
        this.s_Email = s_Email;
        this.s_Password = s_Password;
        this.s_Phone = s_Phone;
    }

    public int getS_ID() {
        return s_ID;
    }

    public String getS_Name() {
        return s_Name;
    }

    public void setS_Name(String s_Name) {
        this.s_Name = s_Name;
    }

    public String getS_Dept() {
        return s_Dept;
    }

    public void setS_Dept(String s_Dept) {
        this.s_Dept = s_Dept;
    }

    public String getS_PassingYear() {
        return s_PassingYear;
    }

    public void setS_PassingYear(String s_PassingYear) {
        this.s_PassingYear = s_PassingYear;
    }

    public String getS_College() {
        return s_College;
    }

    public void setS_College(String s_College) {
        this.s_College = s_College;
    }

    public String getS_Email() {
        return s_Email;
    }

    public void setS_Email(String s_Email) {
        this.s_Email = s_Email;
    }

    public String getS_Password() {
        return s_Password;
    }

    public void setS_Password(String s_Password) {
        this.s_Password = s_Password;
    }

    public String getS_Phone() {
        return s_Phone;
    }

    public void setS_Phone(String s_Phone) {
        this.s_Phone = s_Phone;
    }

    @Override
    public String toString() {
        return "Student{" + "s_ID=" + s_ID + ", s_Name=" + s_Name + ", s_Dept=" + s_Dept + ", s_PassingYear=" + s_PassingYear + ", s_College=" + s_College + ", s_Email=" + s_Email + ", s_Password=" + s_Password + ", s_Phone=" + s_Phone + '}';
    }

    
    
   
    
}
