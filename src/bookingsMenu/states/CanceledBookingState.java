package bookingsMenu.states;

import bookingsMenu.BookingsMenu;
import utility.Utility;

public class CanceledBookingState implements BookingState{
    @Override
    public void handle(BookingsMenu context) {
        Utility.clearScreen();
        System.out.println("Bokning avbruten.");
        context.setCurrentState(null);
    }
}
