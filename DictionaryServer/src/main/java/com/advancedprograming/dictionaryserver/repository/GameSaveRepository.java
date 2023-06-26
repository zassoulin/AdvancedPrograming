package com.advancedprograming.dictionaryserver.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface GameSaveRepository extends MongoRepository<GameSave,String> { //Not testing DAL as it requires mocking and seems a bit excessive in this use case
    List<GameSave> findByHostName(String hostName);
    GameSave findByGameId(String gameId);

}
