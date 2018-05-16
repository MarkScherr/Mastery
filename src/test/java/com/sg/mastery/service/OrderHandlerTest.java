/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.mastery.service;

import com.sg.mastery.dao.DataException;
import com.sg.mastery.dao.OrderDao;
import com.sg.mastery.dao.OrderDaoStub;
import com.sg.mastery.dao.ProductDao;
import com.sg.mastery.dao.ProductDaoStub;
import com.sg.mastery.dao.TaxDao;
import com.sg.mastery.dao.TaxDaoStub;
import com.sg.mastery.dto.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author mrsch
 */
public class OrderHandlerTest {
    OrderHandler service;
    LocalDate date = LocalDate.now();
    
    
    public OrderHandlerTest() {
        OrderDao dao = new OrderDaoStub();
        ProductDao pDao = new ProductDaoStub();
        TaxDao tDao = new TaxDaoStub();
        ProductHandling productHandler = new ProductHandling(pDao);
        TaxHandling taxHandler = new TaxHandling(tDao);
        service = new OrderHandler(dao, productHandler, taxHandler);
    }
    
    @Test
    public void testGetAllOrders() throws DataException {
        assertTrue(service.getOrders(date).size()>0);
    }
        
    /**
     * Test of addAndGetOrder method, of class OrderHandler.
     */
    @Test
    public void testAddAndGetOrder() throws Exception {
        Order order = new Order();
        order.setOrderNumber(1);
        order.setCustomerName("test1");
        order.setState("MI");
        order.setProductType("Wood");
        BigDecimal five = new BigDecimal("5.0");
        BigDecimal ten = new BigDecimal("10.0");
        boolean notThrown = true;
        
        try {
            service.addOrder(date, "test1", "MI" , "Wood", five, ten);
        } catch (Exception e) {
            notThrown = false;

        }
        
        try {
            service.getOrder(date, 1);   
        } catch (Exception e) {
             notThrown = false;

        }
        
        assertTrue(notThrown);
    }

    /**
     * Test of removeOrder method, of class OrderHandler.
     */
    @Test
    public void testRemoveOrder() throws Exception {

        boolean notThrown = true;
        
        try {
            service.removeOrder(date, 1);
        } catch (Exception e) {
            notThrown = false;
        }
        assertTrue(notThrown);
    }

    /**
     * Test of updateOrder method, of class OrderHandler.
     */
    @Test
    public void testGetItemNotInList() throws Exception {
        boolean notThrown = true;
        try {
            service.getOrder(date, 15);
        } catch (Exception e) {
            notThrown =false;
        }
        assertFalse(notThrown);
    }
    
    @Test
    public void removeAnInvalidOrder() {
        boolean notThrown = true;
        try {
            service.removeOrder(date, 500);
        } catch (Exception e) {
            notThrown = false;
        }
        assertFalse(notThrown);
    }
    
    @Test
    public void addAnItemWithInvalidData() {
        boolean notThrown = true;
        Order order = new Order();
        order.setOrderNumber(2);
        order.setCustomerName("test1");
        order.setState("USA");
        order.setProductType("kittens");
        BigDecimal five = new BigDecimal("4");
        BigDecimal ten = new BigDecimal("6");
        try {
            service.addOrder(date, order.getCustomerName(), order.getState(), order.getProductType(), five, ten);
        } catch (Exception e) {
            notThrown = false;
        }
        
        assertFalse(notThrown);
    }


    
}
