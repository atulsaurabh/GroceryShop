package org.groceryshop.entity;

import javafx.scene.control.Button;

import javax.persistence.*;


/**
 * Created by bipul.saurabh on 10/27/16.
 */

@Entity
@Table(name = "storeitem")
public class StoreItem {

    private int srno;
    private long itemid;
    private String itemname;
    private float priceperunit;
    private Button action;
    private float availablequantity;
    private float purchasequantity;
    private float remainingquantity;
    private float weightofitme;
    private String groupname;
    private String printedunit;

    private ItemGroup group;
    public StoreItem(int s, Button button) {
        srno = s;
        action = button;
    }

    public StoreItem() {

    }

    public StoreItem(int s, String item, float p, Button checkBox) {
        srno = s;
        itemname = item;
        priceperunit = p;
        action = checkBox;
    }

    @Transient
    public int getSrno() {
        return srno;
    }

    public void setSrno(int srno) {
        this.srno = srno;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getItemid() {
        return itemid;
    }

    public void setItemid(long itemid) {
        this.itemid = itemid;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public float getPriceperunit() {
        return priceperunit;
    }

    public void setPriceperunit(float priceperunit) {
        this.priceperunit = priceperunit;
    }

    @Transient
    public Button getAction() {
        return action;
    }

    public void setAction(Button action) {
        this.action = action;
    }

    public float getAvailablequantity() {
        return availablequantity;
    }

    public void setAvailablequantity(float availablequantity) {
        this.availablequantity = availablequantity;
    }

    public float getPurchasequantity() {
        return purchasequantity;
    }

    public void setPurchasequantity(float purchasequantity) {
        this.purchasequantity = purchasequantity;
    }

    public float getRemainingquantity() {
        return remainingquantity;
    }

    public void setRemainingquantity(float remainingquantity) {
        this.remainingquantity = remainingquantity;
    }

    public float getWeightofitme() {
        return weightofitme;
    }

    public void setWeightofitme(float weightofitme) {
        this.weightofitme = weightofitme;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getPrintedunit() {
        return printedunit;
    }

    public void setPrintedunit(String printedunit) {
        this.printedunit = printedunit;
    }


    @ManyToOne(cascade = CascadeType.ALL)
    public ItemGroup getGroup() {
        return group;
    }

    public void setGroup(ItemGroup group) {
        this.group = group;
    }

}
