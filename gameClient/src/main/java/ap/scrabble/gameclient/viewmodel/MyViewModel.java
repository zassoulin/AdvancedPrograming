package ap.scrabble.gameclient.viewmodel;

import java.io.*;
import java.text.MessageFormat;
import java.util.*;

import ap.scrabble.gameclient.model.Model;
import ap.scrabble.gameclient.model.board.Board;
import ap.scrabble.gameclient.model.board.GameData;
import ap.scrabble.gameclient.model.board.Tile;
import ap.scrabble.gameclient.model.board.Word;
import ap.scrabble.gameclient.util.Message;


public class MyViewModel extends ViewModel {
	// Implement the ViewModel facade

	private Model model;
	private GameData gameData;
	private Tile[] tileList;
	private static List<String> playerNames;
	private Map<String,Integer> playersScores = new HashMap<>();
	private ArrayList<Word> wordsOnBoard = new ArrayList<>(); // don't need this probably
	private Word tempWord;
	private String currentPlayer;


	public MyViewModel(Model model) {
		this.model = model;
		model.addObserver(this);
		model.getGameState();
	}

	public void saveGame()  {
		GameState gameState = new GameState(gameData, tileList, playerNames, playersScores, currentPlayer);
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("game.save"))) {
			oos.writeObject(gameState);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void loadGame()  {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("game.save"))) {
			GameState gameState = (GameState) ois.readObject();
			gameData = gameState.getGameData();
			tileList = gameState.getTileList();
			playerNames = gameState.getPlayerNames();
			playersScores = gameState.getPlayersScores();
			currentPlayer = gameState.getCurrentPlayer();
		}
		catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void setPlayersScores(Map<String, Integer> playersScores) {
		this.playersScores = playersScores;
		sendMessage("PLAYER_SCORES", this.playersScores);
	}
	public static List<String> getPlayerNames() {
		return playerNames;
	}

	public static void setPlayerNames(List<String> playerNames) {
		MyViewModel.playerNames = playerNames;
	}

	public void createGameRt(String playerName) { model.CreateGame(playerName);}
//	public void setPlayerNameRt(String playerName){model.addLocalPlayer(playerName);}
	public void joinGameRt(String playerName){model.JoinGame(playerName);}
	public void startGameRt(){
		model.StartGame();
//		model.GetCurrentPlayerTiles(); // update
	}

	public void addPlayer(String playerName){
		model.addLocalPlayer(playerName);
	}

	public void sendSubmittedWord(char[] letters, int x, int y, boolean vertical){
		Word word = new Word(buildTiles(letters),x,y,vertical);
//		System.out.println("VM sending to M: " + word.GetWordName());
		tempWord = word;
		model.addWord(word);
	}

	public Tile[] buildTiles(char[] chars){
		Tile[] tiles = new Tile[chars.length];
		int idx = 0;
		for (char c:chars) {
			tiles[idx] = getTileFromList(c);
			idx++;
		}
		return tiles;
	}

	private Tile getTileFromList(char c){
		for (Tile t:tileList) {
			if (t != null && t.letter == c)
				return t;
		}
		return null;
	}

	private void printBoard(){
		Board b = gameData.getBoard();
		b.print();
	}

	private void sendMessage(String type, Object arg) {
		setChanged();
		notifyObservers(new ap.scrabble.gameclient.util.Message<String>(type, arg));
	}

	private char[] tilesToChars(){
		char[] c = new char[tileList.length];
		int i = 0;
		for (Tile t:this.tileList){
			c[i] = t.letter;
			i++;
		}
		return c;
	}

	@Override
	public void update(Observable o, Object arg) {
		Message message = (Message) arg;
		System.out.println(MessageFormat.format("ViewModel Received message of type {0} with Value: {1}", message.type , message.arg));
		HandleMessage(message);
	}
	public void HandleMessage(Message message){
		if(message.type == "UPDATE_GAME_DATA"){ // When word returned from server is valid
			gameData = (GameData) message.arg;
			printBoard();
			setPlayersScores(gameData.getPlayersScores());
			// Save State
			wordsOnBoard.add(tempWord); // save word for save/load
			tempWord = null;
			//viewmodel/view needs to update board&score board accordingly
		}
		else if(message.type == "PLAYER_TILES"){
//			Tile[] tileList = (Tile[]) message.arg;
			this.tileList = (Tile[]) message.arg;
			System.out.print("ViewModel: Got player tiles: ");
			System.out.println(tilesToChars());
			sendMessage("PLAYER_TILES",tilesToChars());
			//TODO:show to view the player Tiles, When Player tries to place Word view/Viewmodel needs to make sure they are in the list
		}
		else if (message.type == "PLAYER_ADDED"){
			playerNames = (List<String>) message.arg;
			// initialize player scores
			playersScores.put(playerNames.get(playerNames.size()-1),0);
			setPlayersScores(playersScores);
			//TODO: view needs to update the player count in lobby and if implemented show the player list
			//TODO: When viewmodel calls the JoinGame request it may want to know if it has succeed if so the viewModel needs to check if Its PlayerName is included in the playerNames list
		} else if (message.type == "PLAYER_ALREADY_EXISTS") {
			String PlayerName = (String) message.arg;
			//TODO: This message is received when Trying to join a game with a name which already exists,View needs to tell User to try joining again with a different name
		} else if (message.type == "GAME_STARTED") { // arg = GameData
			gameData = (GameData) message.arg;
			sendMessage("GAME_STARTED","GAME_STARTED");
//			model.GetCurrentPlayerTiles(); // inital call to get first player's tiles
		} else if (message.type == "CURRENT_PLAYER") {
			String CurrentPlayerName = (String) message.arg;
			sendMessage("CURRENT_PLAYER", CurrentPlayerName);
			model.GetCurrentPlayerTiles();
			// Save game
			currentPlayer = CurrentPlayerName;
			//TODO: change View current playName to the player received
		} else if (message.type == "MY_TURN") {
			boolean isMyTurn = (boolean) message.arg;
			//TODO:Tells View if it is his turn to play or not if isMyTurn true call GetPlayerTiles and allow player to play else lock view
		} else if (message.type == "GAME_OVER") {
			//TODO Game is over handle however you want  you can show ScoreBoard end program etc...
			//IF you want you can close connections and free resources but its not important
		} else if (message.type == "CANT_JOIN_HOST") {
			//TODO:Display error message
		} else if (message.type == "ILLEGAL_WORD") {
			//TODO Tell user The word he tried to place is illegal and he need to try again
		}else {
			System.out.println("ERROR RReceived unknown message of type " + message.type);
			System.out.println("message Value is" + message.arg);
		}
	}

}
