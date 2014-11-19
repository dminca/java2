/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author student
 */
public class TransactionManager {
    private static TransactionManager singleton;
    
    private PreparedStatement start;
    private PreparedStatement commit;
    private PreparedStatement rollback;
    
    private TransactionManager(){
        try {
            Connection con = ConnectionController.getInstance().getConnection();
            start = con.prepareStatement("START TRANSACTION");
            commit = con.prepareStatement("COMMIT");
            rollback = con.prepareStatement("ROLLBACK");
        } catch (SQLException ex) {
            Logger.getLogger(TransactionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static TransactionManager getInstance(){
        if(singleton == null){
            singleton = new TransactionManager();
        }
        return singleton;
    }
    
    public void startTransaction() throws SQLException{
        start.execute();
    }
    
    public void releaseTransaction(boolean commit) throws SQLException{
        if(commit){
            this.commit.execute();
        }else{
            this.rollback.execute();
        }
    }
}
