/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.mastery.controller;

import com.sg.mastery.dao.DataException;
import com.sg.mastery.dao.TaxDao;
import com.sg.mastery.dao.TaxDaoImpl;
import com.sg.mastery.dto.TaxByState;
import com.sg.mastery.service.TaxHandling;
import com.sg.mastery.ui.FlooringView;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mrsch
 */
public class TaxMenu {
    FlooringView view;
    private TaxDao tDao = new TaxDaoImpl();
    private TaxHandling tax = new TaxHandling(tDao);
    
    public TaxMenu(FlooringView view) {
        this.view = view;
    }
    void run() {
         boolean keepGoing = true;
         int menuSelection = 0;
         
        while(keepGoing) {
           menuSelection = getMainMenuSelection();

           switch(menuSelection) {
               case 1:
                   getAllTaxRates();
                   break;
               case 2: 
                   return;
               case 3:
                   view.exitBanner();
                   System.exit(0);
                   break;
               default:
                   unknownCommand();
           }
        } 
     
     }

    private int getMainMenuSelection() {
        view.printTaxMenu();
        return view.getSubMenuSelection();
    }


    private void unknownCommand() {
      view.displayUnknownCommand();
    }

    private void getAllTaxRates() {
        List<TaxByState> taxList = new ArrayList<>();
        try {
             taxList = tax.getAllTaxInfo();
             view.displayAllTaxRates(taxList);
        } catch (DataException e) {
            view.displayDataException(e.getMessage());
        }

    }
    
}
