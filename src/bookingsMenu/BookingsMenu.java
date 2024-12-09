package bookingsMenu;

import utility.Util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class BookingsMenu {
    private final Scanner scan;
    private final List<String> availableWeeks = new ArrayList<>();
    private final List<String> bookedWeeks = new ArrayList<>();
    String yellow = "\u001B[33m", green = "\u001B[32m", red = "\u001B[31m", resetColor = "\u001B[0m";
    Util util = new Util();
    Booking booking = new Booking();

    public BookingsMenu(Scanner scan) {
        this.scan = scan;
    }//private->public för test

    public void printMenu() { //private->public för test
        String userInput;
        Optional<Integer> parsedUserInput;
        String instruction = String.format("Sommar%nVinter%nSkriv in 'Sommar' eller 'Vinter': ");

        // Denna del är bara en mock up
        while (true) {
            System.out.println(instruction);
            userInput = scan.nextLine().trim();
            if (userInput.equalsIgnoreCase("Vinter")) {
                loadAvailableWeeks(false);
                break;
            } else if (userInput.equalsIgnoreCase("Sommar")) {
                loadAvailableWeeks(true);
                break;
            } else {
                System.err.println("Vad du skrev in motsvarar varken 'Vinter' eller 'Sommar'");
            }
        }

        if (!availableWeeks.isEmpty()) {
            makeBooking();
        } else {
            System.out.println("Det finns inte några tillgängliga veckor att boka");
        }
    }

    private void makeBooking() {
        String userInput;
        Optional<Integer> parsedUserInput;
        int chosenWeek;

        while (!availableWeeks.isEmpty()) {
            printInstruction();
            printAvailableWeeks();
            System.out.print("Skriv in val eller 'Avsluta': ");

            userInput = scan.nextLine();
            if (userInput.equalsIgnoreCase("Avsluta")) {
                booking.setState(BookingState.CANCELED);
                break;
            }

            parsedUserInput = util.parseIfAble(userInput);
            if (parsedUserInput.isPresent()) {
                chosenWeek = parsedUserInput.get() - 1; // Menyalternativen är 1 större än listindex
                if (validWeek(chosenWeek)) {
                    System.out.printf("%s%s är tillagd i din bokning.%s%n",
                            green, availableWeeks.get(chosenWeek), resetColor);

                    booking.addWeek(availableWeeks.get(chosenWeek));
                    bookedWeeks.add(availableWeeks.get(chosenWeek));
                    availableWeeks.remove(chosenWeek);

                    if (booking.getState() == BookingState.EMPTY) {
                        booking.setState(BookingState.PENDING);
                    }

                    if (finishedBooking()) {
                        booking.setState(BookingState.COMPLETED);
                        break;
                    }
                }

            } else {
                System.err.println("Det du skrev in motsvarar inte en av sifferalternativen. Försök igen.");
            }
        }
        printBookingConfirmation();
    }

    private void printBookingConfirmation() {
        if (booking.getState() == BookingState.COMPLETED) {
            System.out.println(yellow + "Du har bokat:" + resetColor);
            booking.showBooking();
        } else if (booking.getState() == BookingState.CANCELED) {
            System.out.println("Bokning avbruten.");
        }
    }

    private boolean validWeek(int chosenWeek) {
        return chosenWeek < availableWeeks.size() && chosenWeek >= 0;
    }

    private boolean finishedBooking() {
        if (!availableWeeks.isEmpty()) {
            do {
                System.out.println("Vill du boka fler veckor? (Ja / Nej)");
                String input = scan.nextLine();
                if (input.equalsIgnoreCase("Ja") || input.equalsIgnoreCase("J")) {
                    util.clearScreen();
                    return false;
                } else if (input.equalsIgnoreCase("Nej") || input.equalsIgnoreCase("N")) {
                    util.clearScreen();
                    return true;
                } else {
                    System.out.println("Vad du skrev in motsvarar varken 'Ja' eller 'Nej'");
                }
            } while (true);
        } else {
            System.out.printf("%sDet finns inte några fler tillgängliga veckor att boka%s%n", red, resetColor);
            return true;
        }
    }

    private void printAvailableWeeks() {
        for (int i = 0; i < availableWeeks.size(); i++) {
            System.out.printf("%d: %s%n", i + 1, availableWeeks.get(i));
        }
    }

    private void printInstruction() {
        String instruction = "Vänligen skriv in motsvarande siffra för den vecka du vill boka.";
        System.out.println(yellow + instruction + resetColor);
    }

    private void loadAvailableWeeks(boolean summerWeeks) {
        String filePath;
        filePath = "src/bookingsMenu/availableWeeks.properties";

        //Kommentera ut vid demo av programmet. Detta möjliggör clear av konsolskärmen
//        if (util.macUser()) {
//            filePath = System.getProperty("user.dir") + "/bookingsMenu/availableWeeks.properties";
//        } else {
//            filePath = System.getProperty("user.dir") + "\\bookingsMenu\\availableWeeks.properties";
//        }

        Properties properties = new Properties();
        String allWeeks;
        String[] parts;
        try (FileInputStream inputStream = new FileInputStream(filePath)) {
            properties.load(inputStream);
            if (summerWeeks) {
                allWeeks = properties.getProperty("available_summer_weeks");
            } else {
                allWeeks = properties.getProperty("available_winter_weeks");
            }
            if (allWeeks != null) {
                parts = allWeeks.split(",");
                for (String week : parts) {
                    availableWeeks.add(week.trim());
                }
            }
        } catch (IOException e) {
            System.err.printf("Unable to open file: %s%n", filePath);
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        BookingsMenu b = new BookingsMenu(scan);
        b.printMenu();
    }
}
