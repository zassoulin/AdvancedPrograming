package ap.scrabble.gameclient.model;

import ap.scrabble.gameclient.model.board.Word;

public class LocalPlayer extends Player{

    @Override
    public Integer PlayNextTurn() {
        Integer score = 0;
        while (score == 0){
            Word word = GetPlayerWord();
            score = GameManager.getInstance().game.placePlayerTurn(word,this.PlayerName);
            //if failed create output message

        }
        return score;
    }
}
