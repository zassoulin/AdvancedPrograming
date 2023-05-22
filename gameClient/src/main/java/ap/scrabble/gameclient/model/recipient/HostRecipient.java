package ap.scrabble.gameclient.model.recipient;

import ap.scrabble.gameclient.model.GameManager.MessageType;
import ap.scrabble.gameclient.model.HostServerCommunicator;
import ap.scrabble.gameclient.model.SocketHostServerCommunicator;


public class HostRecipient extends GameRecipient {

    private static HostRecipient HostRecipientInstance;
    public static HostRecipient get() {
        if (HostRecipientInstance == null) {
            HostRecipientInstance = new HostRecipient(SocketHostServerCommunicator.get());
        }
        return HostRecipientInstance;
    }
    HostServerCommunicator hostHandler;

    public HostRecipient(HostServerCommunicator hostHandler) {
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
