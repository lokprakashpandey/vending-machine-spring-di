/*
 * @author Lok Prakash Pandey
 * email: lokprakashpandey@gmail.com
 * date: Sep 27, 2022
 * purpose: Dao interface for vending machine
 */

package com.lokpandey.vendingmachine.dao;

import com.lokpandey.vendingmachine.dto.Item;
import java.util.Map;


public interface VendingMachineDao {
    
    Map<Item, Integer> selectAllInventory() throws InventoryPersistenceException;
    
    //List<Item> selectNonZeroInventory() throws InventoryPersistenceException;
    
    void updateInventory(Item item) throws InventoryPersistenceException;
                
}
