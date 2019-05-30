package com.javaguru.console;
import java.util.Scanner;

class Reader {

    int getUserInput(String prompt) {
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

    String getUserInputLine(String prompt) {
        System.out.println(prompt);
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    double getUserInputDouble(String prompt) {
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