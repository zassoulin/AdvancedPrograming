package ap.scrabble.gameclient.model.client;

import ap.scrabble.gameclient.model.Player;
import ap.scrabble.gameclient.model.TurnManager;
import ap.scrabble.gameclient.model.board.Word;

import java.util.List;

public class RemoteClientTurnManager extends TurnManager {
    private Player ClientPlayer;
    private List<Player> playerList;
    private Integer currentPlayer;

    private HostServerCommunicator hostServerCommunicator;

    public RemoteClientTurnManager(Player clientPlayer, List<Player> playerList, HostServerCommunicator hostServerCommunicator) {
        super(playerList);
        ClientPlayer = clientPlayer;
        this.hostServerCommunicator = hostServerCommunicator;

    }


    @Override
    public void StartTurn() {
        if(playerList.get(currentPlayer).getPlayerName() == ClientPlayer.getPlayerName()){
            //play Turn normally
        }
        else {
            //wait for next turn
        }
    }

    @Override
    public boolean EndTurn() {
        //TODO:implement
        return false;
    }

    @Override
    public Integer PlayTurn(Word word) {
        return 0;//TODO implement
    }

}
