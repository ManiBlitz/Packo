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
public class ConnectionLog {
    
    //Instance variables
    
    private int tentative_id;
    private String admin_code_used;
    private String admin_password_used;
    private Date tentative_time;
    private boolean tentative_result;
    private String ip_address;
    
    //Constructor
    
    public ConnectionLog(){
        
    }
    
    //Acccessors

    public int getTentative_id() {
        return tentative_id;
    }

    public String getAdmin_code_used() {
        return admin_code_used;
    }

    public String getAdmin_password_used() {
        return admin_password_used;
    }

    public Date getTentative_time() {
        return tentative_time;
    }

    public boolean isTentative_result() {
        return tentative_result;
    }

    public String getIp_address() {
        return ip_address;
    }
    
    //Mutators

    public void setAdmin_code_used(String admin_code_used) {
        this.admin_code_used = admin_code_used;
    }

    public void setAdmin_password_used(String admin_password_used) {
        this.admin_password_used = admin_password_used;
    }

    public void setTentative_time(Date tentative_time) {
        this.tentative_time = tentative_time;
    }

    public void setTentative_result(boolean tentative_result) {
        this.tentative_result = tentative_result;
    }

    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }
    
    
    
    
}
