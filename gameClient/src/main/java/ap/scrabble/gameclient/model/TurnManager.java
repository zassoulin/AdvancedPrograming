package ap.scrabble.gameclient.model;

import java.util.List;

public abstract class TurnManager {
    List<Player> playerList;

    Integer CurrentPlayerIndex;

    public TurnManager(List<Player> playerList){
        this.playerList = playerList;
        CurrentPlayerIndex = 0;
    }

    public Player getCurrentPlayer() { return playerList.get(CurrentPlayerIndex); }

    public abstract void PlayNextTurn();

}
