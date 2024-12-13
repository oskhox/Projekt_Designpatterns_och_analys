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
        try {
            if (macUser()) {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean macUser() {
        String os = System.getProperty("os.name").toLowerCase();
        return os.contains("mac");
    }
}