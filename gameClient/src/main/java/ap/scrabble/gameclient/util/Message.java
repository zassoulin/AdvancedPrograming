package ap.scrabble.gameclient.util;

import java.io.Serializable;

public class Message<MessageType> implements Serializable {
    public MessageType type;
    public Object arg;

    public Message(MessageType type, Object arg) {
        this.type = type;
        this.arg = arg;
    }
}
