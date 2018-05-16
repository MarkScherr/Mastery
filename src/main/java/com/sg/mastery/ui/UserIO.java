/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.mastery.ui;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author mrsch
 */
public interface UserIO {
        void print(String msg);

        double readDouble(String prompt);

        double readDouble(String prompt, double min, double max);

        int readInt(String prompt);

        int readInt(String prompt, int min, int max);

        String readString(String prompt);
        
         String readString(String prompt, int min);
        
         BigDecimal readBigDecimal(String prompt);
         
         BigDecimal readBigDecimal(String prompt, int min);
        
         LocalDate readLocalDate(String prompt);
}
