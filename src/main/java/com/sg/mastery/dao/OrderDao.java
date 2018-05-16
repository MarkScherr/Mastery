/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.mastery.dao;

import com.sg.mastery.dto.Order;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author mrsch
 */
public interface OrderDao {
    /**
     * 
     * @param order
     * @return 
     */
    public Order addOrder(LocalDate date, Order order)  throws DataException;
    /**
     * 
     * @param orderNumber
     * @return 
     */
    public Order getOrder(LocalDate date, int orderNumber)  throws DataException;
    /**
     * 
     * @return 
     */
    public List<Order> getAllOrders(LocalDate date)  throws DataException;
    /**
     * 
     * @param orderNumber
     * @return 
     */
    public Order removeOrder(LocalDate date, int orderNumber)  throws DataException;
    /**
     * 
     * @param oldOrder
     * @param newOrder 
     */
    public void updateOrder(LocalDate date, Order oldOrder, Order newOrder)  throws DataException;
    /**
     * 
     */
    public void write(LocalDate date) throws DataException;
    /**
     * 
     */
    public void load(LocalDate date) throws DataException;


}
