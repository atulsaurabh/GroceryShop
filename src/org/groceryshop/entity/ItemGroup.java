package org.groceryshop.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by atul_saurabh on 6/11/16.
 */

@Entity
@Table(name = "itemgroup")
public class ItemGroup {

    private long groupid;
    private String groupname;
    private Set<SubCatagory> subCatagories = new HashSet<>();


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


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "catagory_subcatagory",
            joinColumns = {@JoinColumn(name = "groupid")},
            inverseJoinColumns = {@JoinColumn(name = "subcatagoryid")}
    )
    public Set<SubCatagory> getSubCatagories() {
        return subCatagories;
    }

    public void setSubCatagories(Set<SubCatagory> subCatagories) {
        this.subCatagories = subCatagories;
    }
}


