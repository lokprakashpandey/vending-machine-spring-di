/*
 * @author Lok Prakash Pandey
 * email: lokprakashpandey@gmail.com
 * date: SEP 27, 2022
 * purpose: exception class for insufficient funds
 */

package com.lokpandey.vendingmachine.service;


public class InsufficientFundsException extends Exception {

    public InsufficientFundsException(String message) {
        super(message);
    }

    public InsufficientFundsException(String message, Throwable cause) {
        super(message, cause);
    }

}
