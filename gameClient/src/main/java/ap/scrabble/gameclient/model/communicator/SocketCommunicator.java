package ap.scrabble.gameclient.model.communicator;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import ap.scrabble.gameclient.model.message.Message;
import ap.scrabble.gameclient.model.message.MessageHandler;

public abstract class SocketCommunicator implements Communicator {
    protected Socket socket;

    protected ObjectInputStream in;
    protected ObjectOutputStream out;

    protected MessageHandler messageHandler;
    protected Thread listenThread;
    protected boolean stop;

    public SocketCommunicator(Socket socket, MessageHandler messageHandler) {
        this.socket = socket;
        this.messageHandler = messageHandler;
        try {
            this.in = new ObjectInputStream(socket.getInputStream());
            this.out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void listen(){
        while (!stop){
            try {
                if(in.available() == 0){
                    Thread.sleep(100);
                }else {
                    Message msg = (Message) in.readObject();
                    messageHandler.handleMessage(msg);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void start() {
        stop = false;
        listenThread = new Thread(this::listen);
        listenThread.start();
        started();
    }

    @Override
    public void close() {
        stop = true;
        try {
            listenThread.join(1_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeMessage(Message msg) {
        try {
            out.writeObject(msg);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Allow inheriting class to do something after the communication has started
    protected abstract void started();
}
