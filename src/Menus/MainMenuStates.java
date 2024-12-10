package Menus;

public enum MainMenuStates {
    START_SCREEN("0"),
    STORAGE_SCREEN("1"),
    COTTAGE_SCREEN("2"),
    CONTACT_SCREEN("3"),
    ACTIVITY_SCREEN("4"),
    BOOKING_SCREEN("5"),
    EXIT("6");

    final String stateValue;

    MainMenuStates(String stateValue) {
        this.stateValue = stateValue;
    }
}
