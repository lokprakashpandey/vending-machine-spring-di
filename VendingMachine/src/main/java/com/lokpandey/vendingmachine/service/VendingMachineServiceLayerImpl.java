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
import java.util.Map;


public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {

    private final VendingMachineDao dao;
    private final VendingMachineAuditDao auditDao;

    public VendingMachineServiceLayerImpl(VendingMachineDao dao, VendingMachineAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public Map<Item, Integer> serveAllInventory() throws InventoryPersistenceException {
        return dao.selectAllInventory();
    }

//    @Override
//    public List<Item> serveNonZeroInventory() throws InventoryPersistenceException {
//        return dao.selectNonZeroInventory();
//    }
//    
    
    @Override
    public boolean isCapableToBuy(BigDecimal money, BigDecimal cost) 
                                        throws InsufficientFundsException {
        if(money.compareTo(cost) >= 0) return true;
        else throw new InsufficientFundsException("ERROR: $" + money + " is not sufficient");
        
    }
    
    @Override
    public boolean isNoItemInInventory(Item item, Map<Item, Integer> map)
                                            throws NoItemInventoryException {
        boolean isStockZero = false;
        for(Map.Entry<Item, Integer> entry: map.entrySet()) {
            Item mapItem = entry.getKey();
            int quantity = entry.getValue();
            if(mapItem.getName().equals(item.getName()) && quantity == 0) {
                isStockZero = true;
                break;
            }
        }
        if(isStockZero == false) return false;
        else throw new NoItemInventoryException("ERROR: Sorry! Out of stock");
    }

    @Override
    public boolean updateInventory(Item item) throws InventoryPersistenceException {
        dao.updateInventory(item);
        auditDao.writeAuditEntry("Item " + item.getName()+ " was Purchased.");
        return true;
    }
    
}
