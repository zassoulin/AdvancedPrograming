package ap.scrabble.gameclient.model.communicator;

import ap.scrabble.gameclient.model.message.Message;

public interface SynchronousCommunicator extends Communicator {
    Message writeAndReceiveMessage(Message msg);
}
