package ap.scrabble.gameclient.model;

import java.util.List;

import ap.scrabble.gameclient.model.GameManager.MessageType;
import ap.scrabble.gameclient.model.board.Tile;
import ap.scrabble.gameclient.model.board.Word;
import ap.scrabble.gameclient.model.recipient.GameRecipient;

public class LocalPlayer extends Player{


    public LocalPlayer(String playerName, boolean isLocal) {
        super(playerName, isLocal);
    }

    @Override
    public void PlayNextTurn() {
        GameManager.getInstance().sendLocalMessage(GameManager.MessageType.LOCAL_TURN, null);
    }

    @Override
    public void PlaceWord( Word word) {
        GameManager inst = GameManager.getInstance();

        Integer curScore = inst.getGame().placePlayerTurn(word, PlayerName);
        if (curScore == 0) {
            requester.sendMessage(MessageType.ILLEGAL_WORD, word.toString());
        }
        else {
            inst.sendAllMessage(MessageType.PLAY_NEXT_TURN, new ScoreMessageArg(PlayerName, totalScore));
            EndTurn();
        }
    }

    @Override
    public void EndTurn() {
        GetMissingTiles();
    }

    public void RemoveWordTiles(Word word){
        //remove the tiles from the player stash
    }
    @Override
    public List<Tile> GetMissingTiles() {
        Integer neededTiles = MAXIMUM_TILES_PER_PLAYER - this.playersTiles.size();
        List<Tile> newTiles = GameManager.getInstance().getGame().GetTiles(neededTiles);
        return newTiles;
    }

}
