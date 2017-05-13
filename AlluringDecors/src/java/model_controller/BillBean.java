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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import utils.DBConnector;

/**
 *
 * @author NamWin
 */
@Named(value = "billBean")
@SessionScoped
public class BillBean implements Serializable {

// <editor-fold desc="DTO" defaultstate="collapsed">
    public BillBean() {
    }
    int id;
    ServicesRequestBean serviceRequestID;

    BillBean selectedItem;

    public BillBean getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(BillBean selectedItem) {
        this.selectedItem = selectedItem;
    }

    public int getBillID() {
        return id;
    }

    public void setBillID(int billID) {
        this.id = billID;
    }

    public ServicesRequestBean getServiceRequestID() {
        if (serviceRequestID == null) {
            serviceRequestID = new ServicesRequestBean();
        }
        return serviceRequestID;
    }

    public void setServiceRequestID(ServicesRequestBean serviceRequestID) {
        this.serviceRequestID = serviceRequestID;
    }

// </editor-fold>
// <editor-fold desc="DAO" defaultstate="collapsed">  
    final String tableName = "Bill";
    final String props[] = {"BillID", "ServicesRequestID", "DomainID"};
    private final String sqlCreate = "INSERT INTO " + tableName + " VALUES(?)";
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
            pst.setInt(1, this.getBillID());
            pst.setInt(2, this.getServiceRequestID().id);

            if (pst.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "All fields are required", "Failed"));
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "All fields are required", "Failed"));
        } finally {
            try {
                pst.close();
                DBConnector.closeConnection();
            } catch (SQLException ex) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_FATAL, "All fields are required", "Failed"));
                Logger.getLogger(BillBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    /**
     * Create new Customer
     */
    public int createReturnID() {

        try {
            pst = DBConnector.getConnection().prepareStatement(sqlCreate, Statement.RETURN_GENERATED_KEYS);
            //pst.setInt(1, this.getBillID());
            pst.setInt(1, this.getServiceRequestID().id);

            if (pst.executeUpdate() > 0) {
                try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    } else {
                        throw new SQLException("Creating user failed, no ID obtained.");
                    }
                }
            }
        } catch (SQLException ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "All fields are required", "Failed"));
            return 0;
        } finally {
            try {
                pst.close();
                DBConnector.closeConnection();
            } catch (SQLException ex) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_FATAL, "All fields are required", "Failed"));
                Logger.getLogger(BillBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }

    /**
     * read all Customer in database
     *
     * @return
     */
    public ArrayList<BillBean> readAll() {
        try {
            ArrayList<BillBean> list = new ArrayList<BillBean>();
            rs = DBConnector.getConnection().createStatement().executeQuery(sqlRead);
            while (rs.next()) {
                BillBean obj = new BillBean();
                obj.setBillID(rs.getInt(props[0]));
                obj.setServiceRequestID(new ServicesRequestBean().readById(rs.getInt(props[1])));
                list.add(obj);
            }
            return list;
        } catch (SQLException ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "All fields are required", "Failed"));
            Logger.getLogger(BillBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                DBConnector.closeConnection();
            } catch (SQLException ex) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_FATAL, "All fields are required", "Failed"));
                Logger.getLogger(BillBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    /**
     * Read Customer base id
     */
    public BillBean readById(int id) {

        try {
            pst = DBConnector.getConnection().prepareStatement(sqlReadById, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if (rs.first()) {
                BillBean obj = new BillBean();
                obj.setBillID(rs.getInt(props[0]));
                obj.setServiceRequestID(new ServicesRequestBean().readById(rs.getInt(props[1])));
                return obj;
            }
        } catch (SQLException ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "All fields are required", "Failed"));
            Logger.getLogger(BillBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                pst.close();
                DBConnector.closeConnection();
            } catch (SQLException ex) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_FATAL, "All fields are required", "Failed"));
                Logger.getLogger(BillBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public void editRedirect() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
        int id = Integer.valueOf(request.getParameter("id"));
        BillBean data = this.readById(id);
        this.id = id;
        this.serviceRequestID = data.getServiceRequestID();
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

            pst.setInt(1, this.selectedItem.id);
            rs = pst.executeQuery();
            if (rs.first()) {
                rs.updateInt(props[1], this.selectedItem.getServiceRequestID().id);
                rs.updateRow();
                return true;
            }
        } catch (SQLException ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "All fields are required", "Failed"));
            Logger.getLogger(BillBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                pst.close();
                DBConnector.closeConnection();
            } catch (SQLException ex) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_FATAL, "All fields are required", "Failed"));
                Logger.getLogger(BillBean.class.getName()).log(Level.SEVERE, null, ex);
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
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "All fields are required", "Failed"));
            Logger.getLogger(BillBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pst.close();
                DBConnector.closeConnection();
            } catch (SQLException ex) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_FATAL, "All fields are required", "Failed"));
                Logger.getLogger(BillBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

//    public DomainBean getDomain(){
//        return new DomainBean().readById(this.domainID);
//    }
//    
//    public ServicesRequestBean getServicesRequest(){
//        return new ServicesRequestBean().readById(this.serviceRequestID);
//    }
// </editor-fold>
}
