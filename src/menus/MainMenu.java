package menus;

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
                    System.err.println("Felaktig inmatning, försök igen.");
                }
            }
        }
    }

    public void mainMenuOptions(){
        System.out.print("""
                [1] Storage
                [2] Cottage
                [3] Contact info
                [4] Activities
                [5] Booking
                [6] Exit
                """);
        System.out.print("Enter choice: ");
    }

    public List<menus.MenuScreen> creatMenuScreens(Scanner scan) {

        List<menus.MenuScreen> menuScreens = new ArrayList<>();
        menuScreens.add(new menus.ActivityStorage(scan));
        menuScreens.add(new menus.ContactDetails(scan));
        menuScreens.add(new ContactDetails(scan));
        menuScreens.add(new LocalActivities(scan));
        menuScreens.add(new BookingsMenu(scan));
        menuScreens.add(()-> System.exit(0));
        return menuScreens;
    }
}

