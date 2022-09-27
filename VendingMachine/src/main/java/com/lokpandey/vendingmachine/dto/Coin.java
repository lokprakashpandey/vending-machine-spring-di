/*
 * @author Lok Prakash Pandey
 * email: lokprakashpandey@gmail.com
 * date: Sep 27, 2022
 * purpose: enum of coins
 */
package com.lokpandey.vendingmachine.dto;

/**
 *
 * @author lokpandey
 */
public enum Coin {
        QUARTER(25), DIME(10), NICKEL(5), PENNY(1);
        private final int coinValue;
        
        public int getValue() {
            return this.coinValue;
        }
        
        private Coin(int value) {
            this.coinValue = value;
        }
}
