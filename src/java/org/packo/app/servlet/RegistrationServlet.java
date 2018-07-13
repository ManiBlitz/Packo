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
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.catalina.tribes.util.Arrays;
 
import org.packo.app.beans.Admin;
import org.packo.app.beans.Residence;
import org.packo.app.tools.EmailingSystem;
import org.packo.app.tools.EncryptionTool;
import org.packo.app.utils.DBUtils;
import org.packo.app.utils.MyUtils;
 
@WebServlet(urlPatterns = { "/admin" })
public class RegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public RegistrationServlet() {
        super();
    }
 
    // Show product creation page.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/adminRegistrationView.jsp");
        dispatcher.forward(request, response);
    }
 
    // When the user enters the product information, and click Submit.
    // This method will be called.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
 
        String name = (String) request.getParameter("name");
        String admin_code = (String) request.getParameter("admin_code");
        String password = (String) request.getParameter("password");
        String email = (String) request.getParameter("email");
        String phone_number = (String) request.getParameter("phone_number");
        String dob = request.getParameter("date_of_birth");
        int priority = Integer.parseInt(request.getParameter("priority"));
        String role = (String) request.getParameter("role");
        String address = (String) request.getParameter("address");
        Residence complex = null;
        String errorString = null;
        
        try {
            complex = DBUtils.findComplexFromCode(conn, (String) request.getParameter("residence_code"));
        } catch (Exception ex) {
            errorString = ex.getMessage();
        }
        
        Admin new_admin = new Admin();
        
        new_admin.setAdmin_name(name);
        new_admin.setAdmin_code(admin_code);
        try{
            new_admin.setAdmin_password(EncryptionTool.getEncryption(password));
        }catch (NoSuchAlgorithmException e){
            errorString = e.getMessage();
        }
        new_admin.setEmail(email);
        new_admin.setAdmin_priority(priority);
        new_admin.setRole(role);
        new_admin.setPhone_number(phone_number);
        new_admin.setAddress(address);
        new_admin.setResidence_id(complex.getResidence_id());
        if(dob != null){
            String date_parts[] = dob.split("-");
            System.out.print(Arrays.toString(date_parts));
            int year = Integer.parseInt(date_parts[0]);
            int month = Integer.parseInt(date_parts[1]);
            int day = Integer.parseInt(date_parts[2]);
            new_admin.setDate_birth(new Date(year,month,day));
        }
        
 
        // Product ID is the string literal [a-zA-Z_0-9]
        // with at least 1 character
        String regex = "\\w+";
        
        // Email regex: 
        // All emails should be of the form *****@******.***
        String email_regex = "[a-zA-Z0-9]+@[a-zA-Z]*[.][a-z]{3}"; 
        
        // password regex
        // All passwords must contain at least one uppercase, one lowercase
        // one number and be 8 characters long at least.
        String password_regex = "^\\S*(?=\\S{8,})(?=\\S*[a-z])(?=\\S*[A-Z])(?=\\S*[\\d])\\S*$";
        
        // Phone number regex
        // The phone number assumes the standard of the US
        String phone_regex = "[0-9]{3}[0-9]{3}[0-9]{4}";
        
        // Username regex
        // The admin_code must start with a letter and should not contain any
        // special character.
        String admin_code_regex = "[a-zA-Z]{1}[a-zA-Z0-9]{1,29}";
        
        // Date regex
        // The date will be of the format mm/dd/yyyy
        String date_regex = "^\\d{2}\\/\\d{2}\\/\\d{4}$";
        
        if (name == null) {
            errorString = "You must provide a name";
        }else if(password == null || !password.matches(password_regex)){
            errorString = "Invalid password. It must containt at least 1 uppercase"+
                    "1 lowercase, 1 number and be 8 characters long";
        }else if(email == null || !email.matches(email_regex)){
            errorString = "Invalid email. The email should be of the form exmaple@123.any";
        }else if(phone_number == null || !phone_number.matches(phone_regex)){
            errorString = "Invalid phone number. Please enter a valid one";
        }else if(admin_code == null || !admin_code.matches(admin_code_regex)){
            errorString = "Invalid admin_code. The admin_code must start by a alphabetical"+
                    " character, be less than 30 characters long, without special characters";
        }else if(dob == null){
            errorString = "Invalid date format. The date must be of the form mm/dd/yyyy";
        }
 
        if (errorString == null) {
            try {
                DBUtils.registerAdmin(conn, new_admin);
                if(!EmailingSystem.sendAdministratorConfirmationEmail(complex, admin_code, email)){
                    throw new Exception("Error occured while transfering the confirmation message");
                }
            } catch (Exception e) {
                errorString = e.getMessage();
            }
        }
 
        // Store infomation to request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("new_admin", new_admin);
 
        // If error, forward to Edit page.
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/adminRegistrationView.jsp");
            dispatcher.forward(request, response);
        }
        // If everything nice.
        // Redirect to the product listing page.
        else {
            response.sendRedirect(request.getContextPath() + "/home");
        }
    }
 
}
