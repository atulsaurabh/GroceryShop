package org.groceryshop.entity;

import javafx.scene.control.Button;

import javax.persistence.*;


/**
 * Created by bipul.saurabh on 10/27/16.
 */

@Entity
@Table(name = "StoreItem")
public class StoreItem {

    private int srno;
    private long itemid;
    private String itemname;
    private float price;
    private String unit;
    private Button action;
    private double quantity;
    private double purchasequantity;
    private double remaining;
    private double weight;
    private String groupname;

    private ItemGroup group;

    public StoreItem(int s, Button button) {
        srno = s;
        action = button;
    }

    public StoreItem() {

    }

    public StoreItem(int s, String item, float p, String u, Button checkBox) {
        srno = s;
        itemname = item;
        price = p;
        unit = u;
        action = checkBox;
    }

    @Transient
    public int getSrno() {
        return srno;
    }

    public void setSrno(int srno) {
        this.srno = srno;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Transient
    public Button getAction() {
        return action;
    }

    public void setAction(Button action) {
        this.action = action;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getItemid() {
        return itemid;
    }

    public void setItemid(long itemid) {
        this.itemid = itemid;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }


    public double getPurchasequantity() {
        return purchasequantity;
    }

    public void setPurchasequantity(double purchasequantity) {
        this.purchasequantity = purchasequantity;
    }

    public double getRemaining() {
        return remaining;
    }

    public void setRemaining(double remaining) {
        this.remaining = remaining;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }


    @ManyToOne
    @JoinColumn(name = "groupid")
    public ItemGroup getGroup() {
        return group;
    }

    public void setGroup(ItemGroup group) {
        this.group = group;
    }

    @Transient
    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }
}
