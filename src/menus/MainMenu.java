package menus;

import menus.CottageDetails.CottageDetails;
import bookingsMenu.BookingsMenu;
import utility.Utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainMenu {

    public MainMenu() {
        Utility.clearScreen();
        try (Scanner scan = new Scanner(System.in)) {
            List<menus.MenuScreen> menuScreens = creatMenuScreens(scan);
            mainMenuOptions();
            String line;
            while ((line = scan.nextLine()) !=null) {
                try {
                    menuScreens.get(Integer.parseInt(line)-1).runMenu(); //-1 Så att den stämmer med mainMenuOptions.
                    mainMenuOptions();
                }catch (Exception e) {
                    Utility.clearScreen();
                    mainMenuOptions();
                    System.err.println("Felaktig inmatning, försök igen.");
                }
            }
        }
    }

    public void mainMenuOptions(){
        System.out.print("""
                Välkommen till Stugan på Rödmyravägen 5 i Järvsö
                [1] Information om aktivitetsförråd
                [2] Stuginformation
                [3] Kontaktuppgifter
                [4] Aktiviteter i området
                [5] Boka stugan
                [6] Avsluta
                """);
        System.out.println("Skriv in ditt val: ");
    }

    public List<menus.MenuScreen> creatMenuScreens(Scanner scan) {

        List<MenuScreen> menuScreens = new ArrayList<>();
        menuScreens.add(new ActivityStorage(scan));
        menuScreens.add(new CottageDetails(scan));
        menuScreens.add(new ContactDetails(scan));
        menuScreens.add(new LocalActivities(scan));
        menuScreens.add(new BookingsMenu(scan));
        menuScreens.add(()-> System.exit(0));
        return menuScreens;
    }
}

