package ap.scrabble.gameclient.model;

import ap.scrabble.gameclient.model.board.GameData;
import ap.scrabble.gameclient.model.board.Tile;
import ap.scrabble.gameclient.model.board.Word;

import java.util.ArrayList;
import java.util.List;

public class Game {
    GameData gameData;
    Tile.Bag bag;

    public Game(List<Player> playerList) {
        this.gameData = new GameData(playerList);
        bag = new Tile.Bag();
    }

    public Integer placePlayerTurn(Word word, String playerName){
        //try placing word
        //update Player score accordingly if succeeded
        //return 0 if failed
        return 0;
    }
    public List<Tile> GetTiles(Integer TilesCount){
        List<Tile> tiles = new ArrayList<>();
        for(int i = 0 ;i<TilesCount;i++){
            Tile newTile = bag.getRand();
            if(newTile != null){
                tiles.add(newTile);
            }
            else {
                break;
            }
        }
        return tiles;
    }
    public int getPlayerTilesCount(){
        return 0;
    }
}
