package ap.scrabble.gameclient.model.message;

import ap.scrabble.gameclient.model.GameManager.Message;

public interface MessageHandler {
    void handleMessage(Message msg);
}
