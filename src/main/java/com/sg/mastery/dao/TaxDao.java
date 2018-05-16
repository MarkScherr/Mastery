/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.mastery.dao;

import com.sg.mastery.dto.TaxByState;
import java.util.List;

/**
 *
 * @author mrsch
 */
public interface TaxDao {
    
    TaxByState addTaxRate(TaxByState stateTax) throws DataException;
    
    TaxByState getTaxRate(String stateName) throws DataException;
    
    List<TaxByState> getAllTaxRates() throws DataException;
    
    void updateTaxRates(TaxByState oldTaxRate, TaxByState newTaxRate) throws DataException;
    
    void write() throws DataException;
    
    void load() throws DataException;
    
    
}
