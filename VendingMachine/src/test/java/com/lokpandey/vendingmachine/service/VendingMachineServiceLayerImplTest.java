/*
 * @author Lok Prakash Pandey
 * email: lokprakashpandey@gmail.com
 * date: Sep 28, 2022
 * purpose: Test for service layer
 */
package com.lokpandey.vendingmachine.service;

import com.lokpandey.vendingmachine.dao.InventoryPersistenceException;
import com.lokpandey.vendingmachine.dao.VendingMachineAuditDao;
import com.lokpandey.vendingmachine.dao.VendingMachineAuditDaoFileImpl;
import com.lokpandey.vendingmachine.dao.VendingMachineDao;
import com.lokpandey.vendingmachine.dao.VendingMachineDaoFileImpl;
import com.lokpandey.vendingmachine.dao.VendingMachineDaoFileImplTest;
import com.lokpandey.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author lokpandey
 */
public class VendingMachineServiceLayerImplTest {
    
    private final VendingMachineServiceLayer service;
    
    public VendingMachineServiceLayerImplTest() {
        VendingMachineDao dao = new VendingMachineDaoFileImpl("testservice.txt");
        VendingMachineAuditDao auditDao = new VendingMachineAuditDaoFileImpl("testaudit.txt");
        service = new VendingMachineServiceLayerImpl(dao, auditDao);
    }

    @Test
    public void testServeAllInventory() throws InventoryPersistenceException {
        
        Map<Item, Integer> inventoryFromFile = service.serveAllInventory();
        Map<Item, Integer> expectedInventory = new HashMap<>();
        expectedInventory.put(new Item("Toffee", new BigDecimal("0.99")), 15);
        expectedInventory.put(new Item("Pepsi", new BigDecimal("2.59")), 10);
        
        assertTrue(VendingMachineDaoFileImplTest.compareMapsResult(inventoryFromFile, expectedInventory), 
                                "Both maps should be equal and return true");
    }
    
    @Test
    public void testIsCapableToBuy() throws InsufficientFundsException {
        
        BigDecimal money = new BigDecimal("2.5");
        BigDecimal cost = new BigDecimal("1.99");
        
        assertTrue(service.isCapableToBuy(money, cost), "Must be true");
        
        cost = new BigDecimal("2.5");
        
        assertTrue(service.isCapableToBuy(money, cost), "Must be true");
        
        cost = new BigDecimal("2.6");
        
        try {
            service.isCapableToBuy(money, cost);
            fail("Expected to throw InsufficientFundsException");
        } catch(InsufficientFundsException ife) {
            
        }
    }
    
    @Test
    public void testIsNoItemInInventory() throws NoItemInventoryException, InventoryPersistenceException {
        Map<Item, Integer> inventory = new HashMap<>();
        Item item = new Item("Kitkat", new BigDecimal("1.99"));
        inventory.put(item, 1);
        
        assertFalse(service.isNoItemInInventory(item, inventory), "Should be false");
        
        inventory.put(item, 0);
            
        try {
            service.isNoItemInInventory(item, inventory);
            fail("Expected to throw NoItemInventoryException");
        } catch(NoItemInventoryException niie) {
            
        }             
    }
    
    @Test
    public void testUpdateInventory() throws InventoryPersistenceException {
        Item item = new Item("Toffee", new BigDecimal("0.99"));
        int quantity = 15;
        
        boolean auditWritten = service.updateInventory(item);
        
        Map<Item, Integer> inventory = service.serveAllInventory();
        boolean updated = false;
        for(Map.Entry<Item, Integer> entry: inventory.entrySet()) {
            if(entry.getKey().getName().equalsIgnoreCase(item.getName()) 
                    && entry.getKey().getCost().compareTo(new BigDecimal("0.99")) == 0
                    && entry.getValue() == quantity -1) {
                updated = true;
                break;
            }
        }
        
        assertTrue(updated, "The inventory was updated");
        assertTrue(auditWritten, "The audit was written");
        
    }
    
}
