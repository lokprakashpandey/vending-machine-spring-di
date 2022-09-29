/*
 * @author Lok Prakash Pandey
 * email: lokprakashpandey@gmail.com
 * date: Sep 27, 2022
 * purpose: service layer for vending machine app
 */
package com.lokpandey.vendingmachine.service;

import com.lokpandey.vendingmachine.dao.InventoryPersistenceException;
import com.lokpandey.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.Map;

/**
 *
 * @author lokpandey
 */
public interface VendingMachineServiceLayer {
    
    /**
     * Selects the complete inventory into a Map.
     *
     * @param void
     * @return Map<Item, Integer> from the inventory
     * @throws InventoryPersistenceException if the inventory cannot be read
    */
    Map<Item, Integer> serveAllInventory() throws InventoryPersistenceException;
    
    /**
     * Checks to see the capability to buy.
     *
     * @param BigDecimal money is the money to buy the item
     * @param BigDecimal cost is the cost to buy the item
     * @return true if money is equal to or more than cost
     * @throws InsufficientFundsException when money is less than cost
    */
    boolean isCapableToBuy(BigDecimal money, BigDecimal cost) throws InsufficientFundsException;
    
    
    /**
     * Checks to see the if there is stock for the item in inventory or not.
     *
     * @param Item item is the item to be checked in inventory
     * @param Map<Item, Integer> is the inventory map to be checked against
     * @return false if there is at least one item for the item name in the inventory
     * @throws NoItemInventoryException when the stock for the item is zero
    */
    
    public boolean isNoItemInInventory(Item item, Map<Item, Integer> map) 
            throws NoItemInventoryException;
    
    /**
     * Updates the inventory by decrementing the quantity of Item by 1.
     *
     * @param Item item object whose associated quantity is to be decremented
     * @return true if inventory file is updated and audit file is written
     * @throws InventoryPersistenceException when inventory file cannot be saved 
     * or audit file is not written
    */
    
    boolean updateInventory(Item item) throws InventoryPersistenceException;
    
}
