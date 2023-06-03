package ap.scrabble.gameclient.model;

import ap.scrabble.gameclient.model.board.GameData;
import ap.scrabble.gameclient.model.board.Tile;
import ap.scrabble.gameclient.model.board.Word;
import ap.scrabble.gameclient.model.message.Message;
import ap.scrabble.gameclient.model.message.MessageType;
import ap.scrabble.gameclient.model.properties.DictionaryServerConfig;
import ap.scrabble.gameclient.model.properties.HostServerConfig;
import org.junit.Test;

import java.text.MessageFormat;
import java.util.Observable;
import java.util.Observer;

import static org.junit.Assert.assertTrue;

public class ClientTester2 implements Observer {
    @Test
    public void ClientEndToEndTest() throws InterruptedException {
        GameManager gameManager = GameManager.get();
        MyModel model = new MyModel(new DictionaryServerConfig("dictionary_server.ini"),new HostServerConfig("host_server.ini"));
        GameManager.get().addObserver(this);
        model.JoinGame("P3");
        Tile.Bag bag = new Tile.Bag();
        Tile [] tiles = new Tile[3];
        System.out.println("SLEEPING FOR 30 sec To wait for my turn ");
        Thread.sleep(30000);
        System.out.println("ITS P3 TURN SO REQUESTING PLAYER TILES");
        model.GetCurrentPlayerTiles();
        System.out.println("P3 placing Legal word WOW");
        tiles[0] = bag.getTile('W');
        tiles[1] = bag.getTile('O');
        tiles[2] = bag.getTile('W');
        Word word = new Word(tiles , 5, 7,true);
        model.addWord(word);
        Thread.sleep(20000);
    }

    @Override
    public void update(Observable o, Object arg) {
        assertTrue(o instanceof GameManager);
        assertTrue(arg instanceof Message);
        Message message = (Message) arg;
        System.out.println(MessageFormat.format("View Received message of type {0} with Value: {1}",message.type,message.arg));
        if(message.type == MessageType.UPDATE_GAME_DATA){
            GameData gameData = (GameData) message.arg;
            gameData.getBoard().print();
            System.out.println( gameData.getPlayersScores());
        }
        else if(message.type == MessageType.PLAYER_TILES){
            Tile[] tileList = (Tile[]) message.arg;
            System.out.println(tileList);
        }
    }
}
