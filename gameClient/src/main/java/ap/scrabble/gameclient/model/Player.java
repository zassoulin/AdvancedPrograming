package ap.scrabble.gameclient.model;

import ap.scrabble.gameclient.model.board.Tile;
import ap.scrabble.gameclient.model.board.Word;
import ap.scrabble.gameclient.model.recipient.GameRecipient;

import java.util.List;

public abstract class Player {


    List<Tile> playersTiles;
    String PlayerName;

    Integer MAXIMUM_TILES_PER_PLAYER = 7;
    boolean isLocal;

    public Player(String playerName, boolean isLocal) {
        PlayerName = playerName;
        this.isLocal = isLocal;
        GetMissingTiles();
    }

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

    public abstract void PlayNextTurn();
    public abstract void PlaceWord(GameRecipient requester,Word word);

    public abstract void EndTurn();

    public abstract List<Tile> GetMissingTiles();

}
