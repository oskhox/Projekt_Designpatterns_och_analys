package bookingsMenu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Booking {
    private final List<String> bookedWeeks = new ArrayList<>();
    private BookingState state = BookingState.EMPTY;

    public void addWeek(String week) {
        bookedWeeks.add(week);
        Collections.sort(bookedWeeks);
    }

    public BookingState getState() {
        return this.state;
    }

    public void setState(BookingState state) {
        this.state = state;
    }

    public void showBooking() {
        for (String week : bookedWeeks) {
            System.out.println(week);
        }
    }
}
