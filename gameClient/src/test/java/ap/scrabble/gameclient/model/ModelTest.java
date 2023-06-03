package ap.scrabble.gameclient.model;

import ap.scrabble.gameclient.model.board.Tile;
import ap.scrabble.gameclient.model.board.Word;
import ap.scrabble.gameclient.model.properties.DictionaryServerConfig;
import ap.scrabble.gameclient.model.properties.HostServerConfig;
import org.junit.Test;

import java.util.Scanner;

public class ModelTest {
    @Test
    public void TestMain(){
        GameManager gameManager = GameManager.get();
        MyModel model = new MyModel(new DictionaryServerConfig("ap/scrabble/gameclient/dictionary_server.ini"),new HostServerConfig("host_server.ini"));
        model.CreateGame("P1");
        model.addLocalPlayer("P2");
        int a = 1;
        Scanner sc= new Scanner(System.in);
        while (a != 0) {
            a = sc.nextInt();
            System.out.println(a);
        }
        model.StartGame();
        Tile.Bag bag = new Tile.Bag();
        Tile [] tiles = new Tile[3];
        tiles[0] = bag.getTile('W');
        tiles[1] = bag.getTile('A');
        tiles[2] = bag.getTile('S');
        Word word = new Word(tiles , 7, 7,true);
        model.addWord(word);

    }
    @Test
    public void TestHost(){
        GameManager gameManager = GameManager.get();
        MyModel model = new MyModel(new DictionaryServerConfig("ap/scrabble/gameclient/dictionary_server.ini"),new HostServerConfig("host_server.ini"));
        model.JoinGame("P3");
        Tile.Bag bag = new Tile.Bag();
        Tile [] tiles = new Tile[3];
        tiles[0] = bag.getTile('W');
        tiles[1] = bag.getTile('A');
        tiles[2] = bag.getTile('S');
        Word word = new Word(tiles , 3, 3,false);
        model.addWord(word);

    }
}
