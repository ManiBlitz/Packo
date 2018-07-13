/*
 * Copyright (c) 2018, Packo Services - SMALLEE Group
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

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.packo.app.beans.Admin;
import org.packo.app.beans.Parcel;
import org.packo.app.tools.EmailingSystem;
import org.packo.app.utils.DBUtils;
import org.packo.app.utils.MyUtils;
 
@WebServlet(urlPatterns = { "/holdextension" })
public class HoldExtensionServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
 
   public HoldExtensionServlet() {
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
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/holdExtensionView.jsp");
 
        dispatcher.forward(request, response);
   }
 
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       
        HttpSession session = request.getSession();
        Connection conn = MyUtils.getStoredConnection(request);
        int holdExtension = 0;
        
        if(request.getParameter("extension_time") != null && !request.getParameter("extension_time").isEmpty()){
            holdExtension = Integer.parseInt(request.getParameter("extension_time"));
        }
        int parcel_id = Integer.parseInt(request.getParameter("id_package"));
        String errorString = null;
        Parcel pack = new Parcel();
        
        if(holdExtension < 1){
            errorString = "The hold extension must be at least one day.";
        }else{
            
            try {
                pack = DBUtils.getParcelByID(conn, parcel_id);
                MyUtils.setNewHoldExpiration(conn, holdExtension, pack.getExpiration_time(), parcel_id);
                //An email is sent to the user to ensure that his hold extension has been operated.
                
                System.out.println("The extension for the parcel with ID "+parcel_id+" has been successful");
                        
            } catch (Exception ex) {
                errorString = ex.getMessage();
            }
   
        }
        
        request.setAttribute("errorString",errorString);
        
        if(errorString != null){
            RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/holdExtensionView.jsp");
 
            dispatcher.forward(request, response);
        }else{
            session.removeAttribute("pack");
            String successMessage = "The package with code "+pack.getPickup_code()+""
                    + " has been successfully extended by "+holdExtension+" days";
            MyUtils.storeSuccessNotification(session, true, successMessage);
            response.sendRedirect(request.getContextPath() + "/home");
        }
          
    }
 
}
