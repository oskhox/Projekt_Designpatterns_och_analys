package menus;

import utility.Utility;

import java.util.Scanner;

public abstract class SeasonOption {

    private final Scanner scanner;
    private final String yearRoundActivities;
    private final String winterActivities;
    private final String summerActivities;
    private final String menuName;

    public SeasonOption(Scanner scanner,String summer,String winter,String AllYear,String menuName) {

        this.scanner = scanner;
        this.summerActivities = summer;
        this.winterActivities = winter;
        this.yearRoundActivities = AllYear;
        this.menuName = menuName;

    }
    public void runMenu() {
        Utility.clearScreen();
        String menu = String.format(
                menuName+"\n"+
                "För önskat val --> skriv motsvarande siffra %n" +
                        "(1) Året runt%n" +
                        "(2) Vinter%n" +
                        "(3) Sommar");

        System.out.println(menu);
        menuSelection();
    }

    public void menuSelection() {
        boolean listenForInput = true;

        while (listenForInput) {
            int userInput = checkUserInput();

            switch (userInput) {
                case 1:
                    Utility.clearScreen();
                    System.out.println(yearRoundActivities);
                    listenForInput = false;
                    break;
                case 2:
                    Utility.clearScreen();
                    System.out.println(winterActivities);
                    listenForInput = false;
                    break;
                case 3:
                    Utility.clearScreen();
                    System.out.println(summerActivities);
                    listenForInput = false;
                    break;
            }

            if (userInput == 1 || userInput == 2 || userInput == 3) {
                printExitInstructions();
            }
        }
    }

    public int checkUserInput() {
        int userChoiceInput = 0;

        try {
            userChoiceInput = Integer.parseInt(scanner.nextLine());

            if (userChoiceInput < 1 || userChoiceInput > 3) {
                System.out.println("Felaktig inmatning --> Ange ett giltig nummer");
            }

        } catch (NumberFormatException e) {
            System.out.println("Exception vid inmatning av nummer.");
        }
        return userChoiceInput;
    }


    public void printExitInstructions() {
        boolean validInput = false;
        String firstLetter = menuName.substring(0,1).toUpperCase();

        while (!validInput) {
            System.out.printf("Skriv '%s' eller '%s' för att välja mellan %s igen.\\n", menuName, firstLetter, menuName.toLowerCase());
            System.out.println("Skriv 'Exit' eller 'E' för att återgå till huvudmenyn:");
            String exitInput = scanner.nextLine();

            if (exitInput.equalsIgnoreCase("Exit") || exitInput.equalsIgnoreCase("E")) {
                Utility.clearScreen();
                return;
            } else if (exitInput.equalsIgnoreCase(menuName) || exitInput.equalsIgnoreCase(firstLetter)) {
                runMenu();
                validInput = true;
            } else {
                System.out.println("Felaktig inmatning, försök igen.");
            }
        }
    }

}
