/*
 * @author Lok Prakash Pandey
 * email: lokprakashpandey@gmail.com
 * date: Sep 27, 2022
 * purpose: Controller class for vending machine
 */

package com.lokpandey.vendingmachine.controller;

import com.lokpandey.vendingmachine.dao.InventoryPersistenceException;
import com.lokpandey.vendingmachine.dto.Change;
import com.lokpandey.vendingmachine.dto.Item;
import com.lokpandey.vendingmachine.service.InsufficientFundsException;
import com.lokpandey.vendingmachine.service.NoItemInventoryException;
import com.lokpandey.vendingmachine.service.VendingMachineServiceLayer;
import com.lokpandey.vendingmachine.ui.VendingMachineView;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VendingMachineController {

    private final VendingMachineView view;
    private final VendingMachineServiceLayer service;

    public VendingMachineController(VendingMachineView view, VendingMachineServiceLayer service) {
        this.view = view;
        this.service = service;
    }
    
    public void run() {
        try {
                boolean keepGoing = true;
                while(keepGoing) {
                    keepGoing = showMenusAndCompute();
                }
            
        } catch (InventoryPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }

    }
    
    private boolean showMenusAndCompute() throws InventoryPersistenceException {
        
        Map<Item, Integer> inventoryMap = service.serveAllInventory();
        List<Item> inventoryList = new ArrayList<>(inventoryMap.keySet());
        
        view.displayMenu(inventoryList);
        
        //money is inserted in dollars
        BigDecimal moneyInserted = new BigDecimal("0");
        try {
            moneyInserted = getMoneyInserted();
        } catch(NumberFormatException nfe) {
            view.displayErrorMessage(nfe.getMessage());
            return true;
        }
        
        
        int totalMenuOptions = inventoryList.size()+1;//because Exit is also another option
        int menuChoice = view.getMenuChoiceSelection(totalMenuOptions);
        if(menuChoice == totalMenuOptions) { //if Exit is selected
            exitMessage();
            return false; // false is returned and loop in run() gets terminated
        }
        else {
            
            Item itemSelected = inventoryList.get(menuChoice-1);//since menuChoice starts from 1
            try {
                //check inventory for no item
                service.isNoItemInInventory(itemSelected, inventoryMap);
            } catch (NoItemInventoryException ex) {
                view.displayErrorMessage(ex.getMessage());
                return true; //true is returned and loop in run() is continued
            }
            
            try {
                service.isCapableToBuy(moneyInserted, itemSelected.getCost());
                
            } catch (InsufficientFundsException ex) {
                view.displayErrorMessage(ex.getMessage());
                return true;
            }
            buyingMessage(itemSelected);
            BigDecimal changeToReturn = moneyInserted.subtract(itemSelected.getCost());
            //convert changeToReturn to pennies
            changeToReturn = changeToReturn.multiply(BigDecimal.valueOf(100));
            Change change = new Change(changeToReturn.intValue());
            view.displayChange(change);
            service.updateInventory(itemSelected);
            successMessage();
            return true;
        }
    }
    
    private BigDecimal getMoneyInserted() throws NumberFormatException {
        return view.takeMoney();
    }
    
    private void exitMessage() {
        view.displayExitBanner();
    }
    
    private void buyingMessage(Item item) {
        view.displayBuyingMessage(item);
    }
    
    private void successMessage() {
        view.displaySuccessMessageAndWait();
    }
    
    
}
