package ap.scrabble.gameclient.model.board;

import java.io.Serializable;
import java.util.Map;

public class GameData implements Serializable {
    Board board;
    Map<String,Integer> playersScores;

}
