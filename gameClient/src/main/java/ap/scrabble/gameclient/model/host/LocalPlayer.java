package ap.scrabble.gameclient.model.host;

import java.util.List;

import ap.scrabble.gameclient.model.GameManager;
import ap.scrabble.gameclient.model.Player;
import ap.scrabble.gameclient.model.board.Tile;
import ap.scrabble.gameclient.model.board.Word;
import ap.scrabble.gameclient.model.message.MessageType;
import ap.scrabble.gameclient.model.recipient.GameRecipient;

public class LocalPlayer extends Player{


    public LocalPlayer(String playerName) {
        super(playerName, true);
    }

    @Override
    public Integer PlayTurn(Word word) {
        Integer score = GameManager.get().getGame().placePlayerTurn(word,PlayerName);
        if(score == 0)
            return score;
        RemoveWordFromPlayer(word);
        GetMissingTiles();
        return score;
    }

    @Override
    public void PlaceWord(GameRecipient requester, Word word) {
        GameManager inst = GameManager.get();

        Integer curScore = inst.getGame().placePlayerTurn(word, PlayerName);
        if (curScore == 0) {
            requester.sendMessage(MessageType.ILLEGAL_WORD, word.toString());
        }
        else {
//            inst.sendAllMessage(MessageType.PLAY_NEXT_TURN, new ScoreMessageArg(PlayerName, totalScore));
//            EndTurn();
        }
    }

    public void RemoveWordTiles(Word word){
        //remove the tiles from the player stash
    }
    @Override
    public List<Tile> GetMissingTiles() {
        Integer neededTiles = MAXIMUM_TILES_PER_PLAYER - this.playersTiles.size();
        List<Tile> newTiles = GameManager.get().getGame().GetTiles(neededTiles);
        return newTiles;
    }

}
