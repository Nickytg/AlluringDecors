/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;

/**
 *
 * @author NamWin
 */
public class ServicesRequest implements Serializable{
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
    
}
