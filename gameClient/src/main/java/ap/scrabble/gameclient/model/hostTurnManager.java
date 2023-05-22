package ap.scrabble.gameclient.model;

import ap.scrabble.gameclient.model.board.Word;
import ap.scrabble.gameclient.model.recipient.AllRecipient;
import ap.scrabble.gameclient.model.recipient.LocalRecipient;

import java.util.List;

public class hostTurnManager extends TurnManager{


    public hostTurnManager(List<Player> playerList) {
        super(playerList);
    }

    @Override
    public void StartTurn() {
        AllRecipient.get().sendMessage(GameManager.MessageType.CURRENT_PLAYER, getCurrentPlayer().getName());//TODO: change to allRecipients
        if(playerList.get(CurrentPlayerIndex).isLocal == true){
            LocalRecipient.get().sendMessage(GameManager.MessageType.MY_TURN,null);
        }
        else {
            LocalRecipient.get().sendMessage(GameManager.MessageType.OTHER_PLAYER_TURN, null);
        }

    }

    @Override
    public void EndTurn() {
        if (EndConditionReached()) {
            AllRecipient.get().sendMessage(GameManager.MessageType.GAME_OVER, null);
        }
        else {
            CurrentPlayerIndex = GetNextTurnIndex();
        }
    }

    @Override
    public void PlayTurn(Word word) {
        playerList.get(CurrentPlayerIndex).PlayTurn(word);
    }

    public Integer GetNextTurnIndex(){
        return (CurrentPlayerIndex + 1 )% playerList.size();
    }

    public boolean EndConditionReached() {
        return GameManager.get().getGame().bag.size() == 0;
    }
}
