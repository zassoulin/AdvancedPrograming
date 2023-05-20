package ap.scrabble.gameclient.util;

public class Message<MessageType> {
    public MessageType type;
    public Object arg;

    public Message(MessageType type, Object arg) {
        this.type = type;
        this.arg = arg;
    }
}
