package org.groceryshop.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by atul_saurabh on 1/12/16.
 */

@Entity
@Table(name = "subcatagory")
public class SubCatagory {
    private int subcatagoryid;
    private String subcatagoryname;
    private SellingUnitGroup sellingUnitGroup;
    private Collection<StoreItem> storeItems = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getSubcatagoryid() {
        return subcatagoryid;
    }

    public void setSubcatagoryid(int subcatagoryid) {
        this.subcatagoryid = subcatagoryid;
    }

    public String getSubcatagoryname() {
        return subcatagoryname;
    }

    public void setSubcatagoryname(String subcatagoryname) {
        this.subcatagoryname = subcatagoryname;
    }

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    public Collection<StoreItem> getStoreItems() {
        return storeItems;
    }

    public void setStoreItems(Collection<StoreItem> storeItems) {
        this.storeItems = storeItems;
    }


    @OneToOne(targetEntity = SellingUnitGroup.class, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "sellingunitgroupid")
    public SellingUnitGroup getSellingUnitGroup() {
        return sellingUnitGroup;
    }

    public void setSellingUnitGroup(SellingUnitGroup sellingUnitGroup) {
        this.sellingUnitGroup = sellingUnitGroup;
    }
}
