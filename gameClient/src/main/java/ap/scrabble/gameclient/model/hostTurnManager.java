package ap.scrabble.gameclient.model;

import java.util.List;

import ap.scrabble.gameclient.model.GameManager.GameState;

public class hostTurnManager extends TurnManager{


    public hostTurnManager(List<Player> playerList) {
        super(playerList);
    }

    @Override
    public void PlayNextTurn() {
        GameManager.getInstance().sendLocalMessage(GameManager.MessageType.CURRENT_PLAYER, getCurrentPlayer().getName());
        if(playerList.get(CurrentPlayerIndex).isLocal == true){
            playerList.get(CurrentPlayerIndex).PlayNextTurn();
        }
        else {
            GameManager.getInstance().sendLocalMessage(GameManager.MessageType.REMOTE_TURN, null);
        }
        if (EndConditionReached()) {
            GameManager.getInstance().sendAllMessage(GameManager.MessageType.GAME_OVER, null);
        }
        else {
            CurrentPlayerIndex = GetNextTurnIndex();
        }
    }
    public Integer GetNextTurnIndex(){
        return (CurrentPlayerIndex + 1 )% playerList.size();
    }

    public boolean EndConditionReached() {
        return GameManager.getInstance().getGame().bag.size() == 0;
    }
}
