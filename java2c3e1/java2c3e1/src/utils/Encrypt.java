/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author student
 */
public final class Encrypt {
    private Encrypt(){}
    
    
    public static String encryptWithMD5(String parola){
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte [] p = parola.getBytes();
            byte [] digested = digest.digest(p);
            
            StringBuilder sb = new StringBuilder();
            
            for(int i=0;i<digested.length;i++){
                sb.append(Integer.toHexString(0xFF & digested[i]));
            }
            
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Encrypt.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
}
