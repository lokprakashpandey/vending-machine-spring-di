/*
 * @author Lok Prakash Pandey
 * email: lokprakashpandey@gmail.com
 * date: Sep 27, 2022
 * purpose: View class for vending machine
 */

package com.lokpandey.vendingmachine.ui;

import com.lokpandey.vendingmachine.dto.Change;
import com.lokpandey.vendingmachine.dto.Coin;
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
    
    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }
    
    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
        io.print("TRANSACTION INCOMPLETE");
        io.readString("Press a key to continue");
    }
    
    public void displayBuyingMessage(Item item) {
        io.print("You are buying " + item.getName() + " for $" + item.getCost());
    }

    public void displayChange(Change change) {
        String changeString = "CHANGE is: \n";
        if(change.getNumberOfQuarters() != 0) 
            changeString += change.getNumberOfQuarters() + " " + Coin.QUARTER + "\n";
        if(change.getNumberOfDimes() != 0)
            changeString += change.getNumberOfDimes() + " " + Coin.DIME + "\n";
        if(change.getNumberOfNickels() != 0)
            changeString += change.getNumberOfNickels() + " " + Coin.NICKEL + "\n";
        if(change.getNumberOfPennies() != 0)
            changeString += change.getNumberOfPennies() + " " + Coin.PENNY + "\n";
        io.print(changeString);
    }
    
    public void displaySuccessMessageAndWait() {
        io.print("TRANSACTION SUCCESSFUL\nThanks for using the Vending Machine");
        io.readString("Press a key to continue...");
    }
}
