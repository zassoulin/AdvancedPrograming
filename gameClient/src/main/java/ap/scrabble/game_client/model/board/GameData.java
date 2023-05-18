package ap.scrabble.game_client.model.board;

import java.io.Serializable;
import java.util.Map;

public class GameData implements Serializable {
    Board board;
    Map<String,Integer> playersScores;

}
