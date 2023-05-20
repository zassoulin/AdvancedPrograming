package ap.scrabble.gameclient.model;

import static ap.scrabble.gameclient.util.Assert.assertCond;
import static java.util.Map.entry;

import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import ap.scrabble.gameclient.model.properties.DictionaryServerConfig;
import ap.scrabble.gameclient.model.properties.HostServerConfig;
import ap.scrabble.gameclient.util.Message;

public class MyModel extends Model implements Observer{
	// Handle messages from `GameManager`
	private static interface MessageHandler {
		void handle(GameManager.Message msg);
	}
	// Generic message handler that forwards the message to `ViewModel`
	private MessageHandler forwardMessage = (msg)-> sendMessage(msg.type.name(), msg.arg);

	private Map<GameManager.MessageType, MessageHandler> messageHandlers;

	public MyModel(DictionaryServerConfig dictionaryServerConfig, HostServerConfig hostServerConfig) {
		messageHandlers = Map.ofEntries(
			entry(GameManager.MessageType.PLAYER_ALREADY_EXISTS, forwardMessage),
			entry(GameManager.MessageType.PLAYER_ADDED, forwardMessage),
			entry(GameManager.MessageType.CURRENT_PLAYER, forwardMessage),
			entry(GameManager.MessageType.REMOTE_TURN, forwardMessage)
		);

		GameManager.getInstance().addObserver(this);
		GameManager.getInstance().setConfig(dictionaryServerConfig, hostServerConfig);
		GameManager.getInstance().init();
	}

	@Override
	public String getGameState() {
		return GameManager.getInstance().getGameState().name();
	}

	@Override
	public void addLocalPlayer(String name) {
		GameManager.getInstance().AddPlayer(GameManager.getInstance().getLocal(), name);
	}

	@Override
	public void update(Observable o, Object arg) {
		assertCond(arg != null, "MyModel: Notify observer from `GameManager` missing argument");
		var msg = (GameManager.Message)arg;
		messageHandlers.get(msg.type.ordinal()).handle(msg);
	}

	private void sendMessage(String type, Object arg) {
		setChanged();
		notifyObservers(new Message<String>(type, arg));
	}
}
