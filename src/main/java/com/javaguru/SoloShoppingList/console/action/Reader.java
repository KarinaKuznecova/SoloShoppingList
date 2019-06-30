package com.javaguru.SoloShoppingList.console.action;

import java.util.Scanner;

public class Reader {

    public int getUserInput(String prompt) {
        System.out.println(prompt);
        Scanner sc = new Scanner(System.in);
        try {
            return sc.nextInt();
        } catch (Exception ex) {
            System.out.println("Something wrong, try again");
            getUserInput(prompt);
        }
        return 0;
    }

    public String getUserInputLine(String prompt) {
        System.out.println(prompt);
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public double getUserInputDouble(String prompt) {
        System.out.println(prompt);
        Scanner sc = new Scanner(System.in);
        try {
            return sc.nextDouble();
        } catch (Exception ex) {
            System.out.println("Something wrong, try again");
            getUserInputDouble(prompt);
        }
        return 0;
    }
}