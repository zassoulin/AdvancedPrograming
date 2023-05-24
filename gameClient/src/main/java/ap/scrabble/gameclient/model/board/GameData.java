package ap.scrabble.gameclient.model.board;

import ap.scrabble.gameclient.model.Player;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Domain Obj class
public class GameData implements Serializable {

    Board board;
    Map<String,Integer> playersScores;

    public GameData(List<Player> playerList) {
        board = new Board();
        playersScores = new HashMap<>();
        for (Player player : playerList){
            playersScores.put(player.getPlayerName(),0);
        }
    }

    public Board getBoard() {
        return board;
    }

    public Map<String, Integer> getPlayersScores() {
        return playersScores;
    }

    public void addScoreToPlayer(String playerName,Integer score) {
        if (playersScores.get(playerName) != null) {
            playersScores.put(playerName, playersScores.get(playerName) + score);
        } else {
            playersScores.put(playerName, score);
        }
    }



}
