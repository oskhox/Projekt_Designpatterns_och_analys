package menus;

import utility.Utility;

import java.util.Scanner;

public class ActivityStorage implements MenuScreen {

    private final Scanner scanner;

    private final String winterStorage = """
            == Vinterförråd ==
             Skidor
             Pulka
             Pimpeltrustning
             Bastutält
             Snöskyffel
            """;

    private final String summerStorage = """
            == Sommarförråd ==
            Fotboll
            Gångstavar
            Kubb
            Boule
            Fiskeutrustning
            """;

    public ActivityStorage(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void runMenu() {
        Utility.clearScreen();
        String menuTitel = String.format(
                "(1) Vinterförråd%n" +
                "(2) Sommarförråd%n" +
                "(3) Åretruntförråd%n" +
                "(4) Tillbaka till huvudmenyn");
        System.out.println(menuTitel);
        System.out.print("Skriv in motsvarande sifferalternativ: ");
        menuSelection();
    }


    public void menuSelection() {

        boolean listenForInput = true;

        while (listenForInput) {
            int userInput = checkUserInput();
            switch (userInput) {
                case 1:
                    Utility.clearScreen();
                    System.out.println(winterStorage);
                    listenForInput = false;
                    break;
                case 2:
                    Utility.clearScreen();
                    System.out.println(summerStorage);
                    listenForInput = false;
                    break;
                case 3:
                    Utility.clearScreen();
                    System.out.println(winterStorage + "\n" + summerStorage);
                    listenForInput = false;
                    break;
                case 4:
                    return;
            }
            if (userInput == 1 || userInput == 2 || userInput == 3) {
                printExitInstructions();
            }
        }
    }

    public int checkUserInput() {

        int userChoiceInput = 0;

        try {
            userChoiceInput = Integer.parseInt(scanner.nextLine().trim());

            if (userChoiceInput < 1 || userChoiceInput > 4) {
                System.out.println("Felaktig inmatning --> Ange en giltig siffra");
            }
        } catch (NumberFormatException e) {
            System.out.println("Felaktig inmatning --> Endast siffror kan anges vid val, försök igen! ");

        }
        return userChoiceInput;
    }

    public void printExitInstructions() {

        boolean validInput = false;

        while (!validInput) {
            System.out.println("Skriv 'Förråd' eller 'F' för att välja mellan olika aktivitetsförråd igen.");
            System.out.println("Skriv 'Exit' eller 'E' för att återgå till huvudmenyn:");
            String exitInput = scanner.nextLine();

            if (exitInput.equalsIgnoreCase("Exit") || exitInput.equalsIgnoreCase("E")) {
                Utility.clearScreen();
                return;
            } else if (exitInput.equalsIgnoreCase("Förråd") || exitInput.equalsIgnoreCase("F")) {
                // Utility.clearScreen();
                this.runMenu();
                validInput = true;
            } else {
                System.out.println("Felaktig inmatning, försök igen.");
            }
        }
    }
}

