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
    
    /**
     * Selects the inventory file into a HashMap.
     *
     * @param void 
     * @return Map<Item,Integer> of the inventory
     * @throws InventoryPersistenceException when file cannot be read
    */
    Map<Item, Integer> selectAllInventory() throws InventoryPersistenceException;
    
    //List<Item> selectNonZeroInventory() throws InventoryPersistenceException;
    
    
    /**
     * Updates the inventory by decrementing the quantity of Item by 1.
     *
     * @param Item item object whose associated quantity is to be decremented
     * @return void
     * @throws InventoryPersistenceException when inventory file cannot be saved
    */
    void updateInventory(Item item) throws InventoryPersistenceException;
                
}
