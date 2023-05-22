package ap.scrabble.gameclient.model;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

public interface ClientHandler {
    void handleClient(InputStream inFromclient, OutputStream outToClient);
    void close();

    void WriteMessage(Serializable message);
}
