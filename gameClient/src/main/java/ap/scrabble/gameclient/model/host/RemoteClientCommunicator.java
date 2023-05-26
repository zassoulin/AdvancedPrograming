package ap.scrabble.gameclient.model.host;

import java.net.Socket;

import ap.scrabble.gameclient.model.communicator.SocketCommunicator;
import ap.scrabble.gameclient.model.message.MessageHandler;
import ap.scrabble.gameclient.model.recipient.AllRecipient;
import ap.scrabble.gameclient.model.recipient.RemoteRecipient;

public class RemoteClientCommunicator extends SocketCommunicator {
    public RemoteClientCommunicator(Socket socket, MessageHandler messageHandler) {
        super(socket, messageHandler, true);
    }

    public static SocketCommunicator create(Socket socket, MessageHandler messageHandler) {
        return new RemoteClientCommunicator(socket, messageHandler);
    }

    @Override
    protected void started() {
        AllRecipient.get().addRemoteRecipient(new RemoteRecipient(this));
    }
}
