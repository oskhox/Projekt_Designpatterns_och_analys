package factory;

import bookingsMenu.BookingsMenu;
import menus.ActivityStorage;
import menus.ContactDetails;
import menus.CottageDetails.CottageDetails;
import menus.LocalActivities;
import menus.MenuScreen;

import java.util.Scanner;

public class MenuFactory {

    public MenuScreen getMenu(MenuType chosenType, Scanner scan) {
        switch (chosenType) {
            case ACTIVITY_STORAGE -> {
                return new ActivityStorage(scan);
            }
            case COTTAGE_DETAILS -> {
                return new CottageDetails(scan);
            }
            case CONTACT_DETAILS -> {
                return new ContactDetails(scan);
            }
            case LOCAL_ACTIVITIES -> {
                return new LocalActivities(scan);
            }
            case BOOKING_MENU ->  {
                return new BookingsMenu(scan);
            }
            default -> throw new IllegalArgumentException();
        }
    }
}
