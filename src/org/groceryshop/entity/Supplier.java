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
    private String companyname;

    private Collection<ItemGroup> catagory = new ArrayList<>();


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


    public String getCompanyname() {
        return companyname;
    }


    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "supplier_category",
            joinColumns = {@JoinColumn(name = "supplierid")},
            inverseJoinColumns = {@JoinColumn(name = "groupid")}
    )
    public Collection<ItemGroup> getCatagory() {
        return catagory;
    }

    public void setCatagory(Collection<ItemGroup> catagory) {
        this.catagory = catagory;
    }
}
