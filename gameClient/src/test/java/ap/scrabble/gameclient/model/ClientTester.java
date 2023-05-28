package ap.scrabble.gameclient.model;

import ap.scrabble.gameclient.model.board.Tile;
import ap.scrabble.gameclient.model.board.Word;
import ap.scrabble.gameclient.model.message.Message;
import ap.scrabble.gameclient.model.properties.DictionaryServerConfig;
import ap.scrabble.gameclient.model.properties.HostServerConfig;
import org.junit.Test;

import java.text.MessageFormat;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import static org.junit.Assert.assertTrue;

public class ClientTester implements Observer {
    @Test
    public void ClientEndToEndTest() throws InterruptedException {
        GameManager gameManager = GameManager.get();
        MyModel model = new MyModel(new DictionaryServerConfig("dictionary_server.ini"),new HostServerConfig("host_server.ini"));
        GameManager.get().addObserver(this);
        model.JoinGame("P3");
        Tile.Bag bag = new Tile.Bag();
        Tile [] tiles = new Tile[3];
        System.out.println("SLEEPING FOR 1 MIN To wait for my turn ");
        Thread.sleep(60000);
        tiles[0] = bag.getTile('Y');
        tiles[1] = bag.getTile('E');
        tiles[2] = bag.getTile('S');
        Word word = new Word(tiles , 3, 3,false);
        model.addWord(word);
    }

    @Override
    public void update(Observable o, Object arg) {
        assertTrue(o instanceof GameManager);
        assertTrue(arg instanceof Message);
        Message message = (Message) arg;
        System.out.println(MessageFormat.format("View Received message of type {0} with Value: {1}",message.type,message.arg));
    }
}
