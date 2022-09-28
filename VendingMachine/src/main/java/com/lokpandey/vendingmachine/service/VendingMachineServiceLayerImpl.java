/*
 * @author Lok Prakash Pandey
 * email: lokprakashpandey@gmail.com
 * date: Sep 27, 2022
 * purpose: vending machine service layer implementation class
 */

package com.lokpandey.vendingmachine.service;

import com.lokpandey.vendingmachine.dao.InventoryPersistenceException;
import com.lokpandey.vendingmachine.dao.VendingMachineAuditDao;
import com.lokpandey.vendingmachine.dao.VendingMachineDao;
import com.lokpandey.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {

    private VendingMachineDao dao;
    private VendingMachineAuditDao auditDao;

    public VendingMachineServiceLayerImpl(VendingMachineDao dao, VendingMachineAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public Map<Item, Integer> serveInventory() throws InventoryPersistenceException {
        return dao.selectInventory();
    }

    @Override
    public List<Item> serveNonZeroInventory() throws InventoryPersistenceException {
        return dao.selectNonZeroInventory();
    }
    
    
    @Override
    public boolean checkCapacityToBuy(BigDecimal money, BigDecimal cost) 
                                        throws InsufficientFundsException {
        if(money.compareTo(cost) >= 0) return true;
        else throw new InsufficientFundsException("ERROR: $" + money + " is not sufficient");
        
    }
    
}
