package Menus;

import java.util.Scanner;

public class ContactDetails implements MenuScreen {

    Scanner scanner;

    private String contactDetails = """
            === Kontaktuppgifter ===
            Stugan
            Företagsadress:
            Telefontider: 8-20
            Tele-nummer: 0700000000 
            Email: stugan@hotmail.com
            """;


    public ContactDetails(Scanner scanner) {
        this.scanner = scanner;

    }

    @Override
    public void printMenu() {
    }

    public void printExitInstructions() {
    }

    public void exitClass() {
    }

}