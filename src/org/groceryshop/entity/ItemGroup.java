package org.groceryshop.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by atul_saurabh on 6/11/16.
 */

@Entity
public class ItemGroup {

    private long groupid;
    private String groupname;

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


    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Collection<StoreItem> getStoreItems() {
        return storeItems;
    }

    public void setStoreItems(Collection<StoreItem> storeItems) {
        this.storeItems = storeItems;
    }
}
