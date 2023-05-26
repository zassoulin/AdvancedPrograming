package ap.scrabble.gameclient.util;

public class Assert {
    public static void assertCond(boolean condition, String msg) {
        if (!condition) {
            throw new RuntimeException(msg);
        }
    }
}
