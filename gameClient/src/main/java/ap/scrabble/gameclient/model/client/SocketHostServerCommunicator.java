package ap.scrabble.gameclient.model.client;

import java.io.IOException;
import java.net.Socket;

import ap.scrabble.gameclient.model.communicator.SocketCommunicator;
import ap.scrabble.gameclient.model.message.Message;
import ap.scrabble.gameclient.model.message.MessageHandler;
import ap.scrabble.gameclient.util.MessageType;

public class SocketHostServerCommunicator extends SocketCommunicator implements HostServerCommunicator {
    private boolean startedListen = false;

    public SocketHostServerCommunicator(Socket socket, MessageHandler messageHandler) {
        super(socket, messageHandler, true);
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
        writeMessage(msg);
        Message response = waitForResponse();
        return response;
    }

    public Message waitForResponse() {
        if (!startedListen) {
            return null;
        }
        var hostMsgHnd = (HostMessageHandler)messageHandler;
        return hostMsgHnd.waitForResponse();
    }

    @Override
    public Message sendAndReceiveMessage(MessageType type, Object arg) {
        return writeAndReceiveMessage(new Message(type, arg));
    }

    @Override
    protected void started() {
        startedListen = true;
        // HostRecipient.get().setCommunicator(this);
    }
}
