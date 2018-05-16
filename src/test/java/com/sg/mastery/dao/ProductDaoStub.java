/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.mastery.dao;

import com.sg.mastery.dto.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author mrsch
 */
public class ProductDaoStub implements ProductDao {
    private List<Product> productList = new ArrayList<>();
    private Product onlyProduct;
    
    public ProductDaoStub() {
        BigDecimal twoFifty = new BigDecimal("2.50");
        BigDecimal threeFifty = new BigDecimal("3.50");
        
        onlyProduct = new Product();
        onlyProduct.setProductType("Wood");
        onlyProduct.setCostPerSquareFoot(twoFifty);
        onlyProduct.setLaborCostPerSquareFoot(threeFifty);
        
        productList.add(onlyProduct);
    }
    @Override
    public Product addProduct(Product product) throws DataException {
            if(product.getProductType().equals(onlyProduct.getProductType())) {
                return onlyProduct;
            } else {
                return null;
            }
    }

    @Override
    public Product getProduct(String productName) throws DataException {
            if(productName.equals(onlyProduct.getProductType())) {
                return onlyProduct;
            } else {
                return null;
            }
        
    }

    @Override
    public List<Product> getAllProducts() throws DataException {
        return productList;
    }

    @Override
    public void removeProduct(String productName) throws DataException {
    }

    @Override
    public void write() throws DataException { 
        
    }

    @Override
    public void load() throws DataException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}