package ap.scrabble.game_client.model;

import java.util.List;

public abstract class TurnManager {
    List<Player> playerList;

    Integer CurrentPlayerIndex;

    public TurnManager(List<Player> playerList){
        this.playerList = playerList;
        CurrentPlayerIndex = 0;
    }

    public abstract void PlayNextTurn();

}
