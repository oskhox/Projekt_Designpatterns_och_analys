package menus.PriceInformation;

public class SummerPrice implements Strategy {

    private final double basePrice = 1500;

    @Override
    public double calculateSeasonPrice() {
        return basePrice * 0.75;
    }
}