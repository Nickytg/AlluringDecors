/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model_controller;

import model_controller.*;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import utils.DBConnector;

/**
 *
 * @author lehainam
 */
@Named(value = "userBean")
@SessionScoped
public class UserBean implements Serializable {

   // <editor-fold desc="DTO" defaultstate="collapsed">
     /**
     * Creates a new instance of UserBean
     */
    public UserBean() {
    }
    int id;
    String firstname,lastname,email,address,phone;
UserBean selectedItem;

    public UserBean getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(UserBean selectedItem) {
        this.selectedItem = selectedItem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    // </editor-fold>
    // <editor-fold desc="DAO">  
    final String tableName = "[User]";
    final String props[] = {"UserID", "FirstName", "LastName", "Email", "Address","Phone"};
    private final String sqlCreate = "INSERT INTO " + tableName + " VALUES(?,?,?,?,?)";
    private final String sqlRead = "SELECT * FROM " + tableName;
    private final String sqlReadById = "SELECT * FROM " + tableName + " WHERE " + props[0] + " = ?";
    private final String sqlUpdate = "UPDATE " + tableName + " WHERE " + props[0] + " = ?";
    private final String sqlDelete = "DELETE FROM " + tableName + " WHERE " + props[0] + " = ?";
    private ResultSet rs;
    private PreparedStatement pst;

    /**
     * Create new Customer
     */
    public boolean create() {

        try {
            pst = DBConnector.getConnection().prepareStatement(sqlCreate);
            
            pst.setString(1, this.getFirstname());
            pst.setString(2, this.getLastname());
            pst.setString(3, this.getEmail());
            pst.setString(4, this.getAddress());
            pst.setString(5, this.getPhone());
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
                Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    
    /**
     * Create new Customer
     */
    public int createReturnID() {

        try {
            pst = DBConnector.getConnection().prepareStatement(sqlCreate,Statement.RETURN_GENERATED_KEYS);
            
            pst.setString(1, this.getFirstname());
            pst.setString(2, this.getLastname());
            pst.setString(3, this.getEmail());
            pst.setString(4, this.getAddress());
            pst.setString(5, this.getPhone());
            if (pst.executeUpdate() > 0) {
                try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    }
                    else {
                        throw new SQLException("Creating user failed, no ID obtained.");
                    }
                }
            }
        } catch (SQLException ex) {
            return 0;
        } finally {
            try {
                pst.close();
                DBConnector.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }

    /**
     * read all Customer in database
     *
     * @return
     */
    public ArrayList<UserBean> readAll() {
        try {
            ArrayList<UserBean> list = new ArrayList<UserBean>();
            rs = DBConnector.getConnection().createStatement().executeQuery(sqlRead);
            while (rs.next()) {
                UserBean obj = new UserBean();
                obj.setId(rs.getInt(props[0]));
                obj.setFirstname(rs.getString(props[1]));
                obj.setLastname(rs.getString(props[2]));
                obj.setEmail(rs.getString(props[3]));
                obj.setAddress(rs.getString(props[4]));
                obj.setPhone(rs.getString(props[5]));
                list.add(obj);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                DBConnector.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    /**
     * Read Customer base id
     */
    public UserBean readById(int id) {

        try {
            pst = DBConnector.getConnection().prepareStatement(sqlReadById, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if (rs.first()) {
                UserBean obj = new UserBean();
                 obj.setId(rs.getInt(props[0]));
                obj.setFirstname(rs.getString(props[1]));
                obj.setLastname(rs.getString(props[2]));
                obj.setEmail(rs.getString(props[3]));
                obj.setAddress(rs.getString(props[4]));
                obj.setPhone(rs.getString(props[5]));
                return obj;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                pst.close();
                DBConnector.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public void editRedirect() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
        int id = Integer.valueOf(request.getParameter("id"));
        UserBean data = this.readById(id);
        this.id = id;
        this.firstname = data.getFirstname();
        this.lastname = data.getLastname();
        this.phone = data.getPhone();
        this.address = data.getAddress();
        this.email=data.getEmail();
    }

    /**
     * Update Customer base id
     */
    public boolean update() {
        try {
            pst = DBConnector.getConnection().prepareStatement(sqlReadById, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
//            FacesContext fc = FacesContext.getCurrentInstance();
//            HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
//            int id = Integer.valueOf(request.getParameter("id"));

            pst.setInt(1, this.selectedItem.id);
            rs = pst.executeQuery();
            if (rs.first()) {
                rs.updateString(props[1], this.selectedItem.getFirstname());
                rs.updateString(props[2], this.selectedItem.getLastname());
                rs.updateString(props[3], this.selectedItem.getEmail());
                rs.updateString(props[4], this.selectedItem.getAddress());
                 rs.updateString(props[5], this.selectedItem.getPhone());

                rs.updateRow();
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                pst.close();
                DBConnector.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pst.close();
                DBConnector.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
// </editor-fold>
}
