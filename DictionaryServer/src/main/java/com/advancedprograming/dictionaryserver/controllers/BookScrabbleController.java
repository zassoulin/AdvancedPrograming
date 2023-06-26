package com.advancedprograming.dictionaryserver.controllers;

import com.advancedprograming.dictionaryserver.server.BookScrabbleRestHandler;
import com.advancedprograming.dictionaryserver.server.ClientHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

@RestController
@RequestMapping(value = "BookScrabble")
public class BookScrabbleController {
    final BookScrabbleRestHandler bookScrabbleRestHandler;

    public BookScrabbleController(BookScrabbleRestHandler bookScrabbleRestHandler) {
        this.bookScrabbleRestHandler = bookScrabbleRestHandler;
    }
    @GetMapping("/HandleRequest") //GET http://localhost:8080/BookScrabble/HandleRequest?request=Q,s1.txt,s2.txt
    public String HandleRequest(@RequestParam String request){
        return bookScrabbleRestHandler.HandleRequest(request);
    }
}
