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
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.packo.app.beans.Admin;
import org.packo.app.beans.Residence;
import org.packo.app.beans.Resident;
import org.packo.app.tools.EncryptionTool;
import org.packo.app.utils.DBUtils;
import org.packo.app.utils.MyUtils;
 
@WebServlet(urlPatterns = { "/newresident" })
public class NewResidentServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
 
   public NewResidentServlet() {
       super();
   }
 
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
 
        // Forward to /WEB-INF/views/loginView.jsp
        // (Users can not access directly into JSP pages placed in WEB-INF)
        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/newResidentView.jsp");
 
        dispatcher.forward(request, response);
   }
 
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       
        HttpSession session = request.getSession();
        Connection conn = MyUtils.getStoredConnection(request);
        String errorString = null;
        
        String resident_name = request.getParameter("name");
        String resident_email = request.getParameter("email");
        String resident_phone = request.getParameter("phone_number");
        String resident_apt = request.getParameter("apt_num");
        
        if(resident_name.isEmpty() || resident_name == null){
            errorString = "Please provide a name for the resident";
        }else if(resident_email.isEmpty() || resident_email == null){
            errorString = "Please provide an email for the resident";
        }else if(resident_phone.isEmpty() || resident_phone == null){
            errorString = "Please provide a phone number for the resident";
        }else if(resident_apt.isEmpty() || resident_phone == null){
            errorString = "Please provide an apartment number for the resident";   
        }
        
        if(errorString == null){
            
            try{
                
                // We now register the user and send him a confirmation email
                
                Resident user = new Resident();
                user.setName_resident(EncryptionTool.getEncryption(resident_name));
                user.setEmail(EncryptionTool.getEncryption(resident_email));
                user.setTel(EncryptionTool.getEncryption(resident_phone));
                user.setApt_num(resident_apt);
                
                try{
                    
                    Residence complex = (Residence) session.getAttribute("residence");
                    DBUtils.registerNewUser(conn, complex, user);
                    //the email is sent directly through the function DB Utils
                    
                }catch(Exception ex){
                    errorString = ex.getMessage();
                }
                
            }catch(NoSuchAlgorithmException ex){
                errorString = "An unknown error occured during the registration of the user";
            }
            
        }
        
        request.setAttribute("errorString",errorString);
        
        if(errorString != null){
            RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/newResidentView.jsp");
 
            dispatcher.forward(request, response);
        }else{
            String successMessage = "The resident "+resident_name+""
                    + " has been successfully registered";
            MyUtils.storeSuccessNotification(session, true, successMessage);
            response.sendRedirect(request.getContextPath() + "/home");
        }
        
   }
}

