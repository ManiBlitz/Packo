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
 
import org.packo.app.beans.Residence;
import org.packo.app.tools.EmailingSystem;
import org.packo.app.tools.EncryptionTool;
import org.packo.app.tools.UniqueIDGenerator;
import org.packo.app.utils.DBUtils;
import org.packo.app.utils.MyUtils;
 
@WebServlet(urlPatterns = { "/residence" })
public class ResidenceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public ResidenceServlet() {
        super();
    }
 
    // Show product creation page.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/residenceView.jsp");
        dispatcher.forward(request, response);
    }
 
    // When the user enters the product information, and click Submit.
    // This method will be called.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
        
        String name = (String) request.getParameter("residence_name");
        String address = (String) request.getParameter("residence_address");
        String zip_code = (String) request.getParameter("residence_zip_code");
        String city = (String) request.getParameter("residence_city");
        String state = (String) request.getParameter("residence_state");
        String email = (String) request.getParameter("residence_email");
        String phone_number = (String) request.getParameter("residence_num");
        
        Residence complex = new Residence();
        
        complex.setResidence_name(name);
        complex.setResidence_address(address);
        complex.setZip_code(zip_code);
        complex.setCity(city);
        complex.setState(state);
        complex.setResidence_email(email);
        complex.setResidence_phone(phone_number);
        
        
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
            errorString = "You must provide a name";
        }else if(email == null || !email.matches(email_regex)){
            errorString = "Invalid email. The email should be of the form exmaple@123.any";
        }else if(phone_number == null || !phone_number.matches(phone_regex)){
            errorString = "Invalid phone number. Please enter a valid one";
        }
 
        if (errorString == null) {
            try {
                String code = UniqueIDGenerator.getUniqueID();
                DBUtils.registerResidence(conn, complex,code);
                if(!EmailingSystem.sendResidenceRegistrationEmail(complex.getResidence_email(),complex.getResidence_name(),code)){
                    throw new Exception("An error occured during the transmission of the confirmation email!");
                }
                
            } catch (Exception e) {
                errorString = e.getMessage();
            }
        }
 
        // Store infomation to request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("new_residence", complex);
 
        // If error, forward to Edit page.
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/residenceView.jsp");
            dispatcher.forward(request, response);
        }
        // If everything nice.
        // Redirect to the product listing page.
        else {
            response.sendRedirect(request.getContextPath() + "/confirmation");
        }
    }
 
}
