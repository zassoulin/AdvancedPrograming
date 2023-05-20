package ap.scrabble.gameclient.model.recipient;

import ap.scrabble.gameclient.model.ClientHandler;
import ap.scrabble.gameclient.model.GameManager.MessageType;

public class RemoteRecipient extends GameRecipient {
    ClientHandler clientHandler;

    public RemoteRecipient(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
    }

    @Override
    public Type getType() {
        return Type.REMOTE;
    }

    @Override
    public void sendMessage(MessageType type, Object arg) {
        // TODO: Send message (should be serializable) to remote client through `ClientHandler`
    }
}
