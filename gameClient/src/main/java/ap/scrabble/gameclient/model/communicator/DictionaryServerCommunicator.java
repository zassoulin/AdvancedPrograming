package ap.scrabble.gameclient.model.communicator;

// Not actually related to the other communicator classes.
// Maybe this is not the right package for it? Maybe it should extend `Communicator`?
public interface DictionaryServerCommunicator {
    String runClientQueryRequest(String word,String ... books);
    String runClientChallengeRequest(String word,String ... books);
    //TODO: maybe add close?
}
