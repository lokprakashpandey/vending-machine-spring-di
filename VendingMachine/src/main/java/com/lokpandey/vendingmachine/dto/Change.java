/*
 * @author Lok Prakash Pandey
 * email: lokprakashpandey@gmail.com
 * date: Sep 27, 2022
 * purpose: Change class for coin change with a utility method
 */

package com.lokpandey.vendingmachine.dto;


public class Change {
    
    public static int[] calculate(int amountInPennies) {
        int numberOfQuarters = 0, numberOfDimes = 0, numberOfNickels = 0, numberOfPennies = 0;
        
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
        
        return new int[]{numberOfQuarters,numberOfDimes, numberOfNickels, numberOfNickels};
    }

}
