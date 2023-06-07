package ap.scrabble.gameclient.model.message;

import ap.scrabble.gameclient.util.MessageType;

public class Message extends ap.scrabble.gameclient.util.Message<MessageType> {
    public Message(MessageType type, Object arg) {
        super(type, arg);
    }
}