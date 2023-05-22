package ap.scrabble.gameclient.model.recipient;

import ap.scrabble.gameclient.model.GameManager;
import ap.scrabble.gameclient.model.GameManager.Message;
import ap.scrabble.gameclient.model.GameManager.MessageType;

public class LocalRecipient extends GameRecipient  {

    private static LocalRecipient LocalRecipientInstance;
    public static LocalRecipient get() {
        if (LocalRecipientInstance == null) {
            LocalRecipientInstance = new LocalRecipient();
        }
        return LocalRecipientInstance;
    }

    @Override
    public Type getType() {
        return Type.LOCAL;
    }

    @Override
    public void sendMessage(MessageType type, Object arg) {
        GameManager inst = GameManager.get();

        inst.setChanged();
        inst.notifyObservers(new Message(type, arg));
    }
}
