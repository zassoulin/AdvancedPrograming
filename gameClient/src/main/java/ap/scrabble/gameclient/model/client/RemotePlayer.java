package ap.scrabble.gameclient.model.client;

import ap.scrabble.gameclient.model.Player;
import ap.scrabble.gameclient.model.board.Tile;
import ap.scrabble.gameclient.model.board.Word;

import java.util.List;

public class RemotePlayer extends Player{
    public RemotePlayer(String playerName) {
        super(playerName, false);
    }

    @Override
    public Integer PlayTurn(Word word) {
        return 0;//TODO: implement
    }

    @Override
    public List<Tile> GetMissingTiles() {
        return null;
    }

}
