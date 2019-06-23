package com.javaguru;

import com.javaguru.config.AppConfig;
import com.javaguru.console.ConsoleUI;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class ShoppingListApplication {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ConsoleUI consoleUI = context.getBean(ConsoleUI.class);
        consoleUI.go();
    }
}