package com.advancedprograming.dictionaryserver.repository;

import com.advancedprograming.dictionaryserver.other.Player;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
//@Data
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class GameSave {//Not testing DAL as it requires mocking and seems a bit excessive in this use case
        //TODO: add fields and data as you want just make sure to update the constructor and not touch existing variables
    public GameSave(String gameId,String hostName) {
        this.gameId = gameId;
        this.hostName = hostName;
    }

    @Id
    private String gameId;
    private String hostName;

}