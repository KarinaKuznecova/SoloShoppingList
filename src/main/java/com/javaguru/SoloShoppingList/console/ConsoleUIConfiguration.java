package com.javaguru.SoloShoppingList.console;

import com.javaguru.SoloShoppingList.console.action.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ConsoleUIConfiguration {
    private final MenuItem createNewMenu;
    private final MenuItem findByIdMenu;
    private final MenuItem addDiscountByIdMenu;
    private final MenuItem editProductMenu;
    private final MenuItem deleteByIdMenu;
    private final MenuItem printAllProductsMenu;
    private final MenuItem createNewShoppingCartMenu;
    private final MenuItem addToCartMenu;
    private final MenuItem printCartItemsMenu;

    @Autowired
    public ConsoleUIConfiguration(MenuItem createNewMenu, MenuItem findByIdMenu, MenuItem addDiscountByIdMenu, MenuItem editProductMenu, MenuItem deleteByIdMenu, MenuItem printAllProductsMenu, MenuItem createNewShoppingCartMenu, MenuItem addToCartMenu, MenuItem printCartItemsMenu) {
        this.createNewMenu = createNewMenu;
        this.findByIdMenu = findByIdMenu;
        this.addDiscountByIdMenu = addDiscountByIdMenu;
        this.editProductMenu = editProductMenu;
        this.deleteByIdMenu = deleteByIdMenu;
        this.printAllProductsMenu = printAllProductsMenu;
        this.createNewShoppingCartMenu = createNewShoppingCartMenu;
        this.addToCartMenu = addToCartMenu;
        this.printCartItemsMenu = printCartItemsMenu;
    }

    @Bean
    ConsoleUI consoleUI() {
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(0, createNewMenu);
        menuItems.add(1, findByIdMenu);
        menuItems.add(2, addDiscountByIdMenu);
        menuItems.add(3, editProductMenu);
        menuItems.add(4, deleteByIdMenu);
        menuItems.add(5, printAllProductsMenu);
        menuItems.add(6, createNewShoppingCartMenu);
        menuItems.add(7, addToCartMenu);
        menuItems.add(8, printCartItemsMenu);
        return new ConsoleUI(menuItems);
    }
}
