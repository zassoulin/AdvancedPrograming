package ap.scrabble.gameclient.model;

import ap.scrabble.gameclient.model.board.GameData;
import ap.scrabble.gameclient.model.board.Tile;
import ap.scrabble.gameclient.model.board.Word;
import ap.scrabble.gameclient.model.message.Message;
import ap.scrabble.gameclient.util.MessageType;
import ap.scrabble.gameclient.model.properties.DictionaryServerConfig;
import ap.scrabble.gameclient.model.properties.HostServerConfig;
import org.junit.Test;

import java.text.MessageFormat;
import java.util.Observable;
import java.util.Observer;

import static org.junit.Assert.assertTrue;

public class HostTester2 implements Observer {
    @Test
    public void HostEndToEndTest() throws InterruptedException {
        GameManager gameManager = GameManager.get();
        MyModel model = new MyModel(new DictionaryServerConfig("ap/scrabble/gameclient/dictionary_server.ini"),new HostServerConfig("host_server.ini"));
        GameManager.get().addObserver(this);
        System.out.println("creating Game with P1");
        model.CreateGame("P1");//P1 is Host create game is setting the host!
        System.out.println("Adding Player P2");
        model.addLocalPlayer("P2");
        System.out.println("SLEEPING FOR 40 sec and waiting for players to join");
        Thread.sleep(40000);//Now we need to start ClientTester
        System.out.println("Starting Game");
        model.StartGame();//Host is starting
        System.out.println("ITS P1 TURN SO REQUESTING PLAYER TILES");
        model.GetCurrentPlayerTiles();//When YOURTURN IS TRUE
        System.out.println("P1 placing word WAS");
        Tile.Bag bag = new Tile.Bag();
        Tile [] tiles = new Tile[3];
        tiles[0] = bag.getTile('W');
        tiles[1] = bag.getTile('A');
        tiles[2] = bag.getTile('S');
        Word word = new Word(tiles , 7, 7,true);
        model.addWord(word);
        System.out.println("ITS P2 TURN SO REQUESTING PLAYER TILES");
        model.GetCurrentPlayerTiles();//When in it my Turn Call GetCurrentPlayerTiles;
        System.out.println("P2 placing Illegal word ZIV");
        tiles[0] = bag.getTile('Z');
        tiles[1] = bag.getTile('I');
        tiles[2] = bag.getTile('V');
        word = new Word(tiles , 10, 10,true);
        model.addWord(word);
        System.out.println("P2 placing word WHO");
        tiles[0] = bag.getTile('W');
        tiles[1] = bag.getTile('H');
        tiles[2] = bag.getTile('O');
        word = new Word(tiles , 7, 7,false);
        model.addWord(word);
        System.out.println("SLEEPING FOR 2 MIN before closing");
        Thread.sleep(120000);
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
