package org.groceryshop.entity;

import javax.persistence.*;

/**
 * Created by atul_saurabh on 10/11/16.
 */
@Entity
@Table(name = "UnitForSell")
public class UnitForSell {
    private String unitName;
    private double divisionFactor;
    private int unitid;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getUnitid() {
        return unitid;
    }

    public void setUnitid(int unitid) {
        this.unitid = unitid;
    }

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
}
