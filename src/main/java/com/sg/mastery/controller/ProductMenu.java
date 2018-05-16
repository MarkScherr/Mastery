/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.mastery.controller;

import com.sg.mastery.dao.DataException;
import com.sg.mastery.dao.ProductDao;
import com.sg.mastery.dao.ProductDaoImpl;
import com.sg.mastery.dto.Product;
import com.sg.mastery.service.ProductHandling;
import com.sg.mastery.ui.FlooringView;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mrsch
 */
public class ProductMenu {
    FlooringView view;
    private ProductDao dao = new ProductDaoImpl();
    private ProductHandling product = new ProductHandling(dao);
    
    public ProductMenu(FlooringView view) {
        this.view = view;
    }
    void run() {
         boolean keepGoing = true;
         int menuSelection = 0;
         
            while(keepGoing) {
           menuSelection = getMenuSelection();

           switch(menuSelection) {
               case 1:
                    displayProducts();
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

    private int getMenuSelection() {
        view.printProductMenu();
        return view.getSubMenuSelection();   
    }

    private void unknownCommand() {
       view.displayUnknownCommand();
    }

    private void displayProducts() {
        List<Product> productList = new ArrayList<>();
        try {
            productList = product.getAllProducts();
            view.displayAllProducts(productList);
        } catch (DataException e) {
            view.displayDataException(e.getMessage());
        }
    }
    
}
