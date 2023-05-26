package ap.scrabble.gameclient.model.message;

public class Message extends ap.scrabble.gameclient.util.Message<MessageType> {
    public Message(MessageType type, Object arg) {
        super(type, arg);
    }
}