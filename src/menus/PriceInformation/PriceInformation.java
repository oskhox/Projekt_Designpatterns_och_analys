package menus.PriceInformation;

import menus.MenuScreen;
import utility.Utility;
import java.util.Scanner;

public class PriceInformation implements MenuScreen {

    private final Scanner scanner;
    private final Context context = new Context();

    public PriceInformation(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void runMenu() {
        Utility.clearScreen();
        String menu = """
                För önskat val --> skriv motsvarande siffra
                (1) Pris på vintern
                (2) Pris på sommaren
                """;

        System.out.println(menu);
        menuSelection();
    }

    //Välj strategi, anropa Context setStrategy-metod med strategin, och använd strategins metod för att beräkna pris
    public void menuSelection() {
        boolean listenForInput = true;

        while (listenForInput) {
            int userInput = checkUserInput();

            switch (userInput) {
                case 1:
                    Utility.clearScreen();
                    context.setStrategy(new WinterPrice());
                    context.showPrice();
                    listenForInput = false;
                    break;
                case 2:
                    Utility.clearScreen();
                    context.setStrategy(new SummerPrice());
                    context.showPrice();
                    listenForInput = false;
                    break;
            }

            if (userInput == 1 || userInput == 2) {
                printExitInstructions();
            }
        }
    }

    public int checkUserInput() {
        int userChoiceInput = 0;

        try {
            userChoiceInput = Integer.parseInt(scanner.nextLine());

            if (userChoiceInput < 1 || userChoiceInput > 2) {
                System.out.println("Felaktig inmatning --> Ange ett giltig nummer");
            }

        } catch (NumberFormatException e) {
            System.out.println("Exception vid inmatning av nummer.");
        }
        return userChoiceInput;
    }

    public void printExitInstructions() {

        while (true) {
            System.out.println("Skriv 'Exit' eller 'E' för att återgå till huvudmenyn:");
            String exitInput = scanner.nextLine();

            if (exitInput.equalsIgnoreCase("Exit") || exitInput.equalsIgnoreCase("E")) {
                Utility.clearScreen();
                return;
            } else {
                System.out.println("Felaktig inmatning, försök igen.");
            }
        }
    }
}