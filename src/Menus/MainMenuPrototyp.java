package Menus;

import bookingsMenu.BookingsMenu;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainMenuPrototyp {

    public MainMenuPrototyp() {

        try (Scanner scan = new Scanner(System.in)) {
            List<MenuScreen> menuScreens = creatMenuScreens(scan);
            mainMenuOptions();
            String line;
            while ((line = scan.nextLine()) !=null) {
                try {
                    menuScreens.get(Integer.parseInt(line)-1).printMenu(); //-1 Så att den stämmer med mainMenuOptions.
                    mainMenuOptions();
                }catch (Exception e) {
                    System.out.println("Felaktig inmatning, försök igen.");
                }
            }
        }
    }

    public void mainMenuOptions(){
        System.out.println("""
                [1] Storage
                [2] Cottage
                [3] Contact info
                [4] Activities
                [5] Booking
                [6] Exit
                """);
    }

    public List<MenuScreen> creatMenuScreens(Scanner scan) {

        List<MenuScreen> menuScreens = new ArrayList<>();
        menuScreens.add(new ActivityStorage(scan));
        menuScreens.add(new ContactDetails(scan));
        menuScreens.add(new ContactDetails(scan));
        menuScreens.add(new LocalActivities(scan));
        menuScreens.add(new BookingsMenu(scan));
        menuScreens.add(()-> System.exit(0));
        return menuScreens;
    }

    public static void main(String[] args) {
        new MainMenuPrototyp();
    }
}

