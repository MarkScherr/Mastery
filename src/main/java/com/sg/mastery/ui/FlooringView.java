/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.mastery.ui;

import com.sg.mastery.dto.Order;
import com.sg.mastery.dto.Product;
import com.sg.mastery.dto.TaxByState;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author mrsch
 */
public class FlooringView {
     private UserIO io;
       
    public FlooringView(UserIO io) {
        this.io = io;
    }
    
    public void printTaxMenu() {
       io.print("+++Tax Menu +++\n"
               + "1. Display Tax Rates\n"
               + "2. Return to Main Menu.\n"
               + "3. Exit\n");
    }
    
        public void printProductMenu() {
       io.print("+++Product Menu +++\n"
               + "1. Display Products\n"
               + "2. Return to Main Menu.\n"
               + "3. Exit\n");
    }
    
    public void printMainMenu() {
       io.print("1. Display Orders\n"
               + "2. Add Order\n"
               + "3. Update Order\n"
               + "4. Remove Order\n"
               + "5. Go to Tax Menu\n"
               + "6. Go to Product Menu\n"
               + "7. Exit"); 
       
    }
    public int getSubMenuSelection() {
        return io.readInt("Please enter a selection from the menu.", 1, 3);
    }
    
    public int getMenuSelection() {
        return io.readInt("Please enter a selection from the menu.", 1, 7);
       }
    
    public void printMainMenuBanner() {
        io.print("+++Main Menu+++");
    }
    public LocalDate displayOrders() {
        return io.readLocalDate("Please enter a date that you would like to SEE orders for (MMDDYYYY):");    
}
    public LocalDate getAddOrderDay() {
        return io.readLocalDate("Please enter a date that you would like to ADD an order for (MMDDYYYY):");    
    }
    public LocalDate getRemoveOrderDay() {
        return io.readLocalDate("Please enter a date that you would like to REMOVE an order for (MMDDYYYY):");    
    }
    public LocalDate getUpdateOrderDay() {
        return io.readLocalDate("Please enter a date that you would like to UPDATE an order for (MMDDYYYY):");    
    }

    public void exitBanner() {
           io.print("Thanks for flooring with us!");
    }

    public void displayUnknownCommand() {
       io.print("unsure what you did, please try again"); 
    }

    public void displayErrorFindingDateMessage(String message) {
        io.print("We were unable to locate any file with the date of " +  message);  
    }
    
    public void displayOrder(LocalDate date , Order order) {
        io.print("Date of orders: "+ date + "\n"
                    + "Order Number: " + order.getOrderNumber() + "\n"
                    + "Name on File: " + order.getCustomerName() + "\n"
                    + "State: " + order.getState().toUpperCase() + "\n"
                    + "Product Type: " + order.getProductType() + "\n"
                    + "Area: " + order.getArea()+ "\n"
                    + "Material Cost: $" + order.getCostPerSquareFoot()+ "/ft²\n"
                    + "Labor Cost: $" + order.getLaborCostPerSquareFoot()+ "/ft²\n"
                    + "Total Material Cost: $" + order.getMaterialCost()+ "\n"
                    + "Total Labor Cost: $" + order.getLaborCost() + "\n"
                    + "Subtotal: $" + order.getLaborCost().add(order.getMaterialCost()) + "\n"
                    + "Total Tax: $" + order.getTax()+ " (" + order.getTaxRate() +"%)\n"
                    + "Total: $" + order.getTotal()+ "\n");
            
    }
    public void printOrders(List<Order> orders, LocalDate tempDate) {
        for (Order order : orders) {
            displayOrder(tempDate, order);
        }
    }

    public void displayRemovedOrder(LocalDate date, Order removedOrder) {
        io.print("You have successfully removed the following order:");
        displayOrder(date, removedOrder);
    }
    
    public BigDecimal getWidthOfRoom() {
       return io.readBigDecimal("Please enter the Width of the room you are looking to floor (Larger than 0):", 0); 
    
    }

    public BigDecimal getLengthOfRoom() {
       return io.readBigDecimal("Please enter the Length of the room you are looking to floor (Larger than 0):", 0); 
    }

    public String getProductType() {
            return io.readString("Please enter the name of the product you are looking to use:"); 
     }

    public String getState() {
        return io.readString("Please enter the name of the State you are performing this work (SS):", 2); 
  }

    public String getNameOfCustomer() {
        return io.readString("Please enter the name of the Customer:"); 
  }

    public void displayDataException(String localizedMessage) {
        io.print("The following error occured: " + localizedMessage);    }

    public void displayErrorFindingMessage(String message) {
        io.print("****Could not find: "+ message + "****");
    }

    public int getOrderToRemove() {
        return io.readInt("Please enter the Order Number that you want removed:");
    }

    public int getUpdateOrderNumber() {
        return io.readInt("Please enter the Order number that you want to update:");
    }

    public Order updateOrder(Order oldOrder) {
        BigDecimal length = new BigDecimal("1");
        BigDecimal width = new BigDecimal("1");
        String customerName = io.readString("Edit Customer Name **" + oldOrder.getCustomerName()+ "** (or hit Enter)" , 0);
        String stateName = io.readString("Edit State Name **" + oldOrder.getState()+ "** (or hit Enter)" , 0);
        String productName = io.readString("Edit Product Type **" + oldOrder.getProductType()+ "** (or hit Enter)" , 0);
        String adjustArea = io.readString("Edit the Area of the floor **" + oldOrder.getArea() + "  (Y/N)" , 0);
            if (adjustArea.toLowerCase().equals("y")) {
                 length = io.readBigDecimal("Enter new Length: ");
                 width = io.readBigDecimal("Enter new Width: ");
            }
        
        if (!customerName.isEmpty()) {
            oldOrder.setCustomerName(customerName.toUpperCase());
        }
        if (!stateName.isEmpty()) {
            oldOrder.setState(stateName.toUpperCase());
        }
        if (!productName.isEmpty()) {
            oldOrder.setProductType(productName.toUpperCase());
        }
        if (adjustArea.toLowerCase().equals("y")) {
            oldOrder.setArea(length.multiply(width));
        }
  
        return oldOrder;  
    }

    public void orderUpdatedSuccessfully() {
        io.print("Order updated Successfully!"); 
    }

    public LocalDate getTimeEntered() {
        return io.readLocalDate("Please enter a date");   }

    public void displayAllTaxRates(List<TaxByState> taxes) {
       io.print("State___________Tax Rate");
        for (TaxByState state : taxes) {
            io.print(state.getState() + "............................" + state.getTaxRate() +"%");
            
        }    
    }

    public void displayAllProducts(List<Product> productList) {
        io.print("_______Products:_______");
        for (Product product : productList) {
            io.print(product.getProductType().toUpperCase() + "\n"
            + "Material Cost: $" + product.getCostPerSquareFoot() + "/ft²" + "\n"
            + "Labor Cost: $" + product.getLaborCostPerSquareFoot()+ "/ft²"+  "\n");
            
        }
            
        }

}
