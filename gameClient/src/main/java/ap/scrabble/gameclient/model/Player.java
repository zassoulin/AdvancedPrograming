package ap.scrabble.gameclient.model;

import java.util.ArrayList;
import java.util.List;

import ap.scrabble.gameclient.model.board.Tile;
import ap.scrabble.gameclient.model.board.Word;
import ap.scrabble.gameclient.model.recipient.GameRecipient;

public abstract class Player {

    protected List<Tile> playersTiles;
    protected String PlayerName;

    protected Integer MAXIMUM_TILES_PER_PLAYER = 7;
    protected boolean isLocal;

    public Player(String playerName, boolean isLocal) {
        PlayerName = playerName;
        this.isLocal = isLocal;
        this.playersTiles = new ArrayList<Tile>();
        this.playersTiles = GetMissingTiles();
    }

    public boolean getIsLocal() { return isLocal; }
    public String getName() { return PlayerName; }

    public void addTilesToPlayer(List<Tile> tilesToAdd){
        playersTiles.addAll(tilesToAdd);
    }
    public int getPlayerTilesCount(){
        return  playersTiles.size();
    }

    public String getPlayerName(){
        return PlayerName;
    }

    public Word GetPlayerWord(){
        return  new Word(new Tile[4],1,2,true);//TODO: GetPlayerWordFromGIu
    }

    public void RemoveWordFromPlayer(Word word){
        Tile [] tiles = word.getTiles();
        for (Tile tile : tiles){
            playersTiles.remove(tile);
        }
    }

    public Integer PlayTurn(Word word) {
        Integer score = GameManager.get().getGame().placePlayerTurn(word,PlayerName);
        if(score == 0)
            return score;
        RemoveWordFromPlayer(word);
        GetMissingTiles();
        return score;
    }
    public List<Tile> GetMissingTiles() {
        Integer neededTiles = MAXIMUM_TILES_PER_PLAYER - this.playersTiles.size();
        List<Tile> newTiles = GameManager.get().getGame().GetTiles(neededTiles);
        return newTiles;
    }

    // Compare names
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (obj instanceof String) {
            return PlayerName.equals((String)obj);
        } else if (obj instanceof Player) {
            return PlayerName.equals(((Player)obj).PlayerName);
        }
        return false;
    }

}
