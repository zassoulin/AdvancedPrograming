package ap.scrabble.gameclient.model;

import ap.scrabble.gameclient.model.recipient.AllRecipient;
import ap.scrabble.gameclient.model.recipient.RemoteRecipient;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketHostServer implements  HostServer{
    int port;
    int maxNumOfThreads;
    boolean stop;
    Thread serverThread;
    final ClientHandler ch; // to seperate a generic client and server, we give the responsibility of handeling the client to the clientHandler thus seperating the server and client and making it more modular

    List<Socket> clientList;

    public SocketHostServer(int port, ClientHandler ch, int maxNumOfThreads) {
        this.port=port;
        this.ch=ch;
        this.maxNumOfThreads = maxNumOfThreads;
        clientList = new ArrayList<>();
    }


    // to seperate a generic client and server, we give the responsibility of handeling the client to the clientHandler thus seperating the server and client and making it more modular
    @Override
    public void start() {
        System.out.println("Host Server started");
        stop=false;
        serverThread = new Thread(()->startsServer());
        serverThread.start();
    }
    private void startsServer() {
        ExecutorService executor = Executors.newFixedThreadPool(maxNumOfThreads);
        try {
            ServerSocket server = new ServerSocket(port);
            server.setSoTimeout(1000);
            while (!stop) {
                try {
                    Socket client = server.accept();
                    clientList.add(client);
                    executor.execute(() ->run(client));
                } catch (SocketTimeoutException e) {}
            }
            server.close();
            executor.shutdown();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void run(Socket client) {
        try {
            synchronized (ch) {
                AllRecipient.get().addRemoteRecipient(new RemoteRecipient(ch));
                ch.handleClient(client.getInputStream(), client.getOutputStream());
                ch.close();
                clientList.remove(client);//when closing remove client from list
                client.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        System.out.println("Server shutting down...");
        stop = true;
        try {
            serverThread.join(5_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void SendMessageToAll(String message) {

    }
}
