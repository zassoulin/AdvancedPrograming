package ap.scrabble.gameclient.viewmodel;

import ap.scrabble.gameclient.model.Model;
import ap.scrabble.gameclient.model.board.GameData;
import ap.scrabble.gameclient.model.board.Tile;

import java.io.*;
import java.io.Serializable;
import java.util.*;

public class GameState implements Serializable {
    private static final long serialVersionUID = 1L;


    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    private Model model;
    private GameData gameData;
    private Tile[] tileList;
    private List<String> playerNames;
    private Map<String, Integer> playersScores;

    private String currentPlayer;

    private Object viewSave;
    public Object getViewSave() {
        return viewSave;
    }

    public void setViewSave(Object viewSave) {
        this.viewSave = viewSave;
    }




    public GameData getGameData() {
        return gameData;
    }

    public void setGameData(GameData gameData) {
        this.gameData = gameData;
    }

    public Tile[] getTileList() {
        return tileList;
    }

    public void setTileList(Tile[] tileList) {
        this.tileList = tileList;
    }

    public List<String> getPlayerNames() {
        return playerNames;
    }

    public void setPlayerNames(List<String> playerNames) {
        this.playerNames = playerNames;
    }

    public Map<String, Integer> getPlayersScores() {
        return playersScores;
    }

    public void setPlayersScores(Map<String, Integer> playersScores) {
        this.playersScores = playersScores;
    }


    public GameState(Model model, GameData gameData, Tile[] tileList, List<String> playerNames, Map<String, Integer> playersScores, String currentPlayer) {
        this.model = model;
//        this.viewSave  = viewSave;
        this.gameData = gameData;
        this.tileList = tileList;
        this.playerNames = playerNames;
        this.playersScores = playersScores;
        this.currentPlayer = currentPlayer;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(String currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

}