/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.packo.app.beans;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class Parcel {
    
    //Instance variables
    
    private int parcel_id;
    private String sender_name;
    private String address_receiver;
    private String apt_number;
    private String delivery_company;
    private Timestamp delivery_time;
    private Timestamp expiration_time;
    private double package_weight;
    private boolean secret_package;
    private String name_pickup;
    private int residence_id;
    private boolean picked_up;
    private String pickup_code;
    private String receiver_name;
    private String sender_address;
    private Timestamp pickup_time;
    
    
    //Constructor
    
    public Parcel(){
        
    }
    
    //Accessors

    public int getParcel_id() {
        return parcel_id;
    }

    public String getReceiver_name() {
        return receiver_name;
    }

    public String getSender_address() {
        return sender_address;
    }

    public String getSender_name() {
        return sender_name;
    }

    public String getAddress_receiver() {
        return address_receiver;
    }

    public String getApt_number() {
        return apt_number;
    }

    public String getDelivery_company() {
        return delivery_company;
    }

    public Timestamp getDelivery_time() {
        return delivery_time;
    }

    public Timestamp getExpiration_time() {
        return expiration_time;
    }

    public double getPackage_weight() {
        return package_weight;
    }

    public boolean isSecret_package() {
        return secret_package;
    }

    public String getName_pickup() {
        return name_pickup;
    }

    public int getResidence_id() {
        return residence_id;
    }

    public boolean isPicked_up() {
        return picked_up;
    }

    public String getPickup_code() {
        return pickup_code;
    }

    public Timestamp getPickup_time() {
        return pickup_time;
    }
    
    
    
    //Mutators

    public void setParcel_id(int parcel_id) {
        this.parcel_id = parcel_id;
    }

    public void setResidence_id(int residence_id) {
        this.residence_id = residence_id;
    }
    
    

    public void setSender_name(String sender_name) {
        this.sender_name = sender_name;
    }

    public void setAddress_receiver(String address_receiver) {
        this.address_receiver = address_receiver;
    }

    public void setApt_number(String apt_number) {
        this.apt_number = apt_number;
    }

    public void setDelivery_company(String delivery_company) {
        this.delivery_company = delivery_company;
    }

    public void setDelivery_time(Timestamp delivery_time) {
        this.delivery_time = delivery_time;
    }

    public void setExpiration_time(Timestamp expiration_time) {
        this.expiration_time = expiration_time;
    }

    public void setPackage_weight(double package_weight) {
        this.package_weight = package_weight;
    }

    public void setSecret_package(boolean secret_package) {
        this.secret_package = secret_package;
    }

    public void setName_pickup(String name_pickup) {
        this.name_pickup = name_pickup;
    }

    public void setPickup_code(String pickup_code) {
        this.pickup_code = pickup_code;
    }

    public void setReceiver_name(String receiver_name) {
        this.receiver_name = receiver_name;
    }

    public void setSender_address(String sender_address) {
        this.sender_address = sender_address;
    }

    public void setPicked_up(boolean picked_up) {
        this.picked_up = picked_up;
    }

    public void setPickup_time(Timestamp pickup_time) {
        this.pickup_time = pickup_time;
    }
    
    
    
    
    
    
    
}
