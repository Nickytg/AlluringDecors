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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import utils.DBConnector;

/**
 *
 * @author lehainam
 */
@Named(value = "domainBean")
@SessionScoped
public class DomainBean implements Serializable {
// <editor-fold desc="DTO" defaultstate="collapsed">

    /**
     * Creates a new instance of DomainBean
     */
    public DomainBean() {
    }
    int id;
    String name;
    DomainBean selectedDomain;

    public DomainBean getSelectedDomain() {
        return selectedDomain;
    }

    public void setSelectedDomain(DomainBean selectedDomain) {
        this.selectedDomain = selectedDomain;
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
    // </editor-fold>
    // <editor-fold desc="DAO" defaultstate="collapsed">  
    final String tableName = "[Domain]";
    final String props[] = {"DomainID", "Name"};
    private final String sqlCreate = "INSERT INTO " + tableName + " VALUES(?)";
    private final String sqlRead = "SELECT * FROM " + tableName+ " ORDER BY DomainID desc";
    private final String sqlReadById = "SELECT * FROM " + tableName + " WHERE " + props[0] + " = ?";
    private final String sqlDelete = "DELETE FROM " + tableName + " WHERE " + props[0] + " = ?";
    private ResultSet rs;
    private PreparedStatement pst;

    /**
     * Create new Customer
     */
    public boolean create() {

        try {
            if(this.getName().equals("")){
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "All fields are required", "Error"));
                return false;
            }
            pst = DBConnector.getConnection().prepareStatement(sqlCreate);
//            pst.setInt(1, this.getId());
            pst.setString(1, this.getName());
            if (pst.executeUpdate() > 0) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Create Successfully", "Successfully"));
                return true;
            }
        } catch (SQLException ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "All fields are required", "Failed"));
            return false;
        } finally {
            try {
                pst.close();
                DBConnector.closeConnection();
            } catch (SQLException ex) {
//                FacesContext.getCurrentInstance().addMessage(null,
//                        new FacesMessage(FacesMessage.SEVERITY_FATAL, "All fields are required", "Failed"));
                Logger.getLogger(DomainBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    /**
     * read all Customer in database
     *
     * @return
     */
    public ArrayList<DomainBean> readAll() {
        try {
            ArrayList<DomainBean> list = new ArrayList<DomainBean>();
            rs = DBConnector.getConnection().createStatement().executeQuery(sqlRead);
            while (rs.next()) {
                DomainBean obj = new DomainBean();
                obj.setId(rs.getInt(props[0]));
                obj.setName(rs.getString(props[1]));
                list.add(obj);
            }
            return list;
        } catch (SQLException ex) {
//            FacesContext.getCurrentInstance().addMessage(null,
//                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "All fields are required", "Failed"));
        } finally {
            try {
                rs.close();
                DBConnector.closeConnection();
            } catch (SQLException ex) {
//                FacesContext.getCurrentInstance().addMessage(null,
//                        new FacesMessage(FacesMessage.SEVERITY_FATAL, "All fields are required", "Failed"));
                Logger.getLogger(DomainBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    /**
     * Read Customer base id
     */
    public DomainBean readById(int id) {

        try {
            pst = DBConnector.getConnection().prepareStatement(sqlReadById, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if (rs.first()) {
                DomainBean obj = new DomainBean();
                obj.setId(rs.getInt(props[0]));
                obj.setName(rs.getString(props[1]));
                return obj;
            }
        } catch (SQLException ex) {
//            FacesContext.getCurrentInstance().addMessage(null,
//                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "All fields are required", "Failed"));
        } finally {
            try {
                rs.close();
                pst.close();
                DBConnector.closeConnection();
            } catch (SQLException ex) {
//                FacesContext.getCurrentInstance().addMessage(null,
//                        new FacesMessage(FacesMessage.SEVERITY_FATAL, "All fields are required", "Failed"));
                Logger.getLogger(DomainBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public void editRedirect() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
        int id = Integer.valueOf(request.getParameter("id"));
        DomainBean data = this.readById(id);
        this.id = id;
        this.name = data.getName();
    }

    /**
     * Update Customer base id
     */
    public boolean update() {
        try {
            pst = DBConnector.getConnection().prepareStatement(sqlReadById, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            pst.setInt(1, this.selectedDomain.id);
            rs = pst.executeQuery();
            if (rs.first()) {
                rs.updateString(props[1], this.selectedDomain.getName());

                rs.updateRow();
                return true;
            }
        } catch (SQLException ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "All fields are required", "Failed"));
        } finally {
            try {
                rs.close();
                pst.close();
                DBConnector.closeConnection();
            } catch (SQLException ex) {
//                FacesContext.getCurrentInstance().addMessage(null,
//                        new FacesMessage(FacesMessage.SEVERITY_FATAL, "All fields are required", "Failed"));
                Logger.getLogger(DomainBean.class.getName()).log(Level.SEVERE, null, ex);
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
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete succesfully", "Success"));
                return true;
            }
        } catch (SQLException ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "Something went wrong cannot delete this domain", "Failed"));
        } finally {
            try {
                pst.close();
                DBConnector.closeConnection();
            } catch (SQLException ex) {
//                FacesContext.getCurrentInstance().addMessage(null,
//                        new FacesMessage(FacesMessage.SEVERITY_FATAL, "All fields are required", "Failed"));
                Logger.getLogger(DomainBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
// </editor-fold>

}
