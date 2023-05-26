package ap.scrabble.gameclient.model.host;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import ap.scrabble.gameclient.model.communicator.DictionaryServerCommunicator;

public class SocketDictionaryServerCommunicator implements DictionaryServerCommunicator{

    String host;
    Integer port;
    public SocketDictionaryServerCommunicator(String host, Integer port) {
        this.port = port;
        this.host = host;
        if(port == null | port == 0){
            throw new IllegalArgumentException("Dictionary server Port cant be null or 0!");
        } else if (host == null) {
            throw new IllegalArgumentException("Dictionary server host cant be null!");
        }
    }


    @Override
    public String runClientQueryRequest(String word,String ... books) {
        String query = "Q" + formatBooksAndWord(word,books);
        return SendRequest(query);
    }

    @Override
    public String runClientChallengeRequest(String word,String ... books) {
        String query = "C" + formatBooksAndWord(word,books);
        return SendRequest(query);
    }

    private String SendRequest(String query) {
        String res;
        try {
            Socket serverSocket = new Socket(host,port);
            PrintWriter out = new PrintWriter(serverSocket.getOutputStream());
            Scanner in=new Scanner(serverSocket.getInputStream());
            out.println(query);
            out.flush();
            res=in.next();
            in.close();
            out.close();
            serverSocket.close();
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
        return msg+","+word;
    }
}
