package ap.scrabble.gameclient.model;

public class RemoteDictionaryServerCommunicator implements DictionaryServerCommunicator{
    public RemoteDictionaryServerCommunicator() {
        //TODO uses HostRecipent
    }//TODO:implement
    @Override
    public String runClientQueryRequest(String word, String... books) {
        return null;
    }

    @Override
    public String runClientChallengeRequest(String word, String... books) {
        return null;
    }
}
