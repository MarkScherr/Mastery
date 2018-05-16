/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.mastery.dao;

import com.sg.mastery.dto.Order;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author mrsch
 */
public class OrderDaoImpl implements OrderDao {
    private List<Order> orders = new ArrayList<>();
    private String orderInfo = "Orders_";
    private static final String DELIMITER =",";


    @Override
    public Order addOrder(LocalDate date, Order order)  throws DataException{
        load(date);
        if (orders.size() >= 1) {
               order.setOrderNumber(orders.get(orders.size()-1).getOrderNumber()+ 1);
        } else {
            order.setOrderNumber(1);
        }
         orders.add(order);
         write(date);
         return order;
    }

    @Override
    public Order getOrder(LocalDate date, int orderNumber) throws DataException{
        load(date);
        for (Order order : orders) {
            if(order.getOrderNumber() == orderNumber) {
                return orders.get(orders.indexOf(order));
            }
         }
        return null;
    }

    @Override
    public List<Order> getAllOrders(LocalDate date)  throws DataException {
         load(date);
         return orders;
    }

    @Override
    public Order removeOrder(LocalDate date, int orderNumber)  throws DataException {
        int i = 0;
         for (Order order : orders) {
             if(order.getOrderNumber() == orderNumber) {
                 i = orders.indexOf(order);
             }
        }
         Order returnOrder = orders.remove(i);
         write(date);
         return returnOrder;
}

    @Override
    public void updateOrder(LocalDate date, Order oldOrder, Order newOrder)  throws DataException {
        load(date);
        removeOrder(date, oldOrder.getOrderNumber());
        addOrder(date, newOrder);
        write(date);

    }

    @Override
    public void write(LocalDate date) throws DataException{
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMddyyyy");
        orderInfo = "Orders_"  + date.format(dateFormat) + ".txt";
        PrintWriter out;    
        
        try {
            out = new PrintWriter(new FileWriter(orderInfo));
        } catch (IOException e) {
            throw new DataException(
	                "Could not save OrderData data.", e);
        }

        out.println("OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,"
                + "LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total");

        List<Order> orderList = orders;
        for (Order currentOrder : orderList) {
            out.println(currentOrder.getOrderNumber()+ DELIMITER
                                  + currentOrder.getCustomerName()+ DELIMITER
	                + currentOrder.getState()+ DELIMITER 
	                + currentOrder.getTaxRate()+ DELIMITER
                                  + currentOrder.getProductType()+ DELIMITER
                                  + currentOrder.getArea()+ DELIMITER
                                  + currentOrder.getCostPerSquareFoot()+ DELIMITER
                                  + currentOrder.getLaborCostPerSquareFoot()+ DELIMITER
                                  + currentOrder.getMaterialCost()+ DELIMITER
	                + currentOrder.getLaborCost()+ DELIMITER
                                  + currentOrder.getTax()+ DELIMITER
	                + currentOrder.getTotal());
            
           
        }
        out.flush();
        out.close();
    }

    @Override
    public void load(LocalDate date) throws DataException {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMddyyyy");
        orderInfo = "Orders_"  + date.format(dateFormat) + ".txt";
        
        Scanner scanner;
        if(orders.size() > 0) {
            orders.clear();
        }
        File newFile = new File(orderInfo);
            if(!newFile.exists()) {
                return;
            }
        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(orderInfo)));
        } catch (FileNotFoundException ex) {
             throw new DataException ( 
                     "-_- Could not find file to load.", ex);
        }
        
        String currentLine;
        String[] currentTokens; 
        
        if(scanner.hasNext()) {
            scanner.nextLine();
        }
        while(scanner.hasNextLine()){
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            if(currentTokens.length == 12) {
                Order currentOrder= new Order();
                currentOrder.setOrderNumber(Integer.parseInt(currentTokens[0]));
                currentOrder.setCustomerName(currentTokens[1]);
                currentOrder.setState(currentTokens[2]);
                currentOrder.setTaxRate(new BigDecimal(currentTokens[3]));
                currentOrder.setProductType(currentTokens[4]);
                currentOrder.setArea(new BigDecimal(currentTokens[5]));
                currentOrder.setCostPerSquareFoot(new BigDecimal(currentTokens[6]));
                currentOrder.setLaborCostPerSquareFoot(new BigDecimal(currentTokens[7]));
                currentOrder.setMaterialCost(new BigDecimal(currentTokens[8]));
                currentOrder.setLaborCost(new BigDecimal(currentTokens[9]));
                currentOrder.setTax(new BigDecimal(currentTokens[10]));
                currentOrder.setTotal(new BigDecimal(currentTokens[11]));

                orders.add(currentOrder);
            }
            }
        scanner.close(); 

    }      
    
}