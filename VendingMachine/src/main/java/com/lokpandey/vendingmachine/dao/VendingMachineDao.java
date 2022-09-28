/*
 * @author Lok Prakash Pandey
 * email: lokprakashpandey@gmail.com
 * date: Sep 27, 2022
 * purpose: Dao interface for vending machine
 */

package com.lokpandey.vendingmachine.dao;

import com.lokpandey.vendingmachine.dto.Item;
import java.util.List;
import java.util.Map;


public interface VendingMachineDao {
    
    Map<Item, Integer> selectInventory() throws InventoryPersistenceException;
    
    List<Item> selectNonZeroInventory() throws InventoryPersistenceException;

}
