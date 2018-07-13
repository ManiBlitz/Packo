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
import org.packo.app.utils.DBUtils;
import org.packo.app.utils.MyUtils;
 
@WebServlet(urlPatterns = { "/logout" })
public class LogoutServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
 
   public LogoutServlet() {
       super();
   }
 
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
        
        Connection conn = MyUtils.getStoredConnection(request);
       
        HttpSession session = request.getSession();
        String errorString = null;
        
        
        //The administrator information and the tentative ID are saved
        //After each logout to preserve connection information
        Admin admin = (Admin)session.getAttribute("loginedAdmin");
        Integer tentativeID = (Integer)session.getAttribute("tentativeID");
        
        try {
            DBUtils.storeLogoutInformations(conn,admin.getAdmin_id(),tentativeID);
            session.removeAttribute("residence");
            session.removeAttribute("loginedAdmin");
            session.removeAttribute("tentativeID");
            //THe session informations are destroryed after logout.
        }catch(Exception ex){
            errorString = ex.getMessage();
        }
        
        request.setAttribute("errorString", errorString);
        
        //In case of mistake, the admin is redirected to the home screen with a logout error displayed
        if(errorString != null){ 
            RequestDispatcher dispatcher 
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/homeView.jsp");
            dispatcher.forward(request, response);
        }else{
            response.sendRedirect(request.getContextPath() + "/login");
        }   
   }
 
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       
        doGet(request,response);
    }
 
}

