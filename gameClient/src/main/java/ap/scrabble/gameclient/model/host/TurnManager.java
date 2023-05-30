package ap.scrabble.gameclient.model.host;

import ap.scrabble.gameclient.model.Player;
import ap.scrabble.gameclient.model.board.Tile;
import ap.scrabble.gameclient.model.board.Word;

import java.util.List;

public abstract class TurnManager {
    protected List<Player> playerList;

    protected Integer CurrentPlayerIndex;

    public TurnManager(List<Player> playerList){
        this.playerList = playerList;
        CurrentPlayerIndex = 0;
    }

    public Player getCurrentPlayer() { return playerList.get(CurrentPlayerIndex); }

    public abstract void StartTurn();
    public abstract boolean EndTurn();

    public abstract Integer PlayTurn(Word word);
    public Tile[] GetCurrentPlayerTiles(){
        return  playerList.get(CurrentPlayerIndex).getPlayersTiles();
    }

}
