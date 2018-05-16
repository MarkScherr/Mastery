/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.mastery.dao;

import com.sg.mastery.dto.Product;
import java.util.List;

/**
 *
 * @author mrsch
 */
public interface ProductDao {
    
    Product addProduct(Product product) throws DataException;
    
    Product getProduct(String productName) throws DataException;
    
    List<Product> getAllProducts() throws DataException;
    
    void removeProduct(String productName) throws DataException;
    
    void write() throws DataException;
    
    void load() throws DataException;
}
