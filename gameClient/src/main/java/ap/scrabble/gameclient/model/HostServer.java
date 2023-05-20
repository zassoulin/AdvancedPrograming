package ap.scrabble.gameclient.model;

public interface HostServer {
    void start();
    void close();

    void SendMessageToAll(String message);
}
