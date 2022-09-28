/*
 * @author Lok Prakash Pandey
 * email: lokprakashpandey@gmail.com
 * date: Sep 27, 2022
 * purpose: View class for vending machine
 */

package com.lokpandey.vendingmachine.ui;

import com.lokpandey.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;


public class VendingMachineView {

    private final UserIO io;

    public VendingMachineView(UserIO io) {
        this.io = io;
    }
    
    public void displayMenu(List<Item> menuItems) {
        io.print("!!!MENU!!!");
        io.print("------------");
        for(int i=0; i<menuItems.size(); i++) {
            io.print((i+1) + ". " + menuItems.get(i).getName() + " $" + menuItems.get(i).getCost());
        }
        io.print((menuItems.size() + 1) + ". Exit");
        io.print("Please put in some money to continue...");
    }
    
    public BigDecimal takeMoney() {
        return new BigDecimal(io.readString("Enter the amount of money:"));
    }
    
    public int getMenuChoiceSelection(int choices) {
        io.print("What do you want to do?");
        return io.readInt("Please input your choice:",1, choices);
    }
    
    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }
    
    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }
    
    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
}
