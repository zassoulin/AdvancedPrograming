package ap.scrabble.game_client.model;

import ap.scrabble.game_client.model.board.Tile;

import java.util.List;

public abstract class Player {
    List<Tile> playersTiles;
    String PlayerName;

    boolean isLocal;
    public void addTilesToPlayer(List<Tile> tilesToAdd){
        playersTiles.addAll(tilesToAdd);
    }
    public int getPlayerTilesCount(){
        return  playersTiles.size();
    }

    public String getPlayerName(){
        return PlayerName;
    }


}
