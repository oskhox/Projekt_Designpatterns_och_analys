package bookingsMenu.states;

import bookingsMenu.BookingsMenu;
import utility.Utility;

import java.util.Optional;
import java.util.Scanner;

public class SelectWeekState implements BookingState {
    private Scanner scan;

    SelectWeekState(Scanner scan) {
        this.scan = scan;
    }

    @Override
    public void handle(BookingsMenu context) {
        String userInput;
        Optional<Integer> parsedUserInput;
        int chosenWeek;

        Utility.clearScreen();
        while (context.availableWeeks()) {
            printInstructionHeader();
            context.printAvailableWeeks();
            printInstructionFooter(context);
            userInput = scan.nextLine();
            if (userInput.equalsIgnoreCase("Klar")) {
                context.setCurrentState(new CompletedBookingState());
                return;
            }
            if (userInput.equalsIgnoreCase("Avsluta")) {
                context.setCurrentState(new CanceledBookingState());
                return;
            }
            parsedUserInput = Utility.parseIfAble(userInput);
            if (parsedUserInput.isPresent()) {
                chosenWeek = parsedUserInput.get() - 1; // Menyalternativen är 1 större än listindex

                if (context.validWeek(chosenWeek)) {
                    context.addBooking(chosenWeek);
                    if (!context.getBooking().isPending()) {
                        context.getBooking().setPending(true);
                    }
                    if (finishedBooking(context)) {
                        context.setCurrentState(new CompletedBookingState());
                        return;
                    }
                }
            } else {
                System.err.println("Det du skrev in motsvarar inte något av alternativen. Försök igen.");
            }
        }
    }

    private void printInstructionFooter(BookingsMenu context) {
        if (context.getBooking().isPending()) {
            System.out.print("Skriv in val, 'Klar' eller 'Avsluta': ");
        } else {
            System.out.print("Skriv in val eller 'Avsluta': ");
        }
    }

    private void printInstructionHeader() {
        String yellow = "\u001B[33m";
        String resetColor = "\u001B[0m";
        String instruction = "Vänligen skriv in motsvarande siffra för den vecka du vill boka.";
        System.out.println(yellow + instruction + resetColor);
    }

    private boolean finishedBooking(BookingsMenu context) {
        if (context.availableWeeks()) {
            do {
                System.out.println("Vill du boka fler veckor? (Ja / Nej)");
                String input = scan.nextLine();
                if (input.equalsIgnoreCase("Ja") || input.equalsIgnoreCase("J")) {
                    Utility.clearScreen();
                    return false;
                } else if (input.equalsIgnoreCase("Nej") || input.equalsIgnoreCase("N")) {
                    Utility.clearScreen();
                    return true;
                } else {
                    System.out.println("Vad du skrev in motsvarar varken 'Ja' eller 'Nej'");
                }
            } while (true);
        } else {
            System.err.println("Det finns inte några fler tillgängliga veckor att boka under vald period.");
            return true;
        }
    }
}

