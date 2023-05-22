package ap.scrabble.gameclient.model;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

public class RemoteClientHandler implements ClientHandler{


    @Override
    public void handleClient(InputStream inFromclient, OutputStream outToClient) {

    }

    @Override
    public void close() {

    }

    @Override
    public void WriteMessage(Serializable message) {

    }
}
