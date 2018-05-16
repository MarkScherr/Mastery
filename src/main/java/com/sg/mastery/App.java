/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.mastery;


import com.sg.mastery.controller.MainMenu;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author mrsch
 */
public class App {
    public static void main(String[] args) {
        ApplicationContext ctx = 
           new ClassPathXmlApplicationContext("applicationContext.xml");
        MainMenu main = 
           ctx.getBean("mainMenu", MainMenu.class);
        main.run();
       
        /* Code if you were to remove Spring;
        
        UserIO myIO = new UserIOConsoleImpl();
        FlooringView myView = new FlooringView(myIO);
        OrderDao myOrderDao = new OrderDaoImpl();
        ProductDao myProductDao = new ProductDaoImpl();
        TaxDao myTaxDao = new TaxDaoImpl();
        ProductHandling myProductHandler = new ProductHandlingImpl(myProductDao);
        TaxHandling myTaxHandler = new TaxHandlingImpl(myTaxDao);
        OrderHandler myOrderHandler = new OrderHandlerImpl(myOrderDao, myProductHandler, myTaxHandler);
        MainMenu main = new MainMenu(myView, myOrderHandler);
        main.run(); */
    }
    
}
