package org.example;

import javafx.application.Application;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите номер задания (1 - 5): ");

        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Ошибка ввода. Введите число 1 – 5.");
            return;
        }

        switch (choice) {
            case 1: {
                Application.launch(ex1.WordFlipper.class, args);
                break;
            }
            case 2: {
                Application.launch(ex2.WidgetToggle.class, args);
                break;
            }
            case 3: {
                Application.launch(ex3.FrenchRestaurant.class, args);
                break;
            }
            case 4: {
                Application.launch(ex4.Calculator.class, args);
                break;
            }
            case 5: {
                Application.launch(ex5.TextFlag.class, args);
                break;
            }

            default: {
                System.out.println("Нет такого задания. Нужно число от 1 до 5.");
                break;
            }
        }
    }
}
