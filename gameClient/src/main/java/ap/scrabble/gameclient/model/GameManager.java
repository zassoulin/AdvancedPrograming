package ap.scrabble.gameclient.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class GameManager extends Observable {
    private static GameManager GameManagerInstance;

    String Ip;
    Integer port;

    List<Player> playerList;

    HostServer hostServer;
    private GameManager(){
        playerList = new ArrayList<>();
//        hostServer = new SocketHostServer(port,ClientHandler);

    }

    public static GameManager getInstance() {
        if (GameManagerInstance == null) {
            GameManagerInstance = new GameManager();
        }
        return GameManagerInstance;
    }

    public void CreateGame(String HostName){
        //Start server
        //add Host player
        Player HostPlayer;//Init
        TurnManager turnManager = new hostTurnManager(playerList);

    }
    public void JoinGame(String ClientName){

    }
    public void AddPlayer(String PlayerName,Boolean IsLocal){
        //add player to list
        //notify observers

    }
    public void StartGame(){

    }



}
