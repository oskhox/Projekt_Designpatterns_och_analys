package menus.PriceInformation;

public class WinterPrice implements Strategy {

    private final double basePrice = 1500;

    @Override
    public double calculateSeasonPrice() {
        return basePrice * 1.25;
    }
}