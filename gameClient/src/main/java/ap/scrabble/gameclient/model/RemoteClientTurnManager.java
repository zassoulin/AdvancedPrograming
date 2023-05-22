package ap.scrabble.gameclient.model;

import java.util.List;

public class RemoteClientTurnManager extends TurnManager {
    Player ClientPlayer;
    List<Player> playerList;
    Integer currentPlayer;

    SocketHostServerCommunicator hostServerCommunicator;

    public RemoteClientTurnManager(Player clientPlayer, List<Player> playerList) {
        super(playerList);
        ClientPlayer = clientPlayer;
        hostServerCommunicator = new SocketHostServerCommunicator();

    }


    @Override
    public void PlayNextTurn() {
        if(playerList.get(currentPlayer).getPlayerName() == ClientPlayer.getPlayerName()){
            //play Turn normally
        }
        else {
            //wait for next turn
        }
    }

}
