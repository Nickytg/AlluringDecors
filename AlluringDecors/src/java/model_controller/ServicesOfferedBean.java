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
@Named(value = "servicesOfferedBean")
@SessionScoped
public class ServicesOfferedBean implements Serializable {

  // <editor-fold desc="DTO" defaultstate="collapsed">
      /**
     * Creates a new instance of ServicesOfferedBean
     */
    public ServicesOfferedBean() {
    }
     int id,domainID;
    String name,content;
    ServicesOfferedBean selectedServicesOffered;

    public ServicesOfferedBean getSelectedServicesOffered() {
        return selectedServicesOffered;
    }

    public void setSelectedServicesOffered(ServicesOfferedBean selectedServicesOffered) {
        this.selectedServicesOffered = selectedServicesOffered;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDomainID() {
        return domainID;
    }

    public void setDomainID(int domainID) {
        this.domainID = domainID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    // </editor-fold>
    // <editor-fold desc="DAO" defaultstate="collapsed">  
    final String tableName = "ServicesOffered";
    final String props[] = {"ServicesOfferedID", "Name", "DomainID","Content"};
    private final String sqlCreate = "INSERT INTO " + tableName + " VALUES(?,?,?,?)";
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
            pst.setInt(3, this.getDomainID());
            pst.setString(4, this.getContent());
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
                Logger.getLogger(ServicesOfferedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    /**
     * read all Customer in database
     *
     * @return
     */
    public ArrayList<ServicesOfferedBean> readAll() {
        try {
            ArrayList<ServicesOfferedBean> list = new ArrayList<ServicesOfferedBean>();
            rs = DBConnector.getConnection().createStatement().executeQuery(sqlRead);
            while (rs.next()) {
                ServicesOfferedBean obj = new ServicesOfferedBean();
                obj.setId(rs.getInt(props[0]));
                obj.setName(rs.getString(props[1]));
                obj.setDomainID(rs.getInt(props[2]));
                obj.setContent(rs.getString(props[3]));
                list.add(obj);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(ServicesOfferedBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                DBConnector.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(ServicesOfferedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    /**
     * Read Customer base id
     */
    public ServicesOfferedBean readById(int id) {

        try {
            pst = DBConnector.getConnection().prepareStatement(sqlReadById, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if (rs.first()) {
               ServicesOfferedBean obj = new ServicesOfferedBean();
                obj.setId(rs.getInt(props[0]));
                obj.setName(rs.getString(props[1]));
                obj.setDomainID(rs.getInt(props[2]));
                obj.setContent(rs.getString(props[3]));
                return obj;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicesOfferedBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                pst.close();
                DBConnector.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(ServicesOfferedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public void editRedirect() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
        int id = Integer.valueOf(request.getParameter("id"));
        ServicesOfferedBean data = this.readById(id);
        this.id = id;
        this.name = data.getName();
        this.content = data.getContent();
        this.domainID = data.getDomainID();
    }

    /**
     * Update Customer base id
     */
    public boolean update() {
        try {
            pst = DBConnector.getConnection().prepareStatement(sqlUpdate, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
//            FacesContext fc = FacesContext.getCurrentInstance();
//            HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
//            int id = Integer.valueOf(request.getParameter("id"));

            pst.setInt(1, this.selectedServicesOffered.id);
            rs = pst.executeQuery();
            if (rs.first()) {
                rs.updateString(props[1], this.selectedServicesOffered.getName());
                rs.updateInt(props[2], this.selectedServicesOffered.getDomainID());
                rs.updateString(props[3], this.selectedServicesOffered.getContent());

                rs.updateRow();
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicesOfferedBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                pst.close();
                DBConnector.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(ServicesOfferedBean.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ServicesOfferedBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pst.close();
                DBConnector.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(ServicesOfferedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    
    public DomainBean getDomain(){
        return new DomainBean().readById(this.domainID);
    }
// </editor-fold>
}
