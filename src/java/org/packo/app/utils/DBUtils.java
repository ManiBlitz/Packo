/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.packo.app.utils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.packo.app.beans.ActionsReport;
import org.packo.app.beans.Admin;
import org.packo.app.beans.Parcel;
import org.packo.app.beans.Residence;
import org.packo.app.beans.Resident;
import org.packo.app.tools.EmailingSystem;
import org.packo.app.tools.EncryptionTool;
 
public class DBUtils {
    
    //function to register a new administrator
    
    public static void registerAdmin(Connection conn, Admin newAdmin) throws SQLException {
        
        String sql = "INSERT INTO admin_system(name,dob,position,admin_priority,address,email,phone_num,admin_code,admin_password,residence_id,registration_time) values (?,?,?,?,?,?,?,?,?,?,NOW())";
        
        PreparedStatement pstm = conn.prepareStatement(sql);
        
        pstm.setString(1, newAdmin.getAdmin_name());
        pstm.setDate(2, (Date) newAdmin.getDate_birth());
        pstm.setString(3, newAdmin.getRole());
        pstm.setInt(4, newAdmin.getAdmin_priority());
        pstm.setString(5, newAdmin.getAddress());
        pstm.setString(6, newAdmin.getEmail());
        pstm.setString(7, newAdmin.getPhone_number());
        pstm.setString(8, newAdmin.getAdmin_code());
        pstm.setString(9, newAdmin.getAdmin_password());
        pstm.setInt(10, newAdmin.getResidence_id());
        
        pstm.executeUpdate();
        
    }
    
    //function to register a new apartment complex
    
    public static void registerResidence(Connection conn, Residence complex, String encryptedCode) throws SQLException {
        
        String sql = "INSERT INTO residence_info(residence_name,residence_address,zip_code,city,state,tel_residence,email_residence,registration_time,residence_code,residence_storage_space,hold_expiration_default,max_package_weight,daily_hold_price,max_hold_time) VALUES(?,?,?,?,?,?,?,NOW(),?,1000,14,150.0,0.1,30)";
        
        PreparedStatement pstm = conn.prepareStatement(sql);
        
        pstm.setString(1, complex.getResidence_name());
        pstm.setString(2, complex.getResidence_address());
        pstm.setString(3, complex.getZip_code());
        pstm.setString(4, complex.getCity());
        pstm.setString(5, complex.getState());
        pstm.setString(6, complex.getResidence_phone());
        pstm.setString(7, complex.getResidence_email());
        pstm.setString(8, encryptedCode);
        
        pstm.executeUpdate();
        
    }
    
    //function to retrieve apartment complex data using the code generated
    
    public static Residence findComplexFromCode(Connection conn, String code) throws Exception{
        
        String sql = "SELECT * FROM residence_info WHERE residence_code = ?";
        
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1,code);
        
        ResultSet rs = pstm.executeQuery();
        
        if(rs.next()){
            
            int id = rs.getInt("residence_id");
            String residence_name = rs.getString("residence_name");
            String residence_address = rs.getString("residence_address");
            String zip_code = rs.getString("zip_code");
            String city = rs.getString("city");
            String state = rs.getString("state");
            String residence_phone = rs.getString("tel_residence");
            String residence_email = rs.getString("email_residence");
            Date residence_registration_time = rs.getDate("registration_time");
            String residence_code = rs.getString("residence_code");
            int residence_storage_space = rs.getInt("residence_storage_space");
            int hold_expiration_default = rs.getInt("hold_expiration_default");
            double max_package_weight = rs.getDouble("max_package_weight");
            double daily_hold_price = rs.getDouble("daily_hold_price");
            int max_hold_time = rs.getInt("max_hold_time");
            
            Residence complex = new Residence();
            
            complex.setResidence_id(id);
            complex.setResidence_name(residence_name);
            complex.setResidence_address(residence_address);
            complex.setZip_code(zip_code);
            complex.setState(state);
            complex.setResidence_phone(residence_phone);
            complex.setResidence_email(residence_email);
            complex.setResidence_registration_time(residence_registration_time);
            complex.setResidence_code(residence_code);
            complex.setResidence_storage_space(residence_storage_space);
            complex.setHold_expiration_default(hold_expiration_default);
            complex.setMax_package_weight(max_package_weight);
            complex.setDaily_hold_price(daily_hold_price);
            complex.setMax_hold_time(max_hold_time);
            
            return complex;
            
        }else{
        
            throw new Exception("No complex found. Invalid residence code");
           
        }
    }
    
    //function to retrieve the residence based on its id
    
    public static Residence findComplexByID(Connection conn, int residence_id) throws Exception{
        
        String sql = "SELECT * FROM residence_info WHERE residence_id = ?";
        
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1,residence_id);
        
        ResultSet rs = pstm.executeQuery();
        
        if(rs.next()){
            
            int id = rs.getInt("residence_id");
            String residence_name = rs.getString("residence_name");
            String residence_address = rs.getString("residence_address");
            String zip_code = rs.getString("zip_code");
            String city = rs.getString("city");
            String state = rs.getString("state");
            String residence_phone = rs.getString("tel_residence");
            String residence_email = rs.getString("email_residence");
            Date residence_registration_time = rs.getDate("registration_time");
            String residence_code = rs.getString("residence_code");
            int residence_storage_space = rs.getInt("residence_storage_space");
            int hold_expiration_default = rs.getInt("hold_expiration_default");
            double max_package_weight = rs.getDouble("max_package_weight");
            double daily_hold_price = rs.getDouble("daily_hold_price");
            int max_hold_time = rs.getInt("max_hold_time");
            
            Residence complex = new Residence();
            
            complex.setResidence_id(id);
            complex.setResidence_name(residence_name);
            complex.setResidence_address(residence_address);
            complex.setZip_code(zip_code);
            complex.setState(state);
            complex.setResidence_phone(residence_phone);
            complex.setResidence_email(residence_email);
            complex.setResidence_registration_time(residence_registration_time);
            complex.setResidence_code(residence_code);
            complex.setResidence_storage_space(residence_storage_space);
            complex.setHold_expiration_default(hold_expiration_default);
            complex.setMax_package_weight(max_package_weight);
            complex.setDaily_hold_price(daily_hold_price);
            complex.setMax_hold_time(max_hold_time);
            
            return complex;
            
        }else{
        
            throw new Exception("No complex found. Invalid residence code");
           
        }
        
    }
    
    // function to update residence info
    // The new informations have already been filtered
    // They will simply be entered directly to database replacing the values that were already present
    
    public static void updateResidenceInfo(Connection conn, Residence complex) throws Exception{
        
        String sql = "UPDATE residence_info "
                + "SET residence_name = ?,"
                + "tel_residence = ?,"
                + "email_residence = ?,"
                + "residence_storage_space = ?,"
                + "hold_expiration_default = ?,"
                + "max_package_weight = ?,"
                + "daily_hold_price = ?,"
                + "max_hold_time = ?";
        
        PreparedStatement pstm = conn.prepareStatement(sql);
        
        pstm.setString(1, complex.getResidence_name());
        pstm.setString(2, complex.getResidence_phone());
        pstm.setString(3, complex.getResidence_email());
        pstm.setDouble(4, complex.getResidence_storage_space());
        pstm.setInt(5, complex.getHold_expiration_default());
        pstm.setDouble(6, complex.getMax_package_weight());
        pstm.setDouble(7, complex.getDaily_hold_price());
        pstm.setInt(8, complex.getMax_hold_time());
        
        pstm.executeUpdate();
   
    }
    
    //function to retrieve an admin
    
    public static Admin findAdministrator(Connection conn, String admin_code, String admin_password) throws SQLException{
        
        String sql = "SELECT * FROM admin_system WHERE admin_code = ?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, admin_code);
 
        ResultSet rs = pstm.executeQuery();
 
        if (rs.next()) {
            
            String password = rs.getString("admin_password");
            
            if(EncryptionTool.getMatchResult(admin_password, password)){
                
                int admin_id = rs.getInt("admin_id");
                Date dob = rs.getDate("dob");
                String role = rs.getString("position");
                int priority = rs.getInt("admin_priority");
                String address = rs.getString("address");
                String email = rs.getString("email");
                String phone_num = rs.getString("phone_num");
                int residence_id = rs.getInt("residence_id");
                Date registration_time = rs.getDate("registration_time");
                
                Admin user = new Admin();
                user.setAdmin_id(admin_id);
                user.setAdmin_code(admin_code);
                user.setAdmin_password(password);
                user.setDate_birth(dob);
                user.setRole(role);
                user.setAdmin_priority(priority);
                user.setAddress(address);
                user.setEmail(email);
                user.setPhone_number(phone_num);
                user.setResidence_id(residence_id);
                user.setRegistration_time(registration_time);
                
                return user;
                
            }
            
        }
        return null;
        
    }
    
    // function to keep log of a connection
    
    public static int storeConnectionLog(Connection conn,String code,String password,boolean result,String ip_address)throws Exception{
        
        String sql = "INSERT INTO admin_connection_log(admin_code,admin_password,tentative_time,tentative_result,ip_address) VALUES(?,?,CURRENT_TIMESTAMP(),?,?)";
        
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, code);
        pstm.setString(2, EncryptionTool.getEncryption(password));
        pstm.setBoolean(3, result);
        pstm.setString(4, ip_address);
        
        pstm.executeUpdate();
        
        int connectionID = getLastConnectionInfo(conn);
        return connectionID;
          
    }
    
    public static int getLastConnectionInfo(Connection conn) throws Exception{
        
        String tentativeSql = "SELECT * FROM admin_connection_log ORDER BY tentative_id DESC LIMIT 1";
        int tentativeID = 0;
        PreparedStatement newPstm = conn.prepareStatement(tentativeSql);
        ResultSet rs = newPstm.executeQuery();
        if(rs.next()){
            tentativeID = rs.getInt("tentative_id");
        }
        
        return tentativeID;
        
    }
    
    // function to keep information about logouts
    
    public static void storeLogoutInformations(Connection conn, int adminID, int tentativeID) throws Exception{
        
        String sql = "INSERT INTO admin_logout_log(admin_id,logout_time, tentative_id) VALUES(?,NOW(),?)";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, adminID);
        pstm.setInt(2, tentativeID);
        
        pstm.executeUpdate();
        
    }
    
    
    // function to add a parcel to the list of parcels
    // generally a parcel is kept for 2 weeks
    
    public static void storeParcelInfo(Connection conn, Parcel newParcel) throws Exception{
        
        // a first verification of the address will be made. 
        // If the address does not validate the package is not save and an exception is fired up
        // Then the apartment number is checked, along with the resident name.
        // if the resident name is valid, no issue and the package information is sent to him
        // if the name is not specified, the apartment number is checked and the email is sent to all residents
        // if the apartment number and the name are not valid, an exception is fired up!:::
        
        int residence_presence = getUserPresenceNotifier(conn,newParcel.getApt_number(),newParcel.getReceiver_name(),newParcel.getResidence_id());
        
        if( residence_presence !=0){
        
        
            List<String> emails = new ArrayList<>();
        
            String sql = "INSERT INTO packages(sender_name,address_receiver,apt_num,deliver_company,delivery_time,"
                    + "hold_expiration,package_weight,secret_package,name_pickup,picked_up,pickup_code,residence_id,"
                    + "receiver_name,address_sender,time_pickup) "
                    + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement pstm = conn.prepareStatement(sql);

            pstm.setString(1,newParcel.getSender_name());
            pstm.setString(2,newParcel.getAddress_receiver());
            pstm.setString(3,newParcel.getApt_number());
            pstm.setString(4,newParcel.getDelivery_company());
            pstm.setTimestamp(5,newParcel.getDelivery_time());
            pstm.setTimestamp(6,newParcel.getExpiration_time());
            pstm.setDouble(7,newParcel.getPackage_weight());
            pstm.setBoolean(8,false);
            pstm.setString(9,null);
            pstm.setBoolean(10,false);
            pstm.setString(11,newParcel.getPickup_code());
            pstm.setInt(12,newParcel.getResidence_id());
            pstm.setString(13,newParcel.getReceiver_name());
            pstm.setString(14,newParcel.getSender_address());
            pstm.setTimestamp(15,null);

            pstm.executeUpdate();
            
            System.out.println("======>/n Package with code "+newParcel.getPickup_code()+" has been successfully saved");
            
            if(residence_presence == 2){
                //Sends email to the only person concerned
                emails.add(getResidentEmail(conn,newParcel.getResidence_id(),newParcel.getApt_number(),newParcel.getReceiver_name()));
                EmailingSystem.sendPacketDeliveryInfo(newParcel, emails);
            }else{
                //Sends email to every resident of the apartment
                emails = getApartmentResidentsEmail(conn,newParcel.getApt_number(),newParcel.getResidence_id());
                EmailingSystem.sendPacketDeliveryInfo(newParcel, emails);
            }
            
            
        }else{
            throw new Exception("The package cannot be processed. No valid resident found in machine. The package must be returned");
        }
        
    }
    
    // function to define a pickup action for a parcel
    
    public static void pickupParcel(Connection conn, int id_parcel, String name_pickup) throws Exception{
        
        String sql = "UPDATE packages "
                + "SET picked_up = TRUE, name_pickup = ?, time_pickup = CURRENT_TIMESTAMP() "
                + "WHERE idpackages = ?";
        
        PreparedStatement pstm = conn.prepareStatement(sql);
        
        pstm.setString(1, name_pickup);
        pstm.setInt(2, id_parcel);
        
        pstm.executeUpdate();
    }
    
    // function to get the email addresses of the members of an apartment
    
    public static List<String> getApartmentResidentsEmail(Connection conn, String apt_num, int residence_id) throws Exception{
     
        String sql = "SELECT email "
                + "FROM users "
                + "WHERE apt_num = ? "
                + "AND residence_id = ?";
        
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1,apt_num);
        pstm.setInt(2, residence_id);
        List<String> emails = new ArrayList<>();
        
        ResultSet rs = pstm.executeQuery();
        while(rs.next()){
            emails.add(rs.getString("email"));
        }
        
        return emails;
        
    }
    
    // function to get resident email via name and apt number
    
    public static String getResidentEmail(Connection conn, int residence_id, String apt_num, String resident_name) throws Exception{
        
        String sql = "SELECT * "
                + "FROM users "
                + "WHERE apt_num = ? "
                + "AND residence_id = ?";
        
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1,apt_num);
        //pstm.setString(2, resident_name);
        pstm.setInt(2, residence_id);
        String result = null;
        
        // We first gather the residence and apartment informations
        // Then we compare the name provided with the one in the system with the encryption tool
        
        ResultSet rs = pstm.executeQuery();
        while(rs.next()){
            if(EncryptionTool.getMatchResult(resident_name, rs.getString("resident_name"))){
                result = rs.getString("email");
                return result;
            }    
        }
        return result;
    }
    
    // function to check the user presence in the complex
    
    public static int getUserPresenceNotifier(Connection conn, String apt_num, String receiver_name, int residence_id) throws Exception{
        
        // this function checks for the apartment number
        // Then it checks for the user. If the apartment number is valid
        // it returns 1, if the apt number and the resident name are valid it returns 2
        // if none is valid, it returns 0;
        
        String sql = "SELECT * FROM users "
                + "WHERE apt_num = ? "
                + "AND residency_status = TRUE "
                + "AND residence_id = ?";
        
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, apt_num);
        pstm.setInt(2, residence_id);
 
        ResultSet rs = pstm.executeQuery();
        int counter = 0;
        
        while(rs.next()){
            if(EncryptionTool.getMatchResult(receiver_name, rs.getString("name_resident"))){
                return 2;
            }
            counter++;
        }
        
        if(counter!=0){
            return 1;
        }
        
        return 0;
        
    }
    
    //function to count the number of parcels that have not yet been picked-up
    
    public static int countRemainingParcels(Connection conn, int residence_id) throws Exception{
        
        String sql = "SELECT COUNT(*) AS parcels FROM packages "
                + "WHERE residence_id = ? AND picked_up = false ";
        
        PreparedStatement pstm = conn.prepareStatement(sql);
        
        pstm.setInt(1, residence_id);
        ResultSet rs = pstm.executeQuery();
        
        if(rs.next()){
            return rs.getInt("parcels");
        }else{
            return 0;
        }
        
    }
    
    //function to count the number of parcels delivered to the residence
    
    public static int countResidenceDeliveries(Connection conn, int residence_id) throws Exception{
        
        String sql = "SELECT COUNT(*) AS parcels FROM packages "
                + "WHERE residence_id = ?";
        
        PreparedStatement pstm = conn.prepareStatement(sql);
        
        pstm.setInt(1, residence_id);
        ResultSet rs = pstm.executeQuery();
        
        if(rs.next()){
            return rs.getInt("parcels");
        }else{
            return 0;
        }
        
    }
    
    public static int countPickedPackages(Connection conn, int residence_id) throws Exception{
        
        String sql = "SELECT COUNT(*) AS parcels FROM packages "
                + "WHERE residence_id = ? AND picked_up = true ";
        
        PreparedStatement pstm = conn.prepareStatement(sql);
        
        pstm.setInt(1, residence_id);
        ResultSet rs = pstm.executeQuery();
        
        if(rs.next()){
            return rs.getInt("parcels");
        }else{
            return 0;
        }
        
    }
    
    // function to count the number of overdue parcels
    
    public static int countOverdueParcels(Connection conn, int residence_id) throws Exception{
        
        return getOverdueParcels(conn,residence_id).size();
    }
    
    //function to delete a particular parcel
    
    public static void deleteParcel(Connection conn, int parcel_id) throws Exception{
        
        String sql = "DELETE FROM packages "
                + "WHERE idpackages = ?";
        
        PreparedStatement pstm = conn.prepareStatement(sql);
        
        pstm.setInt(1, parcel_id);
        pstm.executeUpdate();
        
    }
    
    //function to get the list of parcels that have not yet been picked up
    
    public static List<Parcel> getListParcels(Connection conn,int residence_id)throws Exception{
        
        List<Parcel> packages = new ArrayList<Parcel>();
        String sql = "SELECT * FROM packages "
                + "WHERE residence_id = ? AND picked_up = false "
                + "ORDER BY idpackages DESC LIMIT 50";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, residence_id);
 
        ResultSet rs = pstm.executeQuery();
        
        while(rs.next()){
            int id_parcel = rs.getInt("idpackages");
            String apt_number = rs.getString("apt_num");
            String sender_name = rs.getString("sender_name");
            String receiver_name = rs.getString("receiver_name");
            String address_receiver = rs.getString("address_receiver");
            String delivery_company = rs.getString("deliver_company");
            Timestamp delivery_time = rs.getTimestamp("delivery_time");
            Timestamp hold_expiration = rs.getTimestamp("hold_expiration");
            double package_weight = rs.getDouble("package_weight");
            boolean secret_package = rs.getBoolean("secret_package");
            String name_pickup = rs.getString("name_pickup");
            boolean picked_up = rs.getBoolean("picked_up");
            String pickup_code = rs.getString("pickup_code");
            int residenceID = rs.getInt("residence_id");
            String sender_address = rs.getString("address_sender");
            Timestamp time_pickup = rs.getTimestamp("time_pickup");
            
            Parcel pack = new Parcel();
            pack.setParcel_id(id_parcel);
            pack.setApt_number(apt_number);
            pack.setSender_name(sender_name);
            pack.setReceiver_name(receiver_name);
            pack.setAddress_receiver(address_receiver);
            pack.setDelivery_company(delivery_company);
            pack.setDelivery_time(delivery_time);
            pack.setExpiration_time(hold_expiration);
            pack.setPackage_weight(package_weight);
            pack.setSecret_package(secret_package);
            pack.setName_pickup(name_pickup);
            pack.setPicked_up(picked_up);
            pack.setPickup_code(pickup_code);
            pack.setResidence_id(residenceID);
            pack.setSender_address(sender_address);
            pack.setPickup_time(time_pickup);
            
            packages.add(pack);
        }
        
        return packages;
        
    }
    
    //function to get the list of parcels not yet picked up and over the hold limit
    
    public static List<Parcel> getOverdueParcels(Connection conn, int residence_id) throws Exception{
        
        List<Parcel> latePackages = new ArrayList<Parcel>();
        String sql = "SELECT * FROM packages "
                + "LEFT JOIN actions on packages.idpackages = actions.idpackages "
                + "WHERE actions.idpackages IS NULL AND packages.residence_id = ? AND packages.picked_up = false "
                + "ORDER BY packages.hold_expiration ASC";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, residence_id);
 
        ResultSet rs = pstm.executeQuery();
        
        while(rs.next()){
            int id_parcel = rs.getInt("idpackages");
            String apt_number = rs.getString("apt_num");
            String sender_name = rs.getString("sender_name");
            String receiver_name = rs.getString("receiver_name");
            String address_receiver = rs.getString("address_receiver");
            String delivery_company = rs.getString("deliver_company");
            Timestamp delivery_time = rs.getTimestamp("delivery_time");
            Timestamp hold_expiration = rs.getTimestamp("hold_expiration");
            double package_weight = rs.getDouble("package_weight");
            boolean secret_package = rs.getBoolean("secret_package");
            String name_pickup = rs.getString("name_pickup");
            boolean picked_up = rs.getBoolean("picked_up");
            String pickup_code = rs.getString("pickup_code");
            int residenceID = rs.getInt("residence_id");
            String sender_address = rs.getString("address_sender");
            Timestamp time_pickup = rs.getTimestamp("time_pickup");
            
            Parcel pack = new Parcel();
            pack.setParcel_id(id_parcel);
            pack.setApt_number(apt_number);
            pack.setSender_name(sender_name);
            pack.setReceiver_name(receiver_name);
            pack.setAddress_receiver(address_receiver);
            pack.setDelivery_company(delivery_company);
            pack.setDelivery_time(delivery_time);
            pack.setExpiration_time(hold_expiration);
            pack.setPackage_weight(package_weight);
            pack.setSecret_package(secret_package);
            pack.setName_pickup(name_pickup);
            pack.setPicked_up(picked_up);
            pack.setPickup_code(pickup_code);
            pack.setResidence_id(residenceID);
            pack.setSender_address(sender_address);
            pack.setPickup_time(time_pickup);
            
            if(pack.getExpiration_time().compareTo(new Timestamp(System.currentTimeMillis())) < 0){
               latePackages.add(pack); 
            }

        }
        
        return latePackages;   
    }
    
    //function to get the parcel via its pick_up code
    
    public static Parcel getParcelByCode(Connection conn, String parcelCode) throws Exception{
        
        String sql = "SELECT * FROM packages "
                + "WHERE pickup_code = ?";
        
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, parcelCode);
 
        ResultSet rs = pstm.executeQuery();
        
        if(rs.next()){
            int id_parcel = rs.getInt("idpackages");
            String apt_number = rs.getString("apt_num");
            String sender_name = rs.getString("sender_name");
            String receiver_name = rs.getString("receiver_name");
            String address_receiver = rs.getString("address_receiver");
            String delivery_company = rs.getString("deliver_company");
            Timestamp delivery_time = rs.getTimestamp("delivery_time");
            Timestamp hold_expiration = rs.getTimestamp("hold_expiration");
            double package_weight = rs.getDouble("package_weight");
            boolean secret_package = rs.getBoolean("secret_package");
            String name_pickup = rs.getString("name_pickup");
            boolean picked_up = rs.getBoolean("picked_up");
            String pickup_code = rs.getString("pickup_code");
            int residenceID = rs.getInt("residence_id");
            String sender_address = rs.getString("address_sender");
            Timestamp time_pickup = rs.getTimestamp("time_pickup");
            
            Parcel pack = new Parcel();
            pack.setParcel_id(id_parcel);
            pack.setApt_number(apt_number);
            pack.setSender_name(sender_name);
            pack.setReceiver_name(receiver_name);
            pack.setAddress_receiver(address_receiver);
            pack.setDelivery_company(delivery_company);
            pack.setDelivery_time(delivery_time);
            pack.setExpiration_time(hold_expiration);
            pack.setPackage_weight(package_weight);
            pack.setSecret_package(secret_package);
            pack.setName_pickup(name_pickup);
            pack.setPicked_up(picked_up);
            pack.setPickup_code(pickup_code);
            pack.setResidence_id(residenceID);
            pack.setSender_address(sender_address);
            pack.setPickup_time(time_pickup);
            
            return pack;
            
        }
        
        return null;

    }
    
    // function to get the parcel by ID
    
    public static Parcel getParcelByID(Connection conn, int parcelID) throws Exception{
        
        String sql = "SELECT * FROM packages "
                + "WHERE idpackages = ?";
        
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, parcelID);
 
        ResultSet rs = pstm.executeQuery();
        
        if(rs.next()){
            int id_parcel = rs.getInt("idpackages");
            String apt_number = rs.getString("apt_num");
            String sender_name = rs.getString("sender_name");
            String receiver_name = rs.getString("receiver_name");
            String address_receiver = rs.getString("address_receiver");
            String delivery_company = rs.getString("deliver_company");
            Timestamp delivery_time = rs.getTimestamp("delivery_time");
            Timestamp hold_expiration = rs.getTimestamp("hold_expiration");
            double package_weight = rs.getDouble("package_weight");
            boolean secret_package = rs.getBoolean("secret_package");
            String name_pickup = rs.getString("name_pickup");
            boolean picked_up = rs.getBoolean("picked_up");
            String pickup_code = rs.getString("pickup_code");
            int residenceID = rs.getInt("residence_id");
            String sender_address = rs.getString("address_sender");
            Timestamp time_pickup = rs.getTimestamp("time_pickup");
            
            Parcel pack = new Parcel();
            pack.setParcel_id(id_parcel);
            pack.setApt_number(apt_number);
            pack.setSender_name(sender_name);
            pack.setReceiver_name(receiver_name);
            pack.setAddress_receiver(address_receiver);
            pack.setDelivery_company(delivery_company);
            pack.setDelivery_time(delivery_time);
            pack.setExpiration_time(hold_expiration);
            pack.setPackage_weight(package_weight);
            pack.setSecret_package(secret_package);
            pack.setName_pickup(name_pickup);
            pack.setPicked_up(picked_up);
            pack.setPickup_code(pickup_code);
            pack.setResidence_id(residenceID);
            pack.setSender_address(sender_address);
            pack.setPickup_time(time_pickup);
            
            return pack;
            
        }
        return null;
    }
    
    
    
    // function to increase the hold time of the parcel
    // this function already assumes that the new hold time has already been calculated
    
    public static void setNewHoldExpirationTime(Connection conn, int parcel_id, Timestamp newHoldExpirationTime) throws Exception{
        
        String sql = "UPDATE packages "
                + "SET hold_expiration = ? "
                + "WHERE idpackages = ?";
        
        PreparedStatement pstm = conn.prepareStatement(sql);
        
        pstm.setTimestamp(1, newHoldExpirationTime);
        pstm.setInt(2, parcel_id);
        
        pstm.executeUpdate();
        
        
        Parcel newParcel = getParcelByID(conn, parcel_id);
        List<String>emails = new ArrayList<>();
        
        int residence_presence = getUserPresenceNotifier(conn,newParcel.getApt_number(),newParcel.getReceiver_name(),newParcel.getResidence_id());
        
        if(residence_presence == 2){
            //Sends email to the only person concerned
            emails.add(getResidentEmail(conn,newParcel.getResidence_id(),newParcel.getApt_number(),newParcel.getReceiver_name()));
            EmailingSystem.sendHoldExtensionConfirmation(newParcel, emails);
        }else{
            //Sends email to every resident of the apartment
            emails = getApartmentResidentsEmail(conn,newParcel.getApt_number(),newParcel.getResidence_id());
            EmailingSystem.sendHoldExtensionConfirmation(newParcel, emails);
        }
        
    }
    
    //function to add actions notification
    
    public static void addActionReport(Connection conn, ActionsReport newAction)throws Exception{
        
        String sql = "INSERT INTO actions(idusers, idpackages, action_time, package_pickup, name_pickup_order,"
                + "package_return, address_return, delivery_to_apt, forward_order,name_forward,complete_address_forward"
                + "admin_id,viewed)"
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        
        PreparedStatement pstm = conn.prepareStatement(sql);
        
        pstm.setInt(1, newAction.getId_user());
        pstm.setInt(2, newAction.getAdmin_id());
        pstm.setTimestamp(3, newAction.getAction_time());
        pstm.setBoolean(4,newAction.getType() == ActionsReport.ORDER_PICKUP);
        pstm.setString(5, newAction.getName_pickup_order());
        pstm.setBoolean(6, newAction.getType() == ActionsReport.PACKAGE_RETURN);
        pstm.setString(7, newAction.getAddress_return());
        pstm.setBoolean(8,newAction.getType() == ActionsReport.FORWARD_ORDER);
        pstm.setString(9, newAction.getName_forward());
        pstm.setString(10, newAction.getComplete_address_forward());
        pstm.setInt(11, newAction.getAdmin_id());
        pstm.setBoolean(12, newAction.isViewed());
        
        pstm.executeUpdate();
        
    }
    
    //function that helps adding users to the system
    
    public static void registerNewUser(Connection conn, Residence complex, Resident user) throws Exception{
        
        String sql = "INSERT INTO users(name_resident, email, tel, apt_num, residency_status, residence_id) "
                + "VALUES(?,?,?,?,TRUE,?)";
        
        PreparedStatement pstm = conn.prepareStatement(sql);
        
        pstm.setString(1, user.getName_resident());
        pstm.setString(2, user.getEmail());
        pstm.setString(3, user.getTel());
        pstm.setString(4, user.getApt_num());
        pstm.setInt(5, complex.getResidence_id());
        
        pstm.executeUpdate();
        
        // Then we send a mail to the user saying that he has been added to the service
        // And therefore receive notification whenever he receives a new parcel.
        // and within that email the user can specify that he wants to be removed from the system
        
        EmailingSystem.sendUserRegistrationEmail(complex, user);
         
    }
    
    // function that helps removing users from the database
    
    public static void deleteUser(Connection conn, int resident_id) throws Exception{
        
        String sql = "DELETE FROM users "
                + "WHERE idusers = ?";
        
        PreparedStatement pstm = conn.prepareStatement(sql);
        
        pstm.setInt(1, resident_id);
        pstm.executeUpdate();
        
        // An email is sent to the resident to say that he has been successfully deleted
        // along with all his informations
        // And he can only get back on the platform by directly contacting his leasing administrator
        
    }
    
    //function to get the different actions set on packages
    
    public static List<ActionsReport> getNonCompletedActions(Connection conn, int residence_id) throws Exception{
        
        List<ActionsReport> notifications = new ArrayList<ActionsReport>();
        String sql = "SELECT * FROM actions WHERE viewed = false AND residence_id = ? ORDER BY action_time DESC";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, residence_id);
 
        ResultSet rs = pstm.executeQuery();
        
        while(rs.next()){
            
            int idusers = rs.getInt("idusers");
            int idpackages = rs.getInt("idpackages");
            Timestamp action_time = rs.getTimestamp("action_time");
            boolean package_pickup = rs.getBoolean("package_pickup");
            String name_package_pickup = rs.getString("package_pickup_name");
            boolean package_return = rs.getBoolean("package_return");
            String address_return = rs.getString("address_return");
            boolean delivery_to_apt = rs.getBoolean("deliver_to_apt");
            boolean forward_order = rs.getBoolean("forward_order");
            String name_forward = rs.getString("name_forward");
            String complete_address_forward = rs.getString("complete_address_forward");
            int admin_id = rs.getInt("admin_id");
            boolean viewed = rs.getBoolean("viewed");
            
            ActionsReport action = new ActionsReport();
            action.setId_user(idusers);
            action.setId_packages(idpackages);
            action.setAction_time(action_time);
            action.setAdmin_id(admin_id);
            
            if(package_pickup){
                action.setType(ActionsReport.ORDER_PICKUP);
                action.setName_pickup_order(name_package_pickup);
            }else if(package_return){
                action.setType(ActionsReport.PACKAGE_RETURN);
                action.setAddress_return(address_return);
            }else if(delivery_to_apt){
                action.setType(ActionsReport.DELIVERY_TO_APT);
            }else if(forward_order){
                action.setType(ActionsReport.FORWARD_ORDER);
                action.setName_forward(name_forward);
                action.setComplete_address_forward(complete_address_forward);
            }
            
            action.setViewed(viewed);
            
            notifications.add(action);
             
        }
        
        return notifications;
    }
 
}
