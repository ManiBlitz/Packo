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
public class Residence {
    
    private int residence_id;
    private String residence_name;
    private String residence_address;
    private String zip_code;
    private String city;
    private String state;
    private String residence_phone;
    private String residence_email;
    private Date residence_registration_time;
    private String residence_code;
    private int residence_storage_space;
    private int hold_expiration_default; //Used
    private double max_package_weight;   //Used
    private double daily_hold_price;
    private int max_hold_time;           //Used
    
    
    //constructor
    
    public Residence(){
        
    }
    
    //Accessors

    public int getResidence_id() {
        return residence_id;
    }

    public String getResidence_name() {
        return residence_name;
    }

    public String getResidence_address() {
        return residence_address;
    }

    public String getZip_code() {
        return zip_code;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getResidence_phone() {
        return residence_phone;
    }

    public String getResidence_email() {
        return residence_email;
    }
    
    public Date getResidence_registration_date(){
        return residence_registration_time;
    }

    public Date getResidence_registration_time() {
        return residence_registration_time;
    }

    public String getResidence_code() {
        return residence_code;
    }

    public int getResidence_storage_space() {
        return residence_storage_space;
    }

    public int getHold_expiration_default() {
        return hold_expiration_default;
    }

    public double getMax_package_weight() {
        return max_package_weight;
    }

    public double getDaily_hold_price() {
        return daily_hold_price;
    }

    public int getMax_hold_time() {
        return max_hold_time;
    }
    
    
    
    //Mutators

    public void setResidence_id(int residence_id) {
        this.residence_id = residence_id;
    }

    public void setResidence_name(String residence_name) {
        this.residence_name = residence_name;
    }

    public void setResidence_address(String residence_address) {
        this.residence_address = residence_address;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setResidence_phone(String residence_phone) {
        this.residence_phone = residence_phone;
    }

    public void setResidence_email(String residence_email) {
        this.residence_email = residence_email;
    }
    
    public void setResidence_registration_time(Date registration_time){
        this.residence_registration_time = registration_time;
    }

    public void setResidence_code(String residence_code) {
        this.residence_code = residence_code;
    }

    public void setResidence_storage_space(int residence_storage_space) {
        this.residence_storage_space = residence_storage_space;
    }

    public void setHold_expiration_default(int hold_expiration_default) {
        this.hold_expiration_default = hold_expiration_default;
    }

    public void setMax_package_weight(double max_package_weight) {
        this.max_package_weight = max_package_weight;
    }

    public void setDaily_hold_price(double daily_hold_price) {
        this.daily_hold_price = daily_hold_price;
    }

    public void setMax_hold_time(int max_hold_time) {
        this.max_hold_time = max_hold_time;
    }
    
    
    
    
    
    
}
