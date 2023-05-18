package ap.scrabble.gameclient.model;

import ap.scrabble.gameclient.model.board.GameData;
import ap.scrabble.gameclient.model.board.Tile;
import ap.scrabble.gameclient.model.board.Word;

import java.util.List;

public class Game {
    GameData gameData;
    public int placePlayerTurn(Word word, String playerName){
        //try placing word
        //update Player score accordingly if succeeded
        //return 0 if failed
        return 0;
    }
    public void addTilesToPlayer(List<Tile> tilesToAdd){

    }
    public int getPlayerTilesCount(){
        return 0;
    }
}
