package ap.scrabble.game_client.model;

import java.util.List;

public class hostTurnManager extends TurnManager{


    public hostTurnManager(List<Player> playerList) {
        super(playerList);
    }

    @Override
    public void PlayNextTurn() {
        if(playerList.get(CurrentPlayerIndex).isLocal == true){
            //play turn for local player
        }
        else {
            //wait for remote player
        }
    }
}
