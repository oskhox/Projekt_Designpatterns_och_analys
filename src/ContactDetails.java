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
        System.out.println(contactDetails);
    }

    @Override
    public void printMenu() {

        String menuTitle = String.format(
                "För önskat val --> skriv motsvarande siffra %n" +
                        "(2) Tillbaka till huvudmenyn");

        System.out.println(menuTitle);
        runMenu();
    }

    public void runMenu() {
            int userInput = checkUserInput();
            if (userInput == 2) {
                //tillbaka till huvudmenyn
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

    //blir överflödig
    @Override
    public void printExitInstructions() {
        boolean validInput = false;

        while (!validInput) {
            System.out.println("Skriv 'Exit' eller 'E' för att återgå till huvudmenyn:");
            String exitInput = scanner.nextLine();

            if (exitInput.equalsIgnoreCase("Exit") || exitInput.equalsIgnoreCase("E")) {
                //vad istället för exitclass?
                validInput = true;
            } else {
                System.out.println("Felaktig inmatning, försök igen.");
            }

        }
    }
}
