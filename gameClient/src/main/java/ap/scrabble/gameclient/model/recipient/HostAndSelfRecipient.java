package ap.scrabble.gameclient.model.recipient;

import ap.scrabble.gameclient.model.GameManager.MessageType;

public class HostAndSelfRecipient extends GameRecipient {
    HostRecipient hostRecipient;
    LocalRecipient selfRecipient;

    public HostAndSelfRecipient(HostRecipient hostRecipient) {
        this.hostRecipient = hostRecipient;
        this.selfRecipient = new LocalRecipient();
    }

    @Override
    public Type getType() {
        return Type.HOST_AND_SELF;
    }

    @Override
    public void sendMessage(MessageType type, Object arg) {
        selfRecipient.sendMessage(type, arg);
        hostRecipient.sendMessage(type, arg);
    }
}
