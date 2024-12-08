import java.util.Scanner;

public class LocalActivities implements MenuScreen {

    private final Scanner scanner;

    String yearRoundActivities = """
            == Följande finns året runt ==
            1. ICA Supermarket Järvsö, Turistvägen 45, 827 52 Järvsö (1,2 km bort).
            2. Järvsö Champagnebar, Turistvägen 61, 827 50 Järvsö (1,3 km bort).
            3. Restaurang Bergshotellet, Vallmovägen 62, 827 51 Järvsö (900 meter bort).
            4. Järvsö Crêperie, Stenevägen 12, 827 47 Järvsö (650 meter bort).
            """;
    String winterActivities = """
            == De här vinteraktiviteterna rekommenderar vi ==
            1. Järvsöbacken, Rödmyravägen 17, 827 50 Järvsö (300 meter bort).
            2. Skidstationen (längd), Alpstigen 6, 827 51 Järvsö (550 meter bort).
            3. Järvsö skiduthyrning, Rödmyravägen 22, 827 50 Järvsö (400 meter bort).
            4. Järvsöbaden Spa, Turistvägen 4, 827 51 Järvsö (900 meter bort).
            """;
    String summerActivities = """
            == De här sommaraktiviteterna rekommenderar vi ==
            1. Trappstigens vandringsled, Bergvägen 24, 827 60 Järvsö (2 km bort).
            2. Järvsö Fisketurer, Turistvägen 70, 827 54 Järvsö (1,5 km bort).
            3. Järvsö Mountainbike-park, Vallmovägen 20, 827 51 Järvsö (800 meter bort).
            4. Järvsö Kajak, Strandvägen 23, 827 51 Järvsö (900 meter bort).
            """;

    //Tar in scannern från main
    LocalActivities(Scanner scanner) {
        this.scanner = scanner;
    }

    //Anropas från main
    @Override
    public void printMenu() {
        String menu = String.format(
                "För önskat val --> skriv motsvarande siffra %n" +
                        "(1) Året runt-aktiviteter%n" +
                        "(2) Vinteraktiviteter%n" +
                        "(3) Sommaraktiviteter");

        System.out.println(menu);
        menuSelection();
    }

    public void menuSelection() {
        boolean listenForInput = true;

        while (listenForInput) {
            int userInput = getUserInput();

            switch (userInput) {
                case 1:
                    System.out.println(yearRoundActivities);
                    listenForInput = false;
                    break;
                case 2:
                    System.out.println(winterActivities);
                    listenForInput = false;
                    break;
                case 3:
                    System.out.println(summerActivities);
                    listenForInput = false;
                    break;
            }

            if (userInput == 1 || userInput == 2 || userInput == 3) {
                printExitInstructions();
            }
        }
    }

    public int getUserInput() {
        int userChoiceInput = 0;

        try {
            userChoiceInput = Integer.parseInt(scanner.nextLine());

            if (userChoiceInput < 1 || userChoiceInput > 3) {
                System.out.println("Felaktig inmatning --> Ange ett giltig nummer");
            }

        } catch (NumberFormatException e) {
            System.out.println("Felaktig inmatning --> Ange ett giltig nummer");

        }
        return userChoiceInput;
    }

    @Override
    public void printExitInstructions() {
        System.out.println("Skriv 'Exit' eller 'E' för att återgå till huvudmenyn:");
        String exitInput = scanner.nextLine();
        if (exitInput.equalsIgnoreCase("Exit") || exitInput.equalsIgnoreCase("E")) {
            System.out.println("Här ska terminalen clearas och huvudmenyn skrivas ut igen.");
            System.exit(0); //Ta bort
            //TODO: Cleara terminalen
            //TODO: Skriv ut huvudmenyn igen, anropa Main
        }
    }

    //TODO: Behövs inte alls om vi lyckas rensa terminalen?
    @Override
    public void exitClass() {
        //System.out.print("\u001B[H\u001B[2J");
    }

    //TODO: Ta bort efter merge, detta är bara mock
    public static void main(String[] args) {
        Scanner scanner1 = new Scanner(System.in);
        LocalActivities l = new LocalActivities(scanner1);
        l.printMenu();
    }
}