package ap.scrabble.gameclient.model;

import ap.scrabble.gameclient.model.recipient.HostRecipient;
public class SocketHostServerCommunicator implements HostServerCommunicator{

    private static SocketHostServerCommunicator SocketHostServerCommunicatorInstance;
    public static SocketHostServerCommunicator get() {
        if (SocketHostServerCommunicatorInstance == null) {
            SocketHostServerCommunicatorInstance = new SocketHostServerCommunicator();
        }
        return SocketHostServerCommunicatorInstance;
    }
    private SocketHostServerCommunicator(){}
    @Override
    public String runClientQueryRequest(String word, String... books) {
        return null;
    }

    @Override
    public String runClientChallengeRequest(String word, String... books) {
        return null;
    }
}
