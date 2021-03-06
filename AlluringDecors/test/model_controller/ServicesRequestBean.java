/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model_controller;

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
@Named(value = "servicesRequestBean")
@SessionScoped
public class ServicesRequestBean implements Serializable {

  // <editor-fold desc="DTO" defaultstate="collapsed">
      /**
     * Creates a new instance of ServicesRequestBean
     */
    public ServicesRequestBean() {
    }
    int id,servicesOfferID,userID,servicesRequestStatusID;
    String remark;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getServicesOfferID() {
        return servicesOfferID;
    }

    public void setServicesOfferID(int servicesOfferID) {
        this.servicesOfferID = servicesOfferID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getServicesRequestStatusID() {
        return servicesRequestStatusID;
    }

    public void setServicesRequestStatusID(int servicesRequestStatusID) {
        this.servicesRequestStatusID = servicesRequestStatusID;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    // </editor-fold>
      // <editor-fold desc="DAO" defaultstate="collapsed">  
    final String tableName = "ServicesRequest";
    final String props[] = {"ServicesRequestID", "ServicesOfferedID", "UserID","ServicesRequestStatusID","Remark"};
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
            pst.setInt(2, this.getServicesOfferID());
            pst.setInt(3, this.getUserID());
            pst.setInt(4, this.getServicesRequestStatusID());
            pst.setString(5, this.getRemark());
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
                Logger.getLogger(ServicesRequestBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    /**
     * read all Customer in database
     *
     * @return
     */
    public ArrayList<ServicesRequestBean> readAll() {
        try {
            ArrayList<ServicesRequestBean> list = new ArrayList<ServicesRequestBean>();
            rs = DBConnector.getConnection().createStatement().executeQuery(sqlRead);
            while (rs.next()) {
                ServicesRequestBean obj = new ServicesRequestBean();
                obj.setId(rs.getInt(props[0]));
                obj.setServicesOfferID(rs.getInt(props[1]));
                obj.setUserID(rs.getInt(props[2]));
                obj.setServicesRequestStatusID(rs.getInt(props[3]));
                obj.setRemark(rs.getString(props[4]));
                list.add(obj);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(ServicesRequestBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                DBConnector.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(ServicesRequestBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    /**
     * Read Customer base id
     */
    public ServicesRequestBean readById(int id) {

        try {
            pst = DBConnector.getConnection().prepareStatement(sqlReadById, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if (rs.first()) {
               ServicesRequestBean obj = new ServicesRequestBean();
                 obj.setId(rs.getInt(props[0]));
                obj.setServicesOfferID(rs.getInt(props[1]));
                obj.setUserID(rs.getInt(props[2]));
                obj.setServicesRequestStatusID(rs.getInt(props[3]));
                obj.setRemark(rs.getString(props[4]));
                return obj;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicesRequestBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                pst.close();
                DBConnector.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(ServicesRequestBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public void editRedirect() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
        int id = Integer.valueOf(request.getParameter("id"));
        ServicesRequestBean data = this.readById(id);
        this.id = id;
        this.servicesOfferID = data.getServicesOfferID();
        this.servicesRequestStatusID = data.getServicesRequestStatusID();
        this.userID = data.getUserID();
        this.remark=data.getRemark();
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
                rs.updateInt(props[1], this.getServicesOfferID());
                rs.updateInt(props[2], this.getUserID());
                rs.updateInt(props[3], this.getServicesRequestStatusID());
                rs.updateString(props[4], this.getRemark());

                rs.updateRow();
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicesRequestBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                pst.close();
                DBConnector.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(ServicesRequestBean.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ServicesRequestBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pst.close();
                DBConnector.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(ServicesRequestBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    
    public UserBean getUser(){
        return new UserBean().readById(this.userID);
        
    }
    
    public ServicesOfferedBean getServicesOffered(){
        return new ServicesOfferedBean().readById(this.servicesOfferID);
    }
    
    public ServicesRequestStatusBean getServicesRequestStatus(){
        return new ServicesRequestStatusBean().readById(this.servicesRequestStatusID);
    }
// </editor-fold>
}
