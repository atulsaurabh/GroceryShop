package org.groceryshop.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by atul_saurabh on 6/11/16.
 */

@Entity
@Table(name = "itemgroup")
public class ItemGroup {

    private long groupid;
    private String groupname;
    private SellingUnitGroup sellingUnitGroup;

    private Collection<StoreItem> storeItems = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getGroupid() {
        return groupid;
    }

    public void setGroupid(long groupid) {
        this.groupid = groupid;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
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


