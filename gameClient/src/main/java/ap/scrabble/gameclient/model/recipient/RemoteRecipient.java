package ap.scrabble.gameclient.model.recipient;

import ap.scrabble.gameclient.model.communicator.Communicator;
import ap.scrabble.gameclient.model.message.Message;
import ap.scrabble.gameclient.util.MessageType;

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
