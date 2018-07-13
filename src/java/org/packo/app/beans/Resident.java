/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.packo.app.beans;

/**
 *
 * @author ADMIN
 */
public class Resident {
    
    //instance variables
    
    private int id_user;
    private String name_resident;
    private String email;
    private String tel;
    private String apt_num;
    private boolean residency_status;
    private int residence_status;
    
    //public constructor
    
    public Resident(){
        
    }
    
    // getters

    public int getId_user() {
        return id_user;
    }

    public String getName_resident() {
        return name_resident;
    }

    public String getEmail() {
        return email;
    }

    public String getTel() {
        return tel;
    }

    public String getApt_num() {
        return apt_num;
    }

    public boolean isResidency_status() {
        return residency_status;
    }

    public int getResidence_status() {
        return residence_status;
    }
    
    // setters

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setName_resident(String name_resident) {
        this.name_resident = name_resident;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setApt_num(String apt_num) {
        this.apt_num = apt_num;
    }

    public void setResidency_status(boolean residency_status) {
        this.residency_status = residency_status;
    }

    public void setResidence_status(int residence_status) {
        this.residence_status = residence_status;
    }
    
    
    
}
