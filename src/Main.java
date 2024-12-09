import bookingsMenu.*;

import java.util.Scanner;

public class Main {

    MainMenuStates state = MainMenuStates.START_SCREEN;

    public Main() {

        try (Scanner scan = new Scanner(System.in)) {
            ContactDetails c = new ContactDetails(scan);
            BookingsMenu b = new BookingsMenu(scan);
            LocalActivities l = new LocalActivities(scan);
            ActivityStorage activityStorage = new ActivityStorage(scan);
            //printMainMenu();

            String line = null;
            while (((line != null)||state.equals(MainMenuStates.START_SCREEN))) {

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
                            activityStorage.printMenu();
                            state = MainMenuStates.START_SCREEN;
                        }
                        case COTTAGE_SCREEN -> {
                            System.out.println("Cottage: 2");
                            state = MainMenuStates.START_SCREEN;
                        }
                        case CONTACT_SCREEN -> {
//                            c.printMenu();
                            state = MainMenuStates.START_SCREEN;
                        }
                        case ACTIVITY_SCREEN -> {
                            l.printMenu();
                            state = MainMenuStates.START_SCREEN;
                        }
                        case BOOKING_SCREEN -> {
                            b.printMenu();
                            state = MainMenuStates.START_SCREEN;
                        }
                    }
                }
        }
    }

    public void printMainMenu(){
        System.out.println("\nSkriv in numret bredvid menynamnet för att välja meny:" +
                            "\nStorage[1], Cottage[2], Contact info[3], Activities[4], Booking[5]");
    }

    public static void main (String[]args){
        new Main();
    }
}

