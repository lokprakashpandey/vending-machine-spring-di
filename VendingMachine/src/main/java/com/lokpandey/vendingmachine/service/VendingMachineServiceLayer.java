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
    
    Map<Item, Integer> serveAllInventory() throws InventoryPersistenceException;
    
    //List<Item> serveNonZeroInventory() throws InventoryPersistenceException;
    
    boolean isCapableToBuy(BigDecimal money, BigDecimal cost) throws InsufficientFundsException;
    
    public boolean isNoItemInInventory(Item item, Map<Item, Integer> map) 
            throws NoItemInventoryException;
    
    boolean updateInventory(Item item) throws InventoryPersistenceException;
    
}
