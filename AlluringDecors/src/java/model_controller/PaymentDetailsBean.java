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
import java.sql.Date;
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
@Named(value = "paymentDetailsBean")
@SessionScoped
public class PaymentDetailsBean implements Serializable {

    // <editor-fold desc="DTO" defaultstate="collapsed">
    /**
     * Creates a new instance of PaymentDetailsBean
     */
    public PaymentDetailsBean() {
    }
    int id, billID;
    Date datetime;
    double totalBillAmount, dueAmount, balanceAmount, totalPaidAmount;
    boolean maintained;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBillID() {
        return billID;
    }

    public void setBillID(int billID) {
        this.billID = billID;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public double getTotalBillAmount() {
        return totalBillAmount;
    }

    public void setTotalBillAmount(double totalBillAmount) {
        this.totalBillAmount = totalBillAmount;
    }

    public double getDueAmount() {
        return dueAmount;
    }

    public void setDueAmount(double dueAmount) {
        this.dueAmount = dueAmount;
    }

    public double getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(double balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public double getTotalPaidAmount() {
        return totalPaidAmount;
    }

    public void setTotalPaidAmount(double totalPaidAmount) {
        this.totalPaidAmount = totalPaidAmount;
    }

    public boolean isMaintained() {
        return maintained;
    }

    public void setMaintained(boolean maintained) {
        this.maintained = maintained;
    }

    // </editor-fold>
    // <editor-fold desc="DAO" defaultstate="collapsed">  
    final String tableName = "PaymentDetail";
    final String props[] = {"PaymentDetaiID", "BillID", "Date", "TotalBillAmount", "DueAmount", "BalanceAmount", "TotalPaidAmount", "isMaintained"};
    private final String sqlCreate = "INSERT INTO " + tableName + " VALUES(?,?,?,?,?,?,?,?)";
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
            pst.setInt(2, this.getBillID());
            pst.setDate(3, this.getDatetime());
            pst.setDouble(4, this.getTotalBillAmount());
            pst.setDouble(5, this.getDueAmount());
            pst.setDouble(6, this.getBalanceAmount());
            pst.setDouble(7, this.getTotalPaidAmount());
            pst.setBoolean(8, isMaintained());
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
                Logger.getLogger(PaymentDetailsBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    /**
     * read all Customer in database
     *
     * @return
     */
    public ArrayList<PaymentDetailsBean> readAll() {
        try {
            ArrayList<PaymentDetailsBean> list = new ArrayList<PaymentDetailsBean>();
            rs = DBConnector.getConnection().createStatement().executeQuery(sqlRead);
            while (rs.next()) {
                PaymentDetailsBean obj = new PaymentDetailsBean();
                obj.setId(rs.getInt(props[0]));
                obj.setBillID(rs.getInt(props[1]));
                obj.setDatetime(rs.getDate(props[2]));
                obj.setTotalBillAmount(rs.getDouble(props[3]));
                obj.setDueAmount(rs.getDouble(props[4]));
                obj.setBalanceAmount(rs.getDouble(props[5]));
                obj.setTotalPaidAmount(rs.getDouble(props[6]));
                obj.setMaintained(rs.getBoolean(props[7]));
                list.add(obj);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(PaymentDetailsBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                DBConnector.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(PaymentDetailsBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    /**
     * Read Customer base id
     */
    public PaymentDetailsBean readById(int id) {

        try {
            pst = DBConnector.getConnection().prepareStatement(sqlReadById, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if (rs.first()) {
                PaymentDetailsBean obj = new PaymentDetailsBean();
                obj.setId(rs.getInt(props[0]));
                obj.setBillID(rs.getInt(props[1]));
                obj.setDatetime(rs.getDate(props[2]));
                obj.setTotalBillAmount(rs.getDouble(props[3]));
                obj.setDueAmount(rs.getDouble(props[4]));
                obj.setBalanceAmount(rs.getDouble(props[5]));
                obj.setTotalPaidAmount(rs.getDouble(props[6]));
                obj.setMaintained(rs.getBoolean(props[7]));
                return obj;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PaymentDetailsBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                pst.close();
                DBConnector.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(PaymentDetailsBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public void editRedirect() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
        int id = Integer.valueOf(request.getParameter("id"));
        PaymentDetailsBean data = this.readById(id);
        this.id = id;
        this.totalBillAmount = data.getTotalBillAmount();
        this.totalPaidAmount = data.getTotalPaidAmount();
        this.billID = data.getBillID();
        this.dueAmount = data.getDueAmount();
        this.balanceAmount = data.getBalanceAmount();
        this.datetime = data.getDatetime();
        this.maintained = data.isMaintained();
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
                rs.updateInt(props[1], this.getBillID());
                rs.updateDate(props[2], this.getDatetime());
                rs.updateDouble(props[3], this.getTotalBillAmount());
                rs.updateDouble(props[4], this.getDueAmount());
                rs.updateDouble(props[5], this.getBalanceAmount());
                rs.updateDouble(props[6], this.getTotalPaidAmount());
                rs.updateBoolean(props[7], this.isMaintained());

                rs.updateRow();
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PaymentDetailsBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                pst.close();
                DBConnector.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(PaymentDetailsBean.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(PaymentDetailsBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pst.close();
                DBConnector.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(PaymentDetailsBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    
    public BillBean getBill(){
        return new BillBean().readById(this.billID);
    }
// </editor-fold>
}
