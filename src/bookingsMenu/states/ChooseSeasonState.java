package bookingsMenu.states;

import bookingsMenu.BookingType;
import bookingsMenu.BookingsMenu;
import utility.Utility;

import java.util.Scanner;

public class ChooseSeasonState implements BookingState {
    private final Scanner scan;

    public ChooseSeasonState(Scanner scan) {
        this.scan = scan;
    }

    @Override
    public void handle(BookingsMenu context) {
        String userInput;
        String instruction = String.format("Bokning%nSommar%nVinter%nSkriv in 'Sommar' eller 'Vinter': ");
        Utility.clearScreen();
        while (true) {
            System.out.println(instruction);
            userInput = scan.nextLine().trim();
            if (userInput.equalsIgnoreCase("Vinter")) {
                context.setBookingType(BookingType.WINTER);
                break;
            } else if (userInput.equalsIgnoreCase("Sommar")) {
                context.setBookingType(BookingType.SUMMER);
                break;
            } else {
                Utility.clearScreen();
                System.err.println("Vad du skrev in motsvarar varken 'Vinter' eller 'Sommar'");
            }
        }
        checkAvailableWeeks(context);
    }
    private void checkAvailableWeeks(BookingsMenu context) {
        if (context.getBookingType() == BookingType.SUMMER) {
            if (!context.getAvailableSummerWeeks().isEmpty()) {
                context.setCurrentState(new SelectWeekState(scan));
            } else {
                System.out.println("Det finns inte några tillgängliga veckor under sommaren att boka");
            }
        }
        else {
            if (!context.getAvailableWinterWeeks().isEmpty()) {
                context.setCurrentState(new SelectWeekState(scan));
            } else {
                System.out.println("Det finns inte några tillgängliga veckor under vintern att boka");
            }
        }
    }
}
