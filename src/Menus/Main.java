package Menus;

import bookingsMenu.*;
import utility.Utility;

import java.util.Scanner;

public class Main {

    MainMenuStates state = MainMenuStates.START_SCREEN;

    public Main() {

        try (Scanner scan = new Scanner(System.in)) {
            ContactDetails c = new ContactDetails(scan);
            BookingsMenu b = new BookingsMenu(scan);
            LocalActivities l = new LocalActivities(scan);
            ActivityStorage activityStorage = new ActivityStorage(scan);
            //printMenu();

            String line = null;
            while (((line != null) || state.equals(MainMenuStates.START_SCREEN))) {

                switch (state) {
                    case START_SCREEN -> {
                        printMainMenu();
                        while (state.equals(MainMenuStates.START_SCREEN)) {
                            line = scan.nextLine();
                            for (MainMenuStates chosenState : MainMenuStates.values()) {
                                if (chosenState.stateValue.equals(line.trim())) {
                                    state = chosenState;
                                }
                            }
                        }
                    }
                    case STORAGE_SCREEN -> {
                        Utility.clearScreen();
                        activityStorage.printMenu();
                        state = MainMenuStates.START_SCREEN;
                    }
                    case COTTAGE_SCREEN -> {
                        Utility.clearScreen();
                        System.out.println("Cottage: 2");
                        state = MainMenuStates.START_SCREEN;
                    }
                    case CONTACT_SCREEN -> {
                        Utility.clearScreen();
//                            c.printMenu();
                        state = MainMenuStates.START_SCREEN;
                    }
                    case ACTIVITY_SCREEN -> {
                        Utility.clearScreen();
                        l.printMenu();
                        state = MainMenuStates.START_SCREEN;
                    }
                    case BOOKING_SCREEN -> {
                        Utility.clearScreen();
                        b.printMenu();
                        state = MainMenuStates.START_SCREEN;
                    }
                    case EXIT -> System.exit(0);
                }
            }
        }
    }

    public void printMainMenu() {
        System.out.println("""
                [1] Storage
                [2] Cottage
                [3] Contact info
                [4] Activities
                [5] Booking
                [6] Exit
                """);
    }

    public static void main(String[] args) {
        new Main();
    }
}

