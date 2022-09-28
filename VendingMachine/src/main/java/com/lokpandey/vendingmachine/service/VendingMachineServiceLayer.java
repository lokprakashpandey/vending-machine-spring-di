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
import java.util.List;
import java.util.Map;

/**
 *
 * @author lokpandey
 */
public interface VendingMachineServiceLayer {
    
    Map<Item, Integer> serveInventory() throws InventoryPersistenceException;
    
    List<Item> serveNonZeroInventory() throws InventoryPersistenceException;
    
    boolean checkCapacityToBuy(BigDecimal money, BigDecimal cost) throws InsufficientFundsException;
    
}
