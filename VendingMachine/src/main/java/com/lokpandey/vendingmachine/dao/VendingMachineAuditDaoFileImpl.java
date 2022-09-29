/*
 * @author Lok Prakash Pandey
 * email: lokprakashpandey@gmail.com
 * date: Sep 27, 2022
 * purpose: AuditDao file implementation class
 */

package com.lokpandey.vendingmachine.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;


public class VendingMachineAuditDaoFileImpl implements VendingMachineAuditDao {

    private final String AUDIT_FILE;

    public VendingMachineAuditDaoFileImpl() {
        AUDIT_FILE = "audit.txt";
    }

    public VendingMachineAuditDaoFileImpl(String AUDIT_FILE) {
        this.AUDIT_FILE = AUDIT_FILE;
    }
    
    @Override
        public void writeAuditEntry(String entry) throws InventoryPersistenceException {
            PrintWriter out;

            try {
                out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
            } catch (IOException e) {
                throw new InventoryPersistenceException("Could not persist audit information.", e);
            }

            LocalDateTime timestamp = LocalDateTime.now();
            out.println(timestamp.toString() + " : " + entry);
            out.flush();
        }

}
