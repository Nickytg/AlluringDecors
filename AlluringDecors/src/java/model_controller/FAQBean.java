/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model_controller;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import utils.ConfigDB;

/**
 *
 * @author USER
 */
@Named(value = "fAQBean")
@SessionScoped
public class FAQBean implements Serializable{
    private int faqID;
    private String question;
    private String answer;

    public int getFaqID() {
        return faqID;
    }

    public void setFaqID(int faqID) {
        this.faqID = faqID;
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
    

    /**
     * Creates a new instance of FAQBean
     */
    public FAQBean(int faqID, String question, String answer) {
        this.faqID = faqID;
        this.question = question;
        this.answer = answer;
    }

    public FAQBean(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public FAQBean() {
    }
    
    /*----------------------------------------------------------------------------------------------------*/
    private final String sqlCreate = "INSERT INTO TourOrders VALUES(?, ?, ?, ?, ?, ?)";
    private final String sqlRead = "SELECT * FROM FAQ";
    private final String sqlReadById = "SELECT * FROM TourOrders WHERE OrderID = ?";
    private final String sqlUpdate = "SELECT * FROM TourOrders WHERE OrderID = ?";
    private final String sqlDelete = "DELETE FROM TourOrders WHERE OrderID = ?";
    private Connection connect;
    private ConfigDB db = new ConfigDB();
    private ResultSet rs;
    private PreparedStatement pst;
    
    /**
     * read all Tour Order in database
     */
    public ArrayList<FAQBean> readAll() {
        try {
            connect = db.getConection();
            ArrayList<FAQBean> faqList = new ArrayList<FAQBean>();
            rs = connect.createStatement().executeQuery(sqlRead);
            while (rs.next()) {
                FAQBean faq = new FAQBean();
                faq.setFaqID(rs.getInt("FAQID"));
                faq.setQuestion(rs.getString("Question"));
                faq.setAnswer(rs.getString("Answer"));
                
                faqList.add(faq);
            }
            return faqList;
        } catch (SQLException ex) {
            Logger.getLogger(FAQBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                db.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(FAQBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
}
