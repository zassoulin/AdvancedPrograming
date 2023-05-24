package ap.scrabble.gameclient.model;

import ap.scrabble.gameclient.model.recipient.AllRecipient;

public class HostHandler {


    private static HostHandler HostHandlerInstance;
    public static HostHandler get() {
        if (HostHandlerInstance == null) {
            HostHandlerInstance = new HostHandler();
        }
        return HostHandlerInstance;
    }
    private HostHandler(){}
    void HandleMessage(GameManager.Message message){
        //TODO: implement
    }
}
