/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.packo.app.tools;

/**
 *
 * @author ADMIN
 */

import java.security.NoSuchAlgorithmException;

public class EncryptionTool {
    
    public static String getEncryption(String word) throws NoSuchAlgorithmException{
        String generatedSecuredPasswordHash = BCrypt.hashpw(word, BCrypt.gensalt(12));
        return generatedSecuredPasswordHash;
    }
    
    public static boolean getMatchResult(String word_unencrypted, String word_encrypted){
        boolean matched = BCrypt.checkpw(word_unencrypted, word_encrypted);
        return matched;
    }
    
}
