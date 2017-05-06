/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import utils.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author NamWin
 */
public class DBConnector {

    private static Connection connect;
    private static String url;
    private static String user;
    private static String pass;
    private static String driver;

    private DBConnector() {
    }

    private static Connection connection = null;

    static public Connection getConnection() {
        url = "jdbc:sqlserver://localhost:1433;databaseName=AlluringDecors";
        user = "sa";
        pass = "123456";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connect = DriverManager.getConnection(url, user, pass);
        } catch (SQLException ex) {
            return null;
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Không tải được driver của Microsoft SQL Server", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return connect;

//            try {
//                Class.forName("org.apache.derby.jdbc.ClientDriver");
//                connection = DriverManager.getConnection("jdbc:derby://localhost:1527/EJBTest", "a", "a");
//            } catch (SQLException | ClassNotFoundException ex) {
//                Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        return connection;
    }

    static public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
