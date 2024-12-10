package bookingsMenu.states;

import bookingsMenu.BookingsMenu;
import utility.Utility;

public class CompletedBookingState implements BookingState {
    @Override
    public void handle(BookingsMenu context) {
        addBookings(context);
        printBooking(context);
        context.getBooking().setPending(false);
        context.setCurrentState(null);
    }

    private void addBookings(BookingsMenu context) {
        for (String week : context.getPendingBookings()) {
            context.getBooking().addWeek(week);
        }
    }

    private void printBooking(BookingsMenu context) {
        String yellow = "\u001B[33m";
        String resetColor = "\u001B[0m";
        Utility.clearScreen();
        System.out.println(yellow + "Du har bokat:" + resetColor);
        context.getBooking().showBooking();
        System.out.println();
    }
}
