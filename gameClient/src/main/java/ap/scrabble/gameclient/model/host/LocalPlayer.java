package ap.scrabble.gameclient.model.host;

import java.util.List;

import ap.scrabble.gameclient.model.GameManager;
import ap.scrabble.gameclient.model.Player;
import ap.scrabble.gameclient.model.board.Tile;
import ap.scrabble.gameclient.model.board.Word;

public class LocalPlayer extends Player{


    public LocalPlayer(String playerName) {
        super(playerName, true);
    }



    public void RemoveWordTiles(Word word){
        //remove the tiles from the player stash
    }


}
