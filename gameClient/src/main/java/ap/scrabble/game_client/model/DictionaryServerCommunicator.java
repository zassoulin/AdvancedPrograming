package ap.scrabble.game_client.model;

public interface DictionaryServerCommunicator {
    String runClientQueryRequest(String word,String ... books);
    String runClientChallengeRequest(String word,String ... books);
}
