/*
 * @author Lok Prakash Pandey
 * email: lokprakashpandey@gmail.com
 * date: Sep 27, 2022
 * purpose: inventory class for stock items
 */

package com.lokpandey.vendingmachine.dto;

import java.util.Map;


public class Inventory {
    
    private Map<Item, Integer> inventory;

    public Map<Item, Integer> getInventory() {
        return inventory;
    }

    public void setInventory(Map<Item, Integer> inventory) {
        this.inventory = inventory;
    }
    

}
