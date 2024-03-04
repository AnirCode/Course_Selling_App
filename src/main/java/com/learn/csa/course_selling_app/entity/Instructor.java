package com.learn.csa.course_selling_app.entity;


public class Instructor {
    
    private int i_ID;
    private String i_Name;
    private int join_on;
    private String qualification;
    private String i_address;
    private String i_Phone;
    private String i_Email;
    private String i_Password;

    public Instructor() {
    }

    public Instructor(String i_Name, int join_on, String qualification, String i_address, String i_Phone, String i_Email, String i_Password) {
        this.i_Name = i_Name;
        this.join_on = join_on;
        this.qualification = qualification;
        this.i_address = i_address;
        this.i_Phone = i_Phone;
        this.i_Email = i_Email;
        this.i_Password = i_Password;
    }

    public Instructor(int i_ID, String i_Name, int join_on, String qualification, String i_address, String i_Phone, String i_Email, String i_Password) {
        this.i_ID = i_ID;
        this.i_Name = i_Name;
        this.join_on = join_on;
        this.qualification = qualification;
        this.i_address = i_address;
        this.i_Phone = i_Phone;
        this.i_Email = i_Email;
        this.i_Password = i_Password;
    }

    public int getI_ID() {
        return i_ID;
    }

    public String getI_Name() {
        return i_Name;
    }

    public void setI_Name(String i_Name) {
        this.i_Name = i_Name;
    }

    public int getJoin_on() {
        return join_on;
    }

    public void setJoin_on(int join_on) {
        this.join_on = join_on;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getI_address() {
        return i_address;
    }

    public void setI_address(String i_address) {
        this.i_address = i_address;
    }

    public String getI_Phone() {
        return i_Phone;
    }

    public void setI_Phone(String i_Phone) {
        this.i_Phone = i_Phone;
    }

    public String getI_Email() {
        return i_Email;
    }

    public void setI_Email(String i_Email) {
        this.i_Email = i_Email;
    }

    public String getI_Password() {
        return i_Password;
    }

    public void setI_Password(String i_Password) {
        this.i_Password = i_Password;
    }

    @Override
    public String toString() {
        return "Instructor{" + "i_ID=" + i_ID + ", i_Name=" + i_Name + ", join_on=" + join_on + ", qualification=" + qualification + ", i_address=" + i_address + ", i_Phone=" + i_Phone + ", i_Email=" + i_Email + ", i_Password=" + i_Password + '}';
    }

    
}
