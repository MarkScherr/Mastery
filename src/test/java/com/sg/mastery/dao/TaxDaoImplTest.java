/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.mastery.dao;

import com.sg.mastery.dto.TaxByState;
import java.math.BigDecimal;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author mrsch
 */
public class TaxDaoImplTest {
    TaxDao dao = new TaxDaoImpl();
    
    
    public TaxDaoImplTest() {
    }
    

    /**
     * Test of addAndGetTaxRate method, of class TaxDaoImpl.
     */
    @Test
    public void testAddAndGetTaxRate() throws Exception {
        boolean notThrown = true;
        TaxByState tax = new TaxByState();
        BigDecimal fourPointThreeFive = new BigDecimal("4.35");
        tax.setState("FakeState");
        tax.setTaxRate(fourPointThreeFive);
    
        dao.addTaxRate(tax);
        
        try {
            dao.getTaxRate(tax.getState());
        } catch (Exception e) {
            notThrown = false;
        }
        assertTrue(notThrown);
    }

    }
