/*
 * @author Lok Prakash Pandey
 * email: lokprakashpandey@gmail.com
 * date: Sep 27, 2022
 * purpose: Change class for coin change with a utility method
 */

package com.lokpandey.vendingmachine.dto;


public final class Change {

    private int numberOfQuarters;

    private int numberOfDimes;
    
    private int numberOfNickels;
    
    private final int numberOfPennies;
    
    public Change(int amountInPennies) {
        while(amountInPennies >= Coin.QUARTER.getValue()) {
            ++numberOfQuarters;
            amountInPennies -= Coin.QUARTER.getValue();
        }
        
        while(amountInPennies >= Coin.DIME.getValue()) {
            ++numberOfDimes;
            amountInPennies -= Coin.DIME.getValue();
        }
        
        while(amountInPennies >= Coin.NICKEL.getValue()) {
            ++numberOfNickels;
            amountInPennies -= Coin.NICKEL.getValue();
        }
        
        numberOfPennies = amountInPennies;
        
    }

    public int getNumberOfQuarters() {
        return numberOfQuarters;
    }

    public int getNumberOfDimes() {
        return numberOfDimes;
    }

    public int getNumberOfNickels() {
        return numberOfNickels;
    }

    public int getNumberOfPennies() {
        return numberOfPennies;
    }

}
