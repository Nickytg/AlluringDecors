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
public class Description implements Serializable{
    int id,descriptionTypeID;
    String content;

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
    
}
