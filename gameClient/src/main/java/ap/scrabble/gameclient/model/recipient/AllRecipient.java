package ap.scrabble.gameclient.model.recipient;

import java.util.ArrayList;
import java.util.List;

import ap.scrabble.gameclient.model.GameManager.MessageType;

public class AllRecipient extends GameRecipient {

    private static AllRecipient AllRecipientInstance;
    public static AllRecipient get() {
        if (AllRecipientInstance == null) {
            AllRecipientInstance = new AllRecipient();
        }
        return AllRecipientInstance;
    }
    List<RemoteRecipient> remoteRecipients = new ArrayList<>();
    LocalRecipient localRecipient = new LocalRecipient();

    // Call this when a new remote player connects
    public void addRemoteRecipient(RemoteRecipient remoteRecipient) {
        remoteRecipients.add(remoteRecipient);
    }

    @Override
    public Type getType() {
        return Type.ALL;
    }

    @Override
    public void sendMessage(MessageType type, Object arg) {
        localRecipient.sendMessage(type, arg);
        for (RemoteRecipient r : remoteRecipients) {
            r.sendMessage(type, arg);
        }
    }
}
