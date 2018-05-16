/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.mastery.service;

import com.sg.mastery.dao.DataException;
import com.sg.mastery.dao.ProductDao;
import com.sg.mastery.dto.Product;
import java.util.List;

/**
 *
 * @author mrsch
 */
public class ProductHandling {
    private ProductDao dao;
    
    public ProductHandling(ProductDao dao) {
        this.dao = dao;
    }
    
    public Product getProduct(String productName) throws DataException {
        if (dao.getProduct(productName) ==null ||
                !dao.getProduct(productName).getProductType().toLowerCase().contains(productName.toLowerCase())) {
            throw new DataException(productName + " is not in the list of products.");
        }
        return dao.getProduct(productName);

    }
    
    public List<Product> getAllProducts() throws DataException {
        if (dao.getAllProducts().isEmpty()) {
            throw new DataException("There were no products in the product list.");
        }
        return dao.getAllProducts();
    }
}
