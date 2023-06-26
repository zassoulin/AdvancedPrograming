package com.advancedprograming.dictionaryserver.other;



import com.advancedprograming.dictionaryserver.server.Tile;
import com.advancedprograming.dictionaryserver.server.Word;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {

    public Tile[] getPlayersTiles() {
//        if (playersTiles == null)
//            GetMissingTiles();
        return (Tile[]) playersTiles.toArray(new Tile[0]);
    }

    protected List<Tile> playersTiles;
    protected String PlayerName;

    protected Integer MAXIMUM_TILES_PER_PLAYER = 7;
    protected boolean isLocal;

    public Player(String playerName,boolean isLocal, List<Tile> playersTiles){
        this.PlayerName = playerName;
        this.isLocal = isLocal;
        this.playersTiles = playersTiles;
    }



}
