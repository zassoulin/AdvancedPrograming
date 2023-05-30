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

    // The constructor of `ObjectInputStream` gets stuck when both client and server try to create it first.
    // One of them needs to create `ObjectOutputStream` first (it writes a header or something for the other one to read).
    // We'll arbitrarily choose the client to create `ObjectOutputStream` first.
    public SocketCommunicator(Socket socket, MessageHandler messageHandler, boolean isClient) {
        this.socket = socket;
        this.messageHandler = messageHandler;
        try {
            var inputStream = socket.getInputStream();
            var outputStream = socket.getOutputStream();
            if (isClient) {
                this.out = new ObjectOutputStream(outputStream);
                this.in = new ObjectInputStream(inputStream);
            } else {
                this.in = new ObjectInputStream(inputStream);
                this.out = new ObjectOutputStream(outputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listen(){
        while (!stop){
            try {
                Message msg = (Message) in.readObject();
                messageHandler.handleMessage(msg);
            } catch (IOException e) {
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
            listenThread.join(100);
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
            out.reset();
            out.writeObject(msg);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Allow inheriting class to do something after the communication has started
    protected abstract void started();
}
