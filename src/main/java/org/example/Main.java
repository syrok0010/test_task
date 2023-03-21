package org.example;
import org.example.views.Client;
import org.example.views.Server;

import java.util.Scanner;
import static org.example.views.Home.GetWorkingMode;

public class Main {
    public static Scanner in;
    public static void main(String[] args) {
        in = new Scanner(System.in);
        WorkingMode mode = GetWorkingMode(in);
        switch (mode) {
            case Client -> new Client().run();
            case Server -> new Server().run();
        }
    }
}