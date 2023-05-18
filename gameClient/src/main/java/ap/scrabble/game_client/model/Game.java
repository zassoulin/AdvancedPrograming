package ap.scrabble.game_client.model;

import ap.scrabble.game_client.model.board.GameData;
import ap.scrabble.game_client.model.board.Tile;
import ap.scrabble.game_client.model.board.Word;

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
