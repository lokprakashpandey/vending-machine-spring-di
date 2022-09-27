/*
 * @author Lok Prakash Pandey
 * email: lokprakashpandey@gmail.com
 * date: Sep 27, 2022
 * purpose: exception while persisiting inventory
 */

package com.lokpandey.vendingmachine.dao;


public class InventoryPersistenceException extends Exception {

    public InventoryPersistenceException(String message) {
        super(message);
    }

    public InventoryPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }

}
