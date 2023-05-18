package com.advancedprograming.gameclient.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SocketDictionaryServerCommunicator implements DictionaryServerCommunicator{

    String host;
    Integer port;
    Socket serverSocket;
    public SocketDictionaryServerCommunicator(String host, Integer port) {
        this.port = port;
        this.host = host;
        try {
            serverSocket = new Socket(host,port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String runClientQueryRequest(String word,String ... books) {
        String res;
        String query = "Q," + formatBooksAndWord(word,books);
        return SendRequest(query);
    }

    @Override
    public String runClientChallengeRequest(String word,String ... books) {
        String res;
        String query = "C," + formatBooksAndWord(word,books);
        return SendRequest(query);
    }

    private String SendRequest(String query) {
        String res;
        try {
            PrintWriter out = new PrintWriter(serverSocket.getOutputStream());
            Scanner in=new Scanner(serverSocket.getInputStream());
            out.println(query);
            out.flush();
            res=in.next();
            in.close();
            out.close();
        } catch (IOException e) {
            System.err.println("Error connecting to DictionaryServer!" + e.getMessage());
            throw new RuntimeException(e);
        }
        return res;
    }

    private String formatBooksAndWord(String word , String... books){
        String msg = "";
        for(String book : books ){
            msg = msg +"," + book;
        }
        return msg+word;
    }
}
