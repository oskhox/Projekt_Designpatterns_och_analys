import java.util.Scanner;

public class Main {
    private final int STARSCREEN = 0;
    private final int STORAGESCREEN = 1;
    private final int COTTAGESCREEN = 2;
    private final int CONTACTSCREEN = 3;
    private final int ACTIVITYSCREEN = 4;
    private final int BOOKINGSCREEN = 5;
    private int state = STARSCREEN;


    public Main(){

        try (Scanner sc = new Scanner(System.in)) {
            //System.out.println("Storage: 1, Cottage: 2, Contact info: 3, Activities: 4, Booking: 5");
            String line;
            while ((line = sc.nextLine()) != null) {
                try {
                    state = Integer.parseInt(line);
                }catch (NumberFormatException e){
                    System.out.println("Please enter a valid number");
                    continue;
                }
                switch (state) {
                    case STARSCREEN -> {System.out.println("Storage[1], Cottage[2], Contact info[3], Activities[4], Booking[5]");}
                    case STORAGESCREEN -> {System.out.println("Storage: 1");}
                    case COTTAGESCREEN -> {System.out.println("Cottage: 2");}
                    case CONTACTSCREEN -> {System.out.println("Contact info: 3");}
                    case ACTIVITYSCREEN -> {System.out.println("Activities: 4");}
                    case BOOKINGSCREEN -> {System.out.println("Booking: 5");}
                        }
                }

            }
        }

    public static void main(String[] args) {
        new Main();
    }
}

