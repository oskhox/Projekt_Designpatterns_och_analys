package utility;

import java.util.Optional;

public class Utility {
    public static Optional<Integer> parseIfAble(String toParse) {
        try {
            int result;
            result = Integer.parseInt(toParse.trim());
            return Optional.of(result);
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    public static void clearScreen() {
        System.out.print("\u001B[H\u001B[2J");
    }

    public static boolean macUser() {
        String os = System.getProperty("os.name").toLowerCase();
        return os.contains("mac");
    }
}
