package com.advancedprograming.gameclient.model;

public class SocketHostServer implements  HostServer{
    int port;
    int maxNumOfThreads;
    boolean stop;
    Thread serverThread;
     // to seperate a generic client and server, we give the responsibility of handeling the client to the clientHandler thus seperating the server and client and making it more modular
    @Override
    public void start() {

    }

    @Override
    public void close() {

    }
}
