package ap.scrabble.gameclient.model.communicator;

import java.net.Socket;

import ap.scrabble.gameclient.model.message.MessageHandler;

public interface SocketCommunicatorFactory {
    SocketCommunicator create(Socket socket, MessageHandler messageHandler);
}
