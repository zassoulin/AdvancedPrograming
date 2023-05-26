package ap.scrabble.gameclient.model.host;

import static ap.scrabble.gameclient.util.Assert.assertCond;

import ap.scrabble.gameclient.model.message.Message;
import ap.scrabble.gameclient.model.message.MessageHandler;
import ap.scrabble.gameclient.model.recipient.LocalRecipient;

// Runs on the host's side and handles messages from the client
public class ClientMessageHandler implements MessageHandler {
    public static MessageHandler create() {
        return new ClientMessageHandler();
    }

    @Override
    public void handleMessage(Message msg) {
        // TODO: implement
        // REMOTE_ADD_WORD, REMOTE_JOIN_GAME, etc.

        switch (msg.type) {
            // =========== TEST ===========
        case HELLO_HOST:
            LocalRecipient.get().sendMessage(msg.type, msg.arg);
            break;
        case HI_HOST_THIS_IS_CLIENT:
            LocalRecipient.get().sendMessage(msg.type, msg.arg);
            break;
            // ============================
        default:
            assertCond(false, "HostMessageHandler: Unhandled message type");
            break;
        }
    }
}
