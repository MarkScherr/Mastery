/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.mastery.controller;


import com.sg.mastery.dao.DataException;
import com.sg.mastery.dto.Order;
import com.sg.mastery.service.OrderHandler;
import com.sg.mastery.ui.FlooringView;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author mrsch
 */
public class MainMenu {
    FlooringView view;
    OrderHandler orderService;
    
    public MainMenu(FlooringView view, OrderHandler orderService) {
        this.view = view;
        this.orderService = orderService;
    }
    
    public void run()  {
         boolean keepGoing = true;
         int menuSelection = 0;
         
        while(keepGoing) {
           menuSelection = getMainMenuSelection();

           switch(menuSelection) {
               case 1:
                   displayOrders();
                   break;
               case 2:
                   addOrder();
                   break;
               case 3:
                   updateOrder();
                   break;
               case 4:
                   removeOrder();
                   break;
               case 5:
                    TaxMenu menu = new TaxMenu(view);
                    menu.run();
                   break;
               case 6:
                    ProductMenu trainMenu = new ProductMenu(view);
                    trainMenu.run();
                   break;
               case 7:
                   keepGoing = false;
                   break;
               default:
                   unknownCommand();
           }
        }       
            exitMessage();
     }

    private void displayOrders() {
        LocalDate tempDate = view.displayOrders();
        try {
            List<Order> orders = orderService.getOrders(tempDate);
            view.printOrders(orders, tempDate);
        } catch (DataException e) {
            view.displayErrorFindingDateMessage(e.getMessage());
        }
    }
    

    private void addOrder() {
        LocalDate addDate = view.getAddOrderDay();
        String nameOfCustomer = view.getNameOfCustomer();
        String state = view.getState();
        String productType = view.getProductType();
        BigDecimal lengthOfRoom = view.getLengthOfRoom();
        BigDecimal widthOfRoom = view.getWidthOfRoom();
        
        try {
            Order newOrder = orderService.addOrder(addDate, nameOfCustomer, state, productType, lengthOfRoom, widthOfRoom);
            view.displayOrder(addDate, newOrder);
        } catch (DataException e) {
            view.displayDataException("unable to add Order due to: " + e.getMessage());
        } 
    }

    private void removeOrder() {
        LocalDate date = view.getRemoveOrderDay();
        int removeOrder = view.getOrderToRemove();
        
        try {
            Order removedOrder = orderService.removeOrder(date, removeOrder);
            view.displayRemovedOrder(date, removedOrder);
        } catch (DataException e) {
            view.displayErrorFindingMessage(e.getMessage());
        }
    }

    private void updateOrder()  {
        try {
            LocalDate date = view.getUpdateOrderDay();
            List<Order> orders = orderService.getOrders(date);
            view.printOrders(orders, date);
            int orderNumber = view.getUpdateOrderNumber();

            Order oldOrder = orderService.getOrder(date, orderNumber);

            Order newOrder = view.updateOrder(oldOrder);
            newOrder = orderService.updateOrder(date, newOrder);

            orderService.UpdateOrder(date, oldOrder, newOrder);
            view.orderUpdatedSuccessfully();
            view.displayOrder(date, newOrder);
            
            } catch (DataException e) {
                view.displayDataException("unable to update Order due to: " + e.getMessage());
        }
    }  
    
    private int getMainMenuSelection()  {
        view.printMainMenu();
        return view.getMenuSelection();
    }

    private void exitMessage()  {
          view.exitBanner();
    }
    
    private void unknownCommand() {
       view.displayUnknownCommand();
    }       
    
}


