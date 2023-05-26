package ap.scrabble.gameclient.model.client;

import ap.scrabble.gameclient.model.GameManager.Message;
import ap.scrabble.gameclient.model.GameManager.MessageType;
import ap.scrabble.gameclient.model.communicator.SynchronousCommunicator;

public interface HostServerCommunicator extends SynchronousCommunicator {
    Message sendAndReceiveMessage(MessageType type, Object arg);
}
