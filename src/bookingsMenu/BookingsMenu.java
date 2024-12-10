package bookingsMenu;

import Menus.MenuScreen;
import utility.Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class BookingsMenu implements MenuScreen {
    private final Scanner scan;
    private final List<String> availableSummerWeeks = new ArrayList<>();
    private final List<String> availableWinterWeeks = new ArrayList<>();
    private final List<String> bookedWeeks = new ArrayList<>();
    private final String yellow = "\u001B[33m";
    String red = "\u001B[31m";
    private final String resetColor = "\u001B[0m";
    private final Booking booking = new Booking();
    private BookingType chosenType;

    enum BookingType {
        SUMMER, WINTER
    }

    public BookingsMenu(Scanner scan) {
        this.scan = scan;
        loadAvailableWeeks();
    }

    public void runMenu() {
        chooseSeason();
        if (chosenType == BookingType.SUMMER) {
            if (!availableSummerWeeks.isEmpty()) {
                makeBooking(BookingType.SUMMER);
            } else {
                System.out.println("Det finns inte några tillgängliga veckor under sommaren att boka");
            }
        }
        else {
            if (!availableWinterWeeks.isEmpty()) {
                makeBooking(BookingType.WINTER);
            } else {
                System.out.println("Det finns inte några tillgängliga veckor under vintern att boka");
            }
        }
    }

    private void chooseSeason() {
        String userInput;
        String instruction = String.format("Sommar%nVinter%nSkriv in 'Sommar' eller 'Vinter': ");
        while (true) {
            System.out.println(instruction);
            userInput = scan.nextLine().trim();
            if (userInput.equalsIgnoreCase("Vinter")) {
                chosenType = BookingType.WINTER;
                break;
            } else if (userInput.equalsIgnoreCase("Sommar")) {
                chosenType = BookingType.SUMMER;
                break;
            } else {
                System.err.println("Vad du skrev in motsvarar varken 'Vinter' eller 'Sommar'");
            }
        }
    }

    private void makeBooking(BookingType typeOfBooking) {
        String userInput;
        Optional<Integer> parsedUserInput;
        int chosenWeek;

        Utility.clearScreen();
        while (availableWeeks(typeOfBooking)) {
            printInstruction();
            printAvailableWeeks(typeOfBooking);
            if (booking.getState() == BookingState.PENDING) {
                System.out.print("Skriv in val, 'Klar' eller 'Avsluta': ");
            } else {
                System.out.print("Skriv in val eller 'Avsluta': ");
            }

            userInput = scan.nextLine();
            if (userInput.equalsIgnoreCase("Klar")) {
                booking.setState(BookingState.COMPLETED);
                break;
            }
            if (userInput.equalsIgnoreCase("Avsluta")) {
                booking.setState(BookingState.CANCELED);
                break;
            }

            parsedUserInput = Utility.parseIfAble(userInput);
            if (parsedUserInput.isPresent()) {
                chosenWeek = parsedUserInput.get() - 1; // Menyalternativen är 1 större än listindex

                if (validWeek(chosenWeek, chosenType)) {
                    completeBooking(chosenType, chosenWeek);
                    if (booking.getState() == BookingState.EMPTY) {
                        booking.setState(BookingState.PENDING);
                    }
                    if (finishedBooking(chosenType)) {
                        booking.setState(BookingState.COMPLETED);
                        break;
                    }
                }

            } else {
                System.err.printf("%sDet du skrev in motsvarar inte något av alternativen. Försök igen.%s%n",
                        red, resetColor);
            }
        }
        printBookingConfirmation();
    }

    private void completeBooking(BookingType chosenType, int chosenWeek) {
        String green = "\u001B[32m";
        if (chosenType == BookingType.SUMMER) {
            System.out.printf("%s%s är tillagd i din bokning.%s%n",
                    green, availableSummerWeeks.get(chosenWeek), resetColor);
            booking.addWeek(availableSummerWeeks.get(chosenWeek));
            bookedWeeks.add(availableSummerWeeks.get(chosenWeek));
            availableSummerWeeks.remove(chosenWeek);
        }
        else {
            System.out.printf("%s%s är tillagd i din bokning.%s%n",
                    green, availableWinterWeeks.get(chosenWeek), resetColor);
            booking.addWeek(availableWinterWeeks.get(chosenWeek));
            bookedWeeks.add(availableWinterWeeks.get(chosenWeek));
            availableWinterWeeks.remove(chosenWeek);
        }
    }

    private void printBookingConfirmation() {
        Utility.clearScreen();
        if (booking.getState() == BookingState.COMPLETED) {
            System.out.println(yellow + "Du har bokat:" + resetColor);
            booking.showBooking();
        } else if (booking.getState() == BookingState.CANCELED) {
            System.out.println("Bokning avbruten.");
        }
    }

    private boolean availableWeeks(BookingType typeOfBooking) {
        if (typeOfBooking == BookingType.SUMMER) {
            return !availableSummerWeeks.isEmpty();
        } else {
            return !availableWinterWeeks.isEmpty();
        }
    }

    private boolean validWeek(int chosenWeek, BookingType chosenType) {
        if (chosenType == BookingType.SUMMER) {
            return chosenWeek < availableSummerWeeks.size() && chosenWeek >= 0;
        } else {
            return chosenWeek < availableWinterWeeks.size() && chosenWeek >= 0;
        }
    }

    private boolean finishedBooking(BookingType chosenType) {
        if (availableWeeks(chosenType)) {
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

            System.out.printf("%sDet finns inte några fler tillgängliga veckor att boka under vald period%s%n"
                    , red, resetColor);
            return true;
        }
    }

    private void printAvailableWeeks(BookingType chosenType) {
        if (chosenType == BookingType.SUMMER) {
            for (int i = 0; i < availableSummerWeeks.size(); i++) {
                System.out.printf("%d: %s%n", i + 1, availableSummerWeeks.get(i));
            }
        } else {
            for (int i = 0; i < availableWinterWeeks.size(); i++) {
                System.out.printf("%d: %s%n", i + 1, availableWinterWeeks.get(i));
            }
        }
    }

    private void printInstruction() {
        String instruction = "Vänligen skriv in motsvarande siffra för den vecka du vill boka.";
        System.out.println(yellow + instruction + resetColor);
    }

    private void loadAvailableWeeks() {
        String filePath;
        //Kommentera ut under demo
        filePath = "src/bookingsMenu/availableWeeks.properties";

        //Kommentera in vid demo av programmet. Detta möjliggör clear av konsolskärmen
//        if (Utility.macUser()) {
//            filePath = System.getProperty("user.dir") + "/bookingsMenu/availableWeeks.properties";
//        } else {
//            filePath = System.getProperty("user.dir") + "\\bookingsMenu\\availableWeeks.properties";
//        }

        Properties properties = new Properties();
        String summerWeeks, winterWeeks;
        String[] parts;
        try (FileInputStream inputStream = new FileInputStream(filePath)) {
            properties.load(inputStream);
            summerWeeks = properties.getProperty("available_summer_weeks");
            winterWeeks = properties.getProperty("available_winter_weeks");
            if (summerWeeks != null) {
                parts = summerWeeks.split(",");
                for (String week : parts) {
                    availableSummerWeeks.add(week.trim());
                }
            }
            if (winterWeeks != null) {
                parts = winterWeeks.split(",");
                for (String week : parts) {
                    availableWinterWeeks.add(week.trim());
                }
            }
        } catch (IOException e) {
            System.err.printf("Unable to open file: %s%n", filePath);
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        BookingsMenu b = new BookingsMenu(scan);
        b.runMenu();
    }
}

