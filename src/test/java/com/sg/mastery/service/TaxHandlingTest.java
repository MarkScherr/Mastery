/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.mastery.service;

import com.sg.mastery.dao.TaxDao;
import com.sg.mastery.dao.TaxDaoStub;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author mrsch
 */
public class TaxHandlingTest {
    TaxHandling service;
    
    public TaxHandlingTest() {
        TaxDao dao = new TaxDaoStub();
        service = new TaxHandling(dao);
    }
    

    /**
     * Test of getProduct method, of class ProductHandling.
     */
    @Test
    public void testGetTaxInfo() throws Exception {
        boolean notThrown = true;
        try {
            service.getTaxInfo("MI");
        } catch (Exception e) {
            notThrown = false;
        }
        assertTrue(notThrown);
    }
    
        /**
     * Test of getProductThatDoesntExist method, of class ProductHandling.
     */
    @Test
    public void getTaxInfoThatDoesntExist() throws Exception {
        boolean notThrown = true;
        try {
            service.getTaxInfo("Lightsabers");
        } catch (Exception e) {
            notThrown = false;
        }
        assertFalse(notThrown);
    }
}