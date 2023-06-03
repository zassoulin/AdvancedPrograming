package ap.scrabble.gameclient.view;

public class WordInfo {
    private char[] letters;

    private int x, y;

    private boolean vertical;

    public WordInfo() {
    }

    public boolean isVertical() {
        return vertical;
    }

    public char[] getLetters() {
        return letters;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setLetters(char[] letters) {
        this.letters = letters;
    }

    public void setVertical(boolean vertical) {
        this.vertical = vertical;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
