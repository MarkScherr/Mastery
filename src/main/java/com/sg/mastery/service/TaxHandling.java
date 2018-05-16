/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.mastery.service;

import com.sg.mastery.dao.DataException;
import com.sg.mastery.dao.TaxDao;
import com.sg.mastery.dto.TaxByState;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author mrsch
 */
public class TaxHandling {
    private TaxDao dao;
   
    public TaxHandling (TaxDao dao) {
        this.dao = dao;
    }
    

    public BigDecimal getTaxInfo(String state) throws DataException {
            if (dao.getTaxRate(state) == null ||
                    !dao.getTaxRate(state).getState().toLowerCase().equals(state.toLowerCase())) {
                    throw new DataException(" ' " + state + " ' " + "is not an acceptable state.");
            }
            return dao.getTaxRate(state).getTaxRate();
    }

    public List<TaxByState> getAllTaxInfo() throws DataException {
        if (dao.getAllTaxRates().size() == 0) {
            throw new DataException(" There were no state entries in the system.");
        }
       return dao.getAllTaxRates();
    }
}
