package ap.scrabble.gameclient.model;

import ap.scrabble.gameclient.model.board.Tile;
import ap.scrabble.gameclient.model.board.Word;
import ap.scrabble.gameclient.model.recipient.GameRecipient;

import java.util.List;

public class RemotePlayer extends Player{
    public RemotePlayer(String playerName) {
        super(playerName, false);
    }

    @Override
    public void PlayNextTurn() {

    }

    @Override
    public void PlaceWord(GameRecipient requester, Word word) {

    }

    @Override
    public void EndTurn() {

    }

    @Override
    public List<Tile> GetMissingTiles() {
        return null;
    }

    @Override
    public Integer getScore() {
        return null;
    }
}
