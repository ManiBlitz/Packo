/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.packo.app.tools;

import java.util.Random;
import java.util.UUID;


/**
 *
 * @author ADMIN
 */
public class UniqueIDGenerator {
    
    //random ID generator used for complex registration
    
    public static String getUniqueID(){
        
        UUID uniqueKey = UUID.randomUUID();
        return uniqueKey.toString();
        
    }
    
    //random ID generator used for the parcels pickup
    
    public static String getRandomPickupCode(){
        
        //The pickup code will be a random association of 8 alphanumeric characters
        
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < 8; i++)
            sb.append(chars[rnd.nextInt(chars.length)]);
        
        return sb.toString();
        
    }
    
}
