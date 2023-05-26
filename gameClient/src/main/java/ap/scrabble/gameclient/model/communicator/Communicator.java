package ap.scrabble.gameclient.model.communicator;

import ap.scrabble.gameclient.model.GameManager.Message;

public interface Communicator {
    void start();
    void close();
    void writeMessage(Message msg);
}
