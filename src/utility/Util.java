package utility;

import java.util.Optional;

public class Util {
    public Optional<Integer> parseIfAble(String toParse) {
        try {
            int result;
            result = Integer.parseInt(toParse);
            return Optional.of(result);
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    public void clearScreen() {
        System.out.print("\u001B[H\u001B[2J");
    }

    public boolean macUser() {
        String os = System.getProperty("os.name").toLowerCase();
        return os.contains("mac");
    }
}
