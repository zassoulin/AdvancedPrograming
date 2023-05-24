package ap.scrabble.gameclient.model;

public interface DictionaryServerCommunicator {
    String runClientQueryRequest(String word,String ... books);
    String runClientChallengeRequest(String word,String ... books);
    //TODO: maybe add close?
}
