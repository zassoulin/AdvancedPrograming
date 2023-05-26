package ap.scrabble.gameclient.model.communicator;

import ap.scrabble.gameclient.model.GameManager.Message;

public interface SynchronousCommunicator extends Communicator {
    Message writeAndReceiveMessage(Message msg);
}
