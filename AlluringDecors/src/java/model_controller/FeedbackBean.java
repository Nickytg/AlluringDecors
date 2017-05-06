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
@Named(value = "feedbackBean")
@SessionScoped
public class FeedbackBean implements Serializable {

    // <editor-fold desc="DTO" defaultstate="collapsed">
    /**
     * Creates a new instance of FeedbackBean
     */
    public FeedbackBean() {
    }
    int id, userID;
    String question,answer;
    FeedbackBean selectedFeedback;

    public FeedbackBean getSelectedFeedback() {
        return selectedFeedback;
    }

    public void setSelectedFeedback(FeedbackBean selectedFeedback) {
        this.selectedFeedback = selectedFeedback;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
    // </editor-fold>
    // <editor-fold desc="DAO" defaultstate="collapsed">  
    final String tableName = "[FeedBack]";
    final String props[] = {"FeedBackID", "Question", "Answer", "UserID"};
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
            pst.setString(2, this.getQuestion());
            pst.setString(3, this.getAnswer());
            pst.setInt(4, this.getUserID());
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
                Logger.getLogger(FeedbackBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    /**
     * read all Customer in database
     *
     * @return
     */
    public ArrayList<FeedbackBean> readAll() {
        try {
            ArrayList<FeedbackBean> list = new ArrayList<FeedbackBean>();
            rs = DBConnector.getConnection().createStatement().executeQuery(sqlRead);
            while (rs.next()) {
                FeedbackBean obj = new FeedbackBean();
                obj.setId(rs.getInt(props[0]));
                obj.setQuestion(rs.getString(props[1]));
                obj.setAnswer(rs.getString(props[2]));
                obj.setUserID(rs.getInt(props[3]));
                list.add(obj);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
//            try {
////                rs.close();
//                DBConnector.closeConnection();
//            } catch (SQLException ex) {
//                Logger.getLogger(FeedbackBean.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }
        return null;
    }

    /**
     * Read Customer base id
     */
    public FeedbackBean readById(int id) {

        try {
            pst = DBConnector.getConnection().prepareStatement(sqlReadById, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if (rs.first()) {
                FeedbackBean obj = new FeedbackBean();
                obj.setId(rs.getInt(props[0]));
                obj.setQuestion(rs.getString(props[1]));
                obj.setAnswer(rs.getString(props[2]));
                obj.setUserID(rs.getInt(props[3]));
                return obj;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                pst.close();
                DBConnector.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(FeedbackBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public void editRedirect() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
        int id = Integer.valueOf(request.getParameter("id"));
        FeedbackBean data = this.readById(id);
        this.id = id;
        this.question = data.getQuestion();
        this.answer = data.getAnswer();
        this.userID = data.getUserID();
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

            pst.setInt(1, this.selectedFeedback.id);
            rs = pst.executeQuery();
            if (rs.first()) {
                rs.updateString(props[1], this.selectedFeedback.getQuestion());
                rs.updateString(props[2], this.selectedFeedback.getAnswer());
                rs.updateInt(props[3], this.selectedFeedback.getUserID());

                rs.updateRow();
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
//            try {
//                rs.close();
//                pst.close();
//                DBConnector.closeConnection();
//            } catch (SQLException ex) {
//                Logger.getLogger(FeedbackBean.class.getName()).log(Level.SEVERE, null, ex);
//            }
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
            Logger.getLogger(FeedbackBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pst.close();
                DBConnector.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(FeedbackBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    
    public UserBean getUser(){
        return new UserBean().readById(this.userID);
    }
// </editor-fold>
}
