/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NamWin
 */
public class DBConnector {

    private  DBConnector() {
    }

    private static Connection connection = null;

    static public Connection getConnection() {

            
            try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                connection = DriverManager.getConnection("jdbc:derby://localhost:1527/EJBTest", "a", "a");
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
            }
        return connection;
    }
    
    static public void closeConnection(){
        if(connection!=null){
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
