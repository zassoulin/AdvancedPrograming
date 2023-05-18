package com.advancedprograming.gameclient.model;

import com.advancedprograming.gameclient.model.board.Tile;

import java.util.List;
import java.util.Map;

public abstract class Player {
    List<Tile> playersTiles;
    String PlayerName;

    boolean isLocal;
    public void addTilesToPlayer(List<Tile> tilesToAdd){
        playersTiles.addAll(tilesToAdd);
    }
    public int getPlayerTilesCount(){
        return  playersTiles.size();
    }

    public String getPlayerName(){
        return PlayerName;
    }


}
