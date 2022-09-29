/*
 * @author Lok Prakash Pandey
 * email: lokprakashpandey@gmail.com
 * date:
 * purpose:
 */
package com.lokpandey.vendingmachine.dao;

import com.lokpandey.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author lokpandey
 */
public class VendingMachineDaoFileImplTest {
    
    VendingMachineDao testDao;
    
    public VendingMachineDaoFileImplTest() {
    }
    
    @BeforeEach
    public void setUp() throws Exception {
        String testFile = "testdao.txt";
        testDao = new VendingMachineDaoFileImpl(testFile);
    }

    @Test
    public void testUpdateAndSelectAllInventory() throws Exception {
        
        Map<Item, Integer> mapFromFile = testDao.selectAllInventory();
          
        Map<Item, Integer> mapToCompare = new HashMap<>();
        
        Item item1 = new Item("Toffee", new BigDecimal("0.99"));
        int quantity1 = 15;
        Item item2 = new Item("Pepsi", new BigDecimal("2.59"));
        int quantity2 = 10;
        mapToCompare.put(item1, quantity1);
        mapToCompare.put(item2, quantity2);
        
        //If selectAllInventory() is proper than size should be 2
        assertEquals(mapFromFile.size(), mapToCompare.size(), 
                "Size of map from file should be 2");
        
        assertTrue(compareMapsResult(mapFromFile, mapToCompare), "Both maps should be equal");
        
       testDao.updateInventory(item1); // reduce quantity by -1
       testDao.updateInventory(item2); // reduce quantity by -1
       
       mapFromFile = testDao.selectAllInventory();
       
       mapToCompare.put(item1, quantity1-1);
       mapToCompare.put(item2, quantity2-1);
       
       assertTrue(compareMapsResult(mapFromFile, mapToCompare), "Both maps should be equal");
        
    }
    
    public static boolean compareMapsResult(Map<Item, Integer> mapFromFile, Map<Item, Integer> mapToCompare) {
        
        int resultCount = 0;
        //The resultCount will be 2 when the 2 items from both maps match
    
        for(Map.Entry<Item, Integer> entryFromFile: mapFromFile.entrySet()) {
            Item itemFromFile = entryFromFile.getKey();
            int quantityFromFile = entryFromFile.getValue();
            for(Map.Entry<Item, Integer> entry: mapToCompare.entrySet()) {
                Item item = entry.getKey();
                int quantity = entry.getValue();
                if(itemFromFile.getName().equalsIgnoreCase(item.getName()) 
                    && itemFromFile.getCost().compareTo(item.getCost()) == 0 
                    && quantityFromFile == quantity) {
                resultCount++;
                }
            }
        }
        return resultCount == 2;
    }
    
}
