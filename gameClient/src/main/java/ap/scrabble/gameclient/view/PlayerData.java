package ap.scrabble.gameclient.view;

public class PlayerData {
    private String name;
    private int score;
    private String ip;
    private int tiles;
    private int orderInTheGame;
    // Constructor
    public PlayerData(String name, int score, String ip, int tiles, int orderInTheGame) {
        this.name = name;
        this.score = score;
        this.ip = ip;
        this.tiles = tiles;
        this.orderInTheGame = orderInTheGame;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getTiles() {
        return tiles;
    }

    public void setTiles(int tiles) {
        this.tiles = tiles;
    }

    public int getOrderInTheGame() {
        return orderInTheGame;
    }

    public void setOrderInTheGame(int orderInTheGame) {
        this.orderInTheGame = orderInTheGame;
    }
}
