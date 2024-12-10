package menus;

import utility.Utility;

import java.util.Scanner;

public class ContactDetails implements MenuScreen {

    Scanner scanner;

    private String contactDetails = """
            === Kontaktuppgifter ===
            Stugan
            Företagsadress: 
            Hemsida: https://stugan-myrsjo.se
            Telefontider: 8-20
            Tele-nummer: 0700000000 
            Email: stugan@hotmail.com
            """;


    public ContactDetails(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void runMenu() {
        Utility.clearScreen();
        System.out.println(contactDetails);
        String menuTitle = String.format(
                "För önskat val --> skriv motsvarande siffra %n" +
                        "(2) Tillbaka till huvudmenyn");

        System.out.println(menuTitle);
        menuSelection();
    }

    public void menuSelection() {

        while(true) {
            int userInput = checkUserInput();
            if (userInput == 2) {
                Utility.clearScreen();
                return;
            }
        }
    }


    public int checkUserInput() {
        int userChoiceInput = 0;

        try {
            userChoiceInput = Integer.parseInt(scanner.nextLine().trim());

            if (userChoiceInput != 2) {
                System.out.println("Felaktig inmatning --> Ange en giltig siffra");
            }
        } catch (NumberFormatException e) {
            System.out.println("Felaktig inmatning --> Endast siffror kan anges vid val, försök igen! ");

        }
        return userChoiceInput;
    }
}
