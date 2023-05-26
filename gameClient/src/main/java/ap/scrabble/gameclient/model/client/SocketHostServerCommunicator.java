package ap.scrabble.gameclient.model.client;

import java.io.IOException;
import java.net.Socket;

import ap.scrabble.gameclient.model.communicator.SocketCommunicator;
import ap.scrabble.gameclient.model.message.Message;
import ap.scrabble.gameclient.model.message.MessageHandler;
import ap.scrabble.gameclient.model.message.MessageType;

public class SocketHostServerCommunicator extends SocketCommunicator implements HostServerCommunicator {
    public SocketHostServerCommunicator(Socket socket, MessageHandler messageHandler) {
        super(socket, messageHandler);
    }

    public static SocketHostServerCommunicator create(String ip, int port, MessageHandler messageHandler) {
        Socket socket = null;
        try {
            socket = new Socket(ip, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new SocketHostServerCommunicator(socket, messageHandler);
    }

    @Override
    public Message writeAndReceiveMessage(Message msg) {
        var hostMsgHnd = (HostMessageHandler)messageHandler;
        writeMessage(msg);
        Message response = hostMsgHnd.waitForResponse();
        return response;
    }

    @Override
    public Message sendAndReceiveMessage(MessageType type, Object arg) {
        return writeAndReceiveMessage(new Message(type, arg));
    }

    @Override
    protected void started() {
        // HostRecipient.get().setCommunicator(this);
    }
}
