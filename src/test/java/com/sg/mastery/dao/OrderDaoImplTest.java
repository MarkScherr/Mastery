/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.mastery.dao;

import com.sg.mastery.dto.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author mrsch
 */
public class OrderDaoImplTest {
    OrderDao dao = new OrderDaoImpl();
    LocalDate date;
 
    
    public OrderDaoImplTest() {
                    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMddyyyy");
                    String value = "07042018";
                    date = LocalDate.parse(value, dateFormat);
 
    
    }

    @Test
    public void testAddOrder() throws Exception {
        boolean notThrown = true;
        BigDecimal fifty = new BigDecimal("50");
        BigDecimal twoFifty = new BigDecimal("2.50");
        BigDecimal threeFifty = new BigDecimal("3.50");
        BigDecimal hundred = new BigDecimal("100");
        BigDecimal oneFifty = new BigDecimal("150");
        
        Order order = new Order();
        order.setCustomerName("Test");
        order.setState("MI");
        order.setProductType("Wood");
        order.setArea(fifty);
        order.setCostPerSquareFoot(twoFifty);
        order.setLaborCostPerSquareFoot(threeFifty);
        order.setMaterialCost(oneFifty);
        order.setLaborCost(hundred);
        order.setTaxRate(twoFifty);
        order.setTax(threeFifty);
        BigDecimal twoHundred = fifty.add(oneFifty);
        order.setTotal(twoHundred);
        try {
             dao.addOrder(date, order);
        } catch (Exception e) {
            notThrown=false;
        }
        assertTrue(notThrown);
        
    }

    /**
     * Test of getOrder method, of class OrderDaoImpl.
     */
    @Test
    public void testGetOrder() throws Exception {
        boolean notThrown = true;
        try {
            dao.getOrder(date, 1);
        } catch (Exception e) {
            notThrown = false;
        }
        assertTrue(notThrown);
    }

    /**
     * Test of getAllOrders method, of class OrderDaoImpl.
     */
    @Test
    public void testGetAllOrders() throws Exception {
        boolean notThrown = true;
        try {
            dao.getAllOrders(date);
        } catch (Exception e) {
            notThrown = false;
        }
        
        assertTrue(notThrown);
    }

    /**
     * Test of removeOrder method, of class OrderDaoImpl.
     */
    @Test
    public void testRemoveOrder() throws Exception {
        BigDecimal fifty = new BigDecimal("50");
        BigDecimal twoFifty = new BigDecimal("2.50");
        BigDecimal threeFifty = new BigDecimal("3.50");
        BigDecimal hundred = new BigDecimal("100");
        BigDecimal oneFifty = new BigDecimal("150");
        
        Order order = new Order();
        order.setCustomerName("Test");
        order.setState("MI");
        order.setProductType("Wood");
        order.setArea(fifty);
        order.setCostPerSquareFoot(twoFifty);
        order.setLaborCostPerSquareFoot(threeFifty);
        order.setMaterialCost(oneFifty);
        order.setLaborCost(hundred);
        order.setTaxRate(twoFifty);
        order.setTax(threeFifty);
        BigDecimal twoHundred = fifty.add(oneFifty);
        order.setTotal(twoHundred);
        
        order = dao.addOrder(date, order);
        order = dao.removeOrder(date, order.getOrderNumber());
        assertNull(dao.getOrder(date, order.getOrderNumber()));
        
        
    }

    
}
