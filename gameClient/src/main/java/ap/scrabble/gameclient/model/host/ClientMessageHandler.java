package ap.scrabble.gameclient.model.host;

import ap.scrabble.gameclient.model.GameManager.Message;
import ap.scrabble.gameclient.model.message.MessageHandler;

// Runs on the host's side and handles messages from the client
public class ClientMessageHandler implements MessageHandler {
    public static MessageHandler create() {
        return new ClientMessageHandler();
    }

    @Override
    public void handleMessage(Message msg) {
        // TODO: implement
        // REMOTE_ADD_WORD, REMOTE_JOIN_GAME, etc.
    }
}
