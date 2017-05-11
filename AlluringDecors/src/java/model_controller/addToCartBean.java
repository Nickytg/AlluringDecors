/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model_controller;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author USER
 */
@Named(value = "addToCartBean")
@SessionScoped
public class addToCartBean implements Serializable{

    /**
     * Creates a new instance of addToCartBean
     */
    private ServicesOfferedBean offeredID;
    private UserBean userID;


    public ServicesOfferedBean getOfferedID() {
        return offeredID;
    }

    public void setOfferedID(ServicesOfferedBean offeredID) {
        this.offeredID = offeredID;
    }

    public UserBean getUserID() {
        return userID;
    }

    public void setUserID(UserBean userID) {
        this.userID = userID;
    }
    
    public addToCartBean() {
    }
    
}
