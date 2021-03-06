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
@Named(value = "descriptionBean")
@SessionScoped
public class DescriptionBean implements Serializable {

    // <editor-fold desc="DTO" defaultstate="collapsed">
    /**
     * Creates a new instance of DescriptionBean
     */
    public DescriptionBean() {
//        DescriptionBean selectedItem = new DescriptionBean();
//        selectedItem = this.readAll().get(0);
//            this.selectedItem = new DescriptionBean();

    }
    int id, descriptionTypeID;
    String content;

    DescriptionBean selectedItem;

    public DescriptionBean getSelectedItem() {
        if (selectedItem == null) {
            DescriptionBean temp = new DescriptionBean();
            temp.setDescriptionID(0);
            temp.setDescriptionID(0);
            temp.setContent("");
            selectedItem = temp;
        }
        return selectedItem;
    }

    public void setSelectedItem(DescriptionBean selectedItem) {
        this.selectedItem = selectedItem;
    }

    public int getDescriptionID() {
        return id;
    }

    public void setDescriptionID(int descriptionID) {
        this.id = descriptionID;
    }

    public int getDescriptionTypeID() {
        return descriptionTypeID;
    }

    public void setDescriptionTypeID(int descriptionTypeID) {
        this.descriptionTypeID = descriptionTypeID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    // </editor-fold>

    // <editor-fold desc="DAO" defaultstate="collapsed">  
    final String tableName = "[Description]";
    final String props[] = {"DescriptionID", "DescriptionTypeID", "Content"};
    private final String sqlRead = "SELECT * FROM " + tableName;
    private final String sqlReadById = "SELECT * FROM " + tableName + " WHERE " + props[0] + " = ?";
    private final String sqlReadByTypeId = "SELECT * FROM " + tableName + " WHERE " + props[1] + " = ?";
    private final String sqlDelete = "DELETE FROM " + tableName + " WHERE " + props[0] + " = ?";
    private ResultSet rs;
    private PreparedStatement pst;

    /**
     * read all Customer in database
     *
     * @return
     */
    public ArrayList<DescriptionBean> readAll() {
        try {
            ArrayList<DescriptionBean> list = new ArrayList<DescriptionBean>();
            rs = DBConnector.getConnection().createStatement().executeQuery(sqlRead);
            while (rs.next()) {
                DescriptionBean obj = new DescriptionBean();
                obj.setDescriptionID(rs.getInt(props[0]));
                obj.setDescriptionTypeID(rs.getInt(props[1]));
                obj.setContent(rs.getString(props[2]));
                list.add(obj);
            }
            return list;
        } catch (SQLException ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "All fields are required", "Failed"));
            Logger.getLogger(DescriptionBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                DBConnector.closeConnection();
            } catch (SQLException ex) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_FATAL, "All fields are required", "Failed"));
                Logger.getLogger(DescriptionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    /**
     * Read Customer base id
     */
    public DescriptionBean readById(int id) {

        try {
            pst = DBConnector.getConnection().prepareStatement(sqlReadById, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if (rs.first()) {
                DescriptionBean obj = new DescriptionBean();
                obj.setDescriptionID(rs.getInt(props[0]));
                obj.setDescriptionTypeID(rs.getInt(props[1]));
                obj.setContent(rs.getString(props[2]));
                return obj;
            }
        } catch (SQLException ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "All fields are required", "Failed"));
            Logger.getLogger(DescriptionBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                pst.close();
                DBConnector.closeConnection();
            } catch (SQLException ex) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_FATAL, "All fields are required", "Failed"));
                Logger.getLogger(DescriptionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    /**
     * Read Customer base id
     */
    public DescriptionBean readByTypeId(int id) {

        try {
            pst = DBConnector.getConnection().prepareStatement(sqlReadByTypeId, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if (rs.first()) {
                DescriptionBean obj = new DescriptionBean();
                obj.setDescriptionID(rs.getInt(props[0]));
                obj.setDescriptionTypeID(rs.getInt(props[1]));
                obj.setContent(rs.getString(props[2]));
                return obj;
            }
        } catch (SQLException ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "All fields are required", "Failed"));
            Logger.getLogger(DescriptionBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                pst.close();
                DBConnector.closeConnection();
            } catch (SQLException ex) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_FATAL, "All fields are required", "Failed"));
                Logger.getLogger(DescriptionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public void editRedirect() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
        int id = Integer.valueOf(request.getParameter("id"));
        DescriptionBean data = this.readById(id);
        this.id = id;
        this.descriptionTypeID = data.getDescriptionTypeID();
        this.content = data.getContent();
    }

    /**
     * Update Customer base id
     */
    public boolean update() {
        try {
            pst = DBConnector.getConnection().prepareStatement(sqlReadById, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pst.setInt(1, this.selectedItem.id);
            rs = pst.executeQuery();
            if (rs.first()) {
                rs.updateInt(props[1], this.selectedItem.getDescriptionTypeID());
                rs.updateString(props[2], this.selectedItem.getContent());
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Updated Successfully", "Successfully"));

                rs.updateRow();
                return true;
            }
        } catch (SQLException ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "All fields are required", "Failed"));
            Logger.getLogger(DescriptionBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                pst.close();
                DBConnector.closeConnection();
            } catch (SQLException ex) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_FATAL, "All fields are required", "Failed"));
                Logger.getLogger(DescriptionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Something went wrong, this process had been cancel", "Error"));

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
            Logger.getLogger(DescriptionBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pst.close();
                DBConnector.closeConnection();
            } catch (SQLException ex) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_FATAL, "All fields are required", "Failed"));
                Logger.getLogger(DescriptionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public DescriptionTypeBean getDescriptionType() {
        return new DescriptionTypeBean().readById(this.descriptionTypeID);
    }
// </editor-fold>

}
