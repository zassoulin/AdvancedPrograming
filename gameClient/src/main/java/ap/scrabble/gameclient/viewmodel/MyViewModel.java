package ap.scrabble.gameclient.viewmodel;

import java.text.MessageFormat;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import ap.scrabble.gameclient.model.GameManager;
import ap.scrabble.gameclient.model.Model;
import ap.scrabble.gameclient.model.Player;
import ap.scrabble.gameclient.model.board.GameData;
import ap.scrabble.gameclient.model.board.Tile;
import ap.scrabble.gameclient.model.message.Message;
import ap.scrabble.gameclient.model.message.MessageType;

public class MyViewModel extends ViewModel {
	// Implement the ViewModel facade

	private Model model;

	public MyViewModel(Model model) {
		this.model = model;
		model.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		Message message = (Message) arg;
//		System.out.println(MessageFormat.format("View Received message of type {0} with Value: {1}",message.type,message.arg));
		HandleMessage(message);
	}
	public void HandleMessage(Message message){
		if(message.type == MessageType.UPDATE_GAME_DATA){
			GameData gameData = (GameData) message.arg;
			//TODO: viewmodel/view needs to update board&score board accordingly
//			gameData.getBoard().print();
//			System.out.println( gameData.getPlayersScores());
		}
		else if(message.type == MessageType.PLAYER_TILES){
			Tile[] tileList = (Tile[]) message.arg;
			//TODO:show to view the player Tiles, When Player tries to place Word view/Viewmodel needs to make sure they are in the list
//			System.out.println(tileList);
		}
		else if (message.type == MessageType.PLAYER_ADDED){
			List<String> playerNames = (List<String>) message.arg;
			//TODO: view needs to update the player count in lobby and if implemented show the player list
			//TODO: When viewmodel calls the JoinGame request it may want to know if it has succeed if so the viewModel needs to check if Its PlayerName is included in the playerNames list
		} else if (message.type == MessageType.PLAYER_ALREADY_EXISTS) {
			String PlayerName = (String) message.arg;
			//TODO: This message is received when Trying to join a game with a name which already exists,View needs to tell User to try joining again with a different name
		} else if (message.type == MessageType.GAME_STARTED) {
			//TODO:move view into Game mode(show user the board scoreboard etc..)
		} else if (message.type == MessageType.CURRENT_PLAYER) {
			String CurrentPlayerName = (String) message.arg;
			//TODO: change View current playName to the player received
		} else if (message.type == MessageType.MY_TURN) {
			boolean isMyTurn = (boolean) message.arg;
			//TODO:Tells View if it is his turn to play or not if isMyTurn true call GetPlayerTiles and allow player to play else lock view
		} else if (message.type == MessageType.GAME_OVER) {
			//TODO Game is over handle however you want  you can show ScoreBoard end program etc...
			//IF you want you can close connections and free resources but its not important
		} else if (message.type == MessageType.CANT_JOIN_HOST) {
			//TODO:Display error message
		} else if (message.type == MessageType.ILLEGAL_WORD) {
			//TODO Tell user The word he tried to place is illegal and he need to try again
		}else {
			System.out.println("ERROR RReceived unknown message of type " + message.type);
			System.out.println("message Value is" + message.arg);
		}
	}

}
