package org.groceryshop.entity;

import javafx.scene.control.Hyperlink;
import javafx.scene.control.RadioButton;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Created by atul_saurabh on 31/10/16.
 */
@Entity
@Table(name = "customer")
public class Customer {
    private String customername;
    private String occupation;
    private String address;
    private String accountnumber;
    private String mobilenumber;
    private Hyperlink details;
    private RadioButton radioButton;


    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Id
    public String getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(String accountnumber) {
        this.accountnumber = accountnumber;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    @Transient
    public Hyperlink getDetails() {
        return details;
    }

    public void setDetails(Hyperlink details) {
        this.details = details;
    }

    @Transient
    public RadioButton getRadioButton() {
        return radioButton;
    }

    public void setRadioButton(RadioButton radioButton) {
        this.radioButton = radioButton;
    }
}

