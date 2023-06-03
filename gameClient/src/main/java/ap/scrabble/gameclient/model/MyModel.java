package ap.scrabble.gameclient.model;

import static ap.scrabble.gameclient.util.Assert.assertCond;

import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

import ap.scrabble.gameclient.model.board.Word;
import ap.scrabble.gameclient.model.client.HostMessageHandler;
import ap.scrabble.gameclient.model.message.Message;
import ap.scrabble.gameclient.model.message.MessageType;
import ap.scrabble.gameclient.model.properties.DictionaryServerConfig;
import ap.scrabble.gameclient.model.properties.HostServerConfig;
import ap.scrabble.gameclient.model.recipient.LocalRecipient;

public class MyModel extends Model implements Observer{

	boolean isHost;
	public MyModel(DictionaryServerConfig dictionaryServerConfig, HostServerConfig hostServerConfig) {

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
		isHost = true;
		GameManager.get().CreateGame(name);
	}
	@Override
	public void JoinGame(String PlayerName){
		isHost = false;
		GameManager.get().JoinGame(PlayerName);
	}
	@Override
	public void StartGame(){
		GameManager.get().StartGame();
	}

	@Override
	public void addWord(Word word) {
		if(isHost){
		GameManager.get().addWord(LocalRecipient.get(), word);
		}
		else{
			GameManager.get().getHostComm().writeMessage(new Message( MessageType.PLAY_REMOTE_PLAYER_TURN,word));
//			LocalRecipient.get().sendMessage(res.type,res.arg);
		}
	}
	@Override
	public void GetCurrentPlayerTiles(){
		if(isHost){
			GameManager.get().GetCurrentPlayerTiles(LocalRecipient.get());
		}
		else{
			GameManager.get().getHostComm().writeMessage(new Message(MessageType.GET_CURRENT_PLAYER_TILES,null));
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		assertCond(arg != null, "MyModel: Notify observer from `GameManager` missing argument");
		var msg = (ap.scrabble.gameclient.model.message.Message)arg;
		sendMessage(msg.type.name(), msg.arg);
	}

	private void sendMessage(String type, Object arg) {
		setChanged();
		notifyObservers(new ap.scrabble.gameclient.util.Message<String>(type, arg));
	}
}
