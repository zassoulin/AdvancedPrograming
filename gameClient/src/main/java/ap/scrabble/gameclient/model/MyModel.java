package ap.scrabble.gameclient.model;

import static ap.scrabble.gameclient.util.Assert.assertCond;

import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import ap.scrabble.gameclient.model.board.Word;
import ap.scrabble.gameclient.model.properties.DictionaryServerConfig;
import ap.scrabble.gameclient.model.properties.HostServerConfig;
import ap.scrabble.gameclient.model.recipient.LocalRecipient;
import ap.scrabble.gameclient.util.Message;

public class MyModel extends Model implements Observer{
	// Handle messages from `GameManager`
	private static interface MessageHandler {
		void handle(GameManager.Message msg);
	}
	// Generic message handler that forwards the message to `ViewModel`
	private MessageHandler forwardMessage = (msg)-> sendMessage(msg.type.name(), msg.arg);

	private Map<GameManager.MessageType, MessageHandler> messageHandlers;
	private boolean forwardByDefault = true;

	public MyModel(DictionaryServerConfig dictionaryServerConfig, HostServerConfig hostServerConfig) {
		// messageHandlers = Map.ofEntries(
		// 	entry(GameManager.MessageType.PLAYER_ALREADY_EXISTS, forwardMessage),
		// 	entry(GameManager.MessageType.PLAYER_ADDED, forwardMessage),
		// 	entry(GameManager.MessageType.CURRENT_PLAYER, forwardMessage),
		// 	entry(GameManager.MessageType.REMOTE_TURN, forwardMessage)
		// );

		GameManager.get().addObserver(this);
		GameManager.get().setConfig(dictionaryServerConfig, hostServerConfig);
		GameManager.get().init();
	}

	@Override
	public String getGameState() {
		return GameManager.get().getGameState().name();
	}

	@Override
	public void addLocalPlayer(String name) {
		GameManager.get().AddPlayer(LocalRecipient.get(), name,true);
	}
	@Override
	public void CreateGame(String name){
		GameManager.get().CreateGame(name);
	}
	@Override
	public void StartGame(){
		GameManager.get().StartGame();
	}


	@Override
	public void addWord(Word word) {
		GameManager.get().addWord(LocalRecipient.get(), word);
	}

	@Override
	public void update(Observable o, Object arg) {
		assertCond(arg != null, "MyModel: Notify observer from `GameManager` missing argument");
		var msg = (GameManager.Message)arg;
		if (!messageHandlers.containsKey(msg.type)) {
			messageHandlers.get(msg.type).handle(msg);
		}
		else if (forwardByDefault) {
			forwardMessage.handle(msg);
		}
	}

	private void sendMessage(String type, Object arg) {
		setChanged();
		notifyObservers(new Message<String>(type, arg));
	}
}
