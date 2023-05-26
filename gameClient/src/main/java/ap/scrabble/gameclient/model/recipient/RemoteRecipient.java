package ap.scrabble.gameclient.model.recipient;

import ap.scrabble.gameclient.model.GameManager.Message;
import ap.scrabble.gameclient.model.GameManager.MessageType;
import ap.scrabble.gameclient.model.communicator.Communicator;

public class RemoteRecipient extends GameRecipient {
    Communicator clientCommunicator;

    public RemoteRecipient(Communicator clientCommunicator) {
        this.clientCommunicator = clientCommunicator;
    }

    @Override
    public Type getType() {
        return Type.REMOTE;
    }

    @Override
    public void sendMessage(MessageType type, Object arg) {
        clientCommunicator.writeMessage(new Message(type,arg));
    }
}
