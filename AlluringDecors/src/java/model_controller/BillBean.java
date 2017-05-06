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
 * @author NamWin
 */
@Named(value = "billBean")
@SessionScoped
public class BillBean implements Serializable {


// <editor-fold desc="DTO" defaultstate="collapsed">
    public BillBean() {
    }
    int id, serviceRequestID, domainID;

    public int getBillID() {
        return id;
    }

    public void setBillID(int billID) {
        this.id = billID;
    }

    public int getServiceRequestID() {
        return serviceRequestID;
    }

    public void setServiceRequestID(int serviceRequestID) {
        this.serviceRequestID = serviceRequestID;
    }

    public int getDomainID() {
        return domainID;
    }

    public void setDomainID(int domainID) {
        this.domainID = domainID;
    }
// </editor-fold>
// <editor-fold desc="DAO" defaultstate="collapsed">  
    final String tableName = "Bill";
    final String props[] = {"BillID","ServicesRequestID","DomainID"};
     private final String sqlCreate = "INSERT INTO "+tableName+" VALUES(?,?,?)";
    private final String sqlRead = "SELECT * FROM "+tableName;
    private final String sqlReadById = "SELECT * FROM "+tableName+" WHERE "+props[0]+" = ?";
    private final String sqlUpdate = "UPDATE "+tableName+" WHERE "+props[0]+" = ?";
    private final String sqlDelete = "DELETE FROM "+tableName+" WHERE "+props[0]+" = ?";
    private ResultSet rs;
    private PreparedStatement pst;
    
    /**
     * Create new Customer
     */
    public boolean create() {
      
        try {
            pst = DBConnector.getConnection().prepareStatement(sqlCreate);
            pst.setInt(1, this.getBillID());
            pst.setInt(2, this.getServiceRequestID());
            pst.setInt(3, this.getDomainID());
           
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
                Logger.getLogger(BillBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    
    /**
     * read all Customer in database
     * @return 
     */
    public ArrayList<BillBean> readAll() {
        try {
            ArrayList<BillBean> list = new ArrayList<BillBean>();
            rs = DBConnector.getConnection().createStatement().executeQuery(sqlRead);
            while (rs.next()) {
                BillBean obj = new BillBean();
                obj.setBillID(rs.getInt(props[0]));
                obj.setServiceRequestID(rs.getInt(props[1]));
                obj.setDomainID(rs.getInt(props[2]));
                list.add(obj);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(BillBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                DBConnector.closeConnection();
            } catch (SQLException ex) {
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
                obj.setServiceRequestID(rs.getInt(props[1]));
                obj.setDomainID(rs.getInt(props[2]));
                return obj;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BillBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                pst.close();
                DBConnector.closeConnection();
            } catch (SQLException ex) {
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
        this.domainID = data.getDomainID();
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
            if(rs.first()) {
                rs.updateInt(props[1], this.getServiceRequestID());
                rs.updateInt(props[2], this.getDomainID());
                rs.updateRow();
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BillBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                pst.close();
                DBConnector.closeConnection();
            } catch (SQLException ex) {
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
            Logger.getLogger(BillBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pst.close();
                DBConnector.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(BillBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    
    public DomainBean getDomain(){
        return new DomainBean().readById(this.domainID);
    }
    
    public ServicesRequestBean getServicesRequest(){
        return new ServicesRequestBean().readById(this.serviceRequestID);
    }
// </editor-fold>
}
