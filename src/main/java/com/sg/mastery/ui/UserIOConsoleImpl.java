/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.mastery.ui;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 *
 * @author mrsch
 */
public class UserIOConsoleImpl implements UserIO {
    
    private Scanner input = new Scanner(System.in);
    
    @Override
    public void print(String prompt) {
        System.out.println(prompt);
    }
        public String readString(String prompt, int min) {
        boolean valid = false;
        String tempString = "";
        do {
            System.out.println(prompt);
            tempString =input.nextLine();
            if (tempString.length() < min) {
                System.out.println("You need to enter at least " + min + " character(s)");
                valid = false;
            } else {
                valid = true;
            }
        }while (!valid);
        return tempString;
    }
    
    @Override
    public String readString(String prompt) {
        boolean valid = false;
        String tempString = "";
        do {
            System.out.println(prompt);
            tempString =input.nextLine();
            if (tempString.length() == 0) {
                System.out.println("You need to enter at least one character!");
               valid = false;
            } else {
                valid = true;
            }
        }while (!valid);
        return tempString;
    }
    @Override
    public BigDecimal readBigDecimal(String prompt) {
        boolean valid = false;
        BigDecimal result = new BigDecimal("1");
        
         do {
             String value = null;
            try {
                value = readString(prompt);
                // value 'x' is used to test that the input is a number.
                double x = Double.parseDouble(value);
                result = new BigDecimal(value);
                result.setScale(2, BigDecimal.ROUND_HALF_UP);
                valid = true;
            } catch (NumberFormatException ex) {
                 System.out.printf("The value '%s' you entered is not a number\n", ex.getMessage());
            } 
        } while (!valid);
        return result;
    }
    
    @Override
    public BigDecimal readBigDecimal(String prompt, int min) {
        boolean valid = false;
        BigDecimal result = new BigDecimal("1");
        
         do {
             String value = null;
            try {
                value = readString(prompt);
                // value 'x' is used to test that the input is a number.
                double x = Double.parseDouble(value);
                if (x > min) {
                    result = new BigDecimal(value);
                    result.setScale(2, BigDecimal.ROUND_HALF_UP);
                    valid = true;
                } else {
                    System.out.println("You need to enter a number larger than " + min);
                }
            } catch (NumberFormatException ex) {
                 System.out.printf("The value '%s' you entered is not a number\n", ex.getMessage());
            } 
        } while (!valid);
        return result;
    }
    
    @Override
    public int readInt(String prompt) {
        boolean valid = false;
        int result = 0;
        do {
            String value = null;
            try {
                value = readString(prompt);
                result = Integer.parseInt(value);
                valid = true;
            } catch (NumberFormatException ex) {
                System.out.printf("The value '%s' you entered is not a number\n", value);
            }
        } while (!valid);
        return result;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        boolean valid = false;
        int result = 0;
        do {
            result = readInt(prompt);
            if (result >= min && result <= max) {
                valid = true;
            } else {
                System.out.printf("Value must be between %d and %d. \n", min, max);
            }
        } while (!valid);
        return result;
    }
    
    @Override
    public double readDouble(String prompt) {
        boolean valid = false;
        double result = 0.0;
        do {
            String value = null;
            try {
                value = readString(prompt);
                result = Double.parseDouble(value);
                valid = true;
            } catch (NumberFormatException ex) {
                System.out.printf("The value '%s' you entered is not a number\n", value);
            }
        } while (!valid);
        return result;
    }
     public LocalDate readLocalDate(String prompt) {
         boolean valid = false;
         LocalDate result = null;
         String value = null;
         DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMddyyyy");
         do {
             try {
                   value = readString(prompt);
                   result = LocalDate.parse(value, dateFormat);
                   valid = true;
             } catch (DateTimeParseException  e) {
                 System.out.printf("The value '%s' you entered is not a date\n", value);
             }
             
         } while (!valid);

         return result;
     }
    @Override
    public double readDouble(String prompt, double min, double max) {
        boolean valid = false;
        double result = 0.0;
        do {
            result = readDouble(prompt);
            if (result >= min && result <= max) {
                valid = true;
            } else {
                System.out.printf("Value must be between '%s' and '%s'. \n", min, max);
            }
        } while (!valid);
        return result;
    }   
}
