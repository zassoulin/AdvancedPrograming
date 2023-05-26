package ap.scrabble.gameclient.model;

import ap.scrabble.gameclient.model.board.Tile;
import ap.scrabble.gameclient.model.board.Word;
import ap.scrabble.gameclient.model.properties.DictionaryServerConfig;
import ap.scrabble.gameclient.model.properties.HostServerConfig;
import org.junit.Test;

public class ModelTest {
    @Test
    public void TestMain(){
        MyModel model = new MyModel(new DictionaryServerConfig("dictionary_server.ini"),new HostServerConfig("host_server.ini"));
        model.CreateGame("P1");
        model.addLocalPlayer("P2");
        model.StartGame();
        Tile.Bag bag = new Tile.Bag();
        Tile [] tiles = new Tile[3];
        tiles[0] = bag.getTile('W');
        tiles[1] = bag.getTile('A');
        tiles[2] = bag.getTile('S');
        Word word = new Word(tiles , 7, 7,true);
        model.addWord(word);

    }
}
