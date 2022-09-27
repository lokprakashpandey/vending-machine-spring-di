/*
 * @author Lok Prakash Pandey
 * email: lokprakashpandey@gmail.com
 * date: Sep 27, 2022
 * purpose: exception for no item in inventory
 */

package com.lokpandey.vendingmachine.service;


public class NoItemInventoryException extends Exception {

    public NoItemInventoryException(String message) {
        super(message);
    }

    public NoItemInventoryException(String message, Throwable cause) {
        super(message, cause);
    }

    
}
