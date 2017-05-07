/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model_controller;

import model_controller.*;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import utils.DBConnector;

/**
 *8
 * @author NamWin
 */
@Named(value = "accountBean")
@SessionScoped
public class AccountBean implements Serializable{

    
    //<editor-fold desc="DTO" defaultstate="collapsed">
    public AccountBean() {
    }
     int userID,roleID;
    String  username,password;
    AccountBean selectedAccount;

    public AccountBean getSelectedAccount() {
        return selectedAccount;
    }

    public void setSelectedAccount(AccountBean selectedAccount) {
        this.selectedAccount = selectedAccount;
    }
    

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    //</editor-fold>
    
    // <editor-fold desc="DAO" defaultstate="collapsed">
    private final String sqlCreate = "INSERT INTO Account VALUES(?, ?, ?, ?)";
    private final String sqlRead = "SELECT * FROM Account";
    private final String sqlReadById = "SELECT * FROM Account WHERE UserID = ?";
    private final String sqlUpdate = "SELECT * FROM Account WHERE UserID = ?";
    private final String sqlDelete = "DELETE FROM Account WHERE UserID = ?";
    private ResultSet rs;
    private PreparedStatement pst;
    
    /**
     * Create new Customer
     */
    public boolean create() {
      
        try {
            pst = DBConnector.getConnection().prepareStatement(sqlCreate);
            pst.setInt(1, this.getUserID());
            pst.setString(2, this.getUsername());
            pst.setString(3, this.getPassword());
            pst.setInt(4, this.getRoleID());
           
            if (pst.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            return false;
        } finally {
            try {
                pst.close();
                DBConnector.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(AccountBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    
    /**
     * read all Customer in database
     * @return 
     */
    public ArrayList<AccountBean> readAll() {
        try {
            ArrayList<AccountBean> cusList = new ArrayList<AccountBean>();
            rs = DBConnector.getConnection().createStatement().executeQuery(sqlRead);
            while (rs.next()) {
                AccountBean cus = new AccountBean();
                cus.setUserID(rs.getInt("UserID"));
                cus.setUsername(rs.getString("Username"));
                cus.setRoleID(rs.getInt("RoleID"));
                cus.setPassword(rs.getString("Password"));
                cusList.add(cus);
            }
            return cusList;
        } catch (SQLException ex) {
            Logger.getLogger(AccountBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                DBConnector.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(AccountBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    /**
     * Read Customer base id
     */
    public AccountBean readById(int id) {

        try {
            pst = DBConnector.getConnection().prepareStatement(sqlReadById, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if (rs.first()) {
                 AccountBean cus = new AccountBean();
                cus.setUserID(rs.getInt("UserID"));
                cus.setUsername(rs.getString("Username"));
                cus.setRoleID(rs.getInt("RoleID"));
                cus.setPassword(rs.getString("Password"));
                return cus;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                pst.close();
                DBConnector.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(AccountBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    public void editRedirect() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
        int id = Integer.valueOf(request.getParameter("id"));
        AccountBean data = this.readById(id);
        this.userID = id;
        this.username = data.getUsername();
        this.password = data.getPassword();
        this.roleID = data.getRoleID();
    }
    
    /**
     * Update Customer base id
     */
    public boolean update() {
        try {
            pst = DBConnector.getConnection().prepareStatement(sqlUpdate, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
//            FacesContext fc = FacesContext.getCurrentInstance();
//        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
//        int id = Integer.valueOf(request.getParameter("id"));
        
            pst.setInt(1, this.selectedAccount.userID);
            rs = pst.executeQuery();
            if(rs.first()) {
                rs.updateString("Username", this.selectedAccount.getUsername());
                rs.updateString("Password", this.selectedAccount.getPassword());
                rs.updateInt("RoleID", this.selectedAccount.getRoleID());
                rs.updateRow();
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                pst.close();
                DBConnector.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(AccountBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    
    /**
     * Delete TourBean base id
     */
    public boolean delete(int id) {
        try {
            pst = DBConnector.getConnection().prepareStatement(sqlDelete, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pst.setInt(1, id);
            if (pst.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pst.close();
                DBConnector.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(AccountBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    
    public RoleBean getRole(){
        return new RoleBean().readById(this.roleID);
    }
    public UserBean getUser(){
        return new UserBean().readById(this.userID);
    }
// </editor-fold>
}
