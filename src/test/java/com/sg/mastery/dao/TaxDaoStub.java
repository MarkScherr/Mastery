/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.mastery.dao;

import com.sg.mastery.dto.TaxByState;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mrsch
 */
public class TaxDaoStub implements TaxDao {
    private List<TaxByState> taxList = new ArrayList<>();
    private TaxByState onlyTax;
    
    public TaxDaoStub() {
        BigDecimal fiveFifty = new BigDecimal("5.50");
         onlyTax = new TaxByState();
         onlyTax.setState("MI");
         onlyTax.setTaxRate(fiveFifty);
         
         taxList.add(onlyTax);
    }
    @Override
    public TaxByState addTaxRate(TaxByState stateTax) throws DataException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TaxByState getTaxRate(String stateName) throws DataException {
        if(stateName.equals(onlyTax.getState())) {
            return onlyTax;
        } else {
            return null;
        }
    }

    @Override
    public List<TaxByState> getAllTaxRates() throws DataException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateTaxRates(TaxByState oldTaxRate, TaxByState newTaxRate) throws DataException {
   }

    @Override
    public void write() throws DataException {
    }

    @Override
    public void load() throws DataException {
  }
    
}
