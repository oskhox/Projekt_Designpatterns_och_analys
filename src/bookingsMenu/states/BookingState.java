package bookingsMenu.states;

import bookingsMenu.BookingsMenu;

public interface BookingState {
    void handle(BookingsMenu context);
}
