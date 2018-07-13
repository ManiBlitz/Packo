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
import java.sql.Connection;
 
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
 
@WebServlet(urlPatterns = { "/pickup" })
public class PickupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public PickupServlet() {
        super();
    }
 
    // Show Login page.
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
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/pickupView.jsp");
 
        dispatcher.forward(request, response);
 
    }
 
    // When the user enters userName & password, and click Submit.
    // This method will be executed.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Connection conn = MyUtils.getStoredConnection(request);
        String pickup_name = request.getParameter("pickup_name");
        int parcel_id = Integer.parseInt(request.getParameter("id_package"));
        String errorString = null;
        Parcel pack = new Parcel();
        
        if(pickup_name.isEmpty()){
            
            errorString = "The name of the person picking up the package must be entered."
                    + " It must also be the direct receiver, a member of the apartment or"
                    + " the person designated to pick up the package.";
        }else{
            
            try {
                pack = DBUtils.getParcelByID(conn, parcel_id);
                DBUtils.pickupParcel(conn, parcel_id, pickup_name);
                System.out.println("The parcel with id "+parcel_id+" has been successfully picked up");
                        
            } catch (Exception ex) {
                errorString = ex.getMessage();
            }
   
        }
        
        request.setAttribute("errorString",errorString);
        
        if(errorString != null){
            RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/pickupView.jsp");
 
            dispatcher.forward(request, response);
        }else{
            session.removeAttribute("pack");
            String successMessage = "The resident "+pickup_name+""
                    + " successfully picked up package with code "+pack.getPickup_code();
            MyUtils.storeSuccessNotification(session, true, successMessage);
            response.sendRedirect(request.getContextPath() + "/home");
        }
          
    }
    
}

