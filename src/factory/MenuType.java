package factory;

public enum MenuType {
    ACTIVITY_STORAGE("Aktivitetsförrådet"),
    COTTAGE_DETAILS("Stugbeskrivning"),
    CONTACT_DETAILS("Kontaktuppgifter"),
    LOCAL_ACTIVITIES("Närliggande aktiviteter"),
    BOOKING_MENU("Boka stuga");

    private final String menuTitle;

    MenuType(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    public String getMenuTitle() {
        return this.menuTitle;
    }
}
