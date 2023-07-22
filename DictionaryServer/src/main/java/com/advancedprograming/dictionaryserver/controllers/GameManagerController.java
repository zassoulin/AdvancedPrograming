package com.advancedprograming.dictionaryserver.controllers;

import com.advancedprograming.dictionaryserver.repository.GameSave;
import com.advancedprograming.dictionaryserver.repository.GameSaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "GameManager")
public class GameManagerController {//Request can be tested with intelij but using postman is more recommended
    final
    GameSaveRepository gameSaveRepository;

    public GameManagerController(GameSaveRepository gameSaveRepository) {
        this.gameSaveRepository = gameSaveRepository;
    }
    @GetMapping("/getSavesByHostName")
    List<GameSave> getSaveByHostName(@RequestParam String hostName){ // GET http://localhost:8080/GameManager/getSavesByHostName?hostName=NewHostName
        List<GameSave> gameSaves = gameSaveRepository.findByHostName(hostName);
        return gameSaves;
    }
    @GetMapping("/getSaveById")
    GameSave getSaveById(@RequestParam String gameID){ //Example request is GET http://localhost:8080/GameManager/getSaveById?gameID=GameSaveID
        GameSave gameSave = gameSaveRepository.findByGameId(gameID); // if you want to return a different obj conver it here
        return gameSave;
    }
    ////Example request:http://localhost:8080/GameManager/SaveGame In the body(json)
    //{
    //    "gameId": "GameSaveID",
    //    "hostName": "TEstHostName"
    //}
    @PutMapping("/SaveGame")
    void SaveGame(@RequestBody GameSave gameSave){
        gameSaveRepository.save(gameSave);
    }
    @PutMapping("/TestSave")
    String TestSaveGame(){
        System.out.println("request Received");
        String res = "";
        GameSave gameSave = new GameSave("GameSaveID","mySave");
        gameSaveRepository.save(gameSave);
        List<GameSave> GaveSaves =  gameSaveRepository.findByHostName("mySave");
        res += "found gave save by name " + GaveSaves.get(0).toString() + "\n updating HostName \n";
        gameSave = new GameSave("GameSaveID","NewHostName");
        gameSaveRepository.save(gameSave);
        GameSave gameSave1 = gameSaveRepository.findByGameId("GameSaveID");
        res += "found gave save by name " + gameSave1.toString() + "\n";
        return res;

    }

}
