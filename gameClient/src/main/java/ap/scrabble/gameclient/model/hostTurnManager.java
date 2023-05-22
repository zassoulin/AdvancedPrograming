package ap.scrabble.gameclient.model;

import ap.scrabble.gameclient.model.recipient.AllRecipient;
import ap.scrabble.gameclient.model.recipient.LocalRecipient;
import ap.scrabble.gameclient.model.recipient.RemoteRecipient;

import java.util.List;

public class hostTurnManager extends TurnManager{


    public hostTurnManager(List<Player> playerList) {
        super(playerList);
    }

    @Override
    public void PlayNextTurn() {
        AllRecipient.get().sendMessage(GameManager.MessageType.CURRENT_PLAYER, getCurrentPlayer().getName());//TODO: change to allRecipients
        if(playerList.get(CurrentPlayerIndex).isLocal == true){
            playerList.get(CurrentPlayerIndex).PlayNextTurn();
        }
        else {
            LocalRecipient.get().sendMessage(GameManager.MessageType.REMOTE_TURN, null);
        }
        if (EndConditionReached()) {
            AllRecipient.get().sendMessage(GameManager.MessageType.GAME_OVER, null);
        }
        else {
            CurrentPlayerIndex = GetNextTurnIndex();
        }
    }
    public Integer GetNextTurnIndex(){
        return (CurrentPlayerIndex + 1 )% playerList.size();
    }

    public boolean EndConditionReached() {
        return GameManager.get().getGame().bag.size() == 0;
    }
}
