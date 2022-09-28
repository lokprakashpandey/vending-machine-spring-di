/*
 * @author Lok Prakash Pandey
 * email: lokprakashpandey@gmail.com
 * date: Sep 27, 2022
 * purpose: Make a vending machine app
 */

package com.lokpandey.vendingmachine;

import com.lokpandey.vendingmachine.controller.VendingMachineController;
import com.lokpandey.vendingmachine.dao.VendingMachineAuditDao;
import com.lokpandey.vendingmachine.dao.VendingMachineAuditDaoFileImpl;
import com.lokpandey.vendingmachine.dao.VendingMachineDao;
import com.lokpandey.vendingmachine.dao.VendingMachineDaoFileImpl;
import com.lokpandey.vendingmachine.service.VendingMachineServiceLayer;
import com.lokpandey.vendingmachine.service.VendingMachineServiceLayerImpl;
import com.lokpandey.vendingmachine.ui.UserIO;
import com.lokpandey.vendingmachine.ui.UserIOConsoleImpl;
import com.lokpandey.vendingmachine.ui.VendingMachineView;

/**
 *
 * @author lokpandey
 */
public class App {

    public static void main(String[] args) {
        // Instantiate the UserIO implementation
        UserIO myIo = new UserIOConsoleImpl();
        // Instantiate the View and wire the UserIO implementation into it
        VendingMachineView myView = new VendingMachineView(myIo);
        // Instantiate the DAO
        VendingMachineDao myDao = new VendingMachineDaoFileImpl();
        // Instantiate the Audit DAO
        VendingMachineAuditDao myAuditDao = new VendingMachineAuditDaoFileImpl();
        // Instantiate the Service Layer and wire the DAO and Audit DAO into it
        VendingMachineServiceLayer myService = new VendingMachineServiceLayerImpl(myDao, myAuditDao);
        // Instantiate the Controller and wire the Service Layer into it
        VendingMachineController controller = new VendingMachineController(myView, myService);
        // Kick off the Controller
        controller.run();
        
    }
}
