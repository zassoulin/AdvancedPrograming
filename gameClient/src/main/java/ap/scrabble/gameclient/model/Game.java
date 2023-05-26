package ap.scrabble.gameclient.model;

import ap.scrabble.gameclient.model.board.GameData;
import ap.scrabble.gameclient.model.board.Tile;
import ap.scrabble.gameclient.model.board.Word;

import java.util.ArrayList;
import java.util.List;

public class Game {
    public GameData getGameData() {
        return gameData;
    }

    GameData gameData;
    Tile.Bag bag;

    public Tile.Bag getBag() { return bag; }

    public Game(List<Player> playerList) {
        this.gameData = new GameData(playerList);
        bag = new Tile.Bag();
    }

    public Integer placePlayerTurn(Word word, String playerName){
        Integer score = gameData.getBoard().tryPlaceWord(word);
        if(score != 0){
            gameData.addScoreToPlayer(playerName,score);
        }
        return score;
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
