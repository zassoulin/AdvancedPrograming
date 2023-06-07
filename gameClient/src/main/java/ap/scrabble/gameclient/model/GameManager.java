package ap.scrabble.gameclient.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import ap.scrabble.gameclient.model.board.Tile;
import ap.scrabble.gameclient.model.board.Word;
import ap.scrabble.gameclient.model.client.*;
import ap.scrabble.gameclient.model.communicator.DictionaryServerCommunicator;
import ap.scrabble.gameclient.model.host.*;
import ap.scrabble.gameclient.model.message.Message;
import ap.scrabble.gameclient.util.MessageType;
import ap.scrabble.gameclient.model.properties.DictionaryServerConfig;
import ap.scrabble.gameclient.model.properties.HostServerConfig;
import ap.scrabble.gameclient.model.recipient.AllRecipient;
import ap.scrabble.gameclient.model.recipient.GameRecipient;
import ap.scrabble.gameclient.model.recipient.LocalRecipient;

public class GameManager extends Observable {

    private static GameManager GameManagerInstance;

    public String getRemotePlayerName() {
        return RemotePlayerName;
    }

    private String RemotePlayerName;
    private List<Player> playerList = new ArrayList<>();
    private GameState gameState = GameState.MAIN_MENU;

    public DictionaryServerConfig getDictionaryServerConfig() {
        return dictionaryServerConfig;
    }

    private DictionaryServerConfig dictionaryServerConfig;

    public HostServerConfig getHostServerConfig() {
        return hostServerConfig;
    }

    private HostServerConfig hostServerConfig;
    private TurnManager turnManager;
    private Game game;

    public DictionaryServerCommunicator getDictionaryServerCommunicator() {
        return dictionaryServerCommunicator;
    }

    private DictionaryServerCommunicator dictionaryServerCommunicator;
    private HostServerCommunicator hostComm;

    public HostServerCommunicator getHostComm() { return hostComm; }

    private SocketHostServer socketHostServer;
    public static enum GameState {
        MAIN_MENU,
        CREATE_GAME,
        JOIN_GAME,
        PLAY
    }

    public static GameManager get() {
        if (GameManagerInstance == null) {
            GameManagerInstance = new GameManager();
        }
        return GameManagerInstance;
    }
    private GameManager(){}

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

    public Game getGame() {
        return game;
    }


    // Host code
    public void CreateGame(String HostName){
        //Start server
        //add Host player
        this.gameState = GameState.CREATE_GAME;
        turnManager = new HostTurnManager(playerList);
        this.game = new Game(playerList);
        this.socketHostServer = new SocketHostServer(
            hostServerConfig.getPort(),6, RemoteClientCommunicator::create, ClientMessageHandler::create);
        socketHostServer.start();
        this.dictionaryServerCommunicator = new SocketDictionaryServerCommunicator(dictionaryServerConfig.getIP(),dictionaryServerConfig.getPort());
        AddPlayer(LocalRecipient.get(), HostName,true);

    }
    public void LoadGame(String SaveName){

    }

    // Remote client code
    public void JoinGame(String ClientName){
        this.gameState = GameState.JOIN_GAME;
        this.hostComm = SocketHostServerCommunicator.create(
            hostServerConfig.getIP(), hostServerConfig.getPort(), HostMessageHandler.create());
        this.hostComm.start();
        this.dictionaryServerCommunicator = new RemoteDictionaryServerCommunicator(this.hostComm);
        Message response = this.hostComm.sendAndReceiveMessage(MessageType.JOIN_GAME, ClientName);
//        System.out.println(MessageFormat.format("JOIN GAME Received message of type {0} with Value: {1}",response.type,response.arg));
        if (response.type == MessageType.PLAYER_ADDED) {
            this.RemotePlayerName = ClientName;
            LocalRecipient.get().sendMessage(MessageType.PLAYER_ADDED, response.arg);
        }else {
            LocalRecipient.get().sendMessage(response.type,response.arg);
        }
    }

    // Host code
    public void AddPlayer(GameRecipient requester, String PlayerName,boolean IsLocal){
        synchronized (playerList) {
            // Player overrides `equals` so it can be compared with a string
            @SuppressWarnings("all")
            boolean contained = playerList.contains(PlayerName);
            if (contained) {
                requester.sendMessage(MessageType.PLAYER_ALREADY_EXISTS, PlayerName);
                return;
            }
            Player player = PlayerFactory.GetInstance().CreatePlayer(PlayerName,IsLocal);
            playerList.add(player);
            game.getGameData().addScoreToPlayer(PlayerName,0);//Adding player to leaderboard
            List<String> playerNamesList = new ArrayList<>();
            playerList.forEach((p) -> playerNamesList.add(p.getName()));
//            requester.sendMessage(MessageType.PLAYER_ADDED_SUCCESSFULLY,playerNamesList); NOT NEEDED
            AllRecipient.get().sendMessage(MessageType.PLAYER_ADDED, playerNamesList);
        }
    }

    public void StartGame(){
        this.gameState = GameState.PLAY;
        AllRecipient.get().sendMessage(MessageType.GAME_STARTED, game.gameData);
        turnManager.StartTurn();
    }
    public void addWord(GameRecipient requester, Word word) {
        Integer score = turnManager.PlayTurn(word);
        if(score == 0){
            requester.sendMessage(MessageType.ILLEGAL_WORD, word.GetWordName());
            return;
        }
        AllRecipient.get().sendMessage(MessageType.UPDATE_GAME_DATA, game.getGameData());
        if(!turnManager.EndTurn())//When EndTUrn returns true game is over.
            turnManager.StartTurn();//o.w continue to next turn
        else {
            close();
        }
        // assuming the word was actually placed... not sure how to handle it otherwise...
//        turnManager.StartTurn(); // This needs to be called after the player successfully placed a word
    }
    public void GetCurrentPlayerTiles(GameRecipient requester){
        Tile[] tiles = turnManager.GetCurrentPlayerTiles();
        requester.sendMessage(MessageType.PLAYER_TILES , tiles);
    }
    @Override
    public synchronized void setChanged() {
        super.setChanged();
    }
    public void close(){
        if(socketHostServer != null){
            socketHostServer.close();
        }
    }

}
