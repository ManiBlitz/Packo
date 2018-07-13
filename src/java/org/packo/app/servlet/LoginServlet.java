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
import org.packo.app.utils.DBUtils;
import org.packo.app.utils.MyUtils;
 
@WebServlet(urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public LoginServlet() {
        super();
    }
 
    // Show Login page.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        // Forward to /WEB-INF/views/loginView.jsp
        // (Users can not access directly into JSP pages placed in WEB-INF)
        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
 
        dispatcher.forward(request, response);
 
    }
 
    // When the user enters userName & password, and click Submit.
    // This method will be executed.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Connection conn = MyUtils.getStoredConnection(request);
        String userName = request.getParameter("admin_code");
        String password = request.getParameter("password");
        String ip_address = request.getRemoteAddr();
        int tentativeID = 0;
 
        Admin user = null;
        boolean hasError = false;
        String errorString = null;
 
        if (userName == null || password == null || userName.length() == 0 || password.length() == 0) {
            hasError = true;
            errorString = "Required username and password!";
        } else {
            try {
                // Find the user in the DB.
                user = DBUtils.findAdministrator(conn, userName, password);
 
                if (user == null) {
                    hasError = true;
                    errorString = "Administrator code or password invalid";
                }
            } catch (Exception e) {
                hasError = true;
                errorString = e.getMessage();
            }
        }
        // If error, forward to /WEB-INF/views/login.jsp
        if (hasError) {
            user = new Admin();
            user.setAdmin_code(userName);
            user.setAdmin_password(password);
            
            try {
                DBUtils.storeConnectionLog(conn, userName, password, !hasError, ip_address);
            } catch (Exception ex) {
                errorString = ex.getMessage();
            }
            
            // Store information in request attribute, before forward.
            request.setAttribute("errorString", errorString);
            request.setAttribute("user", user);
 
            // Forward to /WEB-INF/views/login.jsp
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
 
            dispatcher.forward(request, response);
        }
        // If no error
        // Store user information in Session
        // And redirect to userInfo page.
        else {
            try {
                tentativeID = DBUtils.storeConnectionLog(conn, userName, password, !hasError, ip_address);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            HttpSession session = request.getSession();
            MyUtils.storeLoginedAdministrator(session, user);
            MyUtils.storeTentativeID(session, tentativeID);
            try {
                Residence complex = DBUtils.findComplexByID(conn,user.getResidence_id());
                MyUtils.storeResidenceInfo(session, complex);
            } catch (Exception ex) {
                System.out.println("======>>> INVALID ACTION REPORTED\n"+ex.getMessage());
            }
            MyUtils.storeSuccessNotification(session, false, "");
            response.sendRedirect(request.getContextPath() + "/home");
        }
    }
    
}

