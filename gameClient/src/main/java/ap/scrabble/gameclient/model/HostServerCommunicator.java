package ap.scrabble.gameclient.model;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HostServerCommunicator {
    Integer port;
    String host;

    Socket serverSocket;

    InputStream inputStream;
    OutputStream outputStream;

    ExecutorService executor;

    boolean stop;
    private static HostServerCommunicator HostServerCommunicatorInstance;
    public static HostServerCommunicator get() {
        if (HostServerCommunicatorInstance == null) {
            HostServerCommunicatorInstance = new HostServerCommunicator(GameManager.get().getHostServerConfig().getIP(),GameManager.get().getHostServerConfig().getPort());
        }
        return HostServerCommunicatorInstance;
    }
    private HostServerCommunicator(String host , Integer port){
        this.port = port;
        this.host = host;
        try {
            serverSocket = new Socket(host,port);
            inputStream = serverSocket.getInputStream();
            outputStream = serverSocket.getOutputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(port == null | port == 0){
            throw new IllegalArgumentException("Dictionary server Port cant be null or 0!");
        } else if (host == null) {
            throw new IllegalArgumentException("Dictionary server host cant be null!");
        }
    }
    private void Listen(){
        while (!stop){
            try {
                if(inputStream.available() == 0){
                    Thread.sleep(100);
                }else {//TODO : read message
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void StartListen(){
        executor = Executors.newSingleThreadExecutor();
        stop = false;
        executor.execute(this::Listen);
    }

    public GameManager.Message sendMessage(GameManager.MessageType type, Object arg) {
        // TODO: Send message (should be serializable) to remote client through `HostHandler`
        return new GameManager.Message(null,null);
    }
    public void close(){
        stop = true;
    }
}
