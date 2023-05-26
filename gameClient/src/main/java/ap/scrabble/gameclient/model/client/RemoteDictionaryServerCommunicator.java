package ap.scrabble.gameclient.model.client;

import ap.scrabble.gameclient.model.communicator.DictionaryServerCommunicator;

public class RemoteDictionaryServerCommunicator implements DictionaryServerCommunicator{
    private HostServerCommunicator comm;

    public RemoteDictionaryServerCommunicator(HostServerCommunicator comm) {
        this.comm = comm;
    }//TODO:implement
    @Override
    public String runClientQueryRequest(String word, String... books) {
        // TODO: Implement
        return null;
    }

    @Override
    public String runClientChallengeRequest(String word, String... books) {
        // TODO: Implement
        return null;
    }
}
