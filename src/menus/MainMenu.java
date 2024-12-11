package menus;

import factory.MenuFactory;
import factory.MenuType;
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

    public List<MenuScreen> creatMenuScreens(Scanner scan) {
        MenuFactory factory = new MenuFactory();
        List<MenuScreen> menuScreens = new ArrayList<>();
        for (MenuType type : MenuType.values()) {
            menuScreens.add(factory.getMenu(type, scan));
        }
        return menuScreens;
    }
}

