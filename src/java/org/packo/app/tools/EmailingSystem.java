/*
 *
 */
package org.packo.app.tools;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.servlet.http.*;
import org.packo.app.beans.Parcel;
import org.packo.app.beans.Residence;
import org.packo.app.beans.Resident;

/**
 *
 * @author ADMIN
 */
public class EmailingSystem {
    
    private static final String FROM = "packoservices@gmail.com";
    private static final String USERNAME = "packoservices@gmail.com";
    private static final String PASSWORD = "User426750!";
    private static final String LINK_TO_PAGE = "http://localhost:8080/packo/confirmation";
    
    //======>CONTENT FUNCTIONs
    //Each email service has a full HTML content
    //each therefore needs to be properly defined in a function
    //and then associated to the right email system
    
    private static String confirmationEmailContent(String residence_name){ //The parameters will be defined later
        
        String header = "<br><h2>Registration confirmed</h2>";
        String text_content = "<br><p>The complex apartment <b>"+residence_name+"</b> has been successfully registered to our database and ready to launch."
                +" Now you need to define a prime administrator account and set up the residents informations";
        String signature = "<br><br><b>Packo Services<b>";
        
        String text = header+text_content+signature;
        
        return text;
        
    }
    
    //Registration email content:
    //This email will provide a code after the creation of the apartment complex
    //The code must be copied and compared withing the system to proceed
    //Once the user enters this code the complex is verified 
    //He can therefore start creating administrators
    
    private static String registrationEmailContent(String residence_name,String confirmation_code){
        
        String header = "<br><h2>Complex Successfully created</h2>";
        String text_content = "<br><p>Your complex apartment <b>"+residence_name+"</b> has been successfully created."
                +" Now we need you to copy the following code and go to the link described below to register the apartment complex."
                +" Here is the code<p>";
        String code = "<br><b>"+confirmation_code+"</b>";
        String link = "<br><p>Use the following link: <a href=\""+LINK_TO_PAGE+"\">"+LINK_TO_PAGE+"</a></p>";
        String signature = "<br><br><b>Packo Services<b>";
        
        String text = header+text_content+code+link+signature;
        return text;
        
    }
    
    //Adminsistrator confirmation email content:
    //This email will simply address the administrator directly providing him its code
    //The eamil also contains the different informations of the residence
    
    private static String administratorRegistrationEmailContent(Residence complex, String admin_code){
        
        String header = "<br><h2>Administrator successfully generated</h2>";
        String text_content = "<br><p>The administrator with the code: <b>"+admin_code+"</b> has been successfully generated for the residence"
                + " of the name <b>"+complex.getResidence_name()+"</b> with the following charateristics:</p>";
        String list_characteristics = "<ul>"
                + "<li><b>Address</b>: "+complex.getResidence_address()+"</li>"
                + "<li><b>Zip code</b>: "+complex.getZip_code()+"</li>"
                + "<li><b>State</b>: "+complex.getState()+"</li>"
                + "<li><b>Email</b>: "+complex.getResidence_email()+"</li>"
                + "<li><b>Phone</b>: "+complex.getResidence_phone()+"</li></ul>";
        String signature = "<br><br><b>Packo Services<b>";
        
        String text = header+text_content+list_characteristics+signature;
        return text;
        
    }
    
    //Hold extension email content:
    //this email is addressed to the residence email providing the name of the resident
    //its apartment number, its email, the package id and the hold extension proposed
    
    private static String holdExtensionEmailContent(Resident user, String parcel_code, int days){
        
        String header = "<br><h2>The resident "+user.getName_resident()+" just sent a request for hold extension</h2>";
        String text_content = "<br><p>The resident <b> "+user.getName_resident()+"</b> requested a hold extension for the parcel of code <b>"+parcel_code+"</b> by a duration of "
                + "<b>"+days+"</b> days. This message is only at title of information. You can directly respond to the resident using "
                + "the provided email</p>";
        String list_characteristics = "<ul>"
                + "<li><b>Apartment number</b>: "+user.getApt_num()+"</li>"
                + "<li><b>Email</b>: "+user.getEmail()+"</li></ul>";
        String signature = "<br><br><b>Packo Services<b>";
        
        
        String text = header+text_content+list_characteristics+signature;
        return text;
    }
    
    //Package delivery content
    //This email is addressed to the resident(s) whos package has arrived to the residence
    //It contains the package details along with the package pickup code
    
    private static String packageDeliveryEmailContent(Parcel pack){
        
        String header = "<br><h2>A parcel has just arrived for you</h2>";
        String text_content = "<br><p>A parcel has been register and is awaiting to be picked up by you."
                + " Some important informations have been listed for your information</p>";
        String list_characteristics = "<ul>"
                + "<li><b>Sender Name</b>: "+pack.getSender_name()+"</li>"
                + "<li><b>Receiver Name</b>: "+pack.getReceiver_name()+"</li>"
                + "<li><b>Sender Address</b>: "+pack.getSender_address()+"</li>"
                + "<li><b>Apartment Number</b>: "+pack.getApt_number()+"</li>"
                + "<li><b>Delivery Company</b>: "+pack.getDelivery_company()+"</li>"
                + "<li><b>Delivery Time</b>: "+pack.getDelivery_time()+"</li></ul>";
        String code = "<br><p>The following code must be presented before pickup</p>"
                + "<h3>"+pack.getPickup_code()+"</h3>"
                + "<p>The hold for this package ends on <b>"+pack.getExpiration_time()+"</b></p>";
        String signature = "<br><br><b>Packo Services<b>";
        
        
        String text = header+text_content+list_characteristics+code+signature;
        return text;
        
        
    }
    
    //Hold Extension email content
    //This email is addressed to the resident(s) whose package hold has been extended
    //It solely contains package details
    
    private static String holdExtensionEmailContent(Parcel pack){
        
        String header = "<br><h2>Your parcel hold has been extended</h2>";
        String text_content = "<br><p>Your parcel with parcel code "
                + "<b>"+pack.getPickup_code()+"</b> delivered on "
                + "<b>"+pack.getDelivery_time()+"</b> has had its hold extended to date "
                + "<b>"+pack.getExpiration_time()+"</b></p>"; 
        String signature = "<br><br><b>Packo Services<b>";
        
        
        String text = header+text_content+signature;
        return text;
        
    }
    
    // User registration email content
    // This email will be sent to the user when he is registered by his residence
    
    private static String userRegistrationEmailContent(Residence complex, Resident user){
        
        String header = "<br><h2>Your residence added you to Packo Services</h2>";
        String text_content = "<br><p>Greetings "+user.getName_resident()+",<br>"
                + "As a resident of the residential complex called"
                + " <b>"+complex.getResidence_name()+"</b>, you have been registered"
                + " by the leasing agent to this parcel delivery notification service."
                + " This service is completely free. It"
                + " adds no cost to you rent or bills. You will be notified via email "
                + "whenever you receive a parcel delivered at your leasing office."
                + " Here are some informations concerning your residence</p>";
        String list_characteristics = "<ul>"
                + "<li><b>Residence Name</b>: "+complex.getResidence_name()+"</li>"
                + "<li><b>Residence Address</b>: "+complex.getResidence_address()+"</li>"
                + "<li><b>Residence Email</b>: "+complex.getResidence_email()+"</li>"
                + "<li><b>Residence Phone</b>: "+complex.getResidence_phone()+"</li></ul>";
        String signature = "<br><br><b>Packo Services<b>";
        
        
        String text = header+text_content+list_characteristics+signature;
        return text;
        
    }
    
    //======>Emailing functions
    
    //function to send a confirmation email each time an admin account is created
    //The confirmation email is sent to the administrator email and is needed to activate the account
    //The email will have a link that will verify the account
    
    public static boolean sendConfirmationEmail(Residence complex){

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(USERNAME, PASSWORD);
                }
            });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(complex.getResidence_email()));
            message.setSubject("Registration Confirmation");
            message.setContent(confirmationEmailContent(complex.getResidence_name()),"text/html");

            Transport.send(message);

            System.out.println("Message successfully sent to "+complex.getResidence_email());
            return true;

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    
    // function to send a registration confirmation email  to the resident
    // the registration email will give information about the residence
    
    public static boolean sendUserRegistrationEmail(Residence complex, Resident user){
        
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(USERNAME, PASSWORD);
                }
            });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(user.getEmail()));
            message.setSubject("Resident Registration ");
            message.setContent(userRegistrationEmailContent(complex,user),"text/html");

            Transport.send(message);

            System.out.println("Message successfully sent to "+complex.getResidence_email());
            return true;

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        
    }
    
    //function to send a registration confirmation to the housing email
    //This email just helps get back to the top administrator page and setup the top priority administrator
    
    public static boolean sendResidenceRegistrationEmail(String to,String complex_name, String code){
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(USERNAME, PASSWORD);
                }
            });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject("Registration Confirmation");
            message.setContent(registrationEmailContent(complex_name,code),"text/html");

            Transport.send(message);

            System.out.println("Message successfully sent to "+to);
            return true;

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    } 
    
    //function to send the email notification to the admin concerning his position
    //The administrator will be informed via his email. no confirmation needed.
    //The email will contain the complex information and the administrator code
    
    public static boolean sendAdministratorConfirmationEmail(Residence complex, String admin_code, String admin_email){
        
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(USERNAME, PASSWORD);
                }
            });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(admin_email));
            message.setSubject("Registration Confirmation");
            message.setContent(administratorRegistrationEmailContent(complex,admin_code),"text/html");

            Transport.send(message);

            System.out.println("Message successfully sent to "+admin_email);
            return true;

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        
    }
    
    //function to send the email notification to the resident concerning his packet
    //This communication contains all of the necessary info and the options for the resident
    
    public static boolean sendPacketDeliveryInfo(Parcel pack,List<String>emails){
        
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(USERNAME, PASSWORD);
                }
            });

        try {
            
            
            for(String email: emails){
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(FROM));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(email));
                message.setSubject("Package Delivered");
                message.setContent(packageDeliveryEmailContent(pack),"text/html");

                Transport.send(message);

                System.out.println("Message successfully sent to "+email);   
            }
            
            return true;

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        
    }
    
    //This email is sent to users desiring to register to the system
    
    public static boolean sendUserRegistrationEmail(){
        return true;
    }
    
    //This email is sent ot the resident(s) concerned after the parcel hold extension is operated
    //It requires the users email along with the Parcel object
    //This email does not have any additional information.
    
    public static boolean sendHoldExtensionConfirmation(Parcel pack,List<String>emails){
        
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(USERNAME, PASSWORD);
                }
            });

        try {
            
            
            for(String email: emails){
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(FROM));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(email));
                message.setSubject("Parcel Hold Extended");
                message.setContent(holdExtensionEmailContent(pack),"text/html");

                Transport.send(message);

                System.out.println("Message successfully sent to "+email);   
            }
            
            return true;

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        
        
        
    }
    
    //This email is to send user hold extension request
    //These request will be submitted directly to the leasing office email
    //Then the leasing office can either accept and extend or refuse
    //The response of the leasing office is independent of the application
    
    public static boolean sendPackageHoldExtensionRequest(Resident user, String package_code, String residence_email, int days){
        
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(USERNAME, PASSWORD);
                }
            });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(residence_email));
            message.setSubject("Hold Extension Request");
            message.setContent(holdExtensionEmailContent(user,package_code,days),"text/html");

            Transport.send(message);

            System.out.println("Message successfully sent to "+residence_email);
            return true;

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        
    }
    
    //======>TEST FUNCTION
    
    public static void main(String[]args){
        
        Resident me = new Resident();
        me.setEmail("tibakevin@gmail.com");
        me.setName_resident("Kevin Tiba");
        me.setApt_num("6208");
        
        
        boolean result = EmailingSystem.sendPackageHoldExtensionRequest(me,UniqueIDGenerator.getRandomPickupCode(),"tibakevin@gmail.com",14);
    }
    
}
