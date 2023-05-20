package ap.scrabble.gameclient.model;

import java.util.List;

public class hostTurnManager extends TurnManager{


    public hostTurnManager(List<Player> playerList) {
        super(playerList);
    }

    @Override
    public void PlayNextTurn() {
        if(playerList.get(CurrentPlayerIndex).isLocal == true){
            playerList.get(CurrentPlayerIndex).PlayNextTurn();
        }
        else {
            //wait for remote player
        }
        CurrentPlayerIndex = GetNextTurnIndex();
    }
    public Integer GetNextTurnIndex(){
        return (CurrentPlayerIndex + 1 )% playerList.size();
    }
    @Override
    public void RunGame() {
        while (GameManager.getInstance().game.bag.size() != 0){//Play the game until bag is empty
            PlayNextTurn();
            //update all players on new Game-data etc...
        }
        //Update all on game end
    }
}
