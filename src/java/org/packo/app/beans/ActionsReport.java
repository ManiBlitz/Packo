/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.packo.app.beans;

import java.sql.Timestamp;

/**
 *
 * @author ADMIN
 */
public class ActionsReport {
    
    //instance variabes
    
    private int id_user;
    private int id_packages;
    private Timestamp action_time;
    private String name_pickup_order;
    private String address_return;
    private String name_forward;
    private String complete_address_forward;
    private int admin_id;
    private boolean viewed;
    private int type;
    
    //Static variables
    
    public static final int ORDER_PICKUP = 1;
    public static final int PACKAGE_RETURN = 2;
    public static final int DELIVERY_TO_APT = 3;
    public static final int FORWARD_ORDER = 4;
    
    
    //constructor
    
    public ActionsReport(){

    }
    
    //getters

    public int getId_user() {
        return id_user;
    }

    public int getId_packages() {
        return id_packages;
    }

    public Timestamp getAction_time() {
        return action_time;
    }

    public String getAddress_return() {
        return address_return;
    }

    public String getName_forward() {
        return name_forward;
    }

    public String getComplete_address_forward() {
        return complete_address_forward;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public String getName_pickup_order() {
        return name_pickup_order;
    }

    public int getType() {
        return type;
    }

    public boolean isViewed() {
        return viewed;
    }
    
    //setters

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setId_packages(int id_packages) {
        this.id_packages = id_packages;
    }

    public void setAction_time(Timestamp action_time) {
        this.action_time = action_time;
    }

    public void setName_pickup_order(String name_pickup_order) {
        this.name_pickup_order = name_pickup_order;
    }

    public void setAddress_return(String address_return) {
        this.address_return = address_return;
    }

    public void setName_forward(String name_forward) {
        this.name_forward = name_forward;
    }

    public void setComplete_address_forward(String complete_address_forward) {
        this.complete_address_forward = complete_address_forward;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public void setViewed(boolean viewed) {
        this.viewed = viewed;
    }

    public void setType(int type) {
        this.type = type;
    }
    
    // Now we need to define a toString function
    // Each type of action has a different message
    // This means that each should end up with a different toString content
    
    @Override
    public String toString(){
        
        String notification = new String();
        
        switch (this.type) {
            case ORDER_PICKUP:
                notification = "A parcel pickup order has been defined for parcel ID:"+this.getId_packages()
                        +". The parcel will be picked up by "+this.getName_pickup_order();
                break;
            case PACKAGE_RETURN:
                notification = "The parcel ID:"+this.getId_packages()+" need to be returned from order of the resident."
                        +" The package will be returned to address "+this.address_return;
                break;
            case DELIVERY_TO_APT:
                notification = "The parcel ID:"+this.getId_packages()+" need to be delivered to the resident apartment on order of the resident";
                break;
            case FORWARD_ORDER:
                notification = "The parcel ID:"+this.getId_packages()+" Has been forwarded on order of the resident."
                        +" It will be delivered to "+this.getName_forward()+" at the address "+this.getComplete_address_forward();
                break;
            default:
                System.out.println("====>>> INVALID CONDITION DETECTED; ACTION REQUIRED");
                break;
        }
        
        return notification;
    }
    
    
    
    
}
