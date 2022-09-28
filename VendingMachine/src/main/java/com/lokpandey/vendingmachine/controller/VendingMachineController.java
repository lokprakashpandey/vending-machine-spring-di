/*
 * @author Lok Prakash Pandey
 * email: lokprakashpandey@gmail.com
 * date: Sep 27, 2022
 * purpose: Controller class for vending machine
 */

package com.lokpandey.vendingmachine.controller;

import com.lokpandey.vendingmachine.dao.InventoryPersistenceException;
import com.lokpandey.vendingmachine.dto.Item;
import com.lokpandey.vendingmachine.service.InsufficientFundsException;
import com.lokpandey.vendingmachine.service.VendingMachineServiceLayer;
import com.lokpandey.vendingmachine.ui.VendingMachineView;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


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
                    exitMessage();
                }
            
        } catch (InventoryPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }

    }
    
    private boolean showMenusAndCompute() throws InventoryPersistenceException {
        List<Item> nonZeroInventory = service.serveNonZeroInventory();
        view.displayMenu(nonZeroInventory);
        //money is inserted in dollars
        BigDecimal moneyInserted = getMoneyInserted();
        int totalMenuOptions = nonZeroInventory.size()+1;
        int menuChoice = view.getMenuChoiceSelection(totalMenuOptions);
        if(menuChoice == totalMenuOptions) {
            return false;
        }
        else {
            Item itemToUpdate = nonZeroInventory.get(menuChoice-1);
            
            boolean capacity = false;
            try {
                capacity = service.checkCapacityToBuy(moneyInserted, itemToUpdate.getCost());
            } catch (InsufficientFundsException ex) {
                view.displayErrorMessage(ex.getMessage());
            }
            if(capacity) {
                //service.updateInventory(Item);
                //service.returnChange(moneyInserted-item.cost);
            }
            
            return true;
        }
    }
    
    private BigDecimal getMoneyInserted() {
        return view.takeMoney();
    }
    
    private void exitMessage() {
        view.displayExitBanner();
    }
    
    
}
