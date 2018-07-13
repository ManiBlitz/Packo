/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.packo.app.utils;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Calendar;
 
import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.packo.app.beans.Admin;
import org.packo.app.beans.Residence;
 
public class MyUtils {
 
    public static final String ATT_NAME_CONNECTION = "ATTRIBUTE_FOR_CONNECTION";
 
    private static final String ATT_NAME_USER_NAME = "ATTRIBUTE_FOR_STORE_USER_NAME_IN_COOKIE";
 
    // Store Connection in request attribute.
    // (Information stored only exist during requests)
    public static void storeConnection(ServletRequest request, Connection conn) {
        request.setAttribute(ATT_NAME_CONNECTION, conn);
    }
 
    // Get the Connection object has been stored in attribute of the request.
    public static Connection getStoredConnection(ServletRequest request) {
        Connection conn = (Connection) request.getAttribute(ATT_NAME_CONNECTION);
        return conn;
    }
    
    // Get stored info in the session concerning the residence
    public static Residence getResidenceinfo(HttpSession session){
        Residence complex = (Residence) session.getAttribute("residence");
        return complex;
    }
    
    // store residence informations
    public static void storeResidenceInfo(HttpSession session, Residence complex){
        session.setAttribute("residence", complex);
    }
    
 
    // Store user info in Session.
    public static void storeLoginedAdministrator(HttpSession session, Admin loginedUser) {
        // On the JSP can access via ${loginedUser}
        session.setAttribute("loginedAdmin", loginedUser);
    }
    
    //Store the tentative ID of the Session
    public static void storeTentativeID(HttpSession session, int tentativeID){
        // On the JSP can access via ${tentativeID}
        session.setAttribute("tentativeID", tentativeID);
    }
    
    //Store Operation success message
    public static void storeSuccessNotification(HttpSession session, boolean success, String message){
        // We have two elements that are the success indicator and the message
        session.setAttribute("successMessage", message);
        session.setAttribute("successIndicator", success);
    }
    
    //Get the success indicator
    public static boolean getSuccesIndicator(HttpSession session){
        boolean success = (boolean) session.getAttribute("successIndicator");
        return success;
    }
    
    //Get the success message
    public static String getSuccessMessage(HttpSession session){
        String message = (String) session.getAttribute("successMessage");
        return message;
    }
    
    
    
 
    // Get the user information stored in the session.
    public static Admin getLoginedAdministrator(HttpSession session) {
        Admin loginedUser = (Admin) session.getAttribute("loginedAdmin");
        return loginedUser;
    }
    
    //Calculate the hold time after adding a particular value of time
    
    public static Timestamp getNewHoldExpiration(int days, Timestamp previousHold){
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(previousHold);            
        calendar.add(Calendar.DAY_OF_YEAR, days);
        Timestamp newHold = new Timestamp(calendar.getTime().getTime());
        return newHold;

    }
    
    //Function to set a new expiration hold for a package
    
    public static void setNewHoldExpiration(Connection conn, int days, Timestamp previousHold, int parcel_id) throws Exception{
        
        DBUtils.setNewHoldExpirationTime(conn, parcel_id, getNewHoldExpiration(days,previousHold));
        
    }
    
    // function to check if an address is similar to the residence address.
    
    public static boolean validatePackageAddress(HttpSession session, String address){
        
        Residence complex = (Residence) getResidenceinfo(session);
        if(address.equalsIgnoreCase(complex.getResidence_address())){
            return true;
        }
        return false;
        
    }

}
