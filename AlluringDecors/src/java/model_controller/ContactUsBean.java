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
@Named(value = "contactUsBean")
@SessionScoped
public class ContactUsBean implements Serializable {

// <editor-fold desc="DTO" defaultstate="collapsed">
    public ContactUsBean() {
    }
    int id;
    String name, phone, address, content;
    ContactUsBean selectedContactUs;

    public ContactUsBean getSelectedContactUs() {
        return selectedContactUs;
    }

    public void setSelectedContactUs(ContactUsBean selectedContactUs) {
        this.selectedContactUs = selectedContactUs;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    // </editor-fold>
// <editor-fold desc="DAO" defaultstate="collapsed">  
    final String tableName = "ContactUs";
    final String props[] = {"ContactUsID", "Name", "Phone", "Address", "Content"};
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
            pst.setInt(1, this.getId());
            pst.setString(2, this.getName());
            pst.setString(3, this.getPhone());
            pst.setString(4, this.getAddress());
            pst.setString(5, this.getContent());
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
                Logger.getLogger(ContactUsBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    /**
     * read all Customer in database
     *
     * @return
     */
    public ArrayList<ContactUsBean> readAll() {
        try {
            ArrayList<ContactUsBean> list = new ArrayList<ContactUsBean>();
            rs = DBConnector.getConnection().createStatement().executeQuery(sqlRead);
            while (rs.next()) {
                ContactUsBean obj = new ContactUsBean();
                obj.setId(rs.getInt(props[0]));
                obj.setName(rs.getString(props[1]));
                obj.setPhone(rs.getString(props[2]));
                obj.setAddress(rs.getString(props[3]));
                obj.setContent(rs.getString(props[4]));
                list.add(obj);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(ContactUsBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                DBConnector.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(ContactUsBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    /**
     * Read Customer base id
     */
    public ContactUsBean readById(int id) {

        try {
            pst = DBConnector.getConnection().prepareStatement(sqlReadById, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if (rs.first()) {
                ContactUsBean obj = new ContactUsBean();
                obj.setId(rs.getInt(props[0]));
                obj.setName(rs.getString(props[1]));
                obj.setPhone(rs.getString(props[2]));
                obj.setAddress(rs.getString(props[3]));
                obj.setContent(rs.getString(props[4]));
                return obj;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContactUsBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                pst.close();
                DBConnector.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(ContactUsBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public void editRedirect() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
        int id = Integer.valueOf(request.getParameter("id"));
        ContactUsBean data = this.readById(id);
        this.id = id;
        this.name = data.getName();
        this.phone = data.getPhone();
        this.content = data.getContent();
        this.address = data.getAddress();
    }

    /**
     * Update Customer base id
     */
    public boolean update() {
        try {
            pst = DBConnector.getConnection().prepareStatement(sqlUpdate, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
            int id = Integer.valueOf(request.getParameter("id"));

            pst.setInt(1, id);
            rs = pst.executeQuery();
            if (rs.first()) {
                rs.updateString(props[1], this.getName());
                rs.updateString(props[2], this.getPhone());
                rs.updateString(props[3], this.getAddress());
                rs.updateString(props[4], this.getContent());

                rs.updateRow();
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContactUsBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                pst.close();
                DBConnector.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(ContactUsBean.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ContactUsBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pst.close();
                DBConnector.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(ContactUsBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
// </editor-fold>
}
