/*
 * @author Lok Prakash Pandey
 * email: lokprakashpandey@gmail.com
 * date: Sep 27, 2022
 * purpose: Dao implementation class
 */

package com.lokpandey.vendingmachine.dao;

import com.lokpandey.vendingmachine.dto.Item;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class VendingMachineDaoFileImpl implements VendingMachineDao {

    public final String INVENTORY_FILE;
    public static final String DELIMITER = "::";
    private final Map<Item, Integer> inventory = new HashMap<>();
    private final List<Item> nonZeroInventory = new ArrayList<>();

    public VendingMachineDaoFileImpl() {
        this.INVENTORY_FILE = "inventory.txt";
    }
    
    public VendingMachineDaoFileImpl(String INVENTORY_FILE) {
        this.INVENTORY_FILE = INVENTORY_FILE;
    }
    
    private void readInventory() throws InventoryPersistenceException {
        inventory.clear();
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(INVENTORY_FILE)));
        } catch (FileNotFoundException e) {
            throw new InventoryPersistenceException(
                    "-_- Could not load inventory data into memory.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentItem holds the most recent line unmarshalled
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // unmarshall the item into an Item object
            unmarshallAndPutItemIntoInventory(currentLine);

        }
        // close scanner
        scanner.close();
    }

    private void unmarshallAndPutItemIntoInventory(String currentLine) {
        // currentLine is expecting a line read in from our file.
        // For example, it might look like this:
        // Lays::1.59::10
        // We then split that line on our DELIMITER - which we are using as ::
        // Leaving us with an array of Strings, stored in studentTokens.
        // Which should look like this:
        // ______________
        // |    |    |  |
        // |Lays|1.59|10|
        // |    |    |  |
        // --------------
        //  [0]   [1] [2]         
        String[] tokens = currentLine.split(DELIMITER);

        // Given the pattern above, the itemName is in index 0 of the array
        // and the cost is in index 1
        String itemName = tokens[0];
        BigDecimal itemCost = new BigDecimal(tokens[1]);
        
        // Which we can then use to create a new Item object 
        Item item = new Item(itemName, itemCost );

        //Now put item into map with quantity
        inventory.put(item, Integer.parseInt(tokens[2]));
    }

    @Override
    public Map<Item, Integer> selectInventory() throws InventoryPersistenceException{
        readInventory();
        return inventory;
    }
    
    @Override
    public List<Item> selectNonZeroInventory() throws InventoryPersistenceException {
        //gets a clean copy of inventory and then iterates the inventory map
        nonZeroInventory.clear();
        for(Map.Entry<Item, Integer> entry: selectInventory().entrySet()) {
            int quantity = entry.getValue();
            if(quantity != 0) {
                nonZeroInventory.add(entry.getKey());
            }
        }
        return nonZeroInventory;    
    }
    
}
