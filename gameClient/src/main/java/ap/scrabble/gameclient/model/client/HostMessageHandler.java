package ap.scrabble.gameclient.model.client;

import static ap.scrabble.gameclient.util.Assert.assertCond;
import static java.lang.System.currentTimeMillis;

import ap.scrabble.gameclient.model.message.Message;
import ap.scrabble.gameclient.model.message.MessageHandler;

public class HostMessageHandler implements MessageHandler{
    private Object synchObject = new Object();
    private boolean responseReady = false;
    // `handleMessage` returns the response for `waitForResponse` here.
    // Read the documentation of `waitForResponse` and `notifyResponse` for more information.
    private Message response;

    public static MessageHandler create() {
        return new HostMessageHandler();
    }

    @Override
    public void handleMessage(Message msg) {
        // TODO: Implement
        // MY_TURN, PLAYER_ADDED, etc.

        switch (msg.type) {
            // =========== HOST ===========
        case GAME_STARTED:
            // TODO: Implement
            break;
            // ============================

            // =========== TEST ===========
        case RESPONSE_HOST:
            notifyResponse(msg);
        case THIS_IS_HOST:
            notifyResponse(msg);
            break;
            // ============================
        default:
            assertCond(false, "HostMessageHandler: Unhandled message type");
            break;
        }
    }

    // Call from the main thread - blocks until a response is read in the listen thread
    public Message waitForResponse() {
        synchronized (synchObject) {
            long waitTime = 0;
            long startTime = currentTimeMillis();
            // Wait for a response no more than 5 seconds
            while (!responseReady && waitTime < 5_000) {
                try {
                    synchObject.wait(5_000 - waitTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                };
                waitTime = currentTimeMillis() - startTime;
            }
            return response;
        }
    }

    // Called by `handleMessage` from the listen thread to notify waiting threads that a response is ready.
    // Call only for response type messages!
    // For example, `PLAYER_ALREADY_EXISTS` is a response message, i.e. syncrhonous message (in response to a remote client requesting to join).
    // But `MY_TURN` is an asyncrhonous message (the client's `GameManager` should be ready to receive asynchronous events from `handleMessage`).
    private void notifyResponse(Message msg) {
        synchronized (synchObject) {
            response = msg;
            responseReady = true;
            synchObject.notifyAll();
        }
    }
}
