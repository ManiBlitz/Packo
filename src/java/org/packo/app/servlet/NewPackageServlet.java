/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.packo.app.servlet;

/**
 *
 * @author ADMIN
 */
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Timestamp;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
import org.packo.app.beans.Admin;
import org.packo.app.beans.Parcel;
import org.packo.app.beans.Residence;
import org.packo.app.tools.EmailingSystem;
import org.packo.app.tools.EncryptionTool;
import org.packo.app.tools.UniqueIDGenerator;
import org.packo.app.utils.DBUtils;
import org.packo.app.utils.MyUtils;
 
@WebServlet(urlPatterns = { "/newpackage" })
public class NewPackageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public NewPackageServlet() {
        super();
    }
 
    // Show product creation page.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Connection conn = MyUtils.getStoredConnection(request);
       
        HttpSession session = request.getSession();
 
        /*
        This first part concerns the administrator connection
        It checks whether or not the dmin is connected
        He is returned to the login page when if not logged in
        */
        
        // Check User has logged on
        Admin administrator = MyUtils.getLoginedAdministrator(session);
 
        // Not logged in
        if (administrator == null) {
            // Redirect to login page.
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
 
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/newPackageView.jsp");
        dispatcher.forward(request, response);
    }
 
    // When the user finishes entering the package informations,
    // The processing is redirected to this page
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Connection conn = MyUtils.getStoredConnection(request);
        Residence complex = (Residence) MyUtils.getResidenceinfo(session);
                
        //This is the error string that is used to get the error description
        String errorString = null;
        
        
        String sender_name = request.getParameter("sender_name");
        String receiver_name = request.getParameter("receiver_name");
        String sender_address = request.getParameter("sender_address");
        String receiver_address = request.getParameter("receiver_address");
        String apt_num = request.getParameter("apt_num");
        String delivery_company = request.getParameter("delivery_company");
        
        Double package_weight = 0.0;
        if(request.getParameter("package_weight") != null && request.getParameter("package_weight") != ""){
            package_weight = Double.parseDouble(request.getParameter("package_weight"));
            if(package_weight > complex.getMax_package_weight()){
                errorString = "The package is weight is higher than the max value. It must be directly delivered to resident door";
            }
        }
        
        
        int residence_id = complex.getResidence_id();
        Timestamp delivery_time = new Timestamp(System.currentTimeMillis());
        Timestamp expiration_time = MyUtils.getNewHoldExpiration(complex.getHold_expiration_default(), delivery_time);
        String pickup_code = UniqueIDGenerator.getRandomPickupCode();
        
        Parcel pack = new Parcel();
        
        pack.setSender_name(sender_name);
        pack.setReceiver_name(receiver_name);
        pack.setSender_address(sender_address);
        pack.setAddress_receiver(receiver_address);
        pack.setApt_number(apt_num);
        pack.setDelivery_company(delivery_company);
        pack.setPackage_weight(package_weight);
        pack.setResidence_id(residence_id);
        pack.setDelivery_time(delivery_time);
        pack.setExpiration_time(expiration_time);
        pack.setPickup_code(pickup_code);
        
        if (sender_name.isEmpty()) {
            errorString = "You must provide a name for the sender";
        }else if(receiver_name.isEmpty()){
            errorString = "You must provide a receiver name";
        }else if(sender_address.isEmpty()){
            errorString = "The sender address field cannot be empty";
        }else if(receiver_address.isEmpty()){
            errorString = "You must enter the receiver address for verification";
        }else if(apt_num.isEmpty()){
            errorString = "A valid apartment number must be entered";
        }else if(delivery_company.isEmpty()){
            errorString = "The delivery company cannot be unknown";
        }
 
        if (errorString == null) {
            try {
                DBUtils.storeParcelInfo(conn, pack);
                //An email will be sent to the resident(s)
            } catch (Exception e) {
                errorString = e.getMessage();
            }
        }
 
        // Store infomation to request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
 
        // If error, forward to Edit page.
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/newPackageView.jsp");
            dispatcher.forward(request, response);
        }
        // If everything nice.
        // Redirect to the product listing page.
        else {
            String successMessage = "The new package with code "+pack.getPickup_code()+""
                    + " addressed to resident "+pack.getReceiver_name()+""
                    + " has been successfully saved";
            MyUtils.storeSuccessNotification(session, true, successMessage);
            response.sendRedirect(request.getContextPath() + "/home");
        }
    }
 
}
