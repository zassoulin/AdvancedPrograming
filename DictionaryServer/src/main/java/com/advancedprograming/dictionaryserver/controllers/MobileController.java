package com.advancedprograming.dictionaryserver.controllers;

import com.advancedprograming.dictionaryserver.repository.GameSave;
import com.advancedprograming.dictionaryserver.repository.GameSaveRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
public class MobileController {
    final
    GameSaveRepository gameSaveRepository;

    public MobileController(GameSaveRepository gameSaveRepository) {
        this.gameSaveRepository = gameSaveRepository;
    }

    @GetMapping("/score-table")//Example request GET http://localhost:8080/score-table?gameID=1
    Map GetScoreTable(@RequestParam String gameID){
        GameSave gameSave = gameSaveRepository.findByGameId(gameID);
        //TODO: extract table
        //And convert to type
//        {
//            "players": ["Avi", "Shimon", "Gaydamat"],
//            "scores": [42, 69, 4206969]
//        }
        Map res = new HashMap();
        List<String> players = new ArrayList();
        List<Integer> scores = new ArrayList();
        res.put("players",players);
        res.put("scores",scores);
        return res;
    }
}
