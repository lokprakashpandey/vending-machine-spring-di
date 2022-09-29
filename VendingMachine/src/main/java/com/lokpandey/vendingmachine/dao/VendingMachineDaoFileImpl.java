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
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class VendingMachineDaoFileImpl implements VendingMachineDao {

    public final String INVENTORY_FILE;
    public static final String DELIMITER = "::";
    private final Map<Item, Integer> inventory = new HashMap<>();
    //private final List<Item> nonZeroInventory = new ArrayList<>();

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
    public Map<Item, Integer> selectAllInventory() throws InventoryPersistenceException{
        readInventory();
        return inventory;
    }
    
//    @Override
//    public List<Item> selectNonZeroInventory() throws InventoryPersistenceException {
//        //gets a clean copy of inventory and then iterates the inventory map
//        nonZeroInventory.clear();
//        for(Map.Entry<Item, Integer> entry: selectAllInventory().entrySet()) {
//            int quantity = entry.getValue();
//            if(quantity != 0) {
//                nonZeroInventory.add(entry.getKey());
//            }
//        }
//        return nonZeroInventory;    
//    }
    
    private void writeInventoryToFile() throws InventoryPersistenceException {
        // We are not handling the IOException - but
        // we are translating it to an application specific exception and 
        // then simple throwing it (i.e. 'reporting' it) to the code that
        // called it.  It is the responsibility of the calling code to 
        // handle any errors that occur.
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(INVENTORY_FILE));
        } catch (IOException e) {
            throw new InventoryPersistenceException(
                    "Could not save inventory information.", e);
        }

        // Write out the Item objects to the roster file along with their quantity.
        //The updated map is written to the file
        String inventoryLineAsText;
        for (Map.Entry<Item, Integer> entry: inventory.entrySet()) {
            Item item = entry.getKey();
            int quantity = entry.getValue();
            //Name::Price::Quantity
            inventoryLineAsText = item.getName() + "::"
                                  + item.getCost() + "::"
                                  + quantity;
            // write the inventoryLineAsText to the file line by line
            out.println(inventoryLineAsText);
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }

    @Override
    public void updateInventory(Item item) throws InventoryPersistenceException {
        
//        for (Map.Entry<Item, Integer> entry: inventory.entrySet()) {
//            Item inventoryItem = entry.getKey();
//            int quantity = entry.getValue();
//            if(inventoryItem.getName().equals(item.getName())) {
//                entry.setValue(quantity-1);
//            }
//        }
//        writeInventoryToFile();
        
        /* Implementation using Lambda */
        readInventory();
        inventory.entrySet()
                .stream()
                .filter(i->i.getKey().getName().equals(item.getName())) //To get the item
                .map(i->i.setValue(i.getValue()-1)) //To update the item
                .forEach(i->{}); // Terminal operation to consume the stream
        
        
        
        writeInventoryToFile();
        
    }
    
       
}
