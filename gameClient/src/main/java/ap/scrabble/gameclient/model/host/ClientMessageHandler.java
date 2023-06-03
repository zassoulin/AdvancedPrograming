package ap.scrabble.gameclient.model.host;

import static ap.scrabble.gameclient.util.Assert.assertCond;

import ap.scrabble.gameclient.model.GameManager;
import ap.scrabble.gameclient.model.board.Word;
import ap.scrabble.gameclient.model.message.Message;
import ap.scrabble.gameclient.model.message.MessageHandler;
import ap.scrabble.gameclient.model.recipient.LocalRecipient;
import ap.scrabble.gameclient.model.recipient.RemoteRecipient;

// Runs on the host's side and handles messages from the client
public class ClientMessageHandler implements MessageHandler {
    private RemoteRecipient recipient;

    public static MessageHandler create() {
        return new ClientMessageHandler();
    }

    public void setRecipient(RemoteRecipient recipient) { this.recipient = recipient; }

    @Override
    public void handleMessage(Message msg) {//handle async communication
        // TODO: implement
        // REMOTE_ADD_WORD, REMOTE_JOIN_GAME, etc.

        switch (msg.type) {
            // ========== CLIENT ==========
            case JOIN_GAME:
            String clientName = (String)msg.arg;
            GameManager.get().AddPlayer(recipient, clientName, false);
            break;
            // ============================
        case PLAY_REMOTE_PLAYER_TURN:
            Word word = (Word) msg.arg;
            GameManager.get().addWord(recipient,word);
            break;
            // =========== TEST ===========
        case HELLO_HOST:
            LocalRecipient.get().sendMessage(msg.type, msg.arg);
            break;
        case  GET_CURRENT_PLAYER_TILES:
            GameManager.get().GetCurrentPlayerTiles(recipient);
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
