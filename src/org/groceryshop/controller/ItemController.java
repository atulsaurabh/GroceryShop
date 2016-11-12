package org.groceryshop.controller;

/**
 * Created by atul_saurabh on 2/11/16.
 */
public interface ItemController {
    void callBack(String customerid, String customerName);

    void setSelectedItem(String itemid, int index);
}
