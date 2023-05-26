package ap.scrabble.gameclient.model.host;

import static ap.scrabble.gameclient.util.Assert.assertCond;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import ap.scrabble.gameclient.model.communicator.Communicator;
import ap.scrabble.gameclient.model.communicator.SocketCommunicatorFactory;
import ap.scrabble.gameclient.model.message.MessageHandlerFactory;

public class SocketHostServer implements HostServer{
    private int port;
    private int maxNumOfThreads;
    private boolean stop;
    private Thread serverThread;

    private SocketCommunicatorFactory communicatorFactory;
    private MessageHandlerFactory clientMsgHndFactory;
    private List<Communicator> clients;

    public SocketHostServer(int port, int maxNumOfThreads, SocketCommunicatorFactory communicatorFactory, MessageHandlerFactory clientMsgHndFactory) {
        this.port=port;
        this.maxNumOfThreads = maxNumOfThreads;
        this.communicatorFactory = communicatorFactory;
        this.clientMsgHndFactory = clientMsgHndFactory;
        clients = new ArrayList<>();
    }

    // to seperate a generic client and server, we give the responsibility of handeling the client to the clientHandler thus seperating the server and client and making it more modular
    @Override
    public void start() {
        System.out.println("Host Server started");
        stop=false;
        serverThread = new Thread(()->run());
        serverThread.start();
    }

    private void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(500);
            while (!stop) {
                assertCond(clients.size() <= maxNumOfThreads, "SocketHostServer: Too many clients (max number of threads allowed is %d)".formatted(maxNumOfThreads));
                try {
                    Socket clientSocket = serverSocket.accept();
                    Communicator clientComm = communicatorFactory.create(clientSocket, clientMsgHndFactory.create());
                    clients.add(clientComm);
                    clientComm.start();
                } catch (SocketTimeoutException e) {}
            }
            for (var client : clients) {
                client.close();
            }
            serverSocket.close();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        System.out.println("Server shutting down...");
        stop = true;
        try {
            serverThread.join(6_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
