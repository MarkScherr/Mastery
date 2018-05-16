/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.mastery.service;

import com.sg.mastery.dao.DataException;
import com.sg.mastery.dao.OrderDao;
import com.sg.mastery.dto.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author mrsch
 */
public class OrderHandler {
    private OrderDao dao;
    private ProductHandling productHandler;
    private TaxHandling taxHandler;
    
    public OrderHandler(OrderDao dao, ProductHandling productHandler,
           TaxHandling taxHandler ) {
        this.dao = dao; 
        this.productHandler = productHandler;
        this.taxHandler = taxHandler;
    }
    
    public List<Order> getOrders(LocalDate date) throws DataException{
        if (dao.getAllOrders(date).size() == 0) {
            throw new DataException(" " + date);
        }
            return dao.getAllOrders(date);
    }
    public Order updateOrder(LocalDate date, Order order) throws DataException {
        
        Order thisOrder = order;
        thisOrder.setCostPerSquareFoot(productHandler.getProduct(order.getProductType()).getCostPerSquareFoot());
        thisOrder.setLaborCostPerSquareFoot(productHandler.getProduct(order.getProductType()).getLaborCostPerSquareFoot());
        thisOrder.setState(order.getState().toUpperCase());
        thisOrder.setTaxRate(taxHandler.getTaxInfo(order.getState()));
        BigDecimal areaOfRoom = order.getArea();
        
        if (order.getArea().signum() > 0) {
            areaOfRoom = areaOfRoom.setScale(2, BigDecimal.ROUND_HALF_UP);
            thisOrder.setArea(areaOfRoom);
        } else {
            throw new DataException(areaOfRoom + " must be larger than 0");
        }
        thisOrder = sharedCalculationsBetweenAddAndUpdate(thisOrder, areaOfRoom);
        
        return thisOrder;
    }
    
    public Order sharedCalculationsBetweenAddAndUpdate(Order order, BigDecimal areaOfRoom) {
        
        BigDecimal materialCost = areaOfRoom.multiply(order.getCostPerSquareFoot());
        materialCost = materialCost.setScale(2, BigDecimal.ROUND_HALF_UP);
        order.setMaterialCost(materialCost);
        
        BigDecimal laborCost =areaOfRoom.multiply(order.getLaborCostPerSquareFoot());
        laborCost =laborCost.setScale(2, BigDecimal.ROUND_HALF_UP);
        order.setLaborCost(laborCost);
        
        BigDecimal subtotal = order.getMaterialCost().add(order.getLaborCost());
        subtotal = subtotal.setScale(2, BigDecimal.ROUND_HALF_UP);
        
        BigDecimal tax = subtotal.multiply((order.getTaxRate().divide((BigDecimal.TEN.multiply(BigDecimal.TEN)))));
        tax = tax.setScale(2, BigDecimal.ROUND_HALF_UP);
        order.setTax(tax);
        
        BigDecimal total = order.getTax().add(subtotal);
        total = total.setScale(2, BigDecimal.ROUND_HALF_UP);
        order.setTotal(total);
        
        return order;
    }
    

    public Order addOrder(LocalDate date, String nameOfCustomer, String state, String productType, 
            BigDecimal lengthOfRoom, BigDecimal widthOfRoom)  throws DataException{
       

        Order thisOrder = new Order();
        
        thisOrder.setCustomerName(nameOfCustomer.toUpperCase());
        
        if (productHandler.getProduct(productType).getProductType().toLowerCase().equals(productType.toLowerCase())) {
            thisOrder.setProductType(productType.toUpperCase());
            thisOrder.setCostPerSquareFoot(productHandler.getProduct(productType).getCostPerSquareFoot());
            thisOrder.setLaborCostPerSquareFoot(productHandler.getProduct(productType).getLaborCostPerSquareFoot());
        } else {
            throw new DataException("Product:  ' " + productType + " ' "+ "does not exist"); 
        }
        
        //this is checked in Tax Handler
        thisOrder.setState(state.toUpperCase());
        thisOrder.setTaxRate(taxHandler.getTaxInfo(state));

        
        if ( lengthOfRoom.signum() > 0 && widthOfRoom.signum()  > 0) {
            BigDecimal areaOfRoom = widthOfRoom.multiply(lengthOfRoom);
            areaOfRoom = areaOfRoom.setScale(2, BigDecimal.ROUND_HALF_UP);
            thisOrder.setArea(areaOfRoom);

            thisOrder = sharedCalculationsBetweenAddAndUpdate(thisOrder, areaOfRoom);
        } else {
             throw new DataException(widthOfRoom + " & " + lengthOfRoom + " both need to be larger than 0"); 
        }
        dao.addOrder(date, thisOrder);
    
    return thisOrder;
    }
    
    public Order removeOrder(LocalDate date, int orderNumber) throws DataException {
        if (dao.getOrder(date, orderNumber) == null) {
            throw new DataException("Order Number: " + orderNumber);
        } 
            return dao.removeOrder(date, orderNumber);
    }

    public Order getOrder(LocalDate date, int orderNumber) throws DataException {
        if(dao.getOrder(date, orderNumber) == null){
             throw new DataException("Order Number: " + date + " - Order#-" + orderNumber + " does not exist.");
        }
        return dao.getOrder(date, orderNumber);
    }

    public void UpdateOrder(LocalDate date, Order oldOrder, Order newOrder) throws DataException {
            dao.updateOrder(date, oldOrder, newOrder); 
    }
    
}
