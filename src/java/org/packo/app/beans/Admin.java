/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.packo.app.beans;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class Admin {
    
    private int admin_id;
    private String admin_name;
    private Date date_birth;
    private String role;
    private int admin_priority;
    private String address;
    private String email;
    private String phone_number;
    private String admin_code;
    private String admin_password;
    private int residence_id;
    private Date registration_time;
    
    //constructor
    
    public Admin(){
            
    }
    
    //Accessors

    public int getAdmin_id() {
        return admin_id;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public Date getDate_birth() {
        return date_birth;
    }

    public String getRole() {
        return role;
    }

    public int getAdmin_priority() {
        return admin_priority;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getAdmin_code() {
        return admin_code;
    }

    public int getResidence_id() {
        return residence_id;
    }
    
    public String getAdmin_password(){
        return admin_password;
    }

    public Date getRegistration_time() {
        return registration_time;
    }
    
    //Mutators

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }

    public void setDate_birth(Date date_birth) {
        this.date_birth = date_birth;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setAdmin_priority(int admin_priority) {
        this.admin_priority = admin_priority;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setAdmin_code(String admin_code) {
        this.admin_code = admin_code;
    }

    public void setAdmin_password(String admin_password) {
        this.admin_password = admin_password;
    }

    public void setResidence_id(int residence_id) {
        this.residence_id = residence_id;
    }

    public void setRegistration_time(Date registration_time) {
        this.registration_time = registration_time;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }
    
    
    
    
    
    
}
