/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.mastery.service;

import com.sg.mastery.dao.ProductDao;
import com.sg.mastery.dao.ProductDaoStub;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author mrsch
 */
public class ProductHandlingTest {
    ProductHandling service;
    
    public ProductHandlingTest() {
        ProductDao dao = new ProductDaoStub();
        service = new ProductHandling(dao);
    }
    

    /**
     * Test of getProduct method, of class ProductHandling.
     */
    @Test
    public void testGetProduct() throws Exception {
        boolean notThrown = true;
        try {
            service.getProduct("Wood");
        } catch (Exception e) {
            notThrown = false;
        }
        assertTrue(notThrown);
    }
    
        /**
     * Test of getProductThatDoesntExist method, of class ProductHandling.
     */
    @Test
    public void getProductThatDoesntExist() throws Exception {
        boolean notThrown = true;
        try {
            service.getProduct("Lightsabers");
        } catch (Exception e) {
            notThrown = false;
        }
        assertFalse(notThrown);
    }
    
    /**
     * Test of getProductThatDoesntExist method, of class ProductHandling.
     */
    @Test
    public void getProductWithImproperDataType() throws Exception {
        boolean notThrown = true;
        try {
            service.getProduct("46");
        } catch (Exception e) {
            notThrown = false;
        }
        assertFalse(notThrown);
    }
}
