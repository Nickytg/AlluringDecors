/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model_controller;

import java.io.Serializable;

/**
 *
 * @author NamWin
 */
public class Bill implements Serializable{
    int id,serviceRequestID,domainID;

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
    
}
