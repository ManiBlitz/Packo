/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.packo.app.servlet;

import java.io.IOException;
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
import org.packo.app.beans.Parcel;
import org.packo.app.utils.DBUtils;
import org.packo.app.utils.MyUtils;
 
@WebServlet(urlPatterns = { "/home" })
public class HomeServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
 
   public HomeServlet() {
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
        // Store info to the request attribute before forwarding.
        request.setAttribute("user", administrator);
        
        try {
            /*
            This second part gets the parcels information
            It will organiwe the different parcels and display those that have not yet been picked up
            Other valuable informations will be displayed
            */
            request.setAttribute("packagesList", DBUtils.getListParcels(conn, administrator.getResidence_id()));
            //request.setAttribute("actionsToComplete", DBUtils.getNonCompletedActions(conn, administrator.getResidence_id()));
            
            /*
            Now we set the parcels that have not been picked up to the other attribute
            */
            
            request.setAttribute("packagesToPick", DBUtils.countRemainingParcels(conn, administrator.getResidence_id()));
            request.setAttribute("packagesOverdue", DBUtils.countOverdueParcels(conn, administrator.getResidence_id()));
            request.setAttribute("packageTotal", DBUtils.countResidenceDeliveries(conn, administrator.getResidence_id()));
            request.setAttribute("packagePicked", DBUtils.countPickedPackages(conn, administrator.getResidence_id()));
            
            //These two elements will be presented in the bloc aside from the table

         } catch (Exception ex) {
            Logger.getLogger(HomeServlet.class.getName()).log(Level.SEVERE, null, ex);
         }   
       
           /*
           This part manages the different activity
           It list them by inverted chronological order
           It contains the different informations about the user
           */
           
           //enter content
        
        request.setAttribute("successIndicator", MyUtils.getSuccesIndicator(session));
        request.setAttribute("successMessage", MyUtils.getSuccessMessage(session));
        MyUtils.storeSuccessNotification(session, false, "");
 
        // If the user has logged in, then forward to the page
        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/homeView.jsp");
        dispatcher.forward(request, response);
        
   }
 
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       
        HttpSession session = request.getSession();
        Connection conn = MyUtils.getStoredConnection(request);
        String error_message = null;
        String submitValue = request.getParameter("action");
        int id_package = 0;
        
        if(request.getParameter("picked_package") != null){
            id_package = Integer.parseInt(request.getParameter("picked_package"));
            try {
                Parcel pack = DBUtils.getParcelByID(conn, id_package);
                session.setAttribute("pack", pack);
            } catch (Exception ex) {
                error_message = ex.getMessage();
            }
            
        }
        
        if(id_package != 0){
            switch (submitValue) {
                case "Pickup":
                    System.out.println("the package id is "+id_package);
                    response.sendRedirect(request.getContextPath() + "/pickup");
                    break;
                case "Delete":
                    try {
                        // start the deletion
                        DBUtils.deleteParcel(conn, id_package);
                        String successMessage = "Package successfully deleted";
                        MyUtils.storeSuccessNotification(session, true, successMessage);
                    } catch (Exception ex) {
                        error_message = "Impossible to delete the parcel. An error occured during the procedure";
                        System.out.println(ex.getMessage());
                    }   break;
                case "HoldExtension":
                    System.out.println("the package id is "+id_package);
                    response.sendRedirect(request.getContextPath() + "/holdextension");
                    break;
                default:
                    break;
            }

            if(!submitValue.equals("Pickup") && !submitValue.equals("HoldExtension")){
                request.setAttribute("errorString", error_message);
                doGet(request,response);
            }
            
        }else{
            error_message = "You must select a package before proceeding";
            request.setAttribute("errorString", error_message);
            doGet(request,response);
        }
    }
 
}
