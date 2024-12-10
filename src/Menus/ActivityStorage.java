package Menus;

import java.util.Scanner;

public class ActivityStorage implements MenuScreen {

    private final Scanner scanner;

    private String winterStorage = """
            == Vinterförråd ==
             Skidor
             Pulka
             Pimpeltrustning
             Bastutält
             Snöskyffel
             """;

    private String summerStorage = """
            == Sommarförråd ==
            Fotboll
            Gångstavar
            Kubb
            Boule
            Fiskeutrustning
            """;

    ActivityStorage(Scanner scanner) {
        this.scanner = scanner;
    }


    @Override
    public void printMenu() {

        String menuTitel = String.format(
                "För önskat val --> skriv motsvarande siffra %n" +
                        "(1) Vinterförråd%n" +
                        "(2) Sommarförråd%n" +
                        "(3) Åretruntförråd%n" +
                        "(4) Tillbaka till huvudmenyn");

        System.out.println(menuTitel);
        runMenu();
    }


    public void runMenu() {

        boolean running = true;

        while (running) {
            int userInput = getUserInput();

            switch (userInput) {
                case 1:
                    System.out.println(winterStorage);
                    break;
                case 2:
                    System.out.println(summerStorage);
                    break;
                case 3:
                    System.out.println(winterStorage + "\n" + summerStorage);
                    break;
                case 4:
                    return;
                    //running = false;
                    //tillbaka till huvudmenyn
                    //break;
            }

            if (userInput == 1 || userInput == 2 || userInput == 3) {
                printExitInstructions();

            }
        }
    }

    public int getUserInput() {

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
                //vad istället för exitclass?
                validInput = true;
                return;
            } else if (exitInput.equalsIgnoreCase("Förråd") || exitInput.equalsIgnoreCase("F")) {
                printMenu();
                validInput = true;
            } else {
                System.out.println("Felaktig inmatning, försök igen.");
            }
        }
    }
}

