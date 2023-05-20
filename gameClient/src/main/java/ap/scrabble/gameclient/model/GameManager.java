package ap.scrabble.gameclient.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import ap.scrabble.gameclient.model.board.Word;
import ap.scrabble.gameclient.model.properties.DictionaryServerConfig;
import ap.scrabble.gameclient.model.properties.HostServerConfig;
import ap.scrabble.gameclient.model.recipient.AllRecipient;
import ap.scrabble.gameclient.model.recipient.GameRecipient;
import ap.scrabble.gameclient.model.recipient.LocalRecipient;

public class GameManager extends Observable {
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
        LOCAL_TURN,
        REMOTE_TURN,
        ILLEGAL_WORD,
        PLAYER_SCORE,
        GAME_OVER,
    }

    public static class Message extends ap.scrabble.gameclient.util.Message<MessageType> {
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
    private LocalRecipient local;
    private AllRecipient all;

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

    public Game getGame() {
        return game;
    }

    public LocalRecipient getLocal() {
        return local;
    }

    public void CreateGame(String HostName){
        //Start server
        //add Host player
        Player HostPlayer;//Init
        turnManager = new hostTurnManager(playerList);
        this.game = new Game(playerList);
        AddPlayer(local, HostName);

    }
    public void JoinGame(String ClientName){

    }
    public void AddPlayer(GameRecipient requester, String PlayerName){
        synchronized (playerList) {
            if (playerList.contains(PlayerName)) {
                requester.sendMessage(MessageType.PLAYER_ALREADY_EXISTS, PlayerName);
                return;
            }
            Player player = PlayerFactory.GetInstance().CreatePlayer(requester, PlayerName);
            playerList.add(player);
            all.sendMessage(MessageType.PLAYER_ADDED, PlayerName);
        }
    }
    public void StartGame(){
        
    }
    public void addWord(GameRecipient requester, Word word) {
        turnManager.getCurrentPlayer().PlaceWord(requester, word);
        // assuming the word was actually placed... not sure how to handle it otherwise...
        turnManager.PlayNextTurn(); // This needs to be called after the player successfully placed a word
    }

    @Override
    public synchronized void setChanged() {
        super.setChanged();
    }

    public void sendLocalMessage(MessageType type, Object arg) {
        local.sendMessage(type, arg);
    }

    public void sendAllMessage(MessageType type, Object arg) {
        all.sendMessage(type, arg);
    }
}
