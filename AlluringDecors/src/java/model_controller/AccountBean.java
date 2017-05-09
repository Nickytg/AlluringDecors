/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model_controller;

import java.awt.event.ActionEvent;
import java.io.IOException;
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
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import utils.DBConnector;
import utils.SessionUtils;

/**
 * 8
 *
 * @author NamWin
 */
@Named(value = "accountBean")
@SessionScoped
public class AccountBean implements Serializable {

    //<editor-fold desc="DTO" defaultstate="collapsed">
    public AccountBean() {
    }
    UserBean userID;
    RoleBean roleID;
    String username, password;
    AccountBean selectedAccount;

    public UserBean getUserID() {
        if (userID == null) {
            userID = new UserBean();
        }
        return userID;
    }

    public void setUserID(UserBean userID) {
        this.userID = userID;
    }

    public RoleBean getRoleID() {
        if (roleID == null) {
            roleID = new RoleBean();
        }
        return roleID;
    }

    public void setRoleID(RoleBean roleID) {
        this.roleID = roleID;
    }

    public AccountBean getSelectedAccount() {
        return selectedAccount;
    }

    public void setSelectedAccount(AccountBean selectedAccount) {
        this.selectedAccount = selectedAccount;
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
    private final String sqlValidation = "SELECT * FROM [Account] WHERE username = ? AND password = ?";
    private ResultSet rs;
    private PreparedStatement pst;

    public boolean isAdministrator() {
        try {
            HttpSession session = SessionUtils.getSession();
            int userId = Integer.parseInt(session.getAttribute("userid").toString());
            AccountBean temp = readById(userId);
            if (temp.roleID.name.equals("administrator")) {
                return true;
            }
            return false;
        } catch (NullPointerException ne) {
            return false;
        }
    }
    
    public boolean isClient() {
        try {
            HttpSession session = SessionUtils.getSession();
            int userId = Integer.parseInt(session.getAttribute("userid").toString());
            AccountBean temp = readById(userId);
            if (temp.roleID.name.equals("client")) {
                return true;
            }
            return false;
        } catch (NullPointerException ne) {
            return false;
        }
    }

    public void logout() throws IOException {
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + "/faces/index.xhtml");
    }

    public boolean isLogged() {
        try {
            HttpSession session = SessionUtils.getSession();
            int userId = Integer.parseInt(session.getAttribute("userid").toString());
            return true;
        } catch (NullPointerException ne) {
            return false;
        }
    }

    public void loginValidation() throws IOException {
        try {
            pst = DBConnector.getConnection().prepareStatement(sqlValidation, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            pst.setString(1, this.getUsername());
            pst.setString(2, this.getPassword());
            rs = pst.executeQuery();
            if (rs.first()) {
                HttpSession session = SessionUtils.getSession();
                session.setAttribute("username", rs.getString("Username"));
                session.setAttribute("userid", rs.getInt("UserID"));
//                session.setAttribute("roleid", rs.getInt("RoleID"));
//                return true;
                ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
                context.redirect(context.getRequestContextPath() + "/faces/index.xhtml");
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

//        return false;
    }

    /**
     * Create new Customer
     */
    public boolean create() {

        try {
            UserBean userInfo = new UserBean();
            userInfo.setFirstname(this.userID.firstname);
            userInfo.setLastname(this.userID.lastname);
            userInfo.setEmail(this.userID.email);
            userInfo.setPhone(this.userID.phone);
            userInfo.setAddress(this.userID.address);
            int newUserID = userInfo.createReturnID();
            if (newUserID != 0) {

                pst = DBConnector.getConnection().prepareStatement(sqlCreate);
                pst.setInt(1, newUserID);
                pst.setString(2, this.getUsername());
                pst.setString(3, this.getPassword());
                pst.setInt(4, this.roleID.id);

                if (pst.executeUpdate() > 0) {
                    return true;
                }
            } else {
                return false;
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
     *
     * @return
     */
    public ArrayList<AccountBean> readAll() {
        try {
            ArrayList<AccountBean> cusList = new ArrayList<AccountBean>();
            rs = DBConnector.getConnection().createStatement().executeQuery(sqlRead);
            while (rs.next()) {
                AccountBean cus = new AccountBean();
                cus.setUserID(new UserBean().readById(rs.getInt("UserID")));
                cus.setUsername(rs.getString("Username"));
                cus.setRoleID(new RoleBean().readById(rs.getInt("RoleID")));
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
                cus.setUserID(new UserBean().readById(rs.getInt("UserID")));
                cus.setUsername(rs.getString("Username"));
                cus.setRoleID(new RoleBean().readById(rs.getInt("RoleID")));
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
        //this.userID = id;
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
            UserBean userInfo = new UserBean();
            userInfo.setId(this.selectedAccount.userID.id);
            userInfo.setFirstname(this.selectedAccount.userID.firstname);
            userInfo.setLastname(this.selectedAccount.userID.lastname);
            userInfo.setEmail(this.selectedAccount.userID.email);
            userInfo.setPhone(this.selectedAccount.userID.phone);
            userInfo.setAddress(this.selectedAccount.userID.address);
            boolean resultStatus = userInfo.update();
            if (resultStatus) {
                pst.setInt(1, this.selectedAccount.userID.id);
                rs = pst.executeQuery();
                if (rs.first()) {
                    rs.updateString("Username", this.selectedAccount.getUsername());
                    rs.updateString("Password", this.selectedAccount.getPassword());
                    rs.updateInt("RoleID", this.selectedAccount.getRoleID().id);
                    rs.updateRow();
                    return true;
                }
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
                UserBean userInfo = new UserBean();
                boolean resultStatus = userInfo.delete(id);
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

// </editor-fold>
}
