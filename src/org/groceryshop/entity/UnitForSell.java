package org.groceryshop.entity;

import javax.persistence.*;

/**
 * Created by atul_saurabh on 10/11/16.
 */
@Entity
@Table(name = "unitforsell")
public class UnitForSell {
    private String unitName;
    private double divisionFactor;
    private SellingUnitGroup group;


    @Id
    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public double getDivisionFactor() {
        return divisionFactor;
    }

    public void setDivisionFactor(double divisionFactor) {
        this.divisionFactor = divisionFactor;
    }

    @ManyToOne
    @JoinColumn(name = "sellingunitgroupid")
    public SellingUnitGroup getGroup() {
        return group;
    }

    public void setGroup(SellingUnitGroup group) {
        this.group = group;
    }
}
