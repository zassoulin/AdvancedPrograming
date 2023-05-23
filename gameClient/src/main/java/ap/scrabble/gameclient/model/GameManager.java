package ap.scrabble.gameclient.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import ap.scrabble.gameclient.model.board.GameData;
import ap.scrabble.gameclient.model.board.Word;
import ap.scrabble.gameclient.model.properties.DictionaryServerConfig;
import ap.scrabble.gameclient.model.properties.HostServerConfig;
import ap.scrabble.gameclient.model.recipient.AllRecipient;
import ap.scrabble.gameclient.model.recipient.GameRecipient;
import ap.scrabble.gameclient.model.recipient.LocalRecipient;

public class GameManager extends Observable {

    private static GameManager GameManagerInstance;
    private List<Player> playerList = new ArrayList<>();
    private GameState gameState = GameState.MAIN_MENU;

    public DictionaryServerConfig getDictionaryServerConfig() {
        return dictionaryServerConfig;
    }

    private DictionaryServerConfig dictionaryServerConfig;
    private HostServerConfig hostServerConfig;
    private TurnManager turnManager;
    private Game game;

    public DictionaryServerCommunicator getDictionaryServerCommunicator() {
        return dictionaryServerCommunicator;
    }

    private DictionaryServerCommunicator dictionaryServerCommunicator;

    SocketHostServer socketHostServer;
    public static enum GameState {
        MAIN_MENU,
        CREATE_GAME,
        JOIN_GAME,
        PLAY
    }

    public static enum MessageType {
        PLAYER_ALREADY_EXISTS,
        PLAYER_ADDED,
        CURRENT_PLAYER,
        MY_TURN,
        OTHER_PLAYER_TURN,
        ILLEGAL_WORD,
        PLAY_NEXT_TURN,
        UPDATE_GAME_DATA,
        GAME_OVER,
        GAME_STARTED,
        QUERY,
        CHALLENGE,
    }

    public static class Message extends ap.scrabble.gameclient.util.Message<MessageType> {
        public Message(MessageType type, Object arg) {
            super(type, arg);
        }
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


    public void CreateGame(String HostName){
        //Start server
        //add Host player
        this.gameState = GameState.CREATE_GAME;
        turnManager = new hostTurnManager(playerList);
        this.game = new Game(playerList);
        this.socketHostServer = new SocketHostServer(hostServerConfig.getPort(),new RemoteClientHandler(),6);
        socketHostServer.start();
        this.dictionaryServerCommunicator = new SocketDictionaryServerCommunicator(dictionaryServerConfig.getIP(),dictionaryServerConfig.getPort());
        AddPlayer(LocalRecipient.get(), HostName,true);

    }
    public void JoinGame(String ClientName){
        this.dictionaryServerCommunicator = new RemoteDictionaryServerCommunicator();

    }
    public void AddPlayer(GameRecipient requester, String PlayerName,boolean IsLocal){
        synchronized (playerList) {
            if (playerList.contains(PlayerName)) {
                requester.sendMessage(MessageType.PLAYER_ALREADY_EXISTS, PlayerName);
                return;
            }
            Player player = PlayerFactory.GetInstance().CreatePlayer(PlayerName,IsLocal);
            playerList.add(player);
            AllRecipient.get().sendMessage(MessageType.PLAYER_ADDED, PlayerName);
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
            requester.sendMessage(MessageType.ILLEGAL_WORD,null);
            return;
        }
        AllRecipient.get().sendMessage(MessageType.UPDATE_GAME_DATA, game.getGameData());
        if(!turnManager.EndTurn())//When EndTUrn returns true game is over.
            turnManager.StartTurn();//o.w continue to next turn
//        turnManager.getCurrentPlayer().PlaceWord(requester, word);
        // assuming the word was actually placed... not sure how to handle it otherwise...
//        turnManager.StartTurn(); // This needs to be called after the player successfully placed a word
    }
    @Override
    public synchronized void setChanged() {
        super.setChanged();
    }

}
