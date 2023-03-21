package org.example.views;

import org.example.WorkingMode;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Home {
    public static WorkingMode GetWorkingMode(Scanner in) {
        System.out.println("Select the working mode");
        var allModes = WorkingMode.values();
        for (int i = 0; i < allModes.length; i++)
            System.out.println(String.join("", Integer.toString(i), " - ", allModes[i].toString()));
        while (true) {
            try {
                var choice = in.nextInt();
                if (choice < 0 || choice >= allModes.length) {
                    System.out.println("The selected mode does not exist. Try again");
                    continue;
                }
                in.nextLine();
                return allModes[choice];
            } catch (InputMismatchException e) {
                System.out.println("The selected mode does not exist. Try again");
            }
        }
    }
}
