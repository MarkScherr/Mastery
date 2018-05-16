/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.mastery.dao;

import com.sg.mastery.dto.Product;
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
public class ProductDaoImpl implements ProductDao {
    private List<Product> products = new ArrayList<>();
    private String productInfo = "Products.txt";
    private static final String DELIMITER =",";
    
    @Override
    public Product addProduct(Product product) throws DataException {
        load();
        products.add(product);
        return product;
    }

    @Override
    public Product getProduct(String productName) throws DataException {
        load();
        for (Product product : products) {
                if (product.getProductType().toLowerCase().contains(productName.toLowerCase())) {
                       return products.get(products.indexOf(product));
                 } 
              }
        return null;
        
    }

    @Override
    public List<Product> getAllProducts() throws DataException {
        load();
        return products;
    }

    @Override
    public void removeProduct(String productName) throws DataException {
        for (Product product : products) {
            if(product.getProductType().toLowerCase().contains(productName.toLowerCase())) {
                products.remove(products.indexOf(product));
            } 
        }
    }

    @Override
    public void write() throws DataException {
        PrintWriter out;
        
        try {
            out = new PrintWriter(new FileWriter(productInfo));
        } catch (IOException e) {
            throw new DataException(
	                "Could not save Product data.", e);
        }
        out.println("ProductType,CostPerSquareFoot,LaborCostPerSquareFoot");
        List<Product> productList = this.getAllProducts();
        for (Product currentProduct : productList) {
            out.println(currentProduct.getProductType()+ DELIMITER
                                  + currentProduct.getCostPerSquareFoot()+ DELIMITER
	                + currentProduct.getLaborCostPerSquareFoot());
            
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
                            new FileReader(productInfo)));
        } catch (FileNotFoundException ex) {
             throw new DataException (
                "-_- Could not find file to load.", ex);
        }
     
        String currentLine;
        String[] currentTokens;
        
        if(products.isEmpty()) {
            scanner.nextLine();
            while(scanner.hasNextLine()){
                currentLine = scanner.nextLine();
                currentTokens = currentLine.split(DELIMITER);
                Product currentProduct= new Product();
                
                if(currentTokens.length == 3) {
                    currentProduct.setProductType(currentTokens[0]);
                    currentProduct.setCostPerSquareFoot(new BigDecimal(currentTokens[1]));
                    currentProduct.setLaborCostPerSquareFoot(new BigDecimal(currentTokens[2]));

                    products.add(currentProduct);
                }
            }
        }
        scanner.close();
    }
    
}
