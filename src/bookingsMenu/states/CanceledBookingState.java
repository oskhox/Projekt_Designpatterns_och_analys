package bookingsMenu.states;

import bookingsMenu.BookingType;
import bookingsMenu.BookingsMenu;
import utility.Utility;

import java.util.Collections;

public class CanceledBookingState implements BookingState{
    @Override
    public void handle(BookingsMenu context) {
        putBackBookings(context);
        Utility.clearScreen();
        System.out.println("Bokning avbruten.");
        context.setCurrentState(null);
    }

    private void putBackBookings(BookingsMenu context) {
        if (context.getBookingType() == BookingType.SUMMER) {
            for (String booking : context.getPendingBookings()) {
                context.getAvailableSummerWeeks().add(booking);
            }
            Collections.sort(context.getAvailableSummerWeeks());
        } else {
            for (String booking : context.getPendingBookings()) {
                context.getAvailableWinterWeeks().add(booking);
            }
            Collections.sort(context.getAvailableWinterWeeks());
        }
    }
}
