package ap.scrabble.gameclient.model.host;

import java.util.List;

import ap.scrabble.gameclient.model.GameManager;
import ap.scrabble.gameclient.model.Player;
import ap.scrabble.gameclient.model.TurnManager;
import ap.scrabble.gameclient.model.board.Word;
import ap.scrabble.gameclient.model.message.MessageType;
import ap.scrabble.gameclient.model.recipient.AllRecipient;
import ap.scrabble.gameclient.model.recipient.LocalRecipient;

public class HostTurnManager extends TurnManager{


    public HostTurnManager(List<Player> playerList) {
        super(playerList);
    }

    @Override
    public void StartTurn() {
        AllRecipient.get().sendMessage(MessageType.CURRENT_PLAYER, getCurrentPlayer().getName());//TODO: change to allRecipients
        if(playerList.get(CurrentPlayerIndex).getIsLocal() == true){
            LocalRecipient.get().sendMessage(MessageType.MY_TURN,null);//TODO :when it is my turn View need to unlock view and request Players Tiles
        }
        else {
            LocalRecipient.get().sendMessage(MessageType.OTHER_PLAYER_TURN, null);//TODo: when not my turn lock view and hide player tiles
        }

    }

    @Override
    public boolean EndTurn() {
        if (EndConditionReached()) {
            AllRecipient.get().sendMessage(MessageType.GAME_OVER, null);
            return true;
        }
        else {
            CurrentPlayerIndex = GetNextTurnIndex();
            return false;
        }
    }

    @Override
    public Integer PlayTurn(Word word) {
        return playerList.get(CurrentPlayerIndex).PlayTurn(word);
    }

    public Integer GetNextTurnIndex(){
        return (CurrentPlayerIndex + 1 )% playerList.size();
    }

    public boolean EndConditionReached() {
        return GameManager.get().getGame().getBag().size() == 0;
    }
}
