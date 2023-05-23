package ap.scrabble.gameclient.model;

import ap.scrabble.gameclient.model.board.Word;

import java.util.List;

public abstract class TurnManager {
    List<Player> playerList;

    Integer CurrentPlayerIndex;

    public TurnManager(List<Player> playerList){
        this.playerList = playerList;
        CurrentPlayerIndex = 0;
    }

    public Player getCurrentPlayer() { return playerList.get(CurrentPlayerIndex); }

    public abstract void StartTurn();
    public abstract boolean EndTurn();

    public abstract Integer PlayTurn(Word word);

}
