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
public class LogoutLog {
    
    //Instance variables
    
    private int logout_id;
    private int admin_id;
    private int tentative_id;
    private long logout_time;
    
    //Constructor
    
    public LogoutLog(){
        
    }
    
    //Accessors

    public int getLogout_id() {
        return logout_id;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public int getTentative_id() {
        return tentative_id;
    }

    public long getLogout_time() {
        return logout_time;
    }
    
    //Mutators

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public void setTentative_id(int tentative_id) {
        this.tentative_id = tentative_id;
    }

    public void setLogout_time(long logout_time) {
        this.logout_time = logout_time;
    }
    
}
