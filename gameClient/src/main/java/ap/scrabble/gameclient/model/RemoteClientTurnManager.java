package ap.scrabble.gameclient.model;

import ap.scrabble.gameclient.model.board.Word;

import java.util.List;

public class RemoteClientTurnManager extends TurnManager {
    Player ClientPlayer;
    List<Player> playerList;
    Integer currentPlayer;

    SocketHostServerCommunicator hostServerCommunicator;

    public RemoteClientTurnManager(Player clientPlayer, List<Player> playerList) {
        super(playerList);
        ClientPlayer = clientPlayer;
        hostServerCommunicator = SocketHostServerCommunicator.get();

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
    public void EndTurn() {

    }

    @Override
    public Integer PlayTurn(Word word) {

    }

}
