package bookingsMenu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Booking {
    private final List<String> bookedWeeks = new ArrayList<>();
    private boolean pending = false;

    public void addWeek(String week) {
        bookedWeeks.add(week);
        Collections.sort(bookedWeeks);
    }

    public boolean isPending() {
        return pending;
    }

    public void setPending(boolean choice) {
        pending = choice;
    }

    public void showBooking() {
        for (String week : bookedWeeks) {
            System.out.println(week);
        }
        //System.out.println("Pris: " + (bookedWeeks.size()*1500)+" kr"); ifall man vill att priset ska visat, men det är
    }   //                                                                hårdkodat in i metoden.
}
