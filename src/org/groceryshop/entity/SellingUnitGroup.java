package org.groceryshop.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by atul_saurabh on 22/11/16.
 */
@Entity
@Table(name = "sellingunitgroup")
public class SellingUnitGroup {
    private int sellingunitgroupid;
    private String groupname;
    private Collection<UnitForSell> sells = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getSellingunitgroupid() {
        return sellingunitgroupid;
    }

    public void setSellingunitgroupid(int sellingunitgroupid) {
        this.sellingunitgroupid = sellingunitgroupid;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    public Collection<UnitForSell> getSells() {
        return sells;
    }

    public void setSells(Collection<UnitForSell> sells) {
        this.sells = sells;
    }
}
