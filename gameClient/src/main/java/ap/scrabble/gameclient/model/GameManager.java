package ap.scrabble.gameclient.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.TreeMap;

import ap.scrabble.gameclient.model.properties.DictionaryServerConfig;
import ap.scrabble.gameclient.model.properties.HostServerConfig;

public class GameManager extends Observable {
    public enum GameState {
        MAIN_MENU,
        CREATE_GAME,
        JOIN_GAME,
        PLAY
    }

    public enum MessageType {
        ADD_PLAYER,
    }

    public class Message extends ap.scrabble.gameclient.util.Message<MessageType> {
        public Message(MessageType type, Object arg) {
            super(type, arg);
        }
    }

    private static GameManager GameManagerInstance;

    private List<Player> playerList = new ArrayList<>();
    private HostServer hostServer;
    private GameState gameState = GameState.MAIN_MENU;
    private DictionaryServerConfig dictionaryServerConfig;
    private HostServerConfig hostServerConfig;
    private TurnManager turnManager;
    private Game game;

    public static GameManager getInstance() {
        if (GameManagerInstance == null) {
            GameManagerInstance = new GameManager();
        }
        return GameManagerInstance;
    }

    public void setConfig(DictionaryServerConfig dictionaryServerConfig, HostServerConfig hostServerConfig) {
        this.dictionaryServerConfig = dictionaryServerConfig;
        this.hostServerConfig = hostServerConfig;
    }

    public void init() {
        // TODO: Initialize stuff if necessary
    }

    public GameState getGameState() {
        return gameState;
    }

    public void CreateGame(String HostName){
        //Start server
        //add Host player
        Player HostPlayer;//Init
        turnManager = new hostTurnManager(playerList);
        this.game = new Game(playerList);
        AddPlayer(HostName,true);

    }
    public void JoinGame(String ClientName){

    }
    public void AddPlayer(String PlayerName,Boolean IsLocal){
        if(IsLocal){
            playerList.add(new LocalPlayer(PlayerName, true));
        }
        else {
            //Add RemotePlayer
        }
        synchronized (playerList) {
            if (playerList.contains(PlayerName)) {
                return
            }
        }

        sendMessage(MessageType.ADD_PLAYER, PlayerName);
    }
    public void StartGame(){
        turnManager.RunGame();
    }

    private void sendMessage(MessageType type, Object arg) {
        setChanged();
        notifyObservers(new Message(MessageType.ADD_PLAYER, arg));
    }


}
