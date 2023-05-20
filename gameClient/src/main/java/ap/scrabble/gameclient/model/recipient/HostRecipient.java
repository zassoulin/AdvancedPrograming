package ap.scrabble.gameclient.model.recipient;

import ap.scrabble.gameclient.model.GameManager.MessageType;

public class HostRecipient extends GameRecipient {
    HostHandler hostHandler;

    public HostRecipient(HostHandler hostHandler) {
        this.hostHandler = hostHandler;
    }

    @Override
    public Type getType() {
        return Type.REMOTE;
    }

    @Override
    public void sendMessage(MessageType type, Object arg) {
        // TODO: Send message (should be serializable) to remote client through `HostHandler`
    }
}
