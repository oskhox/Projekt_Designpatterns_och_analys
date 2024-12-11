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
            List<menus.MenuScreen> menuScreens = createMenuScreens(scan);
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
        int lastOption = 0;
        for (int i = 0; i < MenuType.values().length; i++) {
            System.out.printf("[%d] %s %n", i + 1, MenuType.values()[i].getMenuTitle());
            lastOption = i + 2;
        }
        System.out.printf("[%d] Avsluta %n", lastOption);
        System.out.print("Skriv in motsvarande sifferalternativ: ");
    }

    public List<MenuScreen> createMenuScreens(Scanner scan) {
        MenuFactory factory = new MenuFactory();
        List<MenuScreen> menuScreens = new ArrayList<>();
        for (MenuType type : MenuType.values()) {
            menuScreens.add(factory.getMenu(type, scan));
        }
        menuScreens.add(() -> System.exit(0));
        return menuScreens;
    }
}

