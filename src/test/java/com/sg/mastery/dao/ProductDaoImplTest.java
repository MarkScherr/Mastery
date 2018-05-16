/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.mastery.dao;


import com.sg.mastery.dto.Product;
import java.math.BigDecimal;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author mrsch
 */
public class ProductDaoImplTest {
    ProductDao dao = new ProductDaoImpl();
    
    public ProductDaoImplTest() {
    }

    /**
     * Test of addProduct method, of class ProductDaoImpl.
     */
    @Test
    public void testAddAndGetProduct() throws Exception {
        boolean notThrown = true;
        Product product = new Product();
        BigDecimal fourPointThreeFive = new BigDecimal("4.35");
        BigDecimal threePointFive = new BigDecimal("3.5");
        product.setProductType("Item");
        product.setCostPerSquareFoot(fourPointThreeFive);
        product.setLaborCostPerSquareFoot(threePointFive);
        
        dao.addProduct(product);
        
        
        try {
            dao.getProduct("Item");
        } catch (Exception e) {
            notThrown = false;
        }
        
        assertTrue(notThrown);
    }


}
