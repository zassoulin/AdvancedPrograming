package ap.scrabble.gameclient.model.client;

import ap.scrabble.gameclient.model.communicator.SynchronousCommunicator;
import ap.scrabble.gameclient.model.message.Message;
import ap.scrabble.gameclient.model.message.MessageType;

public interface HostServerCommunicator extends SynchronousCommunicator {
    Message sendAndReceiveMessage(MessageType type, Object arg);
}
