/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.mastery.dao;

import com.sg.mastery.dto.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author mrsch
 */
public class OrderDaoStub implements OrderDao {
    private Order onlyOrder;
    private List<Order> orderList = new ArrayList<>();


    public OrderDaoStub() {
        BigDecimal fifty = new BigDecimal("50");
        BigDecimal twoFifty = new BigDecimal("2.50");
        BigDecimal threeFifty = new BigDecimal("3.50");

        
        onlyOrder = new Order();
        onlyOrder.setOrderNumber(1);
        onlyOrder.setCustomerName("Test Customer Name");
        onlyOrder.setState("MI");
        onlyOrder.setProductType("Wood");
        onlyOrder.setArea(fifty);
        onlyOrder.setCostPerSquareFoot(twoFifty);
        onlyOrder.setLaborCostPerSquareFoot(threeFifty);
        
        orderList.add(onlyOrder);
                
} 
    @Override
    public Order addOrder(LocalDate date, Order order)  throws DataException{
        if(order.getOrderNumber() == onlyOrder.getOrderNumber()) {
            return onlyOrder;
        } else {
            return null;
        }
    }

    @Override
    public Order getOrder(LocalDate date, int orderNumber) throws DataException{
        if(orderNumber == onlyOrder.getOrderNumber()) {
            return onlyOrder;
        } else {
            return null;
        }   
    }

    @Override
    public List<Order> getAllOrders(LocalDate date)  throws DataException {     
         return orderList;
    }

    @Override
    public Order removeOrder(LocalDate date, int orderNumber)  throws DataException {
        if(orderNumber == onlyOrder.getOrderNumber()) {
            return onlyOrder;
        } else {
            return null;
        }
}

    @Override
    public void updateOrder(LocalDate date, Order oldOrder, Order newOrder)  throws DataException {       
    
    }

    @Override
    public void write(LocalDate date) throws DataException{
            for (Order order : orderList) {
                System.out.println(order.getOrderNumber());
        }
    }

    @Override
    public void load(LocalDate date) throws DataException {

    }      


}
    
