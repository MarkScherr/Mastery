/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.mastery.dao;

import com.sg.mastery.dto.TaxByState;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author mrsch
 */
public class TaxDaoImpl implements TaxDao {
    private List<TaxByState> taxes = new ArrayList<>();
    private String taxInfo = "Taxes.txt";
    private static final String DELIMITER =",";

    @Override
    public TaxByState addTaxRate(TaxByState stateTax) throws DataException {
        load();
        taxes.add(stateTax);
        return stateTax;
    }

    @Override
    public TaxByState getTaxRate(String stateName) throws DataException {
        load();
        for (TaxByState tax : taxes) {
            if(tax.getState().toLowerCase().equals(stateName.toLowerCase())) {
                return taxes.get(taxes.indexOf(tax));
            }
        }
        return null;
    }

    @Override
    public List<TaxByState> getAllTaxRates() throws DataException {
        load();
        return taxes;
    }
    public void removeTaxRates(String stateName) {
        for (TaxByState tax : taxes) {
            if(tax.getState().equals(stateName)) {
                taxes.remove(taxes.indexOf(tax));
            }
        }
    }
    @Override
    public void updateTaxRates(TaxByState oldTaxRate, TaxByState newTaxRate) throws DataException {
         int i = taxes.indexOf(oldTaxRate);
        removeTaxRates(oldTaxRate.getState());
        taxes.add(newTaxRate);
        write();   
    }

    @Override
    public void write() throws DataException {
        PrintWriter out;
        
        try {
            out = new PrintWriter(new FileWriter(taxInfo));
        } catch (IOException e) {
            throw new DataException(
	                "Could not save Product data.", e);
        }
        out.println("State,TaxRate");
        List<TaxByState> taxList = this.getAllTaxRates();
        for (TaxByState currentStateTax : taxList) {
                                    out.println(currentStateTax.getState()+ DELIMITER
                                  + currentStateTax.getTaxRate());
            
            out.flush();
        }
        
        out.close();
    }

    @Override
    public void load() throws DataException {
              Scanner scanner;

        try {
            scanner = new Scanner(
                            new BufferedReader(
                            new FileReader(taxInfo)));
        } catch (FileNotFoundException ex) {
             throw new DataException (
                " -_- Could not find file to load.", ex);
        }
     
        String currentLine;
        String[] currentTokens;
        
        if(taxes.isEmpty()) {
            scanner.nextLine();
            while(scanner.hasNextLine()){
                currentLine = scanner.nextLine();
                currentTokens = currentLine.split(DELIMITER);
                TaxByState currentStateTax= new TaxByState();
                if(currentTokens.length == 2) {
                    currentStateTax.setState(currentTokens[0]);
                    currentStateTax.setTaxRate(new BigDecimal(currentTokens[1]));

                    taxes.add(currentStateTax);
                }
            }
        }
        scanner.close();
    }
    
    
}
