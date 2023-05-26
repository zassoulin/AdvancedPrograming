package ap.scrabble.gameclient.model;

import ap.scrabble.gameclient.model.client.RemotePlayer;
import ap.scrabble.gameclient.model.host.LocalPlayer;

public class PlayerFactory {
    private static PlayerFactory inst;

    private PlayerFactory() {}

    public static PlayerFactory GetInstance() {
        if (inst == null) {
            inst = new PlayerFactory();
        }
        return inst;
    }

    public Player CreatePlayer(String playerName,boolean isLocal) {
        // TODO: Return player type based on local/remote requester
        if(isLocal){
            return new LocalPlayer(playerName);
        }
        else {
            return new RemotePlayer(playerName);
        }
    }
}
