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
import javax.faces.application.FacesMessage;
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
    int id;
    BillBean billID;
    Date datetime;
    double totalBillAmount, dueAmount, balanceAmount, totalPaidAmount;
    boolean maintained;

    PaymentDetailsBean selectedItem;

    public PaymentDetailsBean getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(PaymentDetailsBean selectedItem) {
        this.selectedItem = selectedItem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BillBean getBillID() {
        if (billID == null) {
            billID = new BillBean();
        }
        return billID;
    }

    public void setBillID(BillBean billID) {
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
    // <editor-fold desc="DAO" >  
    final String tableName = "PaymentDetail";
    final String props[] = {"PaymentDetaiID", "BillID", "Date", "TotalBillAmount", "DueAmount", "BalanceAmount", "TotalPaidAmount", "isMaintained"};
    private final String sqlCreate = "INSERT INTO " + tableName + " VALUES(?,?,?,?,?,?,?)";
    private final String sqlRead = "SELECT * FROM " + tableName+ " ORDER BY PaymentDetaiID desc";
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
            BillBean billTemp = new BillBean();
            billTemp.setServiceRequestID(this.billID.serviceRequestID);
            int newBillID = billTemp.createReturnID();
            if (newBillID != 0) {
                pst = DBConnector.getConnection().prepareStatement(sqlCreate);
//            pst.setInt(1, this.getId());
                java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
                pst.setInt(1, newBillID);
                pst.setTimestamp(2, date);
                pst.setDouble(3, this.getTotalBillAmount());
                pst.setDouble(4, this.getDueAmount());
                pst.setDouble(5, this.getBalanceAmount());
                pst.setDouble(6, this.getTotalPaidAmount());
                pst.setBoolean(7, isMaintained());
                if (pst.executeUpdate() > 0) {
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO, "Create Successfully", "Successfully"));
                    return true;
                }
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
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_FATAL, "All fields are required", "Failed"));
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
                obj.setBillID(new BillBean().readById(rs.getInt(props[1])));
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
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "All fields are required", "Failed"));
            Logger.getLogger(PaymentDetailsBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                DBConnector.closeConnection();
            } catch (SQLException ex) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_FATAL, "All fields are required", "Failed"));
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
                obj.setBillID(new BillBean().readById(rs.getInt(props[1])));
                obj.setDatetime(rs.getDate(props[2]));
                obj.setTotalBillAmount(rs.getDouble(props[3]));
                obj.setDueAmount(rs.getDouble(props[4]));
                obj.setBalanceAmount(rs.getDouble(props[5]));
                obj.setTotalPaidAmount(rs.getDouble(props[6]));
                obj.setMaintained(rs.getBoolean(props[7]));
                return obj;
            }
        } catch (SQLException ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "All fields are required", "Failed"));
            Logger.getLogger(PaymentDetailsBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                pst.close();
                DBConnector.closeConnection();
            } catch (SQLException ex) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_FATAL, "All fields are required", "Failed"));
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

// </editor-fold>
}
