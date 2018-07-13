/*
 * Copyright (c) 2018, ADMIN
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package org.packo.app.servlet;

/**
 *
 * @author ADMIN
 */

import java.io.IOException;
import java.sql.Connection;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.packo.app.beans.Admin;
 
import org.packo.app.beans.Residence;
import org.packo.app.tools.EmailingSystem;
import org.packo.app.tools.UniqueIDGenerator;
import org.packo.app.utils.DBUtils;
import org.packo.app.utils.MyUtils;
 
@WebServlet(urlPatterns = { "/parameters" })
public class ParametersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public ParametersServlet() {
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
                .getRequestDispatcher("/WEB-INF/views/parametersView.jsp");
        dispatcher.forward(request, response);
    }
 
    // When the user enters the product information, and click Submit.
    // This method will be called.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
        HttpSession session = request.getSession();
        
        String name = (String) request.getParameter("residence_name");
        String email = (String) request.getParameter("residence_email");
        String phone_number = (String) request.getParameter("residence_phone");
        String residence_storage_space = request.getParameter("residence_storage_space");
        String hold_expiration_default = request.getParameter("hold_expiration_default");
        String max_package_weight = request.getParameter("max_package_weight");
        String daily_hold_price = request.getParameter("daily_hold_price");
        String max_hold_time = request.getParameter("max_hold_time");
        
        Residence complex = new Residence();
        String errorString = null;
 
        // Product ID is the string literal [a-zA-Z_0-9]
        // with at least 1 character
        String regex = "\\w+";
        
        // Email regex: 
        // All emails should be of the form *****@******.***
        String email_regex = "[a-zA-Z0-9]+@[a-zA-Z]*[.][a-z]{3}"; 
        
        // Phone number regex
        // The phone number assumes the standard of the US
        String phone_regex = "[0-9]{3}[0-9]{3}[0-9]{4}";
        
        // Username regex
        // The admin_code must start with a letter and should not contain any
        // special character.
        String admin_code_regex = "[a-zA-Z]{1}[a-zA-Z0-9]{5,29}";
        
        // Date regex
        // The date will be of the format mm/dd/yyyy
        String date_regex = "^\\d{2}\\/\\d{2}\\/\\d{4}$";
        
        if (name == null) {
            errorString = "You must provide a name for the residence";
        }else if(email == null || !email.matches(email_regex)){
            errorString = "Invalid email. The email should be of the form exmaple@123.any";
        }else if(phone_number == null || !phone_number.matches(phone_regex)){
            errorString = "Invalid phone number. Please enter a valid one";
        }else if(residence_storage_space == null || Integer.parseInt(residence_storage_space) < 1){
            errorString = "The residence storage space must be at least one";
        }else if(hold_expiration_default == null || Integer.parseInt(hold_expiration_default) < 1){
            errorString = "The hold expiration default must be at least one";
        }else if(max_hold_time == null || Integer.parseInt(max_hold_time) < 1){
            errorString = "The max hold time must be at least one";
        }else if(max_package_weight == null || Double.parseDouble(max_package_weight) < 0.0001){
            errorString = "Invalid max package weight!";
        }else if(daily_hold_price == null || Double.parseDouble(daily_hold_price) < 0.0001){
            errorString = "Invalid daily hold price!";
        }
 
        if (errorString == null) {
            
            int residence_storage_space_val = Integer.parseInt(residence_storage_space);
            int hold_expiration_default_val = Integer.parseInt(hold_expiration_default);
            int max_hold_time_val = Integer.parseInt(max_hold_time);
            double max_package_weight_val = Double.parseDouble(max_package_weight);
            double daily_hold_price_val = Double.parseDouble(daily_hold_price);
            
            if(max_hold_time_val < hold_expiration_default_val){
                errorString = "The value of the maximal hold time cannot be less than the standard hold time";
            }else{

                complex.setResidence_name(name);
                complex.setResidence_phone(phone_number);
                complex.setResidence_email(email);
                complex.setResidence_storage_space(residence_storage_space_val);
                complex.setHold_expiration_default(hold_expiration_default_val);
                complex.setMax_package_weight(max_package_weight_val);
                complex.setDaily_hold_price(daily_hold_price_val);
                complex.setMax_hold_time(max_hold_time_val);
                
                try{
                    DBUtils.updateResidenceInfo(conn, complex);
                    MyUtils.storeResidenceInfo(session, complex);
                }catch(Exception e){
                    errorString = e.getMessage();
                }
                
            }
            
            
        }
 
        // Store infomation to request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
 
        // If error, forward to Edit page.
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/parametersView.jsp");
            dispatcher.forward(request, response);
        }
        // If everything nice.
        // Redirect to the product listing page.
        else {
            String successMessage = "The parameters of the residence have been successfully modified";
            MyUtils.storeSuccessNotification(session, true, successMessage);
            response.sendRedirect(request.getContextPath() + "/home");
        }
    }
 
}
