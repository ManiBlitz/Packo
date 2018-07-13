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
 
import org.packo.app.beans.Residence;
import org.packo.app.tools.EmailingSystem;
import org.packo.app.utils.DBUtils;
import org.packo.app.utils.MyUtils;
 
@WebServlet(urlPatterns = { "/confirmation" })
public class ResidenceConfirmationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public ResidenceConfirmationServlet() {
        super();
    }
 
    // Show product creation page.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/residenceConfirmationView.jsp");
        dispatcher.forward(request, response);
    }
 
    // When the user enters the product information, and click Submit.
    // This method will be called.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);

        
        String codeResidence = request.getParameter("code_residence");
        
        String errorString = null;
        
        Residence complex_data = new Residence();
 
        /*
        In the case the code correspond to one appartment complex code
        The complex information are transmitted to the next page for admin creation
        A confirmation email is also sent
        */
 
        if (errorString == null) {
            try {
                Residence complex = DBUtils.findComplexFromCode(conn, codeResidence);   
                complex_data.setResidence_code(complex.getResidence_code());
                System.out.println("Residence code "+complex_data.getResidence_code()+" verified");
                EmailingSystem.sendConfirmationEmail(complex);
            } catch (Exception e) {
                errorString = e.getMessage();
            }
        }
 
        // Store infomation to request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        HttpSession session = request.getSession();
        session.setAttribute("complex_data", complex_data);
        
 
        // If error, forward to Edit page.
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/residenceConfirmationView.jsp");
            dispatcher.forward(request, response);
        }
        // If everything nice.
        // Redirect to the product listing page.
        else {
            response.sendRedirect(request.getContextPath() + "/admin");
        }
    }
 
}
