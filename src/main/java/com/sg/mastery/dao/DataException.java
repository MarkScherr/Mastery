/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.mastery.dao;

/**
 *
 * @author mrsch
 */
public class DataException extends Exception {
    
        public DataException(String message) {
            super(message);
        }

        public DataException(String message, Throwable cause) {
            super(message, cause);
        }
}
