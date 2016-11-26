package org.groceryshop.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by bipul on 26/11/16.
 */
@Entity
@Table(name = "supplier")
public class Supplier {
    private int supplierid;
    private String suppliername;
    private String supplieraddress;
    private String mobilenumber;

    private Collection<StoreItem> suppliedname = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getSupplierid() {
        return supplierid;
    }

    public void setSupplierid(int supplierid) {
        this.supplierid = supplierid;
    }

    public String getSuppliername() {
        return suppliername;
    }

    public void setSuppliername(String suppliername) {
        this.suppliername = suppliername;
    }

    public String getSupplieraddress() {
        return supplieraddress;
    }

    public void setSupplieraddress(String supplieraddress) {
        this.supplieraddress = supplieraddress;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "supplier_item",
            joinColumns = {@JoinColumn(name = "supplierid")},
            inverseJoinColumns = {@JoinColumn(name = "itemid")}
    )
    public Collection<StoreItem> getSuppliedname() {
        return suppliedname;
    }

    public void setSuppliedname(Collection<StoreItem> suppliedname) {
        this.suppliedname = suppliedname;
    }
}
