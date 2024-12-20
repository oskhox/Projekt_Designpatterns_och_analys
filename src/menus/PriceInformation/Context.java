package menus.PriceInformation;

public class Context {
    private Strategy currentStrategy;

    public void setStrategy(Strategy currentStrategy) {
        this.currentStrategy = currentStrategy;
    }

    public void showPrice() {
        if (currentStrategy != null) {
            System.out.println("Priset är " + currentStrategy.calculateSeasonPrice() + " kronor per natt.");
        } else {
            System.out.println("Ingen strategi valdes.");
        }
    }
}